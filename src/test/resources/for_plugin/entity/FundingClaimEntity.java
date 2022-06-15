@Entity
public class FundingClaimEntity extends BaseEntity<Integer> {

    private Integer id;

    private FundingApplicationEntity fundingApplication;

    private FundingClaimStatusEntity status;

    private String fundingClaimNumber;

    private String contractName;

    private LocalDate contractDt;

    private String contractNumber;

    private FundingTypeEntity fundingType;

    private LocalDate paymentStartDt;

    private LocalDate paymentEndDt;

    private LocalDate contractPaymentDt;

    private BigDecimal paymentAmt;

    private BigDecimal vat;

    private BigDecimal paymentAmtVat;

    private ActTypeEntity actType;

    private String actNumber;

    private LocalDate actDt;

    private String paymentNumber;

    private LocalDate paymentDt;

    private String invoiceNumber;

    private LocalDate invoiceDt;

    private String contractorName;

    private String contractorInn;

    private String contractorOgrn;

    private String contractorCheckingAccount;

    private String contractorBankName;

    private String contractorBankBic;

    private String fundComment;

    private String contractorBankCorrespondentAccount;

    private LocalDateTime sendDttm;

    private Set<@Valid FundingClaimAttachmentEntity> fundingClaimAttachments = new HashSet<>();

    private InvolvedPersonTypeEntity involvedPersonType;
}
