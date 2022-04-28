@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RevisePredicate {

    public static Predicate isFlatographyIdEq(Integer flatographyId) {
        return QReviseEntity.reviseEntity.flatography.id.eq(flatographyId);
    }

    public static Predicate isDeveloperIdEq(Integer developerId) {
        return QReviseEntity.reviseEntity.flatography.buildingObject.developer.id.eq(developerId);
    }
}
