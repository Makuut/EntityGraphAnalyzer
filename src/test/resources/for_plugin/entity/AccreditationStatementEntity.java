@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accreditation_statement")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.accreditation_statement SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@AccreditationCompletionAttachmentsValidation(groups = CrmAccreditationValidation.class)
@FieldsForRenewalValidation(groups = CrmAccreditationValidation.class)
@FieldsForAccreditationProlongationValidation(groups = CrmAccreditationValidation.class)
@FieldsForReversalValidation(groups = CrmAccreditationValidation.class)
public class AccreditationStatementEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accreditation_statement_seq")
    @SequenceGenerator(name = "accreditation_statement_seq", sequenceName = "lkau.accreditation_statement_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "bankruptcy_commissioner_id", referencedColumnName = "id", nullable = false)
    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accreditation_statement_type_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.accreditation-statement-accreditation-statement-type}", groups = CrmAccreditationValidation.class)
    private AccreditationStatementTypeEntity accreditationStatementType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    @InnerRefBookCodeValidation(message = "{crm.validation.status.draft-or-improvement}", codes = {"DRAFT", "IMPROVEMENT"}, groups = CrmAccreditationValidation.class)
    private AccreditationStatementStatusEntity status;

    @Column(name = "inn", length = 12)
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-inn}", groups = CrmAccreditationValidation.class)
    private String inn;

    @Column(name = "snils", length = 11)
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-snils}", groups = CrmAccreditationValidation.class)
    private String snils;

    @Column(name = "passport_serial", length = 4)
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-serial}", groups = CrmAccreditationValidation.class)
    private String passportSerial;

    @Column(name = "passport_number", length = 6)
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-number}", groups = CrmAccreditationValidation.class)
    private String passportNumber;

    @Column(name = "passport_dt")
    @NotNull(message = "{crm.validation.bankruptcy-commissioner-date}", groups = CrmAccreditationValidation.class)
    private LocalDate passportDt;

    @Column(name = "reg_address")
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-reg-address}", groups = CrmAccreditationValidation.class)
    private String regAddress;

    @Column(name = "phone", length = 11)
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-phone}", groups = CrmAccreditationValidation.class)
    private String phone;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-email}", groups = CrmAccreditationValidation.class)
    private String email;

    @Column(name = "commissioner_reg_number", length = 50)
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-reg-number}", groups = {CrmAccreditationValidation.class})
    private String commissionerRegNumber;

    @Column(name = "sro_name")
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-sro-name}", groups = CrmAccreditationValidation.class)
    private String sroName;

    @Column(name = "sro_reg_number", length = 50)
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-sro-reg-number}", groups = CrmAccreditationValidation.class)
    private String sroRegNumber;

    @Column(name = "accreditation_number", length = 50)
    private String accreditationNumber;

    @Column(name = "number", nullable = false)
    @NotNull(message = "{crm.validation.accreditation-statement-number}", groups = CrmAccreditationValidation.class)
    private Integer number;

    @Column(name = "send_dttm")
    private LocalDateTime sendDttm;

    @Column(name = "incoming_number")
    private String incomingNumber;

    @Column(name = "fund_comment")
    private String fundComment;

    @Column(name = "registration_dt")
    private LocalDate registrationDt;

    @OneToMany(mappedBy = "accreditationStatement", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<AccreditationStatementDocEntity> accreditationStatementDocEntitySet = new HashSet<>();

    @Column(name = "birth_dt")
    private LocalDate birthDt;

    @Column(name = "department_code")
    private String departmentCode;

    @Column(name = "experience")
    private String experience;

    @Column(name = "number_of_cases")
    private String numberOfCases;

    @Column(name = "law_violation")
    private String lawViolation;

    @Column(name = "reason")
    private String reason;

    @Column(name = "certificate_section")
    private String certificateSection;

    @Column(name = "postal_address")
    private String postalAddress;

    @Column(name = "certificate_dt")
    private LocalDate certificateDt;
}
