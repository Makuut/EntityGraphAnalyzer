@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submitting_doc_attachment")
@SQLDelete(sql = "UPDATE lkau.submitting_doc_attachment SET deleted_dttm = now() WHERE submitting_doc_id = ? and attachment_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class SubmittingDocAttachmentEntity extends BaseAttachmentKeyEntity<SubmittingDocAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("submittingDocId")
    @JoinColumn(name = "submitting_doc_id", referencedColumnName = "id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private SubmittingDocEntity submittingDoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitting_doc_attachment_type_id", referencedColumnName = "id", nullable = false)
    @NotNull(groups = CrmDocumentValidation.class, message = "{crm.validation.documents.type-attachment}")
    private SubmittingDocAttachmentTypeEntity submittingDocAttachmentType;
}
