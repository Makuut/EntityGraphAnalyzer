@Entity
public class FundingApplicationAmountEntity extends BaseEntity<Integer> {

    private Integer id;

    private FundingApplicationEntity fundingApplication;

    private FundingTypeEntity fundingType;

    private InvolvedPersonTypeEntity involvedPersonType;

    private BigDecimal amount;
}
