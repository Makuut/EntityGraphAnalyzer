@Entity
@Getter
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Setter
@NoArgsConstructor
@Table(name = "missing_doc_state")
public class MissingDocStateEntity extends InnerRefBookEntity {
}
