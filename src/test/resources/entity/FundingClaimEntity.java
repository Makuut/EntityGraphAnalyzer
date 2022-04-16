@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funding_claim")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.funding_claim SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@FundingClaimRequiredTypeDocValidation(groups = CrmFundingValidation.class)
@FundingClaimContractorValidation(groups = CrmFundingValidation.class)
@FundingClaimInvolvedPersonTypeValidation(groups = CrmFundingValidation.class)
public class FundingClaimEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funding_claim_seq")
    @SequenceGenerator(name = "funding_claim_seq", sequenceName = "lkau.funding_claim_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_application_id", referencedColumnName = "id", nullable = false)
    @Valid
    private FundingApplicationEntity fundingApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    @InnerRefBookCodeValidation(message = "{crm.validation.status.draft-or-improvement}", codes = {"DRAFT", "IMPROVEMENT"},
            groups = CrmFundingValidation.class)
    private FundingClaimStatusEntity status;

    @Column(name = "funding_claim_number")
    @NotBlank(message = "{crm.validation.funding-claim.number}", groups = CrmFundingValidation.class)
    private String fundingClaimNumber;

    @Column(name = "contract_name")
    @NotBlank(message = "{crm.validation.funding-claim.contract-name}", groups = CrmFundingValidation.class)
    private String contractName;

    @Column(name = "contract_dt")
    @NotNull(message = "{crm.validation.funding-claim.contract-dt}", groups = CrmFundingValidation.class)
    private LocalDate contractDt;

    @Column(name = "contract_number")
    @NotBlank(message = "{crm.validation.funding-claim.contract-number}", groups = CrmFundingValidation.class)
    private String contractNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_type_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.funding-claim.funding-type}", groups = CrmFundingValidation.class)
    private FundingTypeEntity fundingType;

    @Column(name = "payment_start_dt")
    @NotNull(message = "{crm.validation.funding-claim.payment-start-dt}", groups = CrmFundingValidation.class)
    private LocalDate paymentStartDt;

    @Column(name = "payment_end_dt")
    @NotNull(message = "{crm.validation.funding-claim.payment-end-dt}", groups = CrmFundingValidation.class)
    private LocalDate paymentEndDt;

    @Column(name = "contract_payment_dt")
    @NotNull(message = "{crm.validation.funding-claim.contract-payment-dt}", groups = CrmFundingValidation.class)
    private LocalDate contractPaymentDt;

    @Column(name = "payment_amt")
    @NotNull(message = "{crm.validation.funding-claim.payment-amt}", groups = CrmFundingValidation.class)
    private BigDecimal paymentAmt;

    @Column(name = "vat")
    @NotNull(message = "{crm.validation.funding-claim.vat}", groups = CrmFundingValidation.class)
    private BigDecimal vat;

    @Column(name = "payment_amt_vat")
    @NotNull(message = "{crm.validation.funding-claim.payment-amt-vat}", groups = CrmFundingValidation.class)
    private BigDecimal paymentAmtVat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_type_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.funding-claim.act-type}", groups = CrmFundingValidation.class)
    private ActTypeEntity actType;

    @Column(name = "act_number")
    @NotBlank(message = "{crm.validation.funding-claim.act-number}", groups = CrmFundingValidation.class)
    private String actNumber;

    @Column(name = "act_dt")
    @NotNull(message = "{crm.validation.funding-claim.act-date}", groups = CrmFundingValidation.class)
    private LocalDate actDt;

    @Column(name = "payment_number")
    @NotBlank(message = "{crm.validation.funding-claim.payment-number}", groups = CrmFundingValidation.class)
    private String paymentNumber;

    @Column(name = "payment_dt")
    @NotNull(message = "{crm.validation.funding-claim.payment-dt}", groups = CrmFundingValidation.class)
    private LocalDate paymentDt;

    @Column(name = "invoice_number")
    @NotBlank(message = "{crm.validation.funding-claim.invoice-number}", groups = CrmFundingValidation.class)
    private String invoiceNumber;

    @Column(name = "invoice_dt")
    @NotNull(message = "{crm.validation.funding-claim.invoice-dt}", groups = CrmFundingValidation.class)
    private LocalDate invoiceDt;

    @Column(name = "contractor_name")
    private String contractorName;

    @Column(name = "contractor_inn")
    private String contractorInn;

    @Column(name = "contractor_ogrn")
    private String contractorOgrn;

    @Column(name = "contractor_checking_account")
    private String contractorCheckingAccount;

    @Column(name = "contractor_bank_name")
    private String contractorBankName;

    @Column(name = "contractor_bank_bic")
    private String contractorBankBic;

    @Column(name = "fund_comment")
    private String fundComment;

    @Column(name = "contractor_bank_correspondent_account")
    private String contractorBankCorrespondentAccount;

    @Column(name = "send_dttm")
    private LocalDateTime sendDttm;

    @OneToMany(mappedBy = "fundingClaim", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<@Valid FundingClaimAttachmentEntity> fundingClaimAttachments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "involved_person_type_id", referencedColumnName = "id")
    private InvolvedPersonTypeEntity involvedPersonType;
}
