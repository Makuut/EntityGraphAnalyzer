package org.makuut;

import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.expression.AnnotationValue;
import com.thoughtworks.qdox.model.expression.AnnotationValueList;
import com.thoughtworks.qdox.model.expression.Constant;
import com.thoughtworks.qdox.model.impl.DefaultJavaAnnotation;

import java.io.IOException;
import java.util.*;

import static org.makuut.CollectionUtils.isBlank;
import static org.makuut.StringUtils.compareTypeNames;
import static org.makuut.StringUtils.deleteQuotes;

/**
 * Обработчик сущностей для получения графов
 */
public class EntityGraphProcessorFromEntities {

    private static final String VALUE = "value";
    private static final String NAME = "name";

    private static final String ATTRIBUTE_NODES = "attributeNodes";
    private static final String SUBGRAPHS = "subgraphs";
    private static final String KEY_SUBGRAPHS = "keySubgraphs";
    private static final String SUBCLASS_SUBGRAPHS = "subclassSubgraphs";

    private static final String NAMED_ENTITY_GRAPH_ANNOTATION_NAME = "NamedEntityGraph";
    private static final String NAMED_ENTITY_GRAPHS_ANNOTATION_NAME = "NamedEntityGraphs";

    /**
     * Формирует карту сущностей и относящихся к нему графов из классов сущностей
     *
     * @param entities Список файлов сущностей
     * @return Карта сущностей и графов к нему
     */
    public static HashMap<String, Set<String>> getEntitiesAndTheirGraphsFromEntities(List<JavaClass> entities) throws IOException {
        HashMap<String, Set<String>> entitiesAndTheirGraphs = new HashMap<>();
        for (JavaClass entity : entities) {
            String name = entity.getName();
            Set<String> graphs = new HashSet<>();
            List<JavaAnnotation> annotations = entity.getAnnotations();
            for (JavaAnnotation annotation : annotations) {
                if (compareTypeNames(NAMED_ENTITY_GRAPH_ANNOTATION_NAME, annotation.getType().getName())) {
                    Set<String> graphsFromJavaAnnotation = getGraphsFromJavaAnnotation(annotation);
                    graphs.addAll(graphsFromJavaAnnotation);
                }
                if (compareTypeNames(NAMED_ENTITY_GRAPHS_ANNOTATION_NAME, annotation.getType().getName())) {
                    AnnotationValue value = annotation.getProperty(VALUE);
                    List<AnnotationValue> graphAnnotations = ((AnnotationValueList) value).getValueList();
                    for (AnnotationValue graphAnnotation : graphAnnotations) {
                        Set<String> graphsFromAnnotationValue = getGraphsFromJavaAnnotation((JavaAnnotation) graphAnnotation);
                        graphs.addAll(graphsFromAnnotationValue);
                    }
                }
            }
            if (!graphs.isEmpty()) {
                entitiesAndTheirGraphs.put(name, graphs);
            }
        }
        return entitiesAndTheirGraphs;
    }

