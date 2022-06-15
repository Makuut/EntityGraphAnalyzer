@Entity
public class SignatureAttachmentEntity extends BaseEntity<SignatureAttachmentKey> {

    private SignatureAttachmentKey id;

    private AttachmentEntity attachment;

    private AttachmentEntity signature;

    private AttachmentEntity stampedAttachment;

    private String serialNumber;

    private String commonName;

    private LocalDateTime startDttm;

    private LocalDateTime endDttm;
}
