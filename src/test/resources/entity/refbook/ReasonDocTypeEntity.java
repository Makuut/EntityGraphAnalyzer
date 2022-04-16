@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "reason_doc_type")
public class ReasonDocTypeEntity extends InnerRefBookEntity {
}
