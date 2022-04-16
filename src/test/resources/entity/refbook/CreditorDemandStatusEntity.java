@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "creditor_demand_status")
public class CreditorDemandStatusEntity extends InnerRefBookEntity {
}
