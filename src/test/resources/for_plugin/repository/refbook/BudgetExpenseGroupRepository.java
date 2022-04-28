import org.springframework.data.jpa.repository.EntityGraph;

public interface BudgetExpenseGroupRepository extends RefBookRepository<BudgetExpenseGroupEntity> {

    @Override
    @EntityGraph(attributePaths = {"fundingTypes"})
    List<BudgetExpenseGroupEntity> findAll(@NonNull Predicate predicate, @NonNull Sort sort);
}
