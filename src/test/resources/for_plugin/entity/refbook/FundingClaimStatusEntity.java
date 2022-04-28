@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "funding_claim_status")
public class FundingClaimStatusEntity extends InnerRefBookEntity {
}
