@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submitting_doc")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.submitting_doc SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class SubmittingDocEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "submitting_doc_seq")
    @SequenceGenerator(name = "submitting_doc_seq", sequenceName = "lkau.submitting_doc_seq",
            allocationSize = 1)
    @NotNull(groups = CrmDocumentValidation.class, message = "{crm.validation.document-not-set}")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitting_doc_type_id", referencedColumnName = "id", nullable = false)
    @NotNull(groups = CrmDocumentValidation.class, message = "{crm.validation.documents.type}")
    private SubmittingDocTypeEntity submittingDocType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_object_id", referencedColumnName = "id", nullable = false)
    @NotNull(groups = CrmDocumentValidation.class, message = "{crm.validation.flat.building}")
    @Valid
    private BuildingObjectEntity buildingObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.submitting-doc-status.not-set}")
    private SubmittingDocStatusEntity status;

    @Column(name = "limitation_dt")
    private LocalDate limitationDt;

    @Column(name = "send_dttm")
    private LocalDateTime sendDttm;

    @Column(name = "fund_comment")
    private String fundComment;

    @Column(name = "terms_violation_reason")
    private String termsViolationReason;

    @OneToMany(mappedBy = "submittingDoc", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<@Valid SubmittingDocSubmittingDocMissingEntity> submittingDocSubmittingDocMissingEntities = new HashSet<>();

    @OneToMany(mappedBy = "submittingDoc", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<SubmittingDocAttachmentEntity> submittingDocAttachments = new HashSet<>();

    @OneToMany(mappedBy = "submittingDoc", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<SubmittingDocEventEntity> submittingDocEvents = new HashSet<>();

    /**
     * Отдает вложения добавленные после отправки в фонд
     * @return Набор вложений
     */
    @Valid
    @NotEmpty(groups = CrmDocumentValidation.class, message = "{crm.validation.documents.attachment}")
    public Set<SubmittingDocAttachmentEntity> getUnsentAttachments() {
        if (CollectionUtils.isEmpty(submittingDocAttachments)) {
            return Collections.emptySet();
        }

        return submittingDocAttachments
                .stream()
                .filter(attachment -> getSendDttm() == null || attachment.getCreatedDttm().isAfter(getSendDttm()))
                .collect(Collectors.toSet());
    }
}
