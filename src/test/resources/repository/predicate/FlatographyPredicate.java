@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FlatographyPredicate {

    public static BooleanExpression isIdEq(Integer id) {
        return QFlatographyEntity.flatographyEntity.id.eq(id);
    }

    public static BooleanExpression isDeveloperIdEq(Integer developerId) {
        return QFlatographyEntity.flatographyEntity.buildingObject.developer.id.eq(developerId);
    }

    public static BooleanExpression isStatusIdEq(Integer flatographyStatusId) {
        return QFlatographyEntity.flatographyEntity.status.id.eq(flatographyStatusId);
    }

    public static BooleanExpression isIdOrAddressEq(String searchText) {
        return BuildingObjectPredicate.isIdOrAddressEq(QFlatographyEntity.flatographyEntity.buildingObject, searchText);
    }
}
