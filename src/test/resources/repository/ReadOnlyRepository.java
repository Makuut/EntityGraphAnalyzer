@NoRepositoryBean
public interface ReadOnlyRepository<T, R> extends Repository<T, R>, QuerydslPredicateExecutor<T> {
}
