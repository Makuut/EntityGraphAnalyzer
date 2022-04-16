@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "funding_claim_doc_type")
public class FundingClaimDocTypeEntity extends InnerRefBookEntity {
}
