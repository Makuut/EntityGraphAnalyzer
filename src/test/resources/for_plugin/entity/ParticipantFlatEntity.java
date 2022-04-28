@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant_flat")
@SQLDelete(sql = "UPDATE lkau.participant_flat SET deleted_dttm = now() WHERE id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class ParticipantFlatEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_flat_seq")
    @SequenceGenerator(name = "participant_flat_seq", sequenceName = "lkau.participant_flat_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    @Valid
    @NotNull(message = "{crm.validation.flat.participant}", groups = CrmValidation.class)
    private ParticipantEntity participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_demand_id", referencedColumnName = "id")
    private ParticipantDemandEntity participantDemand;

    @Column(name = "changed_in_version")
    private Integer changedInVersion;

    @Revision(group = "participant_share")
    @Column(name = "numerator")
    @NotNull(message = "{crm.validation.participant-flat.numerator}", groups = CrmValidation.class)
    private Integer numerator;

    @Revision(group = "participant_share")
    @Column(name = "denominator")
    @NotNull(message = "{crm.validation.participant-flat.numerator}", groups = CrmValidation.class)
    private Integer denominator;

    @Revision
    @Column(name = "excluding_arbitration_ruling")
    private String excludingArbitrationRuling;

    @Revision
    @Column(name = "excluding_arbitration_ruling_dt")
    private LocalDate excludingArbitrationRulingDt;

    @Revision
    @Column(name = "sort_index")
    private Integer sortIndex;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownership_type_id", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{crm.validation.participant-flat.ownership-type}", groups = CrmValidation.class)
    private OwnershipTypeEntity ownershipType;

    @Revision
    @OneToMany(mappedBy = "participantFlat", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotEmpty(message = "{crm.validation.participant-flat-attachment}", groups = CrmValidation.class)
    @AttachmentSignaturesValidation(message = "{crm.validation.application.sign-not-set}", groups = CrmValidation.class)
    private Set<@Valid ParticipantFlatAttachmentEntity> participantFlatAttachments = new HashSet<>();
}
