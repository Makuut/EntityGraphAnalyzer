@Entity
public class ParticipantDemandEntity extends BaseEntity<Integer> {

    private Integer id;

    private String idCrm;

    private Boolean isImported;

    private DemandParameterEntity demandParameter;

    private FlatEntity flat;

    private BuildingObjectEntity buildingObject;

    private ReasonDocEntity reasonDoc;

    private Integer participantDemandNumber;

    private ParticipantDemandStatusEntity status;

    private Integer version;

    private String fundComment;

    private Set<ParticipantDemandCorrectionEntity> participantDemandCorrections = new HashSet<>();

    private Set<ParticipantDemandCorrectionDocEntity> participantDemandCorrectionDocs = new HashSet<>();

    private Set<ParticipantDemandEventEntity> participantDemandEvents = new HashSet<>();

    private Set<@Valid LoanAgreementEntity> loanAgreementEntitySet = new HashSet<>();

    private Set<@Valid LoanAgreementAdditionalEntity> loanAgreementAdditionalEntitySet = new HashSet<>();

    private Set<@Valid RightsConcessionEntity> rightsConcessionEntitySet = new HashSet<>();

    private Set<@Valid ParticipantFlatEntity> participantFlats = new HashSet<>();
}
