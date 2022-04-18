package org.makuut;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.makuut.EntityFileProcessor.getEntitiesAndTheirField;
import static org.makuut.EntityGraphProcessor.getEntitiesAndTheirGraphs;
import static org.makuut.FileUtils.search;

/**
 * Плагин определяет наличие полей, указанных в @EntityGraph, в классах сущностей, при их отсутствии выводит сообщение
 * @author Maxim Terentev
 */
@Mojo(name = "analyzer", defaultPhase = LifecyclePhase.VALIDATE, threadSafe = true)
public class EntityGraphAnalyzerPlugin extends AbstractMojo {

    @Parameter(property = "repositoryDirectory", required = true)
    private File repositoryDirectory;
    @Parameter(property = "entityDirectory", required = true)
    private File entityDirectory;

    @Parameter(property = "repositoryFilePattern", defaultValue = ".*Repository.java")
    private String repositoryFilePattern;
    @Parameter(property = "entityFilePattern", defaultValue = ".*Entity.java")
    private String entityFilePattern;

    List<File> existingRepositories = new ArrayList<>();
    List<File> existingEntities = new ArrayList<>();
    HashMap<String, Set<String>> entitiesWithGraphs = new HashMap<>();
    HashMap<String, HashMap<String, String>> entitiesWithFields = new HashMap<>();

    private static final String DOT = ".";
    private static final String DOT_PATTERN = "\\.";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (repositoryDirectory == null || entityDirectory == null
                || !repositoryDirectory.isDirectory() || !entityDirectory.isDirectory()) {
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
                            getLog().info("В классе " + entity + " отсутствует поле-сущность " + field + ", указанное в " + graph);
                        }
                        continue;
                    }
                    String[] fields = graph.split(DOT_PATTERN);

                    String changedEntity = entity;
                    for (String field : fields) {
                        HashMap<String, String> nameAndType = entitiesWithFields.get(changedEntity);

                        if (!nameAndType.containsKey(field)) {
                            getLog().info("В классе " + changedEntity + " отсутствует поле-сущность " + field + ", указанное в " + graph);
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


