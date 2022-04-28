public interface FundingClaimJpqlRepository {
    Optional<FundingClaimEntity> findDraftByIds(FundingClaimCriteria criteria);

    QueryResults<FundingClaimEntity> findAll(FundingClaimCriteria criteria);
}
