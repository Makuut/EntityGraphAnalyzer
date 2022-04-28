@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipantDemandPredicate {

    public static BooleanExpression isParticipantDemandIdEq(Integer participantDemandId) {
        return QParticipantDemandEntity.participantDemandEntity.id.eq(participantDemandId);
    }

    public static BooleanExpression isBuildingObjectIdEq(Integer buildingObjectId) {
        return QParticipantDemandEntity.participantDemandEntity.buildingObject.id.eq(buildingObjectId);
    }

    public static BooleanExpression isDeveloperIdEq(Integer developerId) {
        return QParticipantDemandEntity.participantDemandEntity.buildingObject.developer.id.eq(developerId);
    }

    public static BooleanExpression isStatusIdEq(Integer statusId) {
        return QParticipantDemandEntity.participantDemandEntity.status.id.eq(statusId);
    }

    public static BooleanExpression isUpdatedDttmBetween(LocalDateTime beginDttm, LocalDateTime endDttm) {
        return QParticipantDemandEntity.participantDemandEntity.updatedDttm.between(beginDttm, endDttm);
    }

    public static Predicate isStatusIdsIn(Integer[] statusIds) {
        return QParticipantDemandEntity.participantDemandEntity.status.id.in(statusIds);
    }

    public static Predicate isDemandIdIn(Integer[] demandIds) {
        return QParticipantDemandEntity.participantDemandEntity.id.in(demandIds);
    }

    public static Predicate isReasonDocIdEq(Integer reasonDocId) {
        return QParticipantDemandEntity.participantDemandEntity.reasonDoc.id.in(reasonDocId);
    }

    public static Predicate isParticipantIdIn(Integer[] participantIds) {
        return QParticipantDemandEntity.participantDemandEntity.participantFlats
                .any().participant.id.in(participantIds);
    }

    public static Predicate isVersionEq(Integer version) {
        return QParticipantDemandEntity.participantDemandEntity.version.eq(version);
    }

    public static Predicate isIdCrmEq(String idCrm) {
        return QParticipantDemandEntity.participantDemandEntity.idCrm.equalsIgnoreCase(idCrm);
    }

}
