@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reason_doc_attachment")
@SQLDelete(sql = "UPDATE lkau.reason_doc_attachment SET deleted_dttm = now() WHERE reason_doc_id = ? and attachment_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class ReasonDocAttachmentEntity extends BaseAttachmentKeyEntity<ReasonDocAttachmentKey> {


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("reasonDocId")
    @JoinColumn(name = "reason_doc_id", referencedColumnName = "id")
    private ReasonDocEntity reasonDoc;

}
