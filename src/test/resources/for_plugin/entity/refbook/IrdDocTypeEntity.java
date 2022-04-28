@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "ird_doc_type")
public class IrdDocTypeEntity extends InnerRefBookEntity {
}
