@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "participant_demand_status")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
public class ParticipantDemandStatusEntity extends InnerRefBookEntity {
}
