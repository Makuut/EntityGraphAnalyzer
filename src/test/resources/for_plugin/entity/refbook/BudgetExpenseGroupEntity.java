@Entity
public class BudgetExpenseGroupEntity extends InnerRefBookEntity {
    @OneToMany(mappedBy = "budgetExpenseGroup")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<FundingTypeEntity> fundingTypes = new HashSet<>();
}
