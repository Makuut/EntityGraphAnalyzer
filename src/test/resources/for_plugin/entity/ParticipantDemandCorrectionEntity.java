@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE lkau.participant_demand_correction SET deleted_dttm = now() WHERE id = ?")
@Table(name = "participant_demand_correction")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@ArbitrationRulingValidation(groups = CrmValidation.class)
@OtherTypeValidation(groups = CrmValidation.class)
@CorrectionSignatureValidation(groups = CrmValidation.class)
public class ParticipantDemandCorrectionEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_demand_correction_seq")
    @SequenceGenerator(name = "participant_demand_correction_seq", sequenceName = "lkau.participant_demand_correction_seq",
            allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_demand_id", referencedColumnName = "id", nullable = false)
    private ParticipantDemandEntity participantDemand;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correction_base_id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    private CorrectionBaseEntity correctionBase;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Revision
    @Column(name = "changing_arbitration_ruling")
    private String changingArbitrationRuling;

    @Revision
    @Column(name = "changing_arbitration_ruling_dt")
    private LocalDate changingArbitrationRulingDt;

    @Revision
    @Column(name = "changing_court_decision_demand_amt")
    private BigDecimal changingCourtDecisionDemandAmt;

    @Revision
    @OneToMany(mappedBy = "participantDemandCorrection", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<ParticipantDemandCorrectionAttachmentEntity> participantDemandCorrectionAttachments = new HashSet<>();

    @Revision
    @OneToMany(mappedBy = "participantDemandCorrection", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<ParticipantDemandCorrectionTypeEntity> participantDemandCorrectionTypes = new HashSet<>();

}
