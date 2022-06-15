@Entity
public class SubmittingDocAttachmentEntity extends BaseAttachmentKeyEntity<SubmittingDocAttachmentKey> {

    private SubmittingDocEntity submittingDoc;

    private SubmittingDocAttachmentTypeEntity submittingDocAttachmentType;
}
