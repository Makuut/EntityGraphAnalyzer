@Getter
@Setter
@MappedSuperclass
@AuditOverride(forClass = BaseEntity.class)
public class BaseAttachmentKeyEntity<K extends BaseAttachmentKey> extends BaseEntity<K> {

    @EmbeddedId
    private K id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("attachmentId")
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    @Valid
    private AttachmentEntity attachment;
}
