@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildingObjectPredicate {

    public static BooleanExpression isIdOrAddressEq(String searchText) {
        return isIdOrAddressEq(QBuildingObjectEntity.buildingObjectEntity, searchText);
    }

    public static BooleanExpression isDeveloperIdEq(Integer developerId) {
        return QBuildingObjectEntity.buildingObjectEntity.developer.id.eq(developerId);
    }

    public static BooleanExpression isIdEq(Integer buildingObjectId) {
        return QBuildingObjectEntity.buildingObjectEntity.id.eq(buildingObjectId);
    }

    public static  BooleanExpression isIdOrAddressEq(QBuildingObjectEntity qBuildingObject, String searchText) {
        return qBuildingObject.id.like(searchText)
                .or(qBuildingObject.address.containsIgnoreCase(searchText));
    }
}
