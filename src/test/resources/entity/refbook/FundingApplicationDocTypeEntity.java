@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "funding_application_doc_type")
public class FundingApplicationDocTypeEntity extends InnerRefBookEntity {
}
