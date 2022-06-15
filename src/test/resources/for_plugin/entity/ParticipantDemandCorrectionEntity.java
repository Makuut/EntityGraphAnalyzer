@Entity
public class ParticipantDemandCorrectionEntity extends BaseEntity<Integer> {

    private Integer id;

    private ParticipantDemandEntity participantDemand;

    private CorrectionBaseEntity correctionBase;

    private Integer version;

    private String changingArbitrationRuling;

    private LocalDate changingArbitrationRulingDt;

    private BigDecimal changingCourtDecisionDemandAmt;

    private Set<ParticipantDemandCorrectionAttachmentEntity> participantDemandCorrectionAttachments = new HashSet<>();

    private Set<ParticipantDemandCorrectionTypeEntity> participantDemandCorrectionTypes = new HashSet<>();

}
