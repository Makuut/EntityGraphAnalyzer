import org.springframework.data.jpa.repository.EntityGraph;

public interface BankruptcyCommissionerRepository extends SoftDeleteRepository<BankruptcyCommissionerEntity, UUID> {
    @EntityGraph(attributePaths = {"sro",
            "accreditationStatementEntitySet.accreditationStatementType",
            "accreditationStatementEntitySet.status",
            "accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType",
            "accreditationStatementEntitySet.accreditationStatementDocEntitySet" +
                    ".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature"})
    Optional<BankruptcyCommissionerEntity> findByIdmId(UUID idmId);

    @EntityGraph(attributePaths = {"sro",
            "accreditationStatementEntitySet.accreditationStatementType",
            "accreditationStatementEntitySet.status",
            "accreditationStatementEntitySet.accreditationStatementDocEntitySet.statementDocType",
            "accreditationStatementEntitySet.accreditationStatementDocEntitySet" +
                    ".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature"})
    Optional<BankruptcyCommissionerEntity> findBySnils(String snils);

    @EntityGraph(attributePaths = {"sro", "parentBankruptcyCommissioner.sro"})
    Optional<BankruptcyCommissionerEntity> findOneByIdmId(UUID idmId);
}
