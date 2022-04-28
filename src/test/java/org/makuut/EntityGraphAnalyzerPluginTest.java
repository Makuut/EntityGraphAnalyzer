package org.makuut;

import com.thoughtworks.qdox.model.JavaClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.makuut.EntityFileProcessor.getEntitiesAndTheirField;
import static org.makuut.EntityGraphProcessor.getEntitiesAndTheirGraphs;

class EntityGraphAnalyzerPluginTest {

    private File sourceRoot = new File("src/test/resources/for_plugin");

    List<JavaClass> onlyEntities = new ArrayList<>();
    List<JavaClass> graphsInRepo = new ArrayList<>();
    List<JavaClass> entitiesAndGraphs = new ArrayList<>();

    HashMap<String, Set<String>> entitiesWithGraphs = new HashMap<>();
    HashMap<String, HashMap<String, String>> entitiesWithFields = new HashMap<>();

    private static final String DOT = ".";
    private static final String DOT_PATTERN = "\\.";

    @Test
    void execute() {
        if (sourceRoot == null || !sourceRoot.isDirectory()) {
            return;
        }
        try {
            //Проблема нахождения графов в репозитории
            FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);
            entitiesWithFields = getEntitiesAndTheirField(onlyEntities);
            entitiesWithGraphs = getEntitiesAndTheirGraphs(graphsInRepo, entitiesAndGraphs);
            for (Map.Entry<String, Set<String>> entries : entitiesWithGraphs.entrySet()) {
                String entity = entries.getKey();

                Set<String> graphs = entries.getValue();
                for (String graph : graphs) {
                    if (!graph.contains(DOT)) {
                        String field = graph;
                        HashMap<String, String> nameAndType = entitiesWithFields.get(entity);

                        if (!nameAndType.containsKey(field)) {
                            System.out.println("В классе " + entity + " отсутствует поле-сущность " + field + ", указанное в " + graph);
                        }
                        continue;
                    }
                    String[] fields = graph.split(DOT_PATTERN);

                    String changedEntity = entity;
                    for (String field : fields) {
                        HashMap<String, String> nameAndType = entitiesWithFields.get(changedEntity);

                        if (!nameAndType.containsKey(field)) {
                            System.out.println("В классе " + changedEntity + " отсутствует поле-сущность " + field + ", указанное в " + graph);
                            break;
                        }
                        changedEntity = nameAndType.get(field);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}