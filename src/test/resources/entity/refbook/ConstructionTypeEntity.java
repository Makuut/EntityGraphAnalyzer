@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "construction_type")
public class ConstructionTypeEntity extends InnerRefBookEntity {
}
