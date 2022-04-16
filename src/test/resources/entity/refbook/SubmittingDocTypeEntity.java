@Entity
@Getter
@Setter
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@NoArgsConstructor
@Table(name = "submitting_doc_type")
public class SubmittingDocTypeEntity extends InnerRefBookEntity {
}
