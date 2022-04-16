@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "budget_expense_group")
public class BudgetExpenseGroupEntity extends InnerRefBookEntity {
    @OneToMany(mappedBy = "budgetExpenseGroup")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<FundingTypeEntity> fundingTypes = new HashSet<>();
}
