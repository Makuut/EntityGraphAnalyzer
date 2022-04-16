public interface SubmittingDocEventRepository extends SoftDeleteRepository<SubmittingDocEventEntity, SubmittingDocEventKey> {

    @Modifying
    void deleteBySubmittingDocIdAndEventEventTypeId(Integer submittingDocId, Integer eventTypeId);

}
