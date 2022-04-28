import org.springframework.data.jpa.repository.EntityGraph;

public interface ArchiveFileEntityRepository extends JpaRepository<ArchiveFileEntity, Integer>,  QuerydslPredicateExecutor<ArchiveFileEntity> {

    @Override
    @EntityGraph(attributePaths = {"archiveEntity"})
    List<ArchiveFileEntity> findAll(Predicate predicate);
}
