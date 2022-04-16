package org.makuut;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.makuut.StringUtils.*;

/**
 * Обработчик файлов репозиториев
 */
public class EntityGraphProcessor {

    private static final String ENTITY_GRAPH_ANNOTATION_NAME = "org.springframework.data.jpa.repository.EntityGraph";
    private static final String ATTRIBUTE_PATHS_PARAMETER_NAME = "attributePaths";
    private static final String COMMA_PATTERN = "\\,";
    private static final String COMMA = ",";
    private static final String PLUS = "+";

    private static final List<String> collections = List.of("Set", "List");

    /**
     * Формирует общую карту сущностей и относящихся к нему графов (из репозиториев и сущносетй)
     *
     * @param repositories Список предствалений файлов репозиториев
     * @return Карта сущностей и графов к нему
     */
    public static HashMap<String, Set<String>> getEntitiesAndTheirGraphs(List<File> repositories, List<File> entities) throws IOException {
        HashMap<String, List<Object>> entitiesAndTheirGraphs = getEntitiesAndTheirGraphsFromRepository(repositories);
        entitiesAndTheirGraphs.putAll(getEntitiesAndTheirGraphsFromEntities(entities));

        HashMap<String, Set<String>> changedEntitiesAndTheirGraphs = new HashMap<>();

        entitiesAndTheirGraphs.forEach((s, lo) -> changedEntitiesAndTheirGraphs.put(s, changeGraphsToList(lo)));
        return changedEntitiesAndTheirGraphs;
    }

    /**
     * Формирует карту сущностей и относящихся к нему графов из репозиториев
     *
     * @param repositories Список предствалений файлов репозиториев
     * @return Карта сущностей и графов к нему
     */
    public static HashMap<String, List<Object>> getEntitiesAndTheirGraphsFromRepository(List<File> repositories) throws IOException {
        HashMap<String, List<Object>> returnTypeAndGraphs = new HashMap<>();
        String entityName = null;
        List<Object> graphs = new ArrayList<>();
        for (File file : repositories) {
            JavaClass repositoryInterface = getRepository(file);
            List<JavaMethod> methods = repositoryInterface.getMethods();
            for (JavaMethod method : methods) {
                List<JavaAnnotation> annotations = method.getAnnotations();
                if (annotations.isEmpty()) {
                    continue;
                }
                Object attributePaths = null;
                for (JavaAnnotation annotation : annotations) {
                    JavaClass annotationType = annotation.getType();
                    String name = annotationType.getName();
                    if (!ENTITY_GRAPH_ANNOTATION_NAME.equals(name)) {
                        continue;
                    }
                    attributePaths = annotation.getNamedParameter(ATTRIBUTE_PATHS_PARAMETER_NAME);
                    if (attributePaths != null) {
                        JavaType returnType = method.getReturnType();
                        String genericValue = returnType.getGenericValue();
                        entityName = getValue(genericValue);
                        graphs.add(attributePaths);
                    }
                    attributePaths = null;
                }
            }
            if (entityName != null) {
                returnTypeAndGraphs.put(entityName, new ArrayList<Object>(graphs));
                entityName = null;
            }
            graphs.clear();
        }
        return returnTypeAndGraphs;
    }

    private static JavaClass getRepository(File file) throws IOException {
        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        JavaSource src = projectBuilder.addSource(file);
        return src.getClasses().get(0);
    }

    /**
     * Формирует карту сущностей и относящихся к нему графов из репозиториев
     *
     * @param entities Список файлов сущностей
     * @return Карта сущностей и графов к нему
     */
    public static HashMap<String, List<Object>> getEntitiesAndTheirGraphsFromEntities(List<File> entities) throws IOException {
        HashMap<String, List<Object>> returnTypeAndGraphs = new HashMap<>();
//        TODO Написать реализацию
        return returnTypeAndGraphs;
    }

    /**
     * Преобразование массива строк из типа Object в List<String>
     *
     * @param pureGraphs Карта сущностей и графов к нему
     * @return Список графов
     */
    public static Set<String> changeGraphsToList(List<Object> pureGraphs) {
        Set<String> graphs = new HashSet<>();

        if (pureGraphs == null) {
            return graphs;
        }

        for(Object graph: pureGraphs){
            String pureGraphsString = graph.toString();

            if (pureGraphsString.length() < 3) {
                continue;
            }

            int leftBracket = pureGraphsString.indexOf('[');
            int rightBracket = pureGraphsString.lastIndexOf(']');
            pureGraphsString = pureGraphsString.substring(leftBracket + 1, rightBracket);

            if (!pureGraphsString.contains(COMMA)) {
                pureGraphsString = deleteQuotes(pureGraphsString);
                graphs.add(pureGraphsString);
                continue;
            }

            String[] split = pureGraphsString.split(COMMA_PATTERN);
            for (String str : split) {
                str = deleteQuotes(str);
                if (str.contains(PLUS)) {
                    str = joinGraph(str);
                }
                graphs.add(str);
            }
        }
        return graphs;
    }
}
