@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildingObjectViewInfoPredicate {

    public static BooleanExpression isIdOrAddressEq(String searchText) {
        var buildingObjectInfoViewEntity = QBuildingObjectInfoViewEntity.buildingObjectInfoViewEntity;

        return buildingObjectInfoViewEntity.id.like(searchText)
                .or(buildingObjectInfoViewEntity.buildingObjectAddress.containsIgnoreCase(searchText));
    }

    public static BooleanExpression isDeveloperIdEq(Integer developerId) {
        return QBuildingObjectInfoViewEntity.buildingObjectInfoViewEntity.developerId.eq(developerId);
    }
}
