@Getter
@Setter
@NoArgsConstructor
@Entity
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "submitting_doc_status")
public class SubmittingDocStatusEntity extends InnerRefBookEntity {
}
