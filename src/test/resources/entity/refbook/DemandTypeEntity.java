@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Entity
@Table(name = "demand_type")
public class DemandTypeEntity extends InnerRefBookEntity {
}
