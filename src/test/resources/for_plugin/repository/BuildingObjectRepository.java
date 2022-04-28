import org.springframework.data.jpa.repository.EntityGraph;

public interface BuildingObjectRepository extends SoftDeleteRepository<BuildingObjectEntity, Integer> {

    @EntityGraph(attributePaths = {"submittingDocEntities.submittingDocType", "submittingDocEntities.status",
            "submittingDocEntities.submittingDocAttachments.submittingDocAttachmentType",
            "submittingDocEntities.submittingDocSubmittingDocMissingEntities",
            "developer.bankruptcyCommissioner"})
    Optional<BuildingObjectEntity> findFetchById(Integer id);
}
