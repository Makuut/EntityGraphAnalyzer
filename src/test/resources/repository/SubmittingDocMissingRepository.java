import org.springframework.data.jpa.repository.EntityGraph;

public interface SubmittingDocMissingRepository extends SoftDeleteRepository<SubmittingDocMissingEntity, Integer> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"submittingDocRequests.attachment", "missingDocState", "attachment",
            "submittingDocSubmittingDocMissingEntities.submittingDoc.submittingDocType"})
    Optional<SubmittingDocMissingEntity> findById(Integer id);
}
