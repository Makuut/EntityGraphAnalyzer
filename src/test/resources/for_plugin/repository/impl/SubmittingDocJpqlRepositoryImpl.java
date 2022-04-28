@Repository
@RequiredArgsConstructor
public class SubmittingDocJpqlRepositoryImpl implements SubmittingDocJpqlRepository {
    private final EntityManager entityManager;

    @Override
    public List<SubmittingDocEntity> findAllByIdsAndDocType(Integer[] buildingObjectIds, SubmittingDocTypeEntity submittingDocTypeEntity) {
        QSubmittingDocEntity qSubmittingDocEntity = QSubmittingDocEntity.submittingDocEntity;
        QSubmittingDocStatusEntity qSubmittingDocStatusEntity = QSubmittingDocStatusEntity.submittingDocStatusEntity;
        QSubmittingDocTypeEntity qSubmittingDocTypeEntity = QSubmittingDocTypeEntity.submittingDocTypeEntity;
        QSubmittingDocAttachmentEntity qSubmittingDocAttachmentEntity = QSubmittingDocAttachmentEntity.submittingDocAttachmentEntity;
        QAttachmentEntity qA = QAttachmentEntity.attachmentEntity;
        QSignatureAttachmentEntity qS = QSignatureAttachmentEntity.signatureAttachmentEntity;

        var submittingDocEntityJPAQuery = new JPAQuery<>(this.entityManager);

        return submittingDocEntityJPAQuery.select(qSubmittingDocEntity)
                .from(qSubmittingDocEntity)
                .leftJoin(qSubmittingDocEntity.submittingDocType, qSubmittingDocTypeEntity)
                .leftJoin(qSubmittingDocEntity.status, qSubmittingDocStatusEntity)
                .leftJoin(qSubmittingDocEntity.submittingDocAttachments, qSubmittingDocAttachmentEntity)
                .fetchJoin()
                .leftJoin(qSubmittingDocAttachmentEntity.attachment, qA)
                .fetchJoin()
                .leftJoin(qA.signatureAttachments, qS)
                .fetchJoin()
                .leftJoin(qA.signatures, qS)
                .fetchJoin()
                .where(qSubmittingDocEntity.buildingObject.id.in(buildingObjectIds)
                        .and(qSubmittingDocEntity.submittingDocType.code.eq(submittingDocTypeEntity.getCode())))
                .distinct()
                .fetch();
    }
}
