@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant_flat_attachment")
@SQLDelete(sql = "UPDATE lkau.participant_flat_attachment SET deleted_dttm = now() WHERE participant_flat_id = ? and attachment_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class ParticipantFlatAttachmentEntity extends BaseAttachmentKeyEntity<ParticipantFlatAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("participantFlatId")
    @JoinColumn(name = "participant_flat_id", referencedColumnName = "id")
    private ParticipantFlatEntity participantFlat;

}
