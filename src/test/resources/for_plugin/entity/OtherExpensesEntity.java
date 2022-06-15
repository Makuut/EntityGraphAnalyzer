@Entity
public class OtherExpensesEntity extends BaseEntity<Integer> {

    private Integer id;

    private FundingApplicationEntity fundingApplication;

    private BudgetExpenseGroupEntity budgetExpenseGroup;

    private String name;

    private BigDecimal expense;
}
