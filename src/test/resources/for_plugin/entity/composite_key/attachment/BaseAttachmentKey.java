@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@MappedSuperclass
public class BaseAttachmentKey implements Serializable {

    @Column(name = "attachment_id")
    private Integer attachmentId;
}
