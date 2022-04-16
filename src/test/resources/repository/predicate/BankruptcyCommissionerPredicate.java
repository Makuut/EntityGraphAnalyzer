@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankruptcyCommissionerPredicate {

    public static Predicate isCommissionerRegNumberEq(String commissionerRegNumber) {
        return QBankruptcyCommissionerEntity.bankruptcyCommissionerEntity.commissionerRegNumber.equalsIgnoreCase(commissionerRegNumber);
    }

    public static Predicate isSnilsEq(String snils) {
        return QBankruptcyCommissionerEntity.bankruptcyCommissionerEntity.snils.equalsIgnoreCase(snils);
    }

    public static Predicate isIdmIdEq(UUID idmId) {
        return QBankruptcyCommissionerEntity.bankruptcyCommissionerEntity.idmId.eq(idmId);
    }
}
