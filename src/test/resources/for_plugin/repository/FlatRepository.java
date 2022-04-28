public interface FlatRepository extends SoftDeleteRepository<FlatEntity, Integer> {

    @Query(nativeQuery = true)
    List<FlatDoubleSalesDataModel> findSameByConstructionNumberAndEntrance(@Param("developerId") Integer developerId,
                                                                           @Param("buildingId") Integer buildingId);
}
