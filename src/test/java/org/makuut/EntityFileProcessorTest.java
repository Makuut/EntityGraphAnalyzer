package org.makuut;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityFileProcessorTest {

    List<File> accreditationStatementDocAttachmentEntity = List.of(new File("src/test/resources/entity/AccreditationStatementDocAttachmentEntity.java"),
            new File("src/test/resources/entity/BaseAttachmentKeyEntity.java"),
            new File("src/test/resources/entity/BaseEntity.java"));
    List<File> accreditationStatementDocEntity = List.of(new File("src/test/resources/entity/AccreditationStatementDocEntity.java"),
            new File("src/test/resources/entity/BaseAttachmentKeyEntity.java"),
            new File("src/test/resources/entity/BaseEntity.java"));
    List<File> accreditationStatementEntity = List.of(new File("src/test/resources/entity/AccreditationStatementEntity.java"),
            new File("src/test/resources/entity/BaseAttachmentKeyEntity.java"),
            new File("src/test/resources/entity/BaseEntity.java"));
    List<File> attachmentEntity = List.of(new File("src/test/resources/entity/AttachmentEntity.java"),
            new File("src/test/resources/entity/BaseAttachmentKeyEntity.java"),
            new File("src/test/resources/entity/BaseEntity.java"));
    List<File> baseEntity = List.of(new File("src/test/resources/entity/BaseEntity.java"));
    List<File> threeEntities = List.of(new File("src/test/resources/entity/AccreditationStatementEntity.java"),
            new File("src/test/resources/entity/AttachmentEntity.java"),
            new File("src/test/resources/entity/BaseEntity.java"),
            new File("src/test/resources/entity/BaseAttachmentKeyEntity.java"),
            new File("src/test/resources/entity/BaseEntity.java"));

    private static final String ENTITY_POSTFIX = StringUtils.getEntityClassPattern(".*Entity.java");

    @Test
    @DisplayName("Одно поле сущности")
    void oneEntity() throws IOException {
        String expected = "{BaseAttachmentKeyEntity={attachment=AttachmentEntity}, BaseEntity={}, AccreditationStatementDocAttachmentEntity={attachment=AttachmentEntity, accreditationStatementDoc=AccreditationStatementDocEntity}}";
        HashMap<String, HashMap<String, String>> entitiesAndTheirField = EntityFileProcessor
                .getEntitiesAndTheirField(accreditationStatementDocAttachmentEntity, ENTITY_POSTFIX);

        assertEquals(expected, entitiesAndTheirField.toString());
    }

    @Test
    @DisplayName("Два поля сущности и одна коллекция")
    void twoEntitiesAndOneSet() throws IOException {
        String expected = "{AccreditationStatementDocEntity={accreditationStatement=AccreditationStatementEntity, statementDocType=StatementDocTypeEntity, accreditationStatementDocAttachmentEntitySet=AccreditationStatementDocAttachmentEntity}, BaseAttachmentKeyEntity={attachment=AttachmentEntity}, BaseEntity={}}";
        HashMap<String, HashMap<String, String>> entitiesAndTheirField = EntityFileProcessor
                .getEntitiesAndTheirField(accreditationStatementDocEntity, ENTITY_POSTFIX);

        assertEquals(expected, entitiesAndTheirField.toString());
    }

    @Test
    @DisplayName("Три поля сущности и одна коллекция")
    void threeEntitiesAndOneSet() throws IOException {
        String expected = "{AccreditationStatementEntity={accreditationStatementType=AccreditationStatementTypeEntity, accreditationStatementDocEntitySet=AccreditationStatementDocEntity, bankruptcyCommissioner=BankruptcyCommissionerEntity, status=AccreditationStatementStatusEntity}, BaseAttachmentKeyEntity={attachment=AttachmentEntity}, BaseEntity={}}";

        HashMap<String, HashMap<String, String>> entitiesAndTheirField = EntityFileProcessor
                .getEntitiesAndTheirField(accreditationStatementEntity, ENTITY_POSTFIX);

        assertEquals(expected, entitiesAndTheirField.toString());
    }

    @Test
    @DisplayName("Два поля сущности и тринадцать коллекций")
    void twoEntitiesAndThirteenSets() throws IOException {
        String expected = "{BaseAttachmentKeyEntity={attachment=AttachmentEntity}, AttachmentEntity={stampedAttachments=SignatureAttachmentEntity, fundingApplicationDocAttachments=FundingApplicationDocAttachmentEntity, signature=AttachmentEntity, signatureAttachments=SignatureAttachmentEntity, loanAgreementAttachments=LoanAgreementAttachmentEntity, loanAgreementAdditionalAttachments=LoanAgreementAdditionalAttachmentEntity, reasonDocAttachments=ReasonDocAttachmentEntity, signatures=SignatureAttachmentEntity, fundingClaimAttachments=FundingClaimAttachmentEntity, rightsConcessionAttachments=RightsConcessionAttachmentEntity, submittingDocAttachments=SubmittingDocAttachmentEntity, participantAttachments=ParticipantAttachmentEntity, participantDemandCorrectionAttachments=ParticipantDemandCorrectionAttachmentEntity, demandParameterAttachments=DemandParameterAttachmentEntity, fundingAgreementProcessEntity=FundingAgreementProcessEntity}, BaseEntity={}}";

        HashMap<String, HashMap<String, String>> entitiesAndTheirField = EntityFileProcessor
                .getEntitiesAndTheirField(attachmentEntity, ENTITY_POSTFIX);

        assertEquals(expected, entitiesAndTheirField.toString());
    }

    @Test
    @DisplayName("Ноль полей сущности и ноль коллекций")
    void zeroEntityAndZeroSet() throws IOException {
        String expected = "{BaseEntity={}}";

        HashMap<String, HashMap<String, String>> entitiesAndTheirField = EntityFileProcessor
                .getEntitiesAndTheirField(baseEntity, ENTITY_POSTFIX);

        assertEquals(expected, entitiesAndTheirField.toString());
    }

    @Test
    @DisplayName("Три сущности")
    void threeEntities() throws IOException {
        String expected = "{AccreditationStatementEntity={accreditationStatementType=AccreditationStatementTypeEntity, accreditationStatementDocEntitySet=AccreditationStatementDocEntity, bankruptcyCommissioner=BankruptcyCommissionerEntity, status=AccreditationStatementStatusEntity}, BaseAttachmentKeyEntity={attachment=AttachmentEntity}, AttachmentEntity={stampedAttachments=SignatureAttachmentEntity, fundingApplicationDocAttachments=FundingApplicationDocAttachmentEntity, signature=AttachmentEntity, signatureAttachments=SignatureAttachmentEntity, loanAgreementAttachments=LoanAgreementAttachmentEntity, loanAgreementAdditionalAttachments=LoanAgreementAdditionalAttachmentEntity, reasonDocAttachments=ReasonDocAttachmentEntity, signatures=SignatureAttachmentEntity, fundingClaimAttachments=FundingClaimAttachmentEntity, rightsConcessionAttachments=RightsConcessionAttachmentEntity, submittingDocAttachments=SubmittingDocAttachmentEntity, participantAttachments=ParticipantAttachmentEntity, participantDemandCorrectionAttachments=ParticipantDemandCorrectionAttachmentEntity, demandParameterAttachments=DemandParameterAttachmentEntity, fundingAgreementProcessEntity=FundingAgreementProcessEntity}, BaseEntity={}}";

        HashMap<String, HashMap<String, String>> entitiesAndTheirField = EntityFileProcessor
                .getEntitiesAndTheirField(threeEntities, ENTITY_POSTFIX);

        assertEquals(expected, entitiesAndTheirField.toString());
    }
}