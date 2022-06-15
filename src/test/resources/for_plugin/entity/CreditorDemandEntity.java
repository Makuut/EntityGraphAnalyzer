@Entity
public class CreditorDemandEntity extends BaseEntity<Integer> {

    private Integer id;

    private DeveloperEntity developer;

    private CreditorDemandStatusEntity status;

    private Integer version;

    private String fundComment;

    private LocalDate closeDt;

    private BigDecimal firstLineDemand;

    private BigDecimal secondLineDemand;

    private BigDecimal thirdLineDemand;

    private BigDecimal fourthLineDemand;

    private BigDecimal fifthLineDemand;

    private BigDecimal developerRightsDepositDemand;

    private BigDecimal developerPropertyValue;

    private BigDecimal firstLinePayments;

    private BigDecimal secondLinePayments;

    private BigDecimal thirdLinePayments;

    private BigDecimal fourthLinePayments;

    private BigDecimal fifthLinePayments;

    private BigDecimal totalCurrentPayments;

}
