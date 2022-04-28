import org.springframework.data.jpa.repository.EntityGraph;

public interface ParticipantRepository extends SoftDeleteRepository<ParticipantEntity, Integer> {
    @EntityGraph(attributePaths = {"participantType", "participantAttachments.demandDocType",
            "participantAttachments.attachment.signatureAttachments.signature", "participantAddresses.addrType",
            "participantCitizenships.country", "participantFlats", "personDocType"})
    Optional<ParticipantEntity> findFirstByInnAndBankruptcyCommissioner_IdmId(String inn, UUID bankruptcyCommissionerId);

    @EntityGraph(attributePaths = {"participantType", "participantAttachments.demandDocType",
            "participantAttachments.attachment.signatureAttachments.signature", "participantAddresses.addrType",
            "participantCitizenships.country", "participantFlats", "personDocType"})
    Optional<ParticipantEntity> findFirstByPersonDocSerialAndPersonDocNumberAndBankruptcyCommissioner_IdmId(String personDocSerial, String personDocNumber,
                                                                                                            UUID bankruptcyCommissionerId);

    @EntityGraph(attributePaths = {"participantType", "participantAttachments.demandDocType",
            "participantAttachments.attachment.signatureAttachments.signature", "participantAddresses.addrType",
            "participantCitizenships.country", "participantFlats", "personDocType"})
    List<ParticipantEntity> findAllByIdInAndBankruptcyCommissioner_IdmId(List<Integer> id, UUID bankruptcyCommissionerId);
}
