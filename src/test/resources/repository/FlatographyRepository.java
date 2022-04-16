import org.springframework.data.jpa.repository.EntityGraph;

public interface FlatographyRepository extends SoftDeleteRepository<FlatographyEntity, Integer> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"buildingObject", "status", "flatographyCorrections", "revise", "irds"})
    Optional<FlatographyEntity> findOne(@NonNull Predicate predicate);

    @NonNull
    @EntityGraph(attributePaths = {"buildingObject.developer", "status", "flatographyCorrections",
            "buildingObject.constructionType", "flatDataSet.flatType", "flatDataSet.flatKind", "flatDataSet.flatRoomType",
            "flatDataSet.flatStatus", "irds.irdDocType", "revise.attachment", "revise.revisies"})
    Optional<FlatographyEntity> findFetchById(@NonNull Integer flatographyId);

    @Query(nativeQuery = true)
    Optional<FlatographyAggregateData> getFlatographyAggregateData(@Param("buildingId") Integer buildingId,
                                                                   @Param("developerId") Integer developerId);

    @EntityGraph(attributePaths = {"buildingObject", "buildingObject.developer", "buildingObject.constructionType",
            "flatDataSet", "flatDataSet.flatType", "flatDataSet.flatKind", "flatDataSet.flatRoomType", "flatDataSet.flatStatus"})
    Optional<FlatographyEntity> findOneById(Integer id);
}
