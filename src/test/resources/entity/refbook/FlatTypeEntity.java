@Entity
@Getter
@Setter
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@NoArgsConstructor
@Table(name = "flat_type")
public class FlatTypeEntity extends InnerRefBookEntity {
}
