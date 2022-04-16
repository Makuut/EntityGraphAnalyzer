@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "flat_kind")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
public class FlatKindEntity extends InnerRefBookEntity {
}
