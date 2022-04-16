@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ownership_type")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
public class OwnershipTypeEntity extends InnerRefBookEntity {
}
