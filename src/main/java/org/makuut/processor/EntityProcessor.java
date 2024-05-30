package org.makuut.processor;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import org.makuut.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.makuut.util.StringUtils.getTypeName;
import static org.makuut.util.StringUtils.getValue;

/**
 * Обработчик файлов сущностей
 */
public class EntityProcessor {

    private EntityProcessor() {
    }

    private static final String OBJECT_CLASS_NAME = "Object";

    private static final List<String> collections = List.of("Collection", "List", "ArrayList", "LinkedList", "Set", "SortedSet", "TreeSet", "HashSet", "LinkedHashSet");

    /**
     * Формирует карту сущностей и их полей, на основе всего списка сущностей
     *
     * @param entities Список сущностей
     * @return Карта сущностей и их полей
     */
    public static HashMap<String, HashMap<String, String>> getEntitiesAndTheirField(List<JavaClass> entities) throws IOException {
        HashMap<String, HashMap<String, String>> entityAndFields = new HashMap<>();
        HashMap<String, String> classAndSuperclass = new HashMap<>();
        for (JavaClass entity : entities) {
            String entityClassName = entity.getName();
            JavaClass superJavaClass = entity.getSuperJavaClass();
            if (superJavaClass != null && !StringUtils.compareTypeNames(superJavaClass.getName(), OBJECT_CLASS_NAME)) {
                classAndSuperclass.put(getTypeName(entityClassName), getTypeName(superJavaClass.getName()));
            }
            HashMap<String, String> nameAndType = new HashMap<>();
            List<JavaField> fields = entity.getFields();
            for (JavaField field : fields) {
                JavaClass type = field.getType();
                String typeName = type.getName();
                if (collections.contains(typeName)) {
                    typeName = getValue(type.getGenericValue());
                }
                String finalTypeName = typeName;
                boolean isEntity = entities.stream()
                        .anyMatch(javaClass -> StringUtils.compareTypeNames(javaClass.getName(), finalTypeName));
                if (isEntity) {
                    nameAndType.put(field.getName(), getTypeName(typeName));
                }
            }
            entityAndFields.put(entityClassName, nameAndType);
        }

        if (classAndSuperclass.isEmpty()) {
            return entityAndFields;
        }
        for (Map.Entry<String, String> entry : classAndSuperclass.entrySet()) {
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
