@Entity
public class SubmittingDocMissingEntity extends BaseEntity<Integer> {

    private Integer id;

    private String absenceReason;

    private LocalDate plannedDt;

    private MissingDocStateEntity missingDocState;

    private Set<SubmittingDocRequestEntity> submittingDocRequests = new HashSet<>();

    private Set<@Valid SubmittingDocSubmittingDocMissingEntity> submittingDocSubmittingDocMissingEntities = new HashSet<>();

    private AttachmentEntity attachment;

}
