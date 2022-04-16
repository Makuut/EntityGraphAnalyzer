@Repository
@RequiredArgsConstructor
public class FundingClaimJpqlRepositoryImpl implements FundingClaimJpqlRepository, Sortable {
    private final EntityManager entityManager;

    @Override
    public Optional<FundingClaimEntity> findDraftByIds(FundingClaimCriteria criteria) {
        QFundingClaimEntity qFundingClaimEntity = QFundingClaimEntity.fundingClaimEntity;
        QFundingApplicationEntity qFundingApplicationEntity = QFundingApplicationEntity.fundingApplicationEntity;
        QFundingClaimStatusEntity qFundingClaimStatusEntity = QFundingClaimStatusEntity.fundingClaimStatusEntity;

        JPQLQuery<FundingClaimEntity> fundingClaimEntityJPAQuery = new JPAQuery<>(this.entityManager);

        return Optional.ofNullable(fundingClaimEntityJPAQuery
                .select(qFundingClaimEntity)
                .from(qFundingClaimEntity)
                .leftJoin(qFundingClaimEntity.status, qFundingClaimStatusEntity)
                .leftJoin(qFundingClaimEntity.fundingApplication, qFundingApplicationEntity)
                .where(qFundingClaimEntity.status.id.eq(FundingClaimStatusEnum.DRAFT.getId())
                        .and(FundingClaimPredicateConstructor.build(criteria)))
                .fetchOne());
    }

    @Override
    public QueryResults<FundingClaimEntity> findAll(FundingClaimCriteria criteria) {
        QFundingClaimEntity qFundingClaimEntity = QFundingClaimEntity.fundingClaimEntity;
        JPQLQuery<FundingClaimEntity> fundingClaimEntityJPAQuery = new JPAQuery<>(this.entityManager)
                .select(qFundingClaimEntity)
                .from(qFundingClaimEntity)
                .leftJoin(qFundingClaimEntity.status, QFundingClaimStatusEntity.fundingClaimStatusEntity).fetchJoin()
                .leftJoin(qFundingClaimEntity.fundingType, QFundingTypeEntity.fundingTypeEntity).fetchJoin()
                .where(FundingClaimPredicateConstructor.build(criteria))
                .offset(criteria.getOffset())
                .limit(criteria.getPageSize());

        QueryUtils.sortQueryResult(criteria, fundingClaimEntityJPAQuery, getSortParameters());

        return fundingClaimEntityJPAQuery.fetchResults();
    }

    @Override
    public <U extends Comparable<?>> Map<String, ComparableExpressionBase<U>> getSortParameters() {
        var qFundingClaimEntity = QFundingClaimEntity.fundingClaimEntity;
        Map<String, ComparableExpressionBase<U>> sortProperties = new HashMap<>();

        sortProperties.put("lastSentStatusDt", (ComparableExpressionBase<U>) qFundingClaimEntity.sendDttm);
        sortProperties.put("fundingClaimStatus", (ComparableExpressionBase<U>) qFundingClaimEntity.status.name);
        sortProperties.put("paymentDt", (ComparableExpressionBase<U>) qFundingClaimEntity.paymentDt);

        return sortProperties;
    }
}