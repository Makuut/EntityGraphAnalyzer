@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant_demand_correction_attachment")
@SQLDelete(sql = "UPDATE lkau.participant_demand_correction_attachment SET deleted_dttm = now() WHERE participant_demand_correction_id = ? and attachment_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class ParticipantDemandCorrectionAttachmentEntity extends BaseAttachmentKeyEntity<ParticipantDemandCorrectionAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("participantDemandCorrectionId")
    @JoinColumn(name = "participant_demand_correction_id", referencedColumnName = "id", nullable = false)
    private ParticipantDemandCorrectionEntity participantDemandCorrection;
}
