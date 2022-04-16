@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "creditor_demand")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class CreditorDemandEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @MapsId("id")
    @OneToOne(mappedBy = "creditorDemand", cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{crm.validation.creditor-demand.developer}", groups = CrmValidation.class)
    private DeveloperEntity developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.creditor-demand.status}", groups = CrmValidation.class)
    @InnerRefBookCodeValidation(message = "{crm.validation.status.draft}", codes = "DRAFT", groups = CrmValidation.class)
    private CreditorDemandStatusEntity status;

    @Column(name = "version", nullable = false)
    @NotNull(message = "{crm.validation.creditor-demand.version}", groups = CrmValidation.class)
    private Integer version;

    @Column(name = "fund_comment")
    private String fundComment;

    @Revision
    @Column(name = "close_dt")
    @NotNull(message = "{crm.validation.creditor-demand.closeDt}", groups = CrmValidation.class)
    private LocalDate closeDt;

    @Revision
    @Column(name = "first_line_demand")
    @NotNull(message = "{crm.validation.creditor-demand.first-line-demand}", groups = CrmValidation.class)
    private BigDecimal firstLineDemand;

    @Revision
    @Column(name = "second_line_demand")
    @NotNull(message = "{crm.validation.creditor-demand.second-line-demand}", groups = CrmValidation.class)
    private BigDecimal secondLineDemand;

    @Revision
    @Column(name = "third_line_demand")
    @NotNull(message = "{crm.validation.creditor-demand.third-line-demand}", groups = CrmValidation.class)
    private BigDecimal thirdLineDemand;

    @Revision
    @Column(name = "fourth_line_demand")
    @NotNull(message = "{crm.validation.creditor-demand.fourth-line-demand}", groups = CrmValidation.class)
    private BigDecimal fourthLineDemand;

    @Revision
    @Column(name = "fifth_line_demand")
    @NotNull(message = "{crm.validation.creditor-demand.fifth-line-demand}", groups = CrmValidation.class)
    private BigDecimal fifthLineDemand;

    @Revision
    @Column(name = "developer_rights_deposit_demand")
    @NotNull(message = "{crm.validation.creditor-demand.developer-rights-deposit-demand}", groups = CrmValidation.class)
    private BigDecimal developerRightsDepositDemand;

    @Revision
    @Column(name = "developer_property_value")
    @NotNull(message = "{crm.validation.creditor-demand.developer-property-value}", groups = CrmValidation.class)
    private BigDecimal developerPropertyValue;

    @Revision
    @Column(name = "first_line_payments")
    @NotNull(message = "{crm.validation.creditor-demand.first-line-payments}", groups = CrmValidation.class)
    private BigDecimal firstLinePayments;

    @Revision
    @Column(name = "second_line_payments")
    @NotNull(message = "{crm.validation.creditor-demand.second-line-payments}", groups = CrmValidation.class)
    private BigDecimal secondLinePayments;

    @Revision
    @Column(name = "third_line_payments")
    @NotNull(message = "{crm.validation.creditor-demand.third-line-payments}", groups = CrmValidation.class)
    private BigDecimal thirdLinePayments;

    @Revision
    @Column(name = "fourth_line_payments")
    @NotNull(message = "{crm.validation.creditor-demand.fourth-line-payments}", groups = CrmValidation.class)
    private BigDecimal fourthLinePayments;

    @Revision
    @Column(name = "fifth_line_payments")
    @NotNull(message = "{crm.validation.creditor-demand.fifth-line-payments}", groups = CrmValidation.class)
    private BigDecimal fifthLinePayments;

    @Revision
    @Column(name = "total_current_payments")
    @NotNull(message = "{crm.validation.creditor-demand.total-current-payments}", groups = CrmValidation.class)
    private BigDecimal totalCurrentPayments;

}
