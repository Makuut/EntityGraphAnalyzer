@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submitting_doc_submitting_doc_missing")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(targetAuditMode = NOT_AUDITED)
@AuditOverride(forClass = BaseEntity.class)
@SQLDelete(sql = "UPDATE lkau.submitting_doc_submitting_doc_missing SET deleted_dttm = now() WHERE submitting_doc_id = ? and submitting_doc_missing_id = ?")
public class SubmittingDocSubmittingDocMissingEntity extends BaseEntity<SubmittingDocKey> {
    @EmbeddedId
    private SubmittingDocKey id;

    @Column(name = "limitation_dt")
    private LocalDate limitationDt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("submittingDocId")
    @JoinColumn(name = "submitting_doc_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.documents.not-set}", groups = CrmDocumentMissingValidation.class)
    private SubmittingDocEntity submittingDoc;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId("submittingDocMissingId")
    @JoinColumn(name = "submitting_doc_missing_id", referencedColumnName = "id")
    private SubmittingDocMissingEntity submittingDocMissing;
}
