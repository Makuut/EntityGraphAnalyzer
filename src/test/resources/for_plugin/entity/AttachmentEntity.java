@Entity
@Table(name = "attachment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE lkau.attachment SET deleted_dttm = now() WHERE id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class AttachmentEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment_seq")
    @SequenceGenerator(name = "attachment_seq", sequenceName = "lkau.attachment_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_format", nullable = false)
    private String fileFormat;

    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<SignatureAttachmentEntity> signatureAttachments = new HashSet<>();

    @OneToMany(mappedBy = "signature", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<SignatureAttachmentEntity> signatures = new HashSet<>();

    @OneToMany(mappedBy = "stampedAttachment", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<SignatureAttachmentEntity> stampedAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<SubmittingDocAttachmentEntity> submittingDocAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<DemandParameterAttachmentEntity> demandParameterAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<FundingApplicationDocAttachmentEntity> fundingApplicationDocAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<FundingClaimAttachmentEntity> fundingClaimAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<LoanAgreementAdditionalAttachmentEntity> loanAgreementAdditionalAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<LoanAgreementAttachmentEntity> loanAgreementAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<ParticipantAttachmentEntity> participantAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<ParticipantDemandCorrectionAttachmentEntity> participantDemandCorrectionAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<ReasonDocAttachmentEntity> reasonDocAttachments = new HashSet<>();

    @OneToMany(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<RightsConcessionAttachmentEntity> rightsConcessionAttachments = new HashSet<>();

    @OneToOne(mappedBy = "attachment", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private FundingAgreementProcessEntity fundingAgreementProcessEntity;

    @Transient
    @NotNull(groups = {CrmDocumentValidation.class, CrmNonDisclosureValidation.class}, message = "{crm.validation.documents.sign-not-set}")
    private AttachmentEntity signature;

    @PostLoad
    public void postLoad() {
        signature = signatureAttachments.stream()
                .findFirst()
                .map(SignatureAttachmentEntity::getSignature)
                .orElse(null);
    }
}
