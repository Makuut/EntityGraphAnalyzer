@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accreditation_statement_doc_attachment")
@SQLDelete(sql = "UPDATE lkau.accreditation_statement_doc_attachment SET deleted_dttm = now() WHERE accreditation_statement_doc_id = ? and attachment_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class AccreditationStatementDocAttachmentEntity extends BaseAttachmentKeyEntity<AccreditationStatementDocAttachmentKey>{

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accreditationStatementDocId")
    @JoinColumn(name = "accreditation_statement_doc_id", referencedColumnName = "id")
    private AccreditationStatementDocEntity accreditationStatementDoc;
}
