@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "developer_event")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.developer_event SET deleted_dttm = now() WHERE developer_id = ? and event_id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class DeveloperEventEntity extends BaseEventKeyEntity<DeveloperEventKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("developerId")
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    private DeveloperEntity developer;
}
