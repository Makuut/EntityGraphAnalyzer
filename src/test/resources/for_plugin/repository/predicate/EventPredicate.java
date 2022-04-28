@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventPredicate {
    public static BooleanExpression isCommissionerIdmIdEq(UUID idmId) {
        return QEventEntity.eventEntity.bankruptcyCommissioner.idmId.eq(idmId);
    }
    public static BooleanExpression isEventDttmBetween(LocalDateTime fromDt, LocalDateTime toDt) {
        return QEventEntity.eventEntity.eventDttm.between(fromDt, toDt);
    }
}
