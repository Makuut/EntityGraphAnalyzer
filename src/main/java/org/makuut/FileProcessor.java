package org.makuut;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.makuut.StringUtils.compareTypeNames;

/**
 * Обработчик файлов
 */
public class FileProcessor {

    private static final String JAVA_FILE_EXTENSION_PATTERN = ".*\\.java";
    private static final String ENTITY_GRAPH_ANNOTATION_NAME = "EntityGraph";
    private static final String NAMED_ENTITY_GRAPH_ANNOTATION_NAME = "NamedEntityGraph";
    private static final String NAMED_ENTITY_GRAPHS_ANNOTATION_NAME = "NamedEntityGraphs";
    private static final String ENTITY_ANNOTATION_NAME = "Entity";
    private static final String OBJECT_CLASS_NAME = "Object";
    private static List<File> result = new ArrayList<>();

    /**
     * Распределяет все файлы на относящиеся к сущносям (имеют @Entity),
     * графам в репозиториях (класс не имеет @Entity, методы имеют @EntityGraph),
     * сущности с графом (имеет @Entity, @EntityGraph).
     * Классы которые наследуют сущности, тоже будут попадать в спсок сущностей
     *
     * @param sourceRoot Корневая директория
     */
    public static void fileAnalyze(File sourceRoot, List<JavaClass> onlyEntities,
                                   List<JavaClass> graphsInRepo, List<JavaClass> entitiesAndGraphs) throws IOException {

        Set<JavaClass> superClasses = new HashSet<>();
        Set<JavaClass> other = new HashSet<>();
        FileUtils.search(JAVA_FILE_EXTENSION_PATTERN, sourceRoot, result);

        for (File file : result) {
            List<JavaClass> classes = getJavaClass(file);
            boolean isEntity = false;
            boolean isGraphInEntity = false;
            boolean isGraphInMethod = false;
            for (JavaClass javaClass : classes) {
                if (isGraphInMethod) {
                    break;
                }
                List<JavaAnnotation> classAnnotations = javaClass.getAnnotations();
                for (JavaAnnotation classAnnotation : classAnnotations) {
                    JavaClass type = classAnnotation.getType();
                    if (!isEntity) {
                        isEntity = compareTypeNames(ENTITY_ANNOTATION_NAME, type.getName());
                    }
                    if (!isGraphInEntity) {
                        isGraphInEntity = compareTypeNames(NAMED_ENTITY_GRAPH_ANNOTATION_NAME, type.getName()) || compareTypeNames(NAMED_ENTITY_GRAPHS_ANNOTATION_NAME, type.getName());
                    }
                }
                if (isEntity && isGraphInEntity) {
                    entitiesAndGraphs.add(javaClass);
                }
                if (isEntity) {
                    JavaClass superJavaClass = javaClass.getSuperJavaClass();
                    if (superJavaClass != null && !StringUtils.compareTypeNames(superJavaClass.getName(), OBJECT_CLASS_NAME)) {
                        superClasses.add(superJavaClass);
                    }
                    onlyEntities.add(javaClass);
                    continue;
                }
                List<JavaMethod> methods = javaClass.getMethods();
                for (JavaMethod method : methods) {
                    if (isGraphInMethod) {
                        break;
                    }
                    List<JavaAnnotation> methodAnnotations = method.getAnnotations();
                    for (JavaAnnotation methodAnnotation : methodAnnotations) {
                        if (isGraphInMethod) {
                            break;
                        }
                        JavaClass type = methodAnnotation.getType();
                        isGraphInMethod = compareTypeNames(type.getName(), ENTITY_GRAPH_ANNOTATION_NAME);
                    }
                }
                if (isGraphInMethod) {
                    graphsInRepo.add(javaClass);
                    continue;
                }
                other.add(javaClass);
            }
        }

        superClassAnalyser(superClasses, other, onlyEntities);
    }

    private static List<JavaClass> getJavaClass(File file) throws IOException {
        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        JavaSource src = projectBuilder.addSource(file);
        return src.getClasses();
    }

    private static void superClassAnalyser(Set<JavaClass> superClasses, Set<JavaClass> other, List<JavaClass> onlyEntities) {
        if (superClasses.isEmpty()) {
            return;
        }
        Set<JavaClass> classForRemove = new HashSet<>();
        Set<JavaClass> classForAdd = new HashSet<>();
        for (JavaClass javaClass : superClasses) {
            String superClassName = javaClass.getName();
            if (needToSkip(classForAdd, onlyEntities, superClassName)) {
                classForRemove.add(javaClass);
                continue;
            }
            JavaClass entityForSave = other.stream()
                    .filter(notEntity -> compareTypeNames(notEntity.getName(), superClassName))
                    .findFirst()
                    .orElse(null);
            if (entityForSave == null) {
                continue;
            }
            onlyEntities.add(entityForSave);
            JavaClass superJavaClass = entityForSave.getSuperJavaClass();
            if (superJavaClass != null && !StringUtils.compareTypeNames(superJavaClass.getName(), OBJECT_CLASS_NAME)) {
                classForAdd.add(superJavaClass);
            }
            classForRemove.add(javaClass);
        }
        superClasses.removeAll(classForRemove);
        superClasses.addAll(classForAdd);
        superClassAnalyser(superClasses, other, onlyEntities);
    }

    /**
     * Вспомогательный метод проверяет наличие суперкласса в общем списке сущностей
     * и в списке для добавления
     * @param classForAdd Список классов для добавления
     * @param onlyEntities Список классов-сущностей
     * @param superClassName Название суперкласса
     */
    private static boolean needToSkip(Set<JavaClass> classForAdd, List<JavaClass> onlyEntities, String superClassName) {
        boolean isPresentForAdd = classForAdd.stream().
                anyMatch(entity -> compareTypeNames(entity.getName(), superClassName));
        boolean isPresentInEntity = onlyEntities.stream()
                .anyMatch(entity -> compareTypeNames(entity.getName(), superClassName));
        return isPresentForAdd || isPresentInEntity;
    }
}

