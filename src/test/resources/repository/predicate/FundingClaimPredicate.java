@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FundingClaimPredicate {

       public static BooleanExpression isStatusIdEq(Integer fundingClaimStatusId) {
        return QFundingClaimEntity.fundingClaimEntity.status.id.eq(fundingClaimStatusId);
    }

    public static BooleanExpression isIdOrAddressEq(String searchText) {
        return BuildingObjectPredicate
                .isIdOrAddressEq(QFundingClaimEntity.fundingClaimEntity.fundingApplication.developer.buildingObjects.any(), searchText);
    }

    public static BooleanExpression isDeveloperIdEq(Integer developerId) {
        return QFundingClaimEntity.fundingClaimEntity.fundingApplication.developer.id.eq(developerId);
    }

    public static BooleanExpression isIdEq(Integer fundingClaimId) {
        return QFundingClaimEntity.fundingClaimEntity.id.eq(fundingClaimId);
    }

    public static BooleanExpression isFundingNumberOrTypeNameLike(String searchText) {
        return QFundingClaimEntity.fundingClaimEntity.fundingClaimNumber
                .concat(QFundingClaimEntity.fundingClaimEntity.fundingType.name)
                .containsIgnoreCase(searchText);
    }
}
