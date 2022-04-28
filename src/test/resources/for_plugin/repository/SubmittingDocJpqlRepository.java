public interface SubmittingDocJpqlRepository {

    List<SubmittingDocEntity> findAllByIdsAndDocType(Integer[] buildingObjectIds, SubmittingDocTypeEntity submittingDocTypeEntity);
}
