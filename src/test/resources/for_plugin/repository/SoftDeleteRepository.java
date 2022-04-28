@NoRepositoryBean
public interface SoftDeleteRepository<T extends BaseEntity<I>, I> extends JpaRepository<T, I>, QuerydslPredicateExecutor<T> {

    @Query("update #{#entityName} e set e.deletedDttm = CURRENT_TIMESTAMP where e.id = ?1")
    @Modifying
    void softDelete(I id);

    @Query("update #{#entityName} e set e.deletedDttm = CURRENT_TIMESTAMP where e.id in (?1)")
    @Modifying
    void softDeleteAll(List<I> ids);

}
