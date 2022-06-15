@Entity
public class DeveloperFundingApplicationViewEntity extends BaseDeveloperEntity {

    private LocalDateTime lastUpdatedDttm;

    private BigDecimal paidApplicationsSum;

    private Long paidApplicationsCount;

    private Long applicationsCount;

    private FundingApplicationStatusEntity fundingApplicationStatus;

    private AgreementStatusEntity agreementStatusEntity;
}
