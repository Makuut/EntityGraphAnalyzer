@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@SQLDelete(sql = "UPDATE lkau.participant SET deleted_dttm = now() WHERE id = ?")
@Entity(name = "participant")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(targetAuditMode = NOT_AUDITED)
@AuditOverride(forClass = BaseEntity.class)
@ParticipantDataValidate(groups = CrmValidation.class)
@ParticipantAttachmentsValidation(groups = CrmValidation.class)
public class ParticipantEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_seq")
    @SequenceGenerator(name = "participant_seq", sequenceName = "lkau.participant_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankruptcy_commissioner_id", referencedColumnName = "id")
    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_type_id", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{crm.validation.participant.type}", groups = CrmValidation.class)
    @Valid
    private ParticipantTypeEntity participantType;

    @Revision
    @Column(name = "ul_name")
    @NotNull(message = "{crm.validation.participant.ul-name}", groups = CrmDataCompanyValidation.class)
    private String ulName;

    @Revision
    @Column(name = "person_name")
    @NotNull(message = "{crm.validation.participant.person-name}", groups = CrmPersonalDataValidation.class)
    private String personName;

    @Revision
    @Column(name = "person_surname")
    @NotNull(message = "{crm.validation.participant.person-sur-name}", groups = CrmPersonalDataValidation.class)
    private String personSurname;

    @Revision
    @Column(name = "person_patronymic")
    private String personPatronymic;

    @Revision
    @Column(name = "person_birthday")
    @NotNull(message = "{crm.validation.participant.person-birthday}", groups = CrmPersonalDataValidation.class)
    private LocalDate personBirthday;

    @Revision
    @Column(name = "inn")
    @NotNull(message = "{crm.validation.participant.inn}", groups = CrmDataCompanyValidation.class)
    private String inn;

    @Revision
    @Column(name = "ogrn")
    private String ogrn;

    @Revision
    @Column(name = "bic")
    private String bic;

    @Revision
    @Column(name = "correspondent_account")
    private String correspondentAccount;

    @Revision
    @Column(name = "checking_account")
    private String checkingAccount;

    @Revision
    @Column(name = "bank_name")
    private String bankName;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_doc_type_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.participant.doc-type}", groups = CrmPersonalDataValidation.class)
    @Audited(targetAuditMode = NOT_AUDITED)
    private PersonDocTypeEntity personDocType;

    @Revision
    @Column(name = "person_doc_serial")
    @NotNull(message = "{crm.validation.participant.doc-serial}", groups = CrmPersonalDataValidation.class)
    private String personDocSerial;

    @Revision
    @Column(name = "person_doc_number")
    @NotNull(message = "{crm.validation.participant.doc-number}", groups = CrmPersonalDataValidation.class)
    private String personDocNumber;

    @Revision
    @Column(name = "person_document_issue_dt")
    @NotNull(message = "{crm.validation.participant.doc-issue-dt}", groups = CrmPersonalDataValidation.class)
    private LocalDate personDocumentIssueDt;

    @Revision
    @Column(name = "person_doc_issue_dept")
    @NotNull(message = "{crm.validation.participant.doc-issue-dept}", groups = CrmPersonalDataValidation.class)
    private String personDocIssueDept;

    @Revision
    @Column(name = "person_doc_dept_code")
    private String personDocDeptCode;

    @Revision
    @Column(name = "phone_number")
    private String phoneNumber;

    @Revision
    @Column(name = "email")
    private String email;

    @Revision
    @Column(name = "commissioner_reg_number")
    private String commissionerRegNumber;

    @Column(name = "is_imported")
    private Boolean isImported;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<ParticipantFlatEntity> participantFlats = new HashSet<>();

    @Revision
    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty(message = "{crm.validation.participant.country}", groups = CrmPersonalDataValidation.class)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<@Valid ParticipantCitizenshipEntity> participantCitizenships = new HashSet<>();

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<@Valid ParticipantAddressEntity> participantAddresses = new HashSet<>();

    @Revision
    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @AttachmentSignaturesValidation(message = "{crm.validation.identity.sign-not-set}", groups = CrmValidation.class)
    private Set<@Valid ParticipantAttachmentEntity> participantAttachments = new HashSet<>();

    @Revision
    @Transient
    private Set<ParticipantAttachmentEntity> egrulAttachments;

    @Revision
    @Transient
    private String regParticipantAddress;

    @Revision
    @Transient
    private String postalParticipantAddress;

    @Revision
    @Transient
    private String ulAddress;
}
