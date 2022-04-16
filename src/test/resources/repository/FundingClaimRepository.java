import org.springframework.data.jpa.repository.EntityGraph;

public interface FundingClaimRepository extends SoftDeleteRepository<FundingClaimEntity, Integer> {
    Optional<FundingClaimEntity> findTopByOrderByCreatedDttmDesc();

    @EntityGraph(attributePaths = {"actType",
            "fundingApplication.developer.bankruptcyCommissioner", "status", "fundingType", "involvedPersonType",
            "fundingClaimAttachments.fundingClaimDocType",
            "fundingClaimAttachments.attachment.signatureAttachments.signature"})
    Optional<FundingClaimEntity> findOne(Predicate fundingClaimPredicate);

    @EntityGraph(attributePaths = {"actType", "fundingApplication.status",
            "fundingApplication.fundingApplicationDocs.fundingApplicationDocAttachments.attachment.signatureAttachments.signature",
            "fundingApplication.fundingApplicationDocs.fundingApplicationDocType",
            "status", "fundingClaimAttachments.fundingClaimDocType", "fundingType", "involvedPersonType",
            "fundingClaimAttachments.attachment.signatureAttachments.signature"})
    Optional<FundingClaimEntity> findFetchById(Integer id);
}
