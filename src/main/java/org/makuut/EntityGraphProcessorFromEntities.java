package org.makuut;

import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.expression.AnnotationValue;
import com.thoughtworks.qdox.model.expression.AnnotationValueList;
import com.thoughtworks.qdox.model.expression.Constant;
import com.thoughtworks.qdox.model.impl.DefaultJavaAnnotation;

import java.io.IOException;
import java.util.*;

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
            Set<String> graphs = new HashSet<String>();
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
                        Set<String> graphsFromAnnotationValue = getGraphsFromAnnotationValue(graphAnnotation);
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
            //0)Провести анализ subgraphs поместить в структуру

            //1)Проверить attributeNodes, там где указано только value в массиве или нет, записать
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
                    StringBuilder graph = new StringBuilder(nameSubgraph);
                    //TODO переписать наоборот
                    String subgraph = subgraphAndName.get(nameSubgraph);
                    graph.append("." + subgraph);
                    graphs.add(graph.toString());
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
                            StringBuilder graph = new StringBuilder(nameSubgraph);
                            //TODO переписать наоборот
                            String subgraph = subgraphAndName.get(nameSubgraph);
                            graph.append("." + subgraph);
                            graphs.add(graph.toString());
                        }
                    }
                }
            }
            //2)Там где value и subgraph берем карту полученую от анализа subgraph и соединяем ее с value
            System.out.println();
        }

        return graphs;
    }

    /**
     * Формирукт карту с подграфом и именем начального подграфа,
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

        //namedSubgraphs один не в массиве
        if (subgraphs instanceof DefaultJavaAnnotation) {
            HashMap<String, String> valueNameSubgraph = new HashMap<>();
            AnnotationValue propertyName = ((DefaultJavaAnnotation) subgraphs).getProperty(NAME);
            if (propertyName == null) {
                return subgraphAndName;
            }
            //получаем имя namedSubgraphs, который должен быть ключом для nameValueSubgraph
            String name = deleteQuotes(((Constant) propertyName).getImage());
            AnnotationValue propertyAttributeNode = ((DefaultJavaAnnotation) subgraphs).getProperty(ATTRIBUTE_NODES);
            if (propertyAttributeNode == null) {
                return subgraphAndName;
            }
            //получаем один attributeNode не в массиве
            if (propertyAttributeNode instanceof DefaultJavaAnnotation) {
                AnnotationValue value1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(VALUE);
                AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(SUBGRAPHS);
                AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(KEY_SUBGRAPHS);
                if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                    String value = deleteQuotes(value1.toString());
                    //у attributeNode только value
                    valueNameSubgraph.put(value, null);
                } else if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                    String value = deleteQuotes(value1.toString());
                    String subgraphName = deleteQuotes(subgraphs1.toString());
                    //у attributeNode только value и subgraphs
                    valueNameSubgraph.put(value, subgraphName);
                }
                //сохранить в тройноую карту
                subNames_Nodes_RefSubNames.put(name, valueNameSubgraph);

                //получаем много attributeNode не в массиве
            } else if (propertyAttributeNode instanceof AnnotationValueList) {
                List<AnnotationValue> attributeNodes = ((AnnotationValueList) propertyAttributeNode).getValueList();
                for (AnnotationValue annotationValue : attributeNodes) {
                    if (annotationValue instanceof DefaultJavaAnnotation) {
                        AnnotationValue value1 = ((DefaultJavaAnnotation) annotationValue).getProperty(VALUE);
                        AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(SUBGRAPHS);
                        AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(KEY_SUBGRAPHS);
                        if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                            String value = deleteQuotes(value1.toString());
                            //у attributeNode только value
                            valueNameSubgraph.put(value, null);
                        } else if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                            String value = deleteQuotes(value1.toString());
                            String subgraphName = deleteQuotes(subgraphs1.toString());
                            //у attributeNode только value и subgraphs
                            valueNameSubgraph.put(value, subgraphName);
                        }
                    }
                }
                //сохранить в тройную карту
                subNames_Nodes_RefSubNames.put(name, valueNameSubgraph);
            }
            //namedSubgraphs в массиве
        } else if (subgraphs instanceof AnnotationValueList) {
            List<AnnotationValue> nameSubgraphList = ((AnnotationValueList) subgraphs).getValueList();
            //берем каждый namedSubgraphs отдельно
            for (AnnotationValue nameSubgraph : nameSubgraphList) {
                HashMap<String, String> valueNameSubgraph = new HashMap<>();
                if (nameSubgraph instanceof DefaultJavaAnnotation) {
                    AnnotationValue propertyName = ((DefaultJavaAnnotation) nameSubgraph).getProperty(NAME);
                    if (propertyName == null) {
                        return subgraphAndName;
                    }
                    //получаем имя namedSubgraphs, который должен быть ключом для nameValueSubgraph
                    String name = deleteQuotes(((Constant) propertyName).getImage());

                    AnnotationValue propertyAttributeNode = ((DefaultJavaAnnotation) nameSubgraph).getProperty(ATTRIBUTE_NODES);
                    if (propertyAttributeNode == null) {
                        return subgraphAndName;
                    }
                    //получаем один attributeNode не в массиве
                    if (propertyAttributeNode instanceof DefaultJavaAnnotation) {
                        AnnotationValue value1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(VALUE);
                        AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(SUBGRAPHS);
                        AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) propertyAttributeNode).getProperty(KEY_SUBGRAPHS);
                        if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                            String value = deleteQuotes(value1.toString());
                            //у attributeNode только value
                            valueNameSubgraph.put(value, null);
                        } else if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                            String value = deleteQuotes(value1.toString());
                            String subgraphName = deleteQuotes(subgraphs1.toString());
                            //у attributeNode только value и subgraphs
                            valueNameSubgraph.put(value, subgraphName);
                        }
                        //сохранить в тройную карту
                        subNames_Nodes_RefSubNames.put(name, valueNameSubgraph);

                        //attributeNodes в массиве
                    } else if (propertyAttributeNode instanceof AnnotationValueList) {
                        List<AnnotationValue> attributeNodes = ((AnnotationValueList) propertyAttributeNode).getValueList();
                        for (AnnotationValue annotationValue : attributeNodes) {
                            if (annotationValue instanceof DefaultJavaAnnotation) {
                                AnnotationValue value1 = ((DefaultJavaAnnotation) annotationValue).getProperty(VALUE);
                                AnnotationValue subgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(SUBGRAPHS);
                                AnnotationValue keySubgraphs1 = ((DefaultJavaAnnotation) annotationValue).getProperty(KEY_SUBGRAPHS);
                                if (value1 != null && subgraphs1 == null && keySubgraphs1 == null) {
                                    String value = deleteQuotes(value1.toString());
                                    //у attributeNode только value
                                    valueNameSubgraph.put(value, null);
                                } else if (value1 != null && subgraphs1 != null && keySubgraphs1 == null) {
                                    String value = deleteQuotes(value1.toString());
                                    String subgraphName = deleteQuotes(subgraphs1.toString());
                                    //у attributeNode только value и subgraphs
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
     * @param subNames_Nodes_RefSubNames Карта названия подграфа и карта упзла с именем дальнейшего подграфом
     * @return Карта всех подграфа одного имени подграфа
     */
    private static HashMap<String, String> changeSubgraphsFormat(HashMap<String, HashMap<String, String>> subNames_Nodes_RefSubNames) {
        HashMap<String, String> subgraphAndName = new HashMap<>();
        List<String> finalSubgraphs = new ArrayList<>();
        List<StringBuilder> variableSubgraphs = new ArrayList<>();

        //Главный цикл который проходится по всем подграфам
        for (Map.Entry<String, HashMap<String, String>> subName_Nodes_RefSubNames : subNames_Nodes_RefSubNames.entrySet()) {
            String subgraphName = subName_Nodes_RefSubNames.getKey();
            fillFinalSubgraphs(variableSubgraphs, finalSubgraphs, subName_Nodes_RefSubNames.getValue(), subNames_Nodes_RefSubNames);
            for (String subgraph : finalSubgraphs) {
                subgraphAndName.put(subgraph, subgraphName);
            }
        }
        return subgraphAndName;
    }

    private static void fillFinalSubgraphs(List<StringBuilder> variableSubgraphs, List<String> finalSubgraphs,
                                           HashMap<String, String> nodesAndRefSubNames, HashMap<String, HashMap<String, String>> subNames_nodes_refSubNames) {
        //Для имени подграфа нет ноды и ссылки
        if (nodesAndRefSubNames == null || nodesAndRefSubNames.isEmpty()) {
            return;
        }

    }


    /**
     * Получает список графов из типа AnnotationValue
     *
     * @param annotation Аннотация NamedEntityGraph в объекте AnnotationValue
     * @return Список графов
     */
    private static Set<String> getGraphsFromAnnotationValue(AnnotationValue annotation) {
        Set<String> graphs = new HashSet<String>();

        return graphs;
    }

}
