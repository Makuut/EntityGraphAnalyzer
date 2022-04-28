
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipantDemandOrReasonDocPredicate {

    public static BooleanExpression isNameOrDoc(String param) {
        var participantDemandEntity = QParticipantDemandEntity.participantDemandEntity;
        var qReasonDocEntity = QParticipantDemandEntity.participantDemandEntity.reasonDoc;
        var qParticipantFlatEntity = participantDemandEntity.participantFlats.any();
        return JPAExpressions.selectOne()
                .from(QParticipantEntity.participantEntity)
                .where(QParticipantEntity.participantEntity.id.eq(qParticipantFlatEntity.participant.id)
                        .and(QParticipantEntity.participantEntity.personName.coalesce("").asString()
                                .concat(QParticipantEntity.participantEntity.personSurname.coalesce(""))
                                .concat(QParticipantEntity.participantEntity.personPatronymic.coalesce(""))
                                .concat(QParticipantEntity.participantEntity.ulName.coalesce(""))
                                .concat(qReasonDocEntity.docNumber.coalesce(""))
                                .containsIgnoreCase(param)))
                .exists();
    }
}
