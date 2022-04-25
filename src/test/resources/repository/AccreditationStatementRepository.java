import org.springframework.data.jpa.repository.EntityGraph;

public interface AccreditationStatementRepository extends SoftDeleteRepository<AccreditationStatementEntity, Integer> {
    @Query(nativeQuery = true)
    Integer findNextNumber();

    @EntityGraph(attributePaths = {"bankruptmissioner", "bankruptcyCommissioner.sro", "accreditationStatementType", "status",
            "accreditationStatementDocEntitySet.statementDocType", "accreditationStatementDocEntitySet" +
            ".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature"})
    Optional<AccreditationStatementEntity> findFetchById(Integer id);
}
