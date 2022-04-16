public interface SubmittingDocMissingTemporaryRepository extends SoftDeleteRepository<SubmittingDocMissingTemporaryEntity, Integer> {

    @NonNull
    Optional<SubmittingDocMissingTemporaryEntity> findById(@NonNull Integer id);
}
