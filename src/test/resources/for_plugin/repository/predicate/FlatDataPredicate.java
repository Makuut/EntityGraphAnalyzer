@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FlatDataPredicate {

    public static Predicate isBuildingIdEq(Integer buildingId) {
        return QFlatDataEntity.flatDataEntity.flatography.id.eq(buildingId);
    }

    public static Predicate isNumberEq(String constructionNumber) {
        return QFlatDataEntity.flatDataEntity.constructionNumber.equalsIgnoreCase(constructionNumber);
    }

    public static Predicate isFloorEq(Integer floor) {
        return QFlatDataEntity.flatDataEntity.floor.eq(floor);
    }

    public static Predicate isEntranceEq(String entrance) {
        return QFlatDataEntity.flatDataEntity.entrance.equalsIgnoreCase(entrance);
    }

    public static Predicate isFlatStatusEq(FlatStatusEnum flatStatus) {
        return QFlatDataEntity.flatDataEntity.flatStatus.code.equalsIgnoreCase(flatStatus.getCode());
    }

    public static Predicate isFlatTypeEq(FlatTypeEnum flatType) {
        return QFlatDataEntity.flatDataEntity.flatType.code.equalsIgnoreCase(flatType.getCode());
    }

    public static Predicate isFlatKindEq(FlatKindEnum flatKind) {
        return QFlatDataEntity.flatDataEntity.flatKind.code.equalsIgnoreCase(flatKind.getCode());
    }

    public static Predicate isFlatRoomTypeEq(FlatRoomTypeEnum flatRoomType) {
        return QFlatDataEntity.flatDataEntity.flatRoomType.code.equalsIgnoreCase(flatRoomType.getCode());
    }

    public static Predicate isIncompleteFieldsOnly() {
        return QFlatDataEntity.flatDataEntity.completePercent.eq(FlatDataEntity.REQUIRED_COMPLETE_PERCENT).not();
    }

    public static Predicate isIdNotEq(Integer id) {
        return QFlatDataEntity.flatDataEntity.id.eq(id).not();
    }

    public static Predicate isIdEq(Integer id) {
        return QFlatDataEntity.flatDataEntity.id.eq(id);
    }


    public static Predicate isDeveloperIdEq(Integer developerId) {
        return QFlatDataEntity.flatDataEntity.flatography.buildingObject.developer.id.eq(developerId);
    }
}
