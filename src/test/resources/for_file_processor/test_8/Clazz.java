public interface Clazz {

    @EntityGraph(attributePaths = {"graph"})
    Optional<FundingApplicationEntity> method(@NonNull Integer developerId);
}
