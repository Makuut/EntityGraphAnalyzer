@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IrdPredicate {

    public static BooleanExpression isIdEq(Integer id) {
        return QIrdEntity.irdEntity.id.eq(id);
    }

    public static BooleanExpression isFlatographyIdEq(Integer flatographyId) {
        return QIrdEntity.irdEntity.flatography.id.eq(flatographyId);
    }

    public static BooleanExpression isDeveloperIdEq(Integer developerId) {
        return QIrdEntity.irdEntity.flatography.buildingObject.developer.id.eq(developerId);
    }
}
