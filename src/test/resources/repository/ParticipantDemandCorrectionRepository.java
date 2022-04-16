import org.springframework.data.jpa.repository.EntityGraph;

public interface ParticipantDemandCorrectionRepository extends SoftDeleteRepository<ParticipantDemandCorrectionEntity, Integer> {
    @EntityGraph(attributePaths = {"correctionBase", "participantDemandCorrectionTypes.correctionType", "participantDemand.status", "participantDemandCorrectionAttachments.attachment"})
    Optional<ParticipantDemandCorrectionEntity> findByParticipantDemandIdAndVersion(Integer id, Integer version);
}
