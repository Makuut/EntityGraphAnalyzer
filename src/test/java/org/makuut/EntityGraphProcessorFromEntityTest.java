package org.makuut;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.makuut.EntityGraphProcessorFromEntities.getEntitiesAndTheirGraphsFromEntities;

public class EntityGraphProcessorFromEntityTest {
    File test0 = new File("src/test/resources/for_entity_graph_from_entities/Test0.java");
    File test1 = new File("src/test/resources/for_entity_graph_from_entities/Test1.java");
    File test2 = new File("src/test/resources/for_entity_graph_from_entities/Test2.java");
    File test3 = new File("src/test/resources/for_entity_graph_from_entities/Test3.java");
    File test4 = new File("src/test/resources/for_entity_graph_from_entities/Test4.java");
    File test5 = new File("src/test/resources/for_entity_graph_from_entities/Test5.java");
    File test6 = new File("src/test/resources/for_entity_graph_from_entities/Test6.java");
    File test7 = new File("src/test/resources/for_entity_graph_from_entities/Test7.java");
    File test8 = new File("src/test/resources/for_entity_graph_from_entities/Test8.java");

    File test10 = new File("src/test/resources/for_entity_graph_from_entities/Test10.java");
    File test11 = new File("src/test/resources/for_entity_graph_from_entities/Test11.java");
    File test12 = new File("src/test/resources/for_entity_graph_from_entities/Test12.java");

    @Test
    @DisplayName("Аннотация не имеет параметров")
    void test0() throws IOException {
        String expected = "{}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test0));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("Две аннотиции графов по одному узлу не в массиве")
    void test1() throws IOException {
        String expected = "{Test1=[node2, node1]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test1));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("Две аннотиции графов в по два узла в массивах")
    void test2() throws IOException {
        String expected = "{Test2=[node4, node2, node3, node1]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test2));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("В параметрах имеются attributeNodes и subgraphs, но есть attributeNodes только с value не в массиве")
    void test3() throws IOException {
        String expected = "{Test3=[node1]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test3));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("В параметрах имеются attributeNodes и subgraphs, но есть attributeNodes только с value в массиве")
    void test4() throws IOException {
        String expected = "{Test4=[node2, node1]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test4));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("В параметрах имеются attributeNodes и subgraphs, attributeNodes имеет value и subgraphs не в массиве." +
            "subgraphs имеет один NamedSubgraph не в массиве.")
    void test5() throws IOException {
        String expected = "{Test5=[node1.node3]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test5));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("В параметрах имеются attributeNodes и subgraphs, attributeNodes имеет value и subgraphs в массиве")
    void test6() throws IOException {
        String expected = "{Test6=[node1.node3, node2.node4]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test6));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("В параметрах имеются attributeNodes и subgraphs, attributeNodes имеет value и subgraphs в массиве." +
            "subgraphs имеет несколько attributeNodes в массиве")
    void test7() throws IOException {
        String expected = "{Test7=[node1.node2, node1.node3]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test7));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("В параметрах имеются attributeNodes и массив subgraphs")
    void test8() throws IOException {
        String expected = "{Test8=[node2.node6, node2.node5, node1.node3, node3.node8.node12, node3.node7.node10.node11, node3.node7.node10.node12, node0, node3.node8.node11, node3.node7.node9]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test8));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("Пустой NamedEntityGraphs")
    void test10() throws IOException {
        String expected = "{}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test10));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("NamedEntityGraphs с двумя простыми NamedEntityGraph")
    void test11() throws IOException {
        String expected = "{Test11=[node2, node1]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test11));

        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("NamedEntityGraphs с двумя сложными NamedEntityGraph")
    void test12() throws IOException {
        String expected = "{Test8=[node2.node6, node2.node5, node3.node8.node12, node3.node7.node10.node11, node3.node7.node10.node12, node1.node3 , node0, node3.node8.node11, node3.node7.node9]}";

        HashMap<String, Set<String>> result = getEntitiesAndTheirGraphsFromEntities(getJavaClass(test12));

        assertEquals(expected, result.toString());
    }

    private static List<JavaClass> getJavaClass(File file) throws IOException {
        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        JavaSource src = projectBuilder.addSource(file);
        return src.getClasses();
    }
}