    /**
     * Получает список графов из типа JavaAnnotation
     *
     * @param annotation Аннотация NamedEntityGraph в объекте JavaAnnotation
     * @return Список графов
     */
    private static Set<String> getGraphsFromJavaAnnotation(JavaAnnotation annotation) {
        Set<String> graphs = new HashSet<>();
        HashMap<String, String> subgraphAndName = new HashMap<>();

        AnnotationValue attributeNodes = annotation.getProperty(ATTRIBUTE_NODES);
        AnnotationValue subgraphs = annotation.getProperty(SUBGRAPHS);
        AnnotationValue subclass_subgraphs = annotation.getProperty(SUBCLASS_SUBGRAPHS);


        if (subgraphs != null) {
            subgraphAndName = getSubgraphs(subgraphs);
        }

        if (attributeNodes != null && subgraphs == null && subclass_subgraphs == null) {
            if (attributeNodes instanceof DefaultJavaAnnotation) {
                AnnotationValue value = ((DefaultJavaAnnotation) attributeNodes).getProperty(VALUE);
                if (value != null) {
                    String graph = deleteQuotes(value.toString());
                    graphs.add(graph);
                }
            } else if (attributeNodes instanceof AnnotationValueList) {
                List<AnnotationValue> valueList = ((AnnotationValueList) attributeNodes).getValueList();
                for (AnnotationValue annotationValue : valueList) {
                    if (annotationValue instanceof DefaultJavaAnnotation) {
                        AnnotationValue value = ((DefaultJavaAnnotation) annotationValue).getProperty(VALUE);
                        if (value != null) {
                            String graph = deleteQuotes(value.toString());
                            graphs.add(graph);
                        }
                    }
                }
            }
        } else if (attributeNodes != null && subgraphs != null && subclass_subgraphs == null) {
            if (attributeNodes instanceof DefaultJavaAnnotation) {
                AnnotationValue value1 = ((DefaultJavaAnnotation) attributeNodes).getProperty(VALUE);
                AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) attributeNodes).getProperty(SUBGRAPHS);
                AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) attributeNodes).getProperty(KEY_SUBGRAPHS);

                if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                    String graph = deleteQuotes(value1.toString());
                    graphs.add(graph);
                }
            } else if (attributeNodes instanceof AnnotationValueList) {
                List<AnnotationValue> valueList = ((AnnotationValueList) attributeNodes).getValueList();
                for (AnnotationValue annotationValue : valueList) {
                    if (annotationValue instanceof DefaultJavaAnnotation) {
                        AnnotationValue value1 = ((DefaultJavaAnnotation) annotationValue).getProperty(VALUE);
                        AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(SUBGRAPHS);
                        AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(KEY_SUBGRAPHS);

                        if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                            String graph = deleteQuotes(value1.toString());
                            graphs.add(graph);
                        }
                    }
                }
            }
            if (attributeNodes instanceof DefaultJavaAnnotation) {
                AnnotationValue value1 = ((DefaultJavaAnnotation) attributeNodes).getProperty(VALUE);
                AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) attributeNodes).getProperty(SUBGRAPHS);
                AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) attributeNodes).getProperty(KEY_SUBGRAPHS);

                if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                    String nameSubgraph = deleteQuotes(((Constant) subgraphs1).getImage());
                    String node = deleteQuotes(((Constant) value1).getImage());
                    subgraphAndName.entrySet().stream().filter(entry -> nameSubgraph.equals(entry.getValue()))
                            .forEach(entry -> {
                                graphs.add(node + "." + entry.getKey());
                            });
                }
            } else if (attributeNodes instanceof AnnotationValueList) {
                List<AnnotationValue> valueList = ((AnnotationValueList) attributeNodes).getValueList();
                for (AnnotationValue annotationValue : valueList) {
                    if (annotationValue instanceof DefaultJavaAnnotation) {
                        AnnotationValue value1 = ((DefaultJavaAnnotation) annotationValue).getProperty(VALUE);
                        AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(SUBGRAPHS);
                        AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(KEY_SUBGRAPHS);

                        if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                            String nameSubgraph = deleteQuotes(((Constant) subgraphs1).getImage());
                            String node = deleteQuotes(((Constant) value1).getImage());
                            subgraphAndName.entrySet().stream().filter(entry -> nameSubgraph.equals(entry.getValue()))
                                    .forEach(entry -> {
                                        graphs.add(node + "." + entry.getKey());
                                    });
                        }
                    }
                }
            }
        }
        return graphs;
    }

    /**
     * Формирует карту с подграфом и именем начального подграфа,
     * который будет использоваться для присоединения к attributeNodes
     *
     * @param subgraphs Параметр subgraphs у аннотации NamedEntityGraph
     * @return Карта с подграфом и именем начального подграфа
     */
    private static HashMap<String, String> getSubgraphs(AnnotationValue subgraphs) {
        HashMap<String, String> subgraphAndName = new HashMap<>();
        if (subgraphs == null) {
            return subgraphAndName;
        }
        HashMap<String, HashMap<String, String>> subNames_Nodes_RefSubNames = new HashMap<>();
        if (subgraphs instanceof DefaultJavaAnnotation) {
            HashMap<String, String> valueNameSubgraph = new HashMap<>();
            AnnotationValue propertyName = ((DefaultJavaAnnotation) subgraphs).getProperty(NAME);
            if (propertyName == null) {
                return subgraphAndName;
            }
            String name = deleteQuotes(((Constant) propertyName).getImage());
            AnnotationValue propertyAttributeNode = ((DefaultJavaAnnotation) subgraphs).getProperty(ATTRIBUTE_NODES);
            if (propertyAttributeNode == null) {
                return subgraphAndName;
            }
            if (propertyAttributeNode instanceof DefaultJavaAnnotation) {
                AnnotationValue value1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(VALUE);
                AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(SUBGRAPHS);
                AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(KEY_SUBGRAPHS);
                if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                    String value = deleteQuotes(value1.toString());
                    valueNameSubgraph.put(value, null);
                } else if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                    String value = deleteQuotes(value1.toString());
                    String subgraphName = deleteQuotes(subgraphs1.toString());
                    valueNameSubgraph.put(value, subgraphName);
                }
                subNames_Nodes_RefSubNames.put(name, valueNameSubgraph);
            } else if (propertyAttributeNode instanceof AnnotationValueList) {
                List<AnnotationValue> attributeNodes = ((AnnotationValueList) propertyAttributeNode).getValueList();
                for (AnnotationValue annotationValue : attributeNodes) {
                    if (annotationValue instanceof DefaultJavaAnnotation) {
                        AnnotationValue value1 = ((DefaultJavaAnnotation) annotationValue).getProperty(VALUE);
                        AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(SUBGRAPHS);
                        AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(KEY_SUBGRAPHS);
                        if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                            String value = deleteQuotes(value1.toString());
                            valueNameSubgraph.put(value, null);
                        } else if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                            String value = deleteQuotes(value1.toString());
                            String subgraphName = deleteQuotes(subgraphs1.toString());
                            valueNameSubgraph.put(value, subgraphName);
                        }
                    }
                }
                subNames_Nodes_RefSubNames.put(name, valueNameSubgraph);
            }
        } else if (subgraphs instanceof AnnotationValueList) {
            List<AnnotationValue> nameSubgraphList = ((AnnotationValueList) subgraphs).getValueList();
            for (AnnotationValue nameSubgraph : nameSubgraphList) {
                HashMap<String, String> valueNameSubgraph = new HashMap<>();
                if (nameSubgraph instanceof DefaultJavaAnnotation) {
                    AnnotationValue propertyName = ((DefaultJavaAnnotation) nameSubgraph).getProperty(NAME);
                    if (propertyName == null) {
                        return subgraphAndName;
                    }
                    String name = deleteQuotes(((Constant) propertyName).getImage());
                    AnnotationValue propertyAttributeNode = ((DefaultJavaAnnotation) nameSubgraph).getProperty(ATTRIBUTE_NODES);
                    if (propertyAttributeNode == null) {
                        return subgraphAndName;
                    }
                    if (propertyAttributeNode instanceof DefaultJavaAnnotation) {
                        AnnotationValue value1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(VALUE);
                        AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(SUBGRAPHS);
                        AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(KEY_SUBGRAPHS);
                        if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                            String value = deleteQuotes(value1.toString());
                            valueNameSubgraph.put(value, null);
                        } else if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                            String value = deleteQuotes(value1.toString());
                            String subgraphName = deleteQuotes(subgraphs1.toString());
                            valueNameSubgraph.put(value, subgraphName);
                        }
                        subNames_Nodes_RefSubNames.put(name, valueNameSubgraph);
                    } else if (propertyAttributeNode instanceof AnnotationValueList) {
                        List<AnnotationValue> attributeNodes = ((AnnotationValueList) propertyAttributeNode).getValueList();
                        for (AnnotationValue annotationValue : attributeNodes) {
                            if (annotationValue instanceof DefaultJavaAnnotation) {
                                AnnotationValue value1 = ((DefaultJavaAnnotation) annotationValue).getProperty(VALUE);
                                AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(SUBGRAPHS);
                                AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(KEY_SUBGRAPHS);
                                if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                                    String value = deleteQuotes(value1.toString());
                                    valueNameSubgraph.put(value, null);
                                } else if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                                    String value = deleteQuotes(value1.toString());
                                    String subgraphName = deleteQuotes(subgraphs1.toString());
                                    valueNameSubgraph.put(value, subgraphName);
                                }
                            }
                        }
                        subNames_Nodes_RefSubNames.put(name, valueNameSubgraph);
                    }
                }
            }
        }
        return changeSubgraphsFormat(subNames_Nodes_RefSubNames);
    }

    /**
     * Изменяет формат представления подграфов
     *
     * @param subNames_Nodes_RefSubNames Карта названия подграфа и карта узла с именем дальнейшего подграфом
     * @return Карта всех подграфа одного имени подграфа
     */
    private static HashMap<String, String> changeSubgraphsFormat(HashMap<String, HashMap<String, String>> subNames_Nodes_RefSubNames) {
        HashMap<String, String> subgraphAndName = new HashMap<>();
        for (Map.Entry<String, HashMap<String, String>> outerEntry : subNames_Nodes_RefSubNames.entrySet()) {
            String subgraphName = outerEntry.getKey();
            HashMap<String, String> nodes_RefSubNames = outerEntry.getValue();
            if (isBlank(nodes_RefSubNames)) {
                continue;
            }
            List<String> finalSubgraphs = new ArrayList<>();
            for (Map.Entry<String, String> innerEntry : nodes_RefSubNames.entrySet()) {
                String node = innerEntry.getKey();
                String refSubName = innerEntry.getValue();
                if (existsNodes(refSubName, subNames_Nodes_RefSubNames) == 0) {
                    finalSubgraphs.add(node);
                    continue;
                }

                recursiveComplete(node, refSubName, finalSubgraphs, subNames_Nodes_RefSubNames);
            }
            finalSubgraphs.forEach(finalSubgraph -> subgraphAndName.put(finalSubgraph, subgraphName));
        }
        return subgraphAndName;
    }

    /**
     * Рекурсивный метод формирует подграфы
     *
     * @param previousNode Название сформированного узла
     * @param refSubName Название подграфа
     * @param finalSubgraphs Карда сформированных подграфов
     * @param subNames_Nodes_RefSubNames Карта имен подграфов с узлами
     */
    private static void recursiveComplete(String previousNode, String refSubName, List<String> finalSubgraphs,
                           HashMap<String, HashMap<String, String>> subNames_Nodes_RefSubNames) {
        for (Map.Entry<String, HashMap<String, String>> entry : subNames_Nodes_RefSubNames.entrySet()) {
            String subgraphName = entry.getKey();
            HashMap<String, String> nodes_RefSubNames = entry.getValue();
            if (refSubName.equals(subgraphName)) {
                for (Map.Entry<String, String> innerEntry : nodes_RefSubNames.entrySet()) {
                    String nextNode = previousNode + "." + innerEntry.getKey();
                    String nextRefSubName = innerEntry.getValue();
                    if (existsNodes(nextRefSubName, subNames_Nodes_RefSubNames) == 0) {
                        finalSubgraphs.add(nextNode);
                        continue;
                    }
                    recursiveComplete(nextNode, nextRefSubName, finalSubgraphs, subNames_Nodes_RefSubNames);
                }
            }
        }
    }

    /**
     * Определяет количество узлов у подграфа
     *
     * @param refSubName Название подграфа
     * @param subNames_Nodes_RefSubNames Карта имен подграфов с узлами
     * @return Количество графов
     */
    private static int existsNodes(String refSubName, HashMap<String, HashMap<String, String>> subNames_Nodes_RefSubNames) {
        if (refSubName == null) {
            return 0;
        }
        for (Map.Entry<String, HashMap<String, String>> entry : subNames_Nodes_RefSubNames.entrySet()) {
            String subName2 = entry.getKey();
            if (refSubName.equals(subName2) && !isBlank(entry.getValue())) {
                return entry.getValue().size();
            }
        }
        return 0;
    }
}
