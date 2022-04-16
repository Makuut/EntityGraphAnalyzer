import org.springframework.data.jpa.repository.EntityGraph;

public interface SubmittingDocRepository extends SoftDeleteRepository<SubmittingDocEntity, Integer> {

    @NonNull
    @EntityGraph(attributePaths = {"submittingDocType", "status", "buildingObject",
            "submittingDocAttachments.attachment.signatureAttachments.signature",
            "submittingDocSubmittingDocMissingEntities.submittingDocMissing.missingDocState"})
    Page<SubmittingDocEntity> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"submittingDocType", "status",
            "submittingDocAttachments.submittingDocAttachmentType",
            "submittingDocAttachments.attachment.signatureAttachments.signature",
            "buildingObject",
            "submittingDocSubmittingDocMissingEntities.submittingDocMissing.missingDocState",
            "submittingDocSubmittingDocMissingEntities.submittingDocMissing.submittingDocRequests.attachment.signatureAttachments.signature"})
    Optional<SubmittingDocEntity> findOne(@NonNull Predicate predicate);

    @EntityGraph(attributePaths = {"buildingObject.developer", "submittingDocType", "status",
            "submittingDocAttachments.submittingDocAttachmentType",
            "submittingDocAttachments.attachment.signatureAttachments.signature"})
    Optional<SubmittingDocEntity> findFetchById(Integer id);

    /**
     * Метод, возвращающий список проблемных объектов, удовлетворяющих требованиям.
     * Метод считывает нативный запрос из файла {@code META-INF/jpa-named-queries.properties}
     *
     * @param statusCode  Код перечисления статуса предоставленного документа
     * @param statusId    Идентификатор перечисления статуса предоставленного документа
     * @param developerId Идентификатор застройщика
     * @return Список проблемных объектов
     */
    @Query(nativeQuery = true)
    List<SubmittingDocBuildingObject> findBuildingObjects(@Param("statusCode") String statusCode,
                                                          @Param("docTypeId") Integer statusId,
                                                          @Param("developerId") Integer developerId);

    /**
     * Возвращает список проблемных объектов для акта об отсутствии документов.
     * Метод считывает нативный запрос из файла {@code META-INF/jpa-named-queries.properties}
     *
     * @param developerId Идентификатор застройщика
     * @return Список проблемных объектов
     */
    @Query(nativeQuery = true)
    List<SubmittingDocBuildingObject> findBuildingObjectsForAct(@Param("developerId") Integer developerId);

    /**
     * Возвращает список проблемных объектов для застройщика без фильтрации
     * Метод считывает нативный запрос из файла {@code META-INF/jpa-named-queries.properties}
     *
     * @param developerId Идентификатор застройщика
     * @return Список проблемных объектов
     */
    @Query(nativeQuery = true)
    List<SubmittingDocBuildingObject> findAllBuildingObjects(@Param("developerId") Integer developerId);

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"buildingObject.developer", "submittingDocType", "status",
            "submittingDocAttachments.submittingDocAttachmentType", "submittingDocAttachments.attachment"})
    List<SubmittingDocEntity> findAll(@NonNull Predicate predicate);

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"submittingDocAttachments.attachment"})
    List<SubmittingDocEntity> findAllById(@NonNull Iterable<Integer> ids);

    @Query(value = "from SubmittingDocEntity s where :submittingDocStatusId = s.status.id " +
            "and s.limitationDt < now()")
    List<SubmittingDocEntity> findBySubmittingDocStatusId(@Param("submittingDocStatusId") Integer submittingDocStatusId);
}
