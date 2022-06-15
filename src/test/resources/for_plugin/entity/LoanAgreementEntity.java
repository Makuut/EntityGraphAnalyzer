@Entity
public class LoanAgreementEntity extends BaseEntity<Integer> {

    private Integer id;

    private ParticipantDemandEntity participantDemand;

    private String mortgageeName;

    private String mortgageeInn;

    private String docNumber;

    private LocalDate docDt;

    private Set<LoanAgreementAttachmentEntity> loanAgreementAttachments = new HashSet<>();
}
