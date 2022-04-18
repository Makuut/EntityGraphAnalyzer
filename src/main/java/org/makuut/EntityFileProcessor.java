package org.makuut;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.makuut.StringUtils.getClassName;
import static org.makuut.StringUtils.getValue;

/**
 * Обработчик файлов сущностей
 */
public class EntityFileProcessor {

    private static final String DOT = ".";
    private static final List<String> collections = List.of("Collection", "List", "ArrayList", "LinkedList", "Set", "SortedSet", "TreeSet", "HashSet", "LinkedHashSet");

    /**
     * Формирует карту сущностей и их полей
     *
     * @param entities       Список файлов сущностей
     * @param ENTITY_POSTFIX Окончание класса сущности
     * @return Карта сущностей и их полей
     */
    public static HashMap<String, HashMap<String, String>> getEntitiesAndTheirField(List<File> entities, final String ENTITY_POSTFIX) throws IOException {
        HashMap<String, HashMap<String, String>> entityAndFields = new HashMap<>();
        HashMap<String, String> classAndSuperclass = new HashMap<>();
        for (File file : entities) {
            JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
            JavaSource src = projectBuilder.addSource(file);
            JavaClass entityClass = src.getClasses().get(0);
            String entityClassName = entityClass.getName();
            JavaClass superJavaClass = entityClass.getSuperJavaClass();

            if (superJavaClass != null && !Objects.equals(superJavaClass.getName(), "Object")) {
                classAndSuperclass.put(entityClassName, getClassName(superJavaClass.getName()));
            }

            HashMap<String, String> nameAndType = new HashMap<>();
            List<JavaField> fields = entityClass.getFields();

            for (JavaField field : fields) {
                JavaClass type = field.getType();
                String typeName = type.getName();
                if (collections.contains(typeName)) {
                    typeName = getValue(type.getGenericValue());
                }
                if (!typeName.matches(ENTITY_POSTFIX)) {
                    continue;
                }
                nameAndType.put(field.getName(), getClassName(typeName));
            }
            entityAndFields.put(entityClassName, nameAndType);
        }

        if (classAndSuperclass.isEmpty()) {
            return entityAndFields;
        }
        for (Map.Entry<String, String> entry : classAndSuperclass.entrySet()){
            String clazz = entry.getKey();
            String superClass = entry.getValue();
            HashMap<String, String> classFields = entityAndFields.get(clazz);
            HashMap<String, String> superClassFields = entityAndFields.get(superClass);
            if (superClassFields == null || superClassFields.isEmpty()) {
                continue;
            }
            classFields.putAll(superClassFields);
        }
            return entityAndFields;
    }
}
