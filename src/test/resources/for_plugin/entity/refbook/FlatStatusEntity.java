@Entity
@Getter
@Setter
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@NoArgsConstructor
@Table(name = "flat_status")
public class FlatStatusEntity extends InnerRefBookEntity {
}
