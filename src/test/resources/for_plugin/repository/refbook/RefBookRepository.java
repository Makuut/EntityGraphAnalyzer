@NoRepositoryBean
public interface RefBookRepository<E extends AbstractBaseRefBookEntity> extends JpaRepository<E, Integer>, QuerydslPredicateExecutor<E> {

    @NonNull
    @Override
    List<E> findAll(@NonNull Predicate predicate, @NonNull Sort sort);
}
