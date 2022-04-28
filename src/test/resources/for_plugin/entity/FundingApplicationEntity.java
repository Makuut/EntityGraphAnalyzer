@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funding_application")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.funding_application SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@FundingRequiredTypeDocValidation(groups = CrmPetitionValidation.class)
public class FundingApplicationEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_crm")
    private String idCrm;

    @Column(name = "funding_application_number", nullable = false)
    @NotEmpty(message = "{crm.validation.funding_application_number}", groups = CrmPetitionValidation.class)
    private String fundingApplicationNumber;

    @MapsId("id")
    @OneToOne(mappedBy = "fundingApplication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotNull(message = "{crm.validation.developer}", groups = CrmPetitionValidation.class)
    @Valid
    private DeveloperEntity developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.funding_application-status}", groups = CrmPetitionValidation.class)
    @InnerRefBookCodeValidation(message = "{crm.validation.status.draft-or-improvement}", codes = {"DRAFT", "IMPROVEMENT"},
            groups = CrmPetitionValidation.class)
    private FundingApplicationStatusEntity status;

    @Column(name = "arbitration_name")
    @NotNull(message = "{crm.validation.funding-application-arbitration-name}", groups = CrmPetitionValidation.class)
    private String arbitrationName;

    @Column(name = "send_dttm")
    private LocalDateTime sendDttm;

    @Column(name = "fund_comment")
    private String fundComment;

    @Column(name = "checking_account")
    @NotNull(message = "{crm.validation.funding-application-checking-account}", groups = {CrmPetitionValidation.class, FundingApplicationPreBudgetTabValidation.class})
    @NotBlank(message = "{crm.validation.funding-application-checking-account}", groups = {FundingApplicationPreBudgetTabValidation.class})
    private String checkingAccount;

    @Column(name = "bank_name")
    @NotNull(message = "{crm.validation.funding-application-bank-name}", groups = {CrmPetitionValidation.class, FundingApplicationPreBudgetTabValidation.class})
    @NotBlank(message = "{crm.validation.funding-application-bank-name}", groups = {FundingApplicationPreBudgetTabValidation.class})
    private String bankName;

    @Column(name = "bic")
    @NotNull(message = "{crm.validation.funding-application-bic}", groups = {CrmPetitionValidation.class, FundingApplicationPreBudgetTabValidation.class})
    @NotBlank(message = "{crm.validation.funding-application-bic}", groups = {FundingApplicationPreBudgetTabValidation.class})
    private String bic;

    @Column(name = "correspondent_account")
    @NotNull(message = "{crm.validation.funding-application-correspondent_account}", groups = {CrmPetitionValidation.class, FundingApplicationPreBudgetTabValidation.class})
    @NotBlank(message = "{crm.validation.funding-application-correspondent_account}", groups = {FundingApplicationPreBudgetTabValidation.class})
    private String correspondentAccount;

    @OneToMany(mappedBy = "fundingApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<@Valid OtherExpensesEntity> otherExpenses = new HashSet<>();

    @OneToMany(mappedBy = "fundingApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotEmpty(message = "{crm.validation.funding-application-amount-set}", groups = CrmPetitionValidation.class)
    @FundingApplicationCompletionAmountValidation(groups = FundingApplicationCompletionAmountValidation.class)
    @InvolvedPersonTypeEntityValidation(groups = CrmPetitionValidation.class)
    private Set<@Valid FundingApplicationAmountEntity> fundingApplicationAmounts = new HashSet<>();

    @OneToMany(mappedBy = "fundingApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<FundingClaimEntity> fundingClaims = new HashSet<>();

    @OneToMany(mappedBy = "fundingApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotEmpty(message = "{crm.validation.funding-application-attachment-set}",
            groups = {CrmPetitionValidation.class, CrmFundingValidation.class})
    @FundingApplicationCompletionAttachmentsValidation(groups = FundingApplicationCompletionAttachmentsValidation.class)
    @FundingApplicationCompletionAttachmentSignaturesValidation(groups = FundingApplicationCompletionAttachmentSignaturesValidation.class)
    private Set<@Valid FundingApplicationDocEntity> fundingApplicationDocs = new HashSet<>();

}
