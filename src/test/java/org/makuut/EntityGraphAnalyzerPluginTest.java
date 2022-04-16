package org.makuut;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.makuut.EntityFileProcessor.getEntitiesAndTheirField;
import static org.makuut.EntityGraphProcessor.getEntitiesAndTheirGraphs;
import static org.makuut.FileUtils.search;

class EntityGraphAnalyzerPluginTest {

    private File repositoryDirectory = new File("src/test/resources/repository");
    private File entityDirectory = new File("src/test/resources/entity");
    private String repositoryFilePattern = ".*Repository.java";
    private String entityFilePattern = ".*Entity.java";

    List<File> existingRepositories = new ArrayList<>();
    List<File> existingEntities = new ArrayList<>();
    HashMap<String, Set<String>> entitiesWithGraphs = new HashMap<>();
    HashMap<String, HashMap<String, String>> entitiesWithFields = new HashMap<>();

    private static final String DOT = ".";
    private static final String DOT_FOR_SPLIT = "\\.";

    @Test
    void execute() {
        if (repositoryDirectory == null || entityDirectory == null) {
            return;
        }

        search(repositoryFilePattern, repositoryDirectory, existingRepositories);
        search(entityFilePattern, entityDirectory, existingEntities);

        try {
            entitiesWithGraphs = getEntitiesAndTheirGraphs(existingRepositories, existingEntities);
            entitiesWithFields = getEntitiesAndTheirField(existingEntities, StringUtils.getEntityClassPattern(entityFilePattern));

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
                    String[] fields = graph.split(DOT_FOR_SPLIT);

                    String changedEntity = entity;
                    for (String field : fields) {
                        HashMap<String, String> nameAndType = entitiesWithFields.get(changedEntity);

                        if (!nameAndType.containsKey(field)) {
                            System.out.println("В классе " + entity + " отсутствует поле-сущность " + field + ", указанное в " + graph);
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