@Entity
public class ParticipantAttachmentEntity extends BaseAttachmentKeyEntity<ParticipantAttachmentKey> {

    private DemandDocTypeEntity demandDocType;

    private ParticipantEntity participant;
}
