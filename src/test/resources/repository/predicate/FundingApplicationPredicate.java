@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FundingApplicationPredicate {

    public static BooleanExpression isIdEq(Integer fundingApplicationId) {
        return QFundingApplicationEntity.fundingApplicationEntity.id.eq(fundingApplicationId);
    }

       public static BooleanExpression isDeveloperIdEq(Integer developerId) {
        return QFundingApplicationEntity.fundingApplicationEntity.developer.id.eq(developerId);
    }
}
