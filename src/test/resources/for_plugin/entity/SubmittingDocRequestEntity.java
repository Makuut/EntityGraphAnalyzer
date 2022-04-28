@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submitting_doc_request")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.submitting_doc_request SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class SubmittingDocRequestEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "submitting_doc_request_seq")
    @SequenceGenerator(name = "submitting_doc_request_seq", sequenceName = "lkau.submitting_doc_request_seq",
            allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitting_doc_missing_id", referencedColumnName = "id", nullable = false)
    private SubmittingDocMissingEntity submittingDocMissing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{submitting-doc-missing.request-attachment.not-set}", groups = CrmDocumentMissingValidation.class)
    private AttachmentEntity attachment;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "request_number")
    private String requestNumber;

    @Column(name = "request_dt")
    private LocalDate requestDt;
}
