package org.makuut;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityGraphProcessorFromRepositoryTest {

//    File accreditationStatementRepository = new File("src/test/resources/for_plugin/repository/AccreditationStatementRepository.java");
//    File archiveEntityRepository = new File("src/test/resources/for_plugin/repository/ArchiveEntityRepository.java");
//    File archiveFileEntityRepository = new File("src/test/resources/for_plugin/repository/ArchiveFileEntityRepository.java");
//    File archiveFileEntityRepository = new File("src/test/resources/for_plugin/repository/ArchiveFileEntityRepository.java");


//    @Test
//    @DisplayName("Один метод с семью графами")
//    void oneMethodAndSevenGraphs() throws IOException {
//        String expected = "{AccreditationStatementEntity=[[\"bankruptmissioner\", \"bankruptcyCommissioner.sro\", \"accreditationStatementType\", \"status\", \"accreditationStatementDocEntitySet.statementDocType\", \"accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\"]]}";
//
//        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
//                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(getJavaClass(accreditationStatementRepository));
//
//        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
//    }
//
//    @Test
//    @DisplayName("Ноль методов")
//    void zeroMethods() throws IOException {
//        String expected = "{}";
//
//        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
//                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(getJavaClass(archiveEntityRepository));
//
//        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
//    }
//
//    @Test
//    @DisplayName("Один метод с одинм графом")
//    void oneMethodAndOneGraph() throws IOException {
//        String expected = "{ArchiveFileEntity=[[\"archiveEntity\"]]}";
//
//        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
//                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(getJavaClass(archiveFileEntityRepository));
//
//        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
//    }
//
//    @Test
//    @DisplayName("Три метода с графами")
//    void threeMethodWithGraphs() throws IOException {
//        String expected = "{BankruptcyCommissionerEntity=[[\"sro\", \"accreditationStatementEntitySet.accreditationStatementType\", \"accreditationStatementEntitySet.status\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\"], [\"sro\", \"accreditationStatementEntitySet.accreditationStatementType\", \"accreditationStatementEntitySet.status\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType\", \"accreditationStatementEntitySet.accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\"], [\"sro\", \"parentBankruptcyCommissioner.sro\"]]}";
//
//        HashMap<String, List<Object>> entitiesAndTheirGraphsFromRepository =
//                EntityGraphProcessor.getEntitiesAndTheirGraphsFromRepository(getJavaClass(bankruptcyCommissionerRepository));
//
//        assertEquals(expected, entitiesAndTheirGraphsFromRepository.toString());
//    }
//
//    private static List<JavaClass> getJavaClass(File file) throws IOException {
//        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
//        JavaSource src = projectBuilder.addSource(file);
//        return src.getClasses();
//    }


}