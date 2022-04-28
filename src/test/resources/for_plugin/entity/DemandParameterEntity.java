
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DemandParameterDecisionTypeValidation(groups = CrmValidation.class)
@DemandParameterInclusionDecisionDtValidation(groups = CrmValidation.class)
@DemandParameterInclusionDtValidation(groups = CrmValidation.class)
@Table(name = "demand_parameter")
@SQLDelete(sql = "UPDATE lkau.demand_parameter SET deleted_dttm = now() WHERE id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class DemandParameterEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @MapsId
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Audited(targetAuditMode = NOT_AUDITED)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private ParticipantDemandEntity participantDemand;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_type_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.demand-parameter.type}", groups = CrmValidation.class)
    @Audited(targetAuditMode = NOT_AUDITED)
    private DemandTypeEntity demandType;

    @Revision
    @Column(name = "notification_dt")
    @NotNull(message = "{crm.validation.demand-parameter.notification-date}", groups = CrmValidation.class)
    private LocalDate notificationDt;

    @Revision
    @Column(name = "participant_inclusion_dt")
    private LocalDate participantInclusionDt;

    @Revision
    @Column(name = "participant_contract_payment")
    @NotNull(message = "{crm.validation.demand-parameter.payment}", groups = CrmValidation.class)
    private BigDecimal participantContractPayment;

    @Revision
    @Column(name = "refund_amt")
    private BigDecimal refundAmt;

    @Revision
    @Column(name = "unfulfilled_obligations_amt")
    @NotNull(message = "{crm.validation.demand-parameter.obligation-amt}", groups = CrmValidation.class)
    private BigDecimal unfulfilledObligationsAmt;

    @Revision
    @Column(name = "real_damage_amt")
    @NotNull(message = "{crm.validation.demand-parameter.damage-amt}", groups = CrmValidation.class)
    private BigDecimal realDamageAmt;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decision_type_id", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    private DecisionTypeEntity decisionType;

    @Revision
    @Column(name = "inclusion_decision_dt")
    private LocalDate inclusionDecisionDt;

    @Revision
    @Column(name = "inclusion_decision_notification_dt")
    private LocalDate inclusionDecisionNotificationDt;

    @Revision
    @Column(name = "repayment_dt")
    private LocalDate repaymentDt;

    @Revision
    @Column(name = "repayment_info")
    private String repaymentInfo;

    @Revision
    @Column(name = "repayment_mark_dt")
    private LocalDate repaymentMarkDt;

    @Revision
    @Column(name = "market_price_amt")
    private BigDecimal marketPriceAmt;

    @Revision
    @Column(name = "evaluation_report_dt")
    private LocalDate evaluationReportDt;

    @OneToMany(mappedBy = "demandParameter", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @DemandParameterPaymentDocExistsValidation(groups = CrmValidation.class)
    @AttachmentSignaturesValidation(message = "{crm.validation.payment.refund.sign-not-set}", groups = CrmValidation.class)
    private Set<DemandParameterAttachmentEntity> demandParameterAttachments = new HashSet<>();

    @Revision
    @Transient
    private Set<DemandParameterAttachmentEntity> paymentDemandParameterAttachments = new HashSet<>();

    @Revision
    @Transient
    private Set<DemandParameterAttachmentEntity> refundDemandParameterAttachments = new HashSet<>();
}
