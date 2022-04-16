public interface DeveloperEventRepository extends SoftDeleteRepository<DeveloperEventEntity, DeveloperEventKey> {
    @Modifying
    void deleteByDeveloperIdAndEventEventTypeId(Integer developerId, Integer eventTypeId);
}
