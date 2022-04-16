import org.springframework.data.jpa.repository.EntityGraph;

public interface ReviseRepository extends SoftDeleteRepository<ReviseEntity, Integer> {

    @Override
    @NonNull
    @EntityGraph(attributePaths = {"revisies.id",
            "flatography.buildingObject.developer",
            "flatography.buildingObject.constructionType"})
    Optional<ReviseEntity> findOne(@NonNull Predicate predicate);

    @Query(nativeQuery = true)
    <E extends Number> List<FlatographyReviseData<E>> getRevise(@Param("developerId") Integer developerId,
                                                                @Param("flatographyId") Integer flatographyId);

}
