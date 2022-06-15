@Entity
public class RightsConcessionEntity extends BaseEntity<Integer> {

    private Integer id;

    private ParticipantDemandEntity participantDemand;

    private BigDecimal concessionAmt;

    private ParticipantTypeEntity transferorType;

    private String docNumber;

    private LocalDate docDt;

    private String docRegNumber;

    private LocalDate docRegDt;

    private Set<RightsConcessionAttachmentEntity> rightsConcessionAttachments = new HashSet<>();
}
