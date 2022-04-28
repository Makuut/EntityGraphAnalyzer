@Entity
@Table(name = "signature_attachment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE lkau.signature_attachment SET deleted_dttm = now() WHERE signature_id = ? and  attachment_id = ? ")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class SignatureAttachmentEntity extends BaseEntity<SignatureAttachmentKey> {

    @EmbeddedId
    private SignatureAttachmentKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("attachmentId")
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private AttachmentEntity attachment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId("signatureId")
    @JoinColumn(name = "signature_id", referencedColumnName = "id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private AttachmentEntity signature;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "stamped_attachment_id", referencedColumnName = "id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private AttachmentEntity stampedAttachment;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "start_dttm")
    private LocalDateTime startDttm;

    @Column(name = "end_dttm")
    private LocalDateTime endDttm;
}
