@Repository
@RequiredArgsConstructor
public class FundingApplicationJpqlRepositoryImpl implements FundingApplicationJpqlRepository {
    private final EntityManager entityManager;

    @Override
    public Tuple findOne(Integer developerId) {
        QFundingApplicationEntity qFundingApplicationEntity = QFundingApplicationEntity.fundingApplicationEntity;
        QDeveloperEntity qDeveloperEntity = QDeveloperEntity.developerEntity;

        JPAQuery<FundingApplicationEntity> fundingApplicationEntityJPAQuery = new JPAQuery<>(this.entityManager);

        return fundingApplicationEntityJPAQuery
                .select(qDeveloperEntity.opfShortName, qDeveloperEntity.shortName, qFundingApplicationEntity.fundComment, qFundingApplicationEntity.status)
                .from(qDeveloperEntity)
                .join(qFundingApplicationEntity).on(qDeveloperEntity.id.eq(qFundingApplicationEntity.id))
                .where(qDeveloperEntity.id.eq(developerId))
                .fetchOne();
    }
}
