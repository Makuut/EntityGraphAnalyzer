import org.springframework.data.jpa.repository.EntityGraph;

public interface CreditorDemandRepository extends SoftDeleteRepository<CreditorDemandEntity, Integer> {

    @Override
    @EntityGraph(attributePaths = {"status", "developer"})
    Optional<CreditorDemandEntity> findById(Integer id);

    @EntityGraph(attributePaths = {"developer", "status"})
    Optional<CreditorDemandEntity> findFetchById(Integer id);
}
