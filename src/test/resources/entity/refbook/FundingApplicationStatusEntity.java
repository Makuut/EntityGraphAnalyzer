@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "funding_application_status")
public class FundingApplicationStatusEntity extends InnerRefBookEntity {
}
