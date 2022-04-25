package org.makuut;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.makuut.StringUtils.compareTypeNames;

/**
 * Обработчик файлов
 */
public class FileProcessor {

    private static final String JAVA_FILE_EXTENSION_PATTERN = ".*\\.java";
    private static final String ENTITY_GRAPH_ANNOTATION_NAME = "EntityGraph";
    private static final String NAMED_ENTITY_GRAPH_ANNOTATION_NAME = "NamedEntityGraph ";
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

        List<JavaClass> superClasses = new ArrayList<>();
        List<JavaClass> other = new ArrayList<>();
        FileUtils.search(JAVA_FILE_EXTENSION_PATTERN, sourceRoot, result);

        for (File file : result) {
            JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
            JavaSource src = projectBuilder.addSource(file);
            List<JavaClass> classes = src.getClasses();
            boolean isEntity = false;
            boolean isGraphInClass = false;
            for (JavaClass javaClass : classes) {
                List<JavaAnnotation> classAnnotations = javaClass.getAnnotations();
                for (JavaAnnotation classAnnotation : classAnnotations) {
                    JavaClass type = classAnnotation.getType();
                    if (!isEntity) {
                        isEntity = compareTypeNames(ENTITY_ANNOTATION_NAME, type.getName());
                    }
                    if (!isGraphInClass) {
                        isGraphInClass = compareTypeNames(NAMED_ENTITY_GRAPH_ANNOTATION_NAME, type.getName());
                    }
                }
                if (isEntity && isGraphInClass) {
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
                boolean isGraphInMethod = false;
                for (JavaMethod method : methods) {
                    List<JavaAnnotation> methodAnnotations = method.getAnnotations();
                    for (JavaAnnotation methodAnnotation : methodAnnotations) {
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

    private static void superClassAnalyser(List<JavaClass> superClasses, List<JavaClass> other, List<JavaClass> onlyEntities) {
        if (superClasses.isEmpty()) {
            return;
        }
        List<JavaClass> classForRemove = new ArrayList<>();
        for (JavaClass javaClass : superClasses) {
            String superClassName = javaClass.getName();
            boolean isPresent = onlyEntities.stream()
                    .anyMatch(entity -> compareTypeNames(entity.getName(), superClassName));
            if (isPresent) {
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
            JavaClass superJavaClass = javaClass.getSuperJavaClass();
            if (superJavaClass != null && !StringUtils.compareTypeNames(superJavaClass.getName(), OBJECT_CLASS_NAME)) {
                superClasses.add(superJavaClass);
            }
            classForRemove.add(javaClass);
        }
        superClasses.removeAll(classForRemove);
        superClassAnalyser(superClasses, other, onlyEntities);
    }
}

