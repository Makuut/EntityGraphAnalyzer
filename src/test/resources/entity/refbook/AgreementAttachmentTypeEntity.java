@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "agreement_attachment_type")
public class AgreementAttachmentTypeEntity extends InnerRefBookEntity {
}
