import org.springframework.data.jpa.repository.EntityGraph;

public interface DeveloperRepository extends SoftDeleteRepository<DeveloperEntity, Integer> {

    @Override
    @EntityGraph(attributePaths = {"creditorDemand.status", "region",
            "buildingObjects.demandEntities.flat",
            "buildingObjects.submittingDocEntities",
            "nonDisclosure", "nonDisclosure.status", "nonDisclosure.attachment",
            "fundingAgreement.status",
            "fundingAgreement.fundingAgreementProcessEntityTreeSet.processType",
            "fundingAgreement.fundingAgreementProcessEntityTreeSet.processStatus",
            "fundingAgreement.fundingAgreementProcessEntityTreeSet.attachment.signatureAttachments.signature"})
    Optional<DeveloperEntity> findById(Integer id);

    @EntityGraph(attributePaths = {"creditorDemand.status", "region",
            "buildingObjects.demandEntities.flat",
            "buildingObjects.submittingDocEntities"})
    Optional<DeveloperEntity> findByIdAndAndBankruptcyCommissioner_IdmId(Integer id, UUID idmId);

    Optional<DeveloperEntity> findByInn(String inn);

    @EntityGraph(attributePaths = {
            "region",
            "buildingObjects",
            "bankruptcyCommissioner.sro",
            "fundingApplication.status",
            "fundingApplication.fundingApplicationAmounts.fundingType",
            "fundingApplication.fundingApplicationAmounts.involvedPersonType",
            "fundingApplication.otherExpenses.budgetExpenseGroup",
            "fundingApplication.fundingApplicationDocs.fundingApplicationDocType",
            "fundingApplication.fundingApplicationDocs.fundingApplicationDocAttachments.attachment.signatureAttachments.signature",
            "bankruptcyCommissioner.accreditationStatementEntitySet.accreditationStatementType",
            "bankruptcyCommissioner.accreditationStatementEntitySet.status"})
    Optional<DeveloperEntity> findOneById(Integer id);
}
