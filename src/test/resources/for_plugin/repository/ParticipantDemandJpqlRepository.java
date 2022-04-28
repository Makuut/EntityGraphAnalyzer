public interface ParticipantDemandJpqlRepository {
    Optional<ParticipantDemandEntity> findOne(ParticipantDemandCriteria criteria);

    QueryResults<ParticipantDemandEntity> findAll(ParticipantDemandCriteria criteria);
}
