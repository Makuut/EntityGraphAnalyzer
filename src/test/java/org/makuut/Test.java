package org.makuut;

import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class Test {

    HashMap<String, String> subgraphAndName = new HashMap<>();
    HashMap<String, HashMap<String, String>> subNames_Nodes_RefSubNames = new HashMap<>();

    @BeforeEach
    void prepare() {
        subNames_Nodes_RefSubNames.put("subgraph-1", null);

        HashMap<String, String> nodes_RefSubNames0 = new HashMap<>();
        nodes_RefSubNames0.put("node1", null);
        nodes_RefSubNames0.put("node2", null);
        nodes_RefSubNames0.put("node3", null);
        nodes_RefSubNames0.put("node4", null);
        nodes_RefSubNames0.put("node5", "subgraph1");
        nodes_RefSubNames0.put("node6", "subgraph2");
        nodes_RefSubNames0.put("node7", "subgraph3");
        nodes_RefSubNames0.put("node8", "subgraph4");
        nodes_RefSubNames0.put("node9", "subgraph5");
        nodes_RefSubNames0.put("node10", "subgraph-1");
        subNames_Nodes_RefSubNames.put("subgraph0", nodes_RefSubNames0);

        HashMap<String, String> nodes_RefSubNames1 = new HashMap<>();
        nodes_RefSubNames1.put("node11", null);
        nodes_RefSubNames1.put("node12", null);
        nodes_RefSubNames1.put("node13", null);
        nodes_RefSubNames1.put("node14", null);
        nodes_RefSubNames1.put("node15", "subgraph11");
        nodes_RefSubNames1.put("node16", "subgraph12");
        nodes_RefSubNames1.put("node17", "subgraph13");
        nodes_RefSubNames1.put("node18", "subgraph14");
        nodes_RefSubNames1.put("node19", "subgraph15");
        nodes_RefSubNames1.put("node20", "subgraph-1");
        subNames_Nodes_RefSubNames.put("subgraph1", nodes_RefSubNames1);

        HashMap<String, String> nodes_RefSubNames2 = new HashMap<>();
        nodes_RefSubNames2.put("node21", null);
        nodes_RefSubNames2.put("node22", null);
        nodes_RefSubNames2.put("node23", null);
        nodes_RefSubNames2.put("node24", null);
        nodes_RefSubNames2.put("node25", "subgraph11");
        nodes_RefSubNames2.put("node26", "subgraph12");
        nodes_RefSubNames2.put("node27", "subgraph13");
        nodes_RefSubNames2.put("node28", "subgraph14");
        nodes_RefSubNames2.put("node29", "subgraph15");
        nodes_RefSubNames2.put("node30", "subgraph-1");
        subNames_Nodes_RefSubNames.put("subgraph2", nodes_RefSubNames2);

        HashMap<String, String> nodes_RefSubNames3 = new HashMap<>();
        nodes_RefSubNames3.put("node31", null);
        nodes_RefSubNames3.put("node32", null);
        nodes_RefSubNames3.put("node33", null);
        nodes_RefSubNames3.put("node34", null);
        nodes_RefSubNames3.put("node35", "subgraph11");
        nodes_RefSubNames3.put("node36", "subgraph12");
        nodes_RefSubNames3.put("node37", "subgraph13");
        nodes_RefSubNames3.put("node38", "subgraph14");
        nodes_RefSubNames3.put("node39", "subgraph15");
        nodes_RefSubNames3.put("node40", "subgraph-1");
        subNames_Nodes_RefSubNames.put("subgraph3", nodes_RefSubNames3);

        HashMap<String, String> nodes_RefSubNames4 = new HashMap<>();
        nodes_RefSubNames4.put("node41", null);
        nodes_RefSubNames4.put("node42", null);
        nodes_RefSubNames4.put("node43", null);
        nodes_RefSubNames4.put("node44", null);
        nodes_RefSubNames4.put("node45", "subgraph11");
        nodes_RefSubNames4.put("node46", "subgraph12");
        nodes_RefSubNames4.put("node47", "subgraph13");
        nodes_RefSubNames4.put("node49", "subgraph15");
        nodes_RefSubNames4.put("node50", "subgraph-1");
        subNames_Nodes_RefSubNames.put("subgraph4", nodes_RefSubNames4);

        HashMap<String, String> nodes_RefSubNames11 = new HashMap<>();
        nodes_RefSubNames11.put("node111", null);
        nodes_RefSubNames11.put("node112", "subgraph100");
        nodes_RefSubNames11.put("node113", "subgraph15");
        nodes_RefSubNames11.put("node114", "subgraph-1");
        subNames_Nodes_RefSubNames.put("subgraph11", nodes_RefSubNames11);

        HashMap<String, String> nodes_RefSubNames12 = new HashMap<>();
        nodes_RefSubNames12.put("node121", null);
        nodes_RefSubNames12.put("node122", "subgraph100");
        nodes_RefSubNames12.put("node123", "subgraph15");
        nodes_RefSubNames12.put("node124", "subgraph-1");
        subNames_Nodes_RefSubNames.put("subgraph12", nodes_RefSubNames12);

        HashMap<String, String> nodes_RefSubNames13 = new HashMap<>();
        nodes_RefSubNames13.put("node131", null);
        nodes_RefSubNames13.put("node132", "subgraph100");
        nodes_RefSubNames13.put("node133", "subgraph15");
        nodes_RefSubNames13.put("node134", "subgraph-1");
        subNames_Nodes_RefSubNames.put("subgraph13", nodes_RefSubNames13);

        HashMap<String, String> nodes_RefSubNames14 = new HashMap<>();
        nodes_RefSubNames14.put("node141", null);
        nodes_RefSubNames14.put("node142", "subgraph100");
        nodes_RefSubNames14.put("node143", "subgraph15");
        nodes_RefSubNames14.put("node144", "subgraph-1");
        subNames_Nodes_RefSubNames.put("subgraph14", nodes_RefSubNames14);

        HashMap<String, String> nodes_RefSubNames100 = new HashMap<>();
        nodes_RefSubNames100.put("node1001", null);
        subNames_Nodes_RefSubNames.put("subgraph100", nodes_RefSubNames100);
    }

    @org.junit.jupiter.api.Test
    void test() {
        for (Map.Entry<String, HashMap<String, String>> outerEntry : subNames_Nodes_RefSubNames.entrySet()) {
            String subgraphName = outerEntry.getKey();
            HashMap<String, String> nodes_RefSubNames = outerEntry.getValue();
            if (nodes_RefSubNames == null) {
                continue;
            }
            List<String> finalSubgraphs = new ArrayList<>();
            for (Map.Entry<String, String> innerEntry : nodes_RefSubNames.entrySet()) {
                List<StringBuilder> variableSubgraphs = new ArrayList<StringBuilder>();
                String node = innerEntry.getKey();
                String refSubName = innerEntry.getValue();
                if (refSubName == null) {
                    StringBuilder subgraph = new StringBuilder();
                    variableSubgraphs.add(subgraph);
                } else {
                    boolean exists = false;
                    for (Map.Entry<String, HashMap<String, String>> entry: subNames_Nodes_RefSubNames.entrySet()) {
                        String subName2 = entry.getKey();
                        if (refSubName.equals(subName2) && entry.getValue() != null && entry.getValue().size() != 0) {
                            exists = true;
                            int size = entry.getValue().size();
                            for (int i = 0; i < size; i++) {
                                StringBuilder subgraph = new StringBuilder();
                                variableSubgraphs.add(subgraph);
                            }
                        }
                    }
                    if (!exists) {
                        StringBuilder subgraph = new StringBuilder();
                        variableSubgraphs.add(subgraph);
                    }
                }
                for (StringBuilder variableSubgraph : variableSubgraphs) {
                    variableSubgraph.append(node);
                }

                //Мы заполнили наш переменный лист нодами первого подграфа в количестве второго
                //Теперь мы должны проверить наличие ссылки на имя подграфа.
                //Если ее нет, то перейти на формирование следующей ноды.
                //А если есть
                if (refSubName == null) {
                    //Если ее нет, то перейти на формирование следующей ноды.
                    continue;
                } else {
                    //А если есть
                }
            }
            //По окончанию цикла прохождения по нодам у нас должен сформироваться finalSubgraphs
            //который мы с именем подграфа запихиваем в subgraphAndName
            for (String subgraph : finalSubgraphs) {
                subgraphAndName.put(subgraph, subgraphName);
            }
        }
    }
}
