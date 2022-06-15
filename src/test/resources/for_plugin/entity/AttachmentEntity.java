@Entity
public class AttachmentEntity extends BaseEntity<Integer> {

    private Integer id;

    private String fileName;

    private String filePath;

    private String fileFormat;

    private Integer fileSize;

    private Set<SignatureAttachmentEntity> signatureAttachments = new HashSet<>();

    private Set<SignatureAttachmentEntity> signatures = new HashSet<>();

    private Set<SignatureAttachmentEntity> stampedAttachments = new HashSet<>();

    private Set<SubmittingDocAttachmentEntity> submittingDocAttachments = new HashSet<>();

    private Set<DemandParameterAttachmentEntity> demandParameterAttachments = new HashSet<>();

    private Set<FundingApplicationDocAttachmentEntity> fundingApplicationDocAttachments = new HashSet<>();

    private Set<FundingClaimAttachmentEntity> fundingClaimAttachments = new HashSet<>();

    private Set<LoanAgreementAdditionalAttachmentEntity> loanAgreementAdditionalAttachments = new HashSet<>();

    private Set<LoanAgreementAttachmentEntity> loanAgreementAttachments = new HashSet<>();

    private Set<ParticipantAttachmentEntity> participantAttachments = new HashSet<>();

    private Set<ParticipantDemandCorrectionAttachmentEntity> participantDemandCorrectionAttachments = new HashSet<>();

    private Set<ReasonDocAttachmentEntity> reasonDocAttachments = new HashSet<>();

    private Set<RightsConcessionAttachmentEntity> rightsConcessionAttachments = new HashSet<>();

    private FundingAgreementProcessEntity fundingAgreementProcessEntity;

}
