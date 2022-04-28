@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant_attachment")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.participant_attachment SET deleted_dttm = now() WHERE participant_id = ? and attachment_id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class ParticipantAttachmentEntity extends BaseAttachmentKeyEntity<ParticipantAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_doc_type_id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{participant-attachment-entity.demand-doc-type.not-set}")
    private DemandDocTypeEntity demandDocType;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("participantId")
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private ParticipantEntity participant;
}
