@Getter
@Setter
@NoArgsConstructor
@Entity
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "decision_type")
public class DecisionTypeEntity extends InnerRefBookEntity{
}
