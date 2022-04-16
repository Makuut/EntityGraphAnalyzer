@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant_demand_event")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.participant_demand_event SET deleted_dttm = now() WHERE participant_demand_id = ? and event_id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class ParticipantDemandEventEntity extends BaseEventKeyEntity<ParticipantDemandEventKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("participantDemandId")
    @JoinColumn(name = "participant_demand_id", referencedColumnName = "id")
    private ParticipantDemandEntity participantDemand;
}
