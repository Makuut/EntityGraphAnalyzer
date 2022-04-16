@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "compensation_area_type")
public class CompensationAreaTypeEntity extends InnerRefBookEntity {
}
