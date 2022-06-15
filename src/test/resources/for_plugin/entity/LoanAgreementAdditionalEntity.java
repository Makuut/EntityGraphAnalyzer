@Entity
public class LoanAgreementAdditionalEntity extends BaseEntity<Integer> {

    private Integer id;

    private ParticipantDemandEntity participantDemand;

    private String docNumber;

    private LocalDate docDt;

    private String docRegNumber;

    private LocalDate docRegDt;

    private Set<LoanAgreementAdditionalAttachmentEntity> loanAgreementAdditionalAttachments = new HashSet<>();
}
