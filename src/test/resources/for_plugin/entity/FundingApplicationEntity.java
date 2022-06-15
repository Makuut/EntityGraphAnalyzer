@Entity
public class FundingApplicationEntity extends BaseEntity<Integer> {

    private Integer id;

    private String idCrm;

    private String fundingApplicationNumber;

    private DeveloperEntity developer;

    private FundingApplicationStatusEntity status;

    private String arbitrationName;

    private LocalDateTime sendDttm;

    private String fundComment;

    private String checkingAccount;

    private String bankName;

    private String bic;

    private String correspondentAccount;

    private Set<@Valid OtherExpensesEntity> otherExpenses = new HashSet<>();

    private Set<@Valid FundingApplicationAmountEntity> fundingApplicationAmounts = new HashSet<>();

    private Set<FundingClaimEntity> fundingClaims = new HashSet<>();

    private Set<@Valid FundingApplicationDocEntity> fundingApplicationDocs = new HashSet<>();

}
