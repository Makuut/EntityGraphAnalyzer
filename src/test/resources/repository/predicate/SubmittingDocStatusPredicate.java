@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubmittingDocStatusPredicate {

       public static Predicate isCodeEqIgnoreCase(SubmittingDocStatusEnum statusEnum) {
        return QSubmittingDocStatusEntity.submittingDocStatusEntity.code.equalsIgnoreCase(statusEnum.getCode());
    }
}
