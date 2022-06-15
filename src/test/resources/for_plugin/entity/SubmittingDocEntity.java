@Entity
public class SubmittingDocEntity extends BaseEntity<Integer> {

    private Integer id;

    private SubmittingDocTypeEntity submittingDocType;

    private BuildingObjectEntity buildingObject;

    private SubmittingDocStatusEntity status;

    private LocalDate limitationDt;

    private LocalDateTime sendDttm;

    private String fundComment;

    private String termsViolationReason;

    private Set<@Valid SubmittingDocSubmittingDocMissingEntity> submittingDocSubmittingDocMissingEntities = new HashSet<>();

    private Set<SubmittingDocAttachmentEntity> submittingDocAttachments = new HashSet<>();

    private Set<SubmittingDocEventEntity> submittingDocEvents = new HashSet<>();
}
