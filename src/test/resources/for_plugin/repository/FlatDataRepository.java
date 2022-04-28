import org.springframework.data.jpa.repository.EntityGraph;

public interface FlatDataRepository extends SoftDeleteRepository<FlatDataEntity, Integer> {

    @Override
    @EntityGraph(attributePaths = {"flatType", "flatStatus", "flatKind"})
    @Nonnull
    Page<FlatDataEntity> findAll(@Nonnull Predicate predicate, @Nonnull Pageable pageable);

    @Override
    @Nonnull
    @EntityGraph(attributePaths = {"flatType", "flatStatus", "flatKind", "flatRoomType", "flatography.status"})
    Optional<FlatDataEntity> findOne(@Nonnull Predicate predicate);

    @Query(nativeQuery = true)
    FlatDataCount countFlatData(@Nonnull @Param("buildingId") Integer buildingId);

}
