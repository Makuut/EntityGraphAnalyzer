@Entity
public class DemandParameterEntity extends BaseEntity<Integer> {

    private Integer id;

    private ParticipantDemandEntity participantDemand;

    private DemandTypeEntity demandType;

    private LocalDate notificationDt;

    private LocalDate participantInclusionDt;

    private BigDecimal participantContractPayment;

    private BigDecimal refundAmt;

    private BigDecimal unfulfilledObligationsAmt;

    private BigDecimal realDamageAmt;

    private DecisionTypeEntity decisionType;

    private LocalDate inclusionDecisionDt;

    private LocalDate inclusionDecisionNotificationDt;

    private LocalDate repaymentDt;

    private String repaymentInfo;

    private LocalDate repaymentMarkDt;

    private BigDecimal marketPriceAmt;

    private LocalDate evaluationReportDt;

    private Set<DemandParameterAttachmentEntity> demandParameterAttachments = new HashSet<>();

    private Set<DemandParameterAttachmentEntity> paymentDemandParameterAttachments = new HashSet<>();

    private Set<DemandParameterAttachmentEntity> refundDemandParameterAttachments = new HashSet<>();
}
