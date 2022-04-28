package org.makuut;

import com.thoughtworks.qdox.model.JavaClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityFileProcessorTest {

    List<JavaClass> onlyEntities = new ArrayList<>();
    List<JavaClass> graphsInRepo = new ArrayList<>();
    List<JavaClass> entitiesAndGraphs = new ArrayList<>();

    @Test
    @DisplayName("Одна сущность без суперкласса и грфа")
    void oneEntity() throws IOException {
        File sourceRoot = new File("src/test/resources/for_file_processor/test_1");

        FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);

        assertEquals(1, onlyEntities.size());
        assertEquals(0, graphsInRepo.size());
        assertEquals(0, entitiesAndGraphs.size());
    }

    @Test
    @DisplayName("Двн сущностт без суперклассов и грфов")
    void twoEntity() throws IOException {
        File sourceRoot = new File("src/test/resources/for_file_processor/test_2");

        FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);

        assertEquals(2, onlyEntities.size());
        assertEquals(0, graphsInRepo.size());
        assertEquals(0, entitiesAndGraphs.size());
    }

    @Test
    @DisplayName("Одна сущность с графом без суперкласса")
    void oneEntityWithGraph() throws IOException {
        File sourceRoot = new File("src/test/resources/for_file_processor/test_3");

        FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);

        assertEquals(1, onlyEntities.size());
        assertEquals(0, graphsInRepo.size());
        assertEquals(1, entitiesAndGraphs.size());
    }

    @Test
    @DisplayName("Две сущности с графом без суперкласса")
    void twoEntityWithGraph() throws IOException {
        File sourceRoot = new File("src/test/resources/for_file_processor/test_4");

        FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);

        assertEquals(2, onlyEntities.size());
        assertEquals(0, graphsInRepo.size());
        assertEquals(2, entitiesAndGraphs.size());
    }

    @Test
    @DisplayName("Две сущности с графом без суперкласса и две без грефов и суперклассов и одна не сущность")
    void twoEntityAndTwoWithGraph() throws IOException {
        File sourceRoot = new File("src/test/resources/for_file_processor/test_5");

        FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);

        assertEquals(4, onlyEntities.size());
        assertEquals(0, graphsInRepo.size());
        assertEquals(2, entitiesAndGraphs.size());
    }

    @Test
    @DisplayName("Одна сущность без грфа c суперклассом")
    void oneEntityWithSuperclass() throws IOException {
        File sourceRoot = new File("src/test/resources/for_file_processor/test_6");

        FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);

        assertEquals(2, onlyEntities.size());
        assertEquals(0, graphsInRepo.size());
        assertEquals(0, entitiesAndGraphs.size());
    }

    @Test
    @DisplayName("Одна сущность без грфа c суперклассом и супксупеклассом")
    void oneEntityWithSuperclassAndSuperSuperclass() throws IOException {
        File sourceRoot = new File("src/test/resources/for_file_processor/test_7");

        FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);

        assertEquals(3, onlyEntities.size());
        assertEquals(0, graphsInRepo.size());
        assertEquals(0, entitiesAndGraphs.size());
    }

    @Test
    @DisplayName("Оди граф в репозитории")
    void oneGraphInRepo() throws IOException {
        File sourceRoot = new File("src/test/resources/for_file_processor/test_8");

        FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);

        assertEquals(0, onlyEntities.size());
        assertEquals(1, graphsInRepo.size());
        assertEquals(0, entitiesAndGraphs.size());
    }

    @Test
    @DisplayName("Множество графов в репозиториях")
    void manyGraphInRepo() throws IOException {
        File sourceRoot = new File("src/test/resources/for_plugin/repository");

        FileProcessor.fileAnalyze(sourceRoot, onlyEntities, graphsInRepo, entitiesAndGraphs);

        assertEquals(0, onlyEntities.size());
        assertEquals(23, graphsInRepo.size());
        assertEquals(0, entitiesAndGraphs.size());
    }
}