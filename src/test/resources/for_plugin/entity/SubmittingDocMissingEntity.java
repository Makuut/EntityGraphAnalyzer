@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submitting_doc_missing")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.submitting_doc_missing SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class SubmittingDocMissingEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "submitting_doc_missing_seq")
    @SequenceGenerator(name = "submitting_doc_missing_seq", sequenceName = "lkau.submitting_doc_missing_seq",
            allocationSize = 1)
    private Integer id;

    @Column(name = "absence_reason")
    private String absenceReason;

    @Column(name = "planned_dt")
    private LocalDate plannedDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "missing_doc_state_id", referencedColumnName = "id")
    private MissingDocStateEntity missingDocState;

    @OneToMany(mappedBy = "submittingDocMissing", orphanRemoval = true, cascade = CascadeType.ALL)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<SubmittingDocRequestEntity> submittingDocRequests = new HashSet<>();

    @OneToMany(mappedBy = "submittingDocMissing", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotEmpty(groups = CrmDocumentValidation.class, message = "{crm.validation.documents.not-set}")
    private Set<@Valid SubmittingDocSubmittingDocMissingEntity> submittingDocSubmittingDocMissingEntities = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.document.missing-attachment}", groups = CrmDocumentMissingValidation.class)
    @Valid
    private AttachmentEntity attachment;

}
