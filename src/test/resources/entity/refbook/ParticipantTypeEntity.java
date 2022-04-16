@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "participant_type")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
public class ParticipantTypeEntity extends InnerRefBookEntity {
}
