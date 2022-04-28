@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.event SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class EventEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", sequenceName = "lkau.event_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "event_dttm")
    private LocalDateTime eventDttm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", referencedColumnName = "id", nullable = false)
    private DeveloperEntity developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_object_id", referencedColumnName = "id", nullable = false)
    private BuildingObjectEntity buildingObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankruptcy_commissioner_id", referencedColumnName = "id")
    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id", referencedColumnName = "id", nullable = false)
    private EventTypeEntity eventType;

    @Column(name = "is_done")
    private Boolean isDone;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<ParticipantDemandEventEntity> participantDemandEvents = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<SubmittingDocEventEntity> submittingDocEvents = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<DeveloperEventEntity> developerEvents = new HashSet<>();
}
