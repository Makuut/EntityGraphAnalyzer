@Service
@RequiredArgsConstructor
public class ParticipantDemandJpqlRepositoryImpl implements ParticipantDemandJpqlRepository, Sortable {
    private final EntityManager entityManager;

    @Override
    public Optional<ParticipantDemandEntity> findOne(ParticipantDemandCriteria criteria) {
        QParticipantDemandEntity qParticipantDemandEntity = QParticipantDemandEntity.participantDemandEntity;
        QFlatEntity qFlatEntity = QFlatEntity.flatEntity;
        QBuildingObjectEntity qBuildingObjectEntity = QBuildingObjectEntity.buildingObjectEntity;
        QDeveloperEntity qDeveloperEntity = QDeveloperEntity.developerEntity;
        QLoanAgreementEntity qLoanAgreementEntity = QLoanAgreementEntity.loanAgreementEntity;
        QLoanAgreementAdditionalEntity qLoanAgreementAdditionalEntity = QLoanAgreementAdditionalEntity.loanAgreementAdditionalEntity;
        QRightsConcessionEntity rightsConcessionEntity = QRightsConcessionEntity.rightsConcessionEntity;

        JPAQuery<ParticipantDemandEntity> participantDemandEntityJPAQuery = new JPAQuery<>(this.entityManager);

        return Optional.ofNullable(participantDemandEntityJPAQuery.select(qParticipantDemandEntity)
                .from(qParticipantDemandEntity)
                .leftJoin(qFlatEntity).on(qParticipantDemandEntity.flat.id.eq(qFlatEntity.id))
                .leftJoin(qParticipantDemandEntity.loanAgreementEntitySet, qLoanAgreementEntity)
                .leftJoin(qParticipantDemandEntity.loanAgreementAdditionalEntitySet, qLoanAgreementAdditionalEntity)
                .leftJoin(qParticipantDemandEntity.rightsConcessionEntitySet, rightsConcessionEntity)
                .leftJoin(qBuildingObjectEntity).on(qParticipantDemandEntity.buildingObject.id.eq(qBuildingObjectEntity.id))
                .leftJoin(qDeveloperEntity).on(qBuildingObjectEntity.developer.id.eq(qDeveloperEntity.id))
                .where(ParticipantDemandPredicateBuilder.build(criteria))
                .fetchOne());
    }

    @Override
    public QueryResults<ParticipantDemandEntity> findAll(ParticipantDemandCriteria criteria) {
        QParticipantDemandEntity qParticipantDemandEntity = QParticipantDemandEntity.participantDemandEntity;
        QParticipantDemandStatusEntity qParticipantDemandStatusEntity = QParticipantDemandStatusEntity.participantDemandStatusEntity;
        QFlatEntity qFlatEntity = QFlatEntity.flatEntity;
        QFlatTypeEntity qFlatTypeEntity = QFlatTypeEntity.flatTypeEntity;
        QReasonDocEntity qReasonDocEntity = QReasonDocEntity.reasonDocEntity;

        JPAQuery<ParticipantDemandEntity> participantDemandEntityJPAQuery = new JPAQuery<>(this.entityManager)
                .select(qParticipantDemandEntity)
                .from(qParticipantDemandEntity)
                .join(qParticipantDemandEntity.flat, qFlatEntity).fetchJoin()
                .leftJoin(qParticipantDemandEntity.status, qParticipantDemandStatusEntity).fetchJoin()
                .leftJoin(qFlatEntity.flatType, qFlatTypeEntity).fetchJoin()
                .leftJoin(qParticipantDemandEntity.reasonDoc, qReasonDocEntity).fetchJoin()
                .where(ParticipantDemandPredicateBuilder.build(criteria))
                .offset(criteria.getOffset())
                .limit(criteria.getPageSize());

        QueryUtils.sortQueryResult(criteria, participantDemandEntityJPAQuery, getSortParameters());

        return participantDemandEntityJPAQuery.fetchResults();
    }

    @Override
    public <U extends Comparable<?>> Map<String, ComparableExpressionBase<U>> getSortParameters() {
        Map<String, ComparableExpressionBase<U>> sortProperties = new HashMap<>();

        sortProperties.put("participantDemandStatus", (ComparableExpressionBase<U>) QParticipantDemandStatusEntity.participantDemandStatusEntity.name);
        sortProperties.put("participantDemandNumber", (ComparableExpressionBase<U>) QParticipantDemandEntity.participantDemandEntity.participantDemandNumber);
        sortProperties.put("updatedDttm", (ComparableExpressionBase<U>) QParticipantDemandEntity.participantDemandEntity.updatedDttm);

        return sortProperties;
    }
}
