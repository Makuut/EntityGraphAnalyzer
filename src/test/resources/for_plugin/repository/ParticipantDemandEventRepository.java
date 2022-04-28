public interface ParticipantDemandEventRepository extends SoftDeleteRepository<ParticipantDemandEventEntity, ParticipantDemandEventKey> {

    @Modifying
    void deleteByParticipantDemandIdAndEventEventTypeId(Integer participantDemandId, Integer eventTypeId);

}
