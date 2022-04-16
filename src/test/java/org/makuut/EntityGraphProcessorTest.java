package org.makuut;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityGraphProcessorTest {

    List<File> accreditationStatementRepository = List.of(new File("src/test/resources/repository/AccreditationStatementRepository.java"));
    List<File> archiveEntityRepository = List.of(new File("src/test/resources/repository/ArchiveEntityRepository.java"));
    List<File> archiveFileEntityRepository = List.of(new File("src/test/resources/repository/ArchiveFileEntityRepository.java"));
    List<File> bankruptcyCommissionerRepository = List.of(new File("src/test/resources/repository/BankruptcyCommissionerRepository.java"));
    List<File> threeRepositories = List.of(new File("src/test/resources/repository/ArchiveEntityRepository.java"),
            new File("src/test/resources/repository/BankruptcyCommissionerRepository.java"),
            new File("src/test/resources/repository/AccreditationStatementRepository.java"));


    @Test
    @DisplayName("Один метод с семью графами")
    void oneMethodAndSevenGraphs() throws IOException {
        String expected = "{AccreditationStatementEntity=[[\"bankruptcyCommissioner\", \"bankruptcyCommissioner.sro\", \"accreditationStatementType\", \"status\", \"accreditationStatementDocEntitySet.statementDocType\", \"accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\"]]}";

        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(accreditationStatementRepository);

        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
    }

    @Test
    @DisplayName("Ноль методов")
    void zeroMethods() throws IOException {
        String expected = "{}";

        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(archiveEntityRepository);

        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
    }

    @Test
    @DisplayName("Один метод с одинм графом")
    void oneMethodAndOneGraph() throws IOException {
        String expected = "{ArchiveFileEntity=[[\"archiveEntity\"]]}";

        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(archiveFileEntityRepository);

        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
    }

    @Test
    @DisplayName("Три метода с графами")
    void threeMethodWithGraphs() throws IOException {
        String expected = "{BankruptcyCommissionerEntity=[[\"sro\", \"accreditationStatementEntitySet.accreditationStatementType\", \"accreditationStatementEntitySet.status\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\"], [\"sro\", \"accreditationStatementEntitySet.accreditationStatementType\", \"accreditationStatementEntitySet.status\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\"], [\"sro\", \"parentBankruptcyCommissioner.sro\"]]}";

        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(bankruptcyCommissionerRepository);

        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
    }

    @Test
    @DisplayName("Несколько файлов")
    void someFiles() throws IOException {
        String expected = "{AccreditationStatementEntity=[[\"bankruptcyCommissioner\", \"bankruptcyCommissioner.sro\", \"accreditationStatementType\", \"status\", \"accreditationStatementDocEntitySet.statementDocType\", \"accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\"]], BankruptcyCommissionerEntity=[[\"sro\", \"accreditationStatementEntitySet.accreditationStatementType\", \"accreditationStatementEntitySet.status\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\"], [\"sro\", \"accreditationStatementEntitySet.accreditationStatementType\", \"accreditationStatementEntitySet.status\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\"], [\"sro\", \"parentBankruptcyCommissioner.sro\"]]}";

        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(threeRepositories);

        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
    }

    @Test
    @DisplayName("Проверка конвертации обьекта в сет строк")
    void changeGraphsToListTest() throws IOException {
        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(threeRepositories);
        String expected = "[accreditationStatementType, accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType, sro, accreditationStatementEntitySet.status, bankruptcyCommissioner, accreditationStatementEntitySet.accreditationStatementDocEntitySet.accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature, accreditationStatementDocEntitySet.accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature, parentBankruptcyCommissioner.sro, bankruptcyCommissioner.sro, status, accreditationStatementDocEntitySet.statementDocType, accreditationStatementEntitySet.accreditationStatementType]";

        List<Object> accreditationStatementEntity = entitiesAndTheirGraphsFromRepository.get("AccreditationStatementEntity");
        accreditationStatementEntity.addAll(entitiesAndTheirGraphsFromRepository.get("BankruptcyCommissionerEntity"));

        Set<String> strings = EntityGraphProcessor.changeGraphsToList(accreditationStatementEntity);

        assertEquals(expected, strings.toString());
    }

    @Test
    @DisplayName("Проверка получения финальной мапы сущностей и энтити графов")
    void getEntitiesAndTheirGraphsTest() throws IOException {
        String expected = "{AccreditationStatementEntity=[accreditationStatementType, bankruptcyCommissioner, accreditationStatementDocEntitySet.accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature, bankruptcyCommissioner.sro, status, accreditationStatementDocEntitySet.statementDocType], BankruptcyCommissionerEntity=[accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType, sro, accreditationStatementEntitySet.status, accreditationStatementEntitySet.accreditationStatementDocEntitySet.accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature, parentBankruptcyCommissioner.sro, accreditationStatementEntitySet.accreditationStatementType]}";

        HashMap<String, Set<String>> entitiesAndTheirGraphs = EntityGraphProcessor.getEntitiesAndTheirGraphs(threeRepositories, List.of());

        assertEquals(expected, entitiesAndTheirGraphs.toString());
    }

}