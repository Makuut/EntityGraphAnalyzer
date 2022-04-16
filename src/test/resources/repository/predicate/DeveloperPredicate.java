@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeveloperPredicate {

    public static <Q extends QBaseDeveloperEntity> BooleanExpression isNameContains(String name, Q qEntity) {
        return qEntity.fullName.concat(qEntity.shortName).containsIgnoreCase(name);
    }

    public static <Q extends QBaseDeveloperEntity> BooleanExpression isInnEq(String inn, Q qEntity) {
        return qEntity.inn.eq(inn);
    }

    public static BooleanExpression isIdEq(Integer id) {
        return QDeveloperEntity.developerEntity.id.eq(id);
    }

    public static <Q extends QBaseDeveloperEntity> BooleanExpression isCommissionerIdmIdEq(UUID id, Q qEntity) {
        return qEntity.bankruptcyCommissioner.idmId.eq(id);
    }
}
