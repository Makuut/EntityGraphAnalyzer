import org.springframework.data.jpa.repository.EntityGraph;

public interface IrdRepository extends SoftDeleteRepository<IrdEntity, Integer> {

    @Override
    @EntityGraph(attributePaths = {"irdDocType"})
    Optional<IrdEntity> findOne(Predicate predicate);
}
