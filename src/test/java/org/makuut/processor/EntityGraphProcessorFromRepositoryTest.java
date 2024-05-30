package org.makuut.processor;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.makuut.processor.EntityGraphProcessorFromRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityGraphProcessorFromRepositoryTest {

    File accreditationStatementRepository = new File("src/test/resources/for_plugin/repository/AccreditationStatementRepository.java");
    File archiveEntityRepository = new File("src/test/resources/for_plugin/repository/ArchiveEntityRepository.java");
    File archiveFileEntityRepository = new File("src/test/resources/for_plugin/repository/ArchiveFileEntityRepository.java");


    @Test
    @DisplayName("Один метод с семью графами")
    void oneMethodAndSevenGraphs() throws IOException {
        String expected = "{AccreditationStatementEntity=[accreditationStatementType, accreditationStatementDocEntitySet.accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature, bankruptcyCommissioner.sro, bankruptmissioner, status, accreditationStatementDocEntitySet.statementDocType]}";

        HashMap<String, Set<String>> entitiesAndTheirGraphsFromRepository =
                EntityGraphProcessorFromRepository.getEntitiesAndTheirGraphsFromRepository(getJavaClass(accreditationStatementRepository));

        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
    }

    @Test
    @DisplayName("Ноль методов")
    void zeroMethods() throws IOException {
        String expected = "{}";

        HashMap<String, Set<String>> entitiesAndTheirGraphsFromRepository =
                EntityGraphProcessorFromRepository.getEntitiesAndTheirGraphsFromRepository(getJavaClass(archiveEntityRepository));

        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
    }

    @Test
    @DisplayName("Один метод с одинм графом")
    void oneMethodAndOneGraph() throws IOException {
        String expected = "{ArchiveFileEntity=[archiveEntity]}";

        HashMap<String, Set<String>> entitiesAndTheirGraphsFromRepository =
                EntityGraphProcessorFromRepository.getEntitiesAndTheirGraphsFromRepository(getJavaClass(archiveFileEntityRepository));

        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
    }

    private static List<JavaClass> getJavaClass(File file) throws IOException {
        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        JavaSource src = projectBuilder.addSource(file);
        return src.getClasses();
    }


}