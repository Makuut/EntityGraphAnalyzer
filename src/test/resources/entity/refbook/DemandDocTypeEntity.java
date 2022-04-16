@Getter
@Setter
@NoArgsConstructor
@Entity
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "demand_doc_type")
public class DemandDocTypeEntity extends InnerRefBookEntity {
}
