import org.springframework.data.jpa.repository.EntityGraph;

public interface NonDisclosureRepository extends SoftDeleteRepository<NonDisclosureEntity, Integer> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"status", "attachment.signatureAttachments.signature"})
    Optional<NonDisclosureEntity> findById(@NonNull Integer id);
}