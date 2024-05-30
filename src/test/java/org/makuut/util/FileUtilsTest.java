package org.makuut.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.makuut.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUtilsTest {

    private File repositoryDirectory = new File("src/test/resources/for_plugin/repository");
    private File entityDirectory = new File("src/test/resources/for_plugin/entity");
    private String repositoryFilePattern = ".*Repository.java";
    private String entityFilePattern = ".*Entity.java";

    List<File> existingRepositories = new ArrayList<>();
    List<File> existingEntities = new ArrayList<>();

    @Test
    @DisplayName("Проверка правильности нахождения файлов в директории репозиториев по паттерну")
    void searchRepositoryFile() {
        int expected = 85;

        FileUtils.search(repositoryFilePattern, repositoryDirectory, existingRepositories);
        int actual = existingRepositories.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка правильности нахождения файлов в директории сущностей по паттерну")
    void searchEntityFile() {
        int expected = 119;

        FileUtils.search(entityFilePattern, entityDirectory, existingEntities);
        int actual = existingEntities.size();

        assertEquals(expected, actual);
    }
}