import org.springframework.data.jpa.repository.EntityGraph;

public interface ReasonDocRepository extends SoftDeleteRepository<ReasonDocEntity, Integer> {

    @EntityGraph(attributePaths = {
            "participantDemandEntitySet.flat",
            "reasonDocType",
            "reasonDocAttachments.attachment.signatureAttachments.signature"})
    List<ReasonDocEntity> findAllByDocNumberIgnoreCaseAndDeveloperId(String docNumber, Integer developerId);

    ReasonDocEntity findByDocRegNumberAndDeveloperId(String docRegNumber, Integer developerId);
}
