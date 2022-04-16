@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccreditationStatementPredicate {

    public static BooleanExpression isCommissionerIdmIdEq(UUID idmId) {
        return QAccreditationStatementEntity.accreditationStatementEntity.bankruptcyCommissioner.idmId.eq(idmId);
    }

    public static BooleanExpression isStatusIdEq(Integer accreditationStatementStatusId) {
        return QAccreditationStatementEntity.accreditationStatementEntity.status.id.eq(accreditationStatementStatusId);
    }

    public static BooleanExpression isTypeIdEq(Integer accreditationStatementTypeId) {
        return QAccreditationStatementEntity.accreditationStatementEntity.accreditationStatementType.id.eq(accreditationStatementTypeId);
    }

    public static BooleanExpression isSentDttmEq(LocalDateTime sendDttm) {
        var sendDay = sendDttm.withHour(0).withMinute(0).withSecond(0).withNano(0);
        return QAccreditationStatementEntity.accreditationStatementEntity.sendDttm.after(sendDay)
                .and(QAccreditationStatementEntity.accreditationStatementEntity.sendDttm.before(sendDay.plusDays(1)));
    }

    public static BooleanExpression isNumbersContains(Integer number) {
        return QAccreditationStatementEntity.accreditationStatementEntity.number.eq(number)
                .or(QAccreditationStatementEntity.accreditationStatementEntity.incomingNumber.containsIgnoreCase(number.toString()));
    }
}
