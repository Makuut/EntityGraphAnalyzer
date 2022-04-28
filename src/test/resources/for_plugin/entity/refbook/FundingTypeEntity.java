@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "funding_type")
public class FundingTypeEntity extends InnerRefBookEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_expense_group_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.funding-application-amount-type-group}", groups = CrmPetitionValidation.class)
    private BudgetExpenseGroupEntity budgetExpenseGroup;
}
