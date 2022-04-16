public interface DeveloperToCommissionerRepository
        extends SoftDeleteRepository<DeveloperToCommissionerEntity, Integer> {
    Optional<DeveloperToCommissionerEntity> findByDeveloperInnAndDeveloperOgrn(String developerInn, String developerOgrn);
}
