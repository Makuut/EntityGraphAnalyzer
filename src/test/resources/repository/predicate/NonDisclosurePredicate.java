@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NonDisclosurePredicate {

    public static BooleanExpression isIdEq(Integer id) {
        return QNonDisclosureEntity.nonDisclosureEntity.id.eq(id);
    }

    public static BooleanExpression isStatusIdEq(Integer statusId) {
        return QNonDisclosureEntity.nonDisclosureEntity.status.id.eq(statusId);
    }

    public static BooleanExpression isBefore(LocalDate limitationDt) {
        return QNonDisclosureEntity.nonDisclosureEntity.limitationDt.before(limitationDt);
    }

}
