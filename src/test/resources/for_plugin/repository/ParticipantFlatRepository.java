import org.springframework.data.jpa.repository.EntityGraph;

public interface ParticipantFlatRepository extends SoftDeleteRepository<ParticipantFlatEntity, Integer> {

    @EntityGraph(attributePaths = {"participant.participantType"})
    List<ParticipantFlatEntity> findByParticipantDemandIdIn(List<Integer> participantDemandIds);
}
