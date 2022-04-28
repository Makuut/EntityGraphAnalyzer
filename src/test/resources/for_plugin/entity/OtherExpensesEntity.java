@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "other_expenses")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.other_expenses SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class OtherExpensesEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "other_expenses_seq")
    @SequenceGenerator(name = "other_expenses_seq", sequenceName = "lkau.other_expenses_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_application_id", referencedColumnName = "id", nullable = false)
    private FundingApplicationEntity fundingApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_group_id", referencedColumnName = "id", nullable = false)
    private BudgetExpenseGroupEntity budgetExpenseGroup;

    @Column(name = "name")
    @NotBlank(message = "{crm.validation.other-expenses-name}", groups = CrmPetitionValidation.class)
    private String name;

    @Column(name = "expense")
    @NotNull(message = "{crm.validation.other-expenses-expense}", groups = CrmPetitionValidation.class)
    private BigDecimal expense;
}
