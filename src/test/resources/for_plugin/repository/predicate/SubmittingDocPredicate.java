@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubmittingDocPredicate {

    public static BooleanExpression isDeveloperIdEq(Integer developerId) {
        return QSubmittingDocEntity.submittingDocEntity.buildingObject.developer.id.eq(developerId);
    }

    public static BooleanExpression isStatusIdEq(Integer statusId) {
        return QSubmittingDocEntity.submittingDocEntity.status.id.eq(statusId);
    }

    public static BooleanExpression isStatusIdIn(List<SubmittingDocStatusEnum> docStatuses) {
        return QSubmittingDocEntity.submittingDocEntity.status.id
                .in(docStatuses.stream()
                        .map(SubmittingDocStatusEnum::getId)
                        .collect(Collectors.toList()));
    }

    public static BooleanExpression isDocTypeCodesIn(List<String> docStatuses) {
        return QSubmittingDocEntity.submittingDocEntity.submittingDocType.code.in(docStatuses);
    }

    public static BooleanExpression isBuildingIdEq(Integer buildingId) {
        return QSubmittingDocEntity.submittingDocEntity.buildingObject.id.eq(buildingId);
    }

    public static BooleanExpression isBuildingIdIn(Integer[] buildingIds) {
        return QSubmittingDocEntity.submittingDocEntity.buildingObject.id.in(buildingIds);
    }

    public static BooleanExpression isIdEq(Integer id) {
        return QSubmittingDocEntity.submittingDocEntity.id.eq(id);
    }

    public static BooleanExpression isIdNotEq(Integer id) {
        return QSubmittingDocEntity.submittingDocEntity.id.eq(id).not();
    }

    public static BooleanExpression isIdOrAddressOrSubmittingDocTypeEq(String searchText) {
        return BuildingObjectPredicate.isIdOrAddressEq(QSubmittingDocEntity.submittingDocEntity.buildingObject, searchText)
                .or(QSubmittingDocEntity.submittingDocEntity.submittingDocType.name.containsIgnoreCase(searchText));
    }
}
