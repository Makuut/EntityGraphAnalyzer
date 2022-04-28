public interface FlatographyJpqlRepository {

    Page<FlatographyObjectInfo> findAll(FlatographyCriteria criteria, Pageable pageable);
}
