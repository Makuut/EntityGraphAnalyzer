@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeePredicate {

    public static BooleanExpression isBankruptcyCommissionerEq(UUID bankruptcyCommissionerId) {
        return QEmployeeEntity.employeeEntity.bankruptcyCommissioner.id.eq(bankruptcyCommissionerId);
    }
}
