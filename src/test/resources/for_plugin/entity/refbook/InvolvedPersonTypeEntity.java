@Entity
@Getter
@Setter
@NoArgsConstructor
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@Table(name = "involved_person_type")
public class InvolvedPersonTypeEntity extends InnerRefBookEntity {
}
