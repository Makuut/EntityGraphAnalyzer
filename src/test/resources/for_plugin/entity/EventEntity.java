@Entity
public class EventEntity extends BaseEntity<Integer> {

    private Integer id;

    private LocalDateTime eventDttm;

    private DeveloperEntity developer;

    private BuildingObjectEntity buildingObject;

    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    private EventTypeEntity eventType;

    private Boolean isDone;

    private Set<ParticipantDemandEventEntity> participantDemandEvents = new HashSet<>();

    private Set<SubmittingDocEventEntity> submittingDocEvents = new HashSet<>();

    private Set<DeveloperEventEntity> developerEvents = new HashSet<>();
}
