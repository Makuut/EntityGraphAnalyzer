import org.springframework.data.jpa.repository.EntityGraph;

public interface FundingAgreementRepository extends SoftDeleteRepository<FundingAgreementEntity, Integer> {

    @EntityGraph(attributePaths = {"status",
            "fundingAgreementProcessEntityTreeSet.processType",
            "fundingAgreementProcessEntityTreeSet.processStatus",
            "fundingAgreementProcessEntityTreeSet.attachment.signatureAttachments.signature",
            "fundingAgreementProcessEntityTreeSet.attachment.signatureAttachments.stampedAttachment"})
    Optional<FundingAgreementEntity> findFetchById(@NotNull Integer id);

    @Query(value = "from FundingAgreementEntity f where :statusId = f.status.id " +
            "and f.limitationDt < now()")
    List<FundingAgreementEntity> findByStatusId(@Param("statusId") Integer agreementStatusId);
}
