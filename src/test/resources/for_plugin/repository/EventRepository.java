import org.springframework.data.jpa.repository.EntityGraph;

public interface EventRepository extends SoftDeleteRepository<EventEntity, Integer> {
    @Override
    @EntityGraph(attributePaths = {"buildingObject", "eventType",
            "submittingDocEvents.submittingDoc.submittingDocType"})
    List<EventEntity> findAll(Predicate predicate);

    @EntityGraph(attributePaths = {"buildingObject", "eventType", "developer",
            "participantDemandEvents",
            "submittingDocEvents.submittingDoc.submittingDocType"})
    Optional<EventEntity> findByIdAndBankruptcyCommissionerIdmId(Integer id, UUID idmId);
}
