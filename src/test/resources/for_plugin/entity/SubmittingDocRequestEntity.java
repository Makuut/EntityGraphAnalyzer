@Entity
@Table(name = "submitting_doc_request")
public class SubmittingDocRequestEntity extends BaseEntity<Integer> {

    private Integer id;

    private SubmittingDocMissingEntity submittingDocMissing;

    private AttachmentEntity attachment;

    private String orgName;

    private String requestNumber;

    private LocalDate requestDt;
}
