@Entity
public class ParticipantFlatEntity extends BaseEntity<Integer> {

    private Integer id;

    private ParticipantEntity participant;

    private ParticipantDemandEntity participantDemand;

    private Integer changedInVersion;

    private Integer numerator;

    private Integer denominator;

    private String excludingArbitrationRuling;

    private LocalDate excludingArbitrationRulingDt;

    private Integer sortIndex;

    private OwnershipTypeEntity ownershipType;

    private Set<@Valid ParticipantFlatAttachmentEntity> participantFlatAttachments = new HashSet<>();
}
