@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "accreditation_statement_type")
public class AccreditationStatementTypeEntity extends InnerRefBookEntity {
}
