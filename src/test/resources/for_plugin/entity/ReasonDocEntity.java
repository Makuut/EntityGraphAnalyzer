@Entity
public class ReasonDocEntity extends BaseEntity<Integer> {

    private Integer id;

    private Boolean multiFlat;

    private DeveloperEntity developer;

    private Set<ParticipantDemandEntity> participantDemandEntitySet = new HashSet<>();

    private ReasonDocTypeEntity reasonDocType;

    private BigDecimal contractPrice;

    private BigDecimal pricePerSqMeter;

    private String docNumber;

    private LocalDate docDt;

    private String docRegNumber;

    private LocalDate docRegDt;

    private Boolean isImported;

    private Set<ReasonDocAttachmentEntity> reasonDocAttachments = new HashSet<>();
}
