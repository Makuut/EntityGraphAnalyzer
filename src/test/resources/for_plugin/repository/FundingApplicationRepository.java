import org.springframework.data.jpa.repository.EntityGraph;

public interface FundingApplicationRepository extends SoftDeleteRepository<FundingApplicationEntity, Integer> {

    @NonNull
    @EntityGraph(attributePaths = {"fundingApplicationDocs.fundingApplicationDocType"})
    Optional<FundingApplicationEntity> findOne(@NonNull Predicate fundingApplicationPredicate);

    @NonNull
    @EntityGraph(attributePaths = {"developer.bankruptcyCommissioner.sro", "otherExpenses.budgetExpenseGroup",
            "developer.buildingObjects", "developer.region", "status",
            "fundingApplicationDocs.fundingApplicationDocType",
            "fundingApplicationDocs.fundingApplicationDocAttachments.attachment.signatureAttachments.signature",
            "fundingApplicationAmounts.fundingType.budgetExpenseGroup", "fundingApplicationAmounts.involvedPersonType"
    })
    Optional<FundingApplicationEntity> findFetchById(@NonNull Integer developerId);
}
