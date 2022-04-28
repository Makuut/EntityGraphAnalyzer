@Entity
@Getter
@Setter
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@NoArgsConstructor
@Table(name = "submitting_doc_attachment_type")
public class SubmittingDocAttachmentTypeEntity extends InnerRefBookEntity {
}
