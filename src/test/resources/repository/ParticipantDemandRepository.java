import org.springframework.data.jpa.repository.EntityGraph;

public interface ParticipantDemandRepository extends SoftDeleteRepository<ParticipantDemandEntity, Integer> {

    @Override
    @EntityGraph(attributePaths = { "status",
            "reasonDoc.reasonDocAttachments", "reasonDoc.reasonDocType",
            "reasonDoc.reasonDocAttachments.attachment", "reasonDoc.participantDemandEntitySet",
            "flat.flatType", "flat.flatRoomType", "flat.compensationAreaType",
            "flat.participantFlats.participant.participantType",
            "buildingObject.developer",
            "flat.buildingObject.flats"})
    Page<ParticipantDemandEntity> findAll(Predicate predicate, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {
            "reasonDoc.reasonDocAttachments.attachment.signatureAttachments.signature", "reasonDoc.reasonDocType",
            "loanAgreementEntitySet.loanAgreementAttachments.attachment.signatureAttachments.signature",
            "loanAgreementAdditionalEntitySet.loanAgreementAdditionalAttachments.attachment.signatureAttachments.signature",
            "rightsConcessionEntitySet.transferorType",
            "rightsConcessionEntitySet.rightsConcessionAttachments.attachment.signatureAttachments.signature",
            "participantDemandCorrectionDocs",
            "participantDemandCorrections.correctionBase",
            "participantDemandCorrections.participantDemandCorrectionTypes.correctionType",
            "participantDemandCorrections.participantDemandCorrectionAttachments.attachment.signatureAttachments.signature",
            "participantDemandEvents",
            "demandParameter.decisionType",
            "demandParameter.demandType",
            "demandParameter.demandParameterAttachments.demandDocType",
            "demandParameter.demandParameterAttachments.attachment.signatureAttachments.signature",
            "status",
            "flat.flatType",
            "flat.flatRoomType",
            "flat.compensationAreaType",
            "buildingObject.developer.bankruptcyCommissioner",
            "participantFlats.ownershipType",
            "participantFlats.participantFlatAttachments.attachment.signatureAttachments.signature",
            "participantFlats.participant.participantType",
            "participantFlats.participant.personDocType",
            "participantFlats.participant.participantAddresses.addrType",
            "participantFlats.participant.participantCitizenships.country",
            "participantFlats.participant.participantAttachments.demandDocType",
            "participantFlats.participant.participantAttachments.attachment.signatureAttachments.signature"})
    Optional<ParticipantDemandEntity> findOne(Predicate predicate);

    @EntityGraph(attributePaths = {"status"})
    Optional<ParticipantDemandEntity> findWithStatusById(Integer participantDemandId);

    @EntityGraph(attributePaths = {
            "reasonDoc.reasonDocAttachments.attachment.signatureAttachments.signature", "reasonDoc.reasonDocType",
            "loanAgreementEntitySet.loanAgreementAttachments.attachment.signatureAttachments.signature",
            "loanAgreementAdditionalEntitySet.loanAgreementAdditionalAttachments.attachment.signatureAttachments.signature",
            "rightsConcessionEntitySet.transferorType",
            "rightsConcessionEntitySet.rightsConcessionAttachments.attachment.signatureAttachments.signature",
            "participantDemandCorrectionDocs",
            "participantDemandCorrections.correctionBase",
            "participantDemandCorrections.participantDemandCorrectionTypes.correctionType",
            "participantDemandCorrections.participantDemandCorrectionAttachments.attachment.signatureAttachments.signature",
            "participantDemandEvents",
            "demandParameter.decisionType",
            "demandParameter.demandType",
            "demandParameter.demandParameterAttachments.demandDocType",
            "demandParameter.demandParameterAttachments.attachment.signatureAttachments.signature",
            "status",
            "flat.flatType",
            "flat.flatRoomType",
            "flat.compensationAreaType",
            "buildingObject.developer.bankruptcyCommissioner",
            "participantFlats.ownershipType",
            "participantFlats.participantFlatAttachments.attachment.signatureAttachments.signature",
            "participantFlats.participant.participantType",
            "participantFlats.participant.personDocType",
            "participantFlats.participant.participantAddresses.addrType",
            "participantFlats.participant.participantCitizenships.country",
            "participantFlats.participant.participantAttachments.demandDocType",
            "participantFlats.participant.participantAttachments.attachment.signatureAttachments.signature"})
    @Override
    Set<ParticipantDemandEntity> findAll(Predicate predicate);

    Set<BaseEntityInterface<Integer>> findIdByReasonDocId(Integer id);

    @Query(value = "select coalesce(max(pd.participant_demand_number) + 1, 1) " +
            "from lkau.participant_demand pd " +
            "where pd.building_object_id = :buildingId and pd.deleted_dttm is null", nativeQuery = true)
    int nextDemandNumber(@Nonnull @Param("buildingId") Integer buildingId);

}
