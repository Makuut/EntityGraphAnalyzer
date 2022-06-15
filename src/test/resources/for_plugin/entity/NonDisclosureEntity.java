@Entity
public class NonDisclosureEntity extends BaseEntity<Integer> {

    private Integer id;

    private DeveloperEntity developer;

    private AgreementStatusEntity status;

    private LocalDate limitationDt;

    private LocalDateTime receivedDttm;

    private AttachmentEntity attachment;

    private LocalDateTime sendDttm;

    private String idCrm;
}
