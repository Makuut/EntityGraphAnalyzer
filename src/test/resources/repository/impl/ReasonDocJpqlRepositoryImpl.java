@Repository
@RequiredArgsConstructor
public class ReasonDocJpqlRepositoryImpl implements ReasonDocJpqlRepository {

    @Autowired
    private final EntityManager entityManager;

    @Override
    public boolean isPriceMatch(Integer reasonDocId) {
        QReasonDocEntity qReasonDocEntity = QReasonDocEntity.reasonDocEntity;
        QFlatEntity qFlatEntity = QFlatEntity.flatEntity;
        QParticipantDemandEntity qParticipantDemandEntity = QParticipantDemandEntity.participantDemandEntity;

        var jPQLQuery = new JPAQuery<>(entityManager);
        return BooleanUtils.isTrue(jPQLQuery
                .select(qReasonDocEntity.contractPrice.eq(qFlatEntity.reasonDocPrice.sum()))
                .from(qReasonDocEntity)
                .join(qParticipantDemandEntity).on(qReasonDocEntity.id.eq(qParticipantDemandEntity.reasonDoc.id))
                .join(qFlatEntity).on(qParticipantDemandEntity.id.eq(qFlatEntity.id))
                .where(qReasonDocEntity.id.eq(reasonDocId))
                .where(qParticipantDemandEntity.deletedDttm.isNull())
                .where(qFlatEntity.deletedDttm.isNull())
                .groupBy(qReasonDocEntity.id)
                .fetchFirst());
    }
}
