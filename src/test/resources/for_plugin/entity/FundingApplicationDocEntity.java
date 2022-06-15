@Entity
public class FundingApplicationDocEntity extends BaseEntity<Integer> {

    private Integer id;

    private FundingApplicationEntity fundingApplication;

    private String otherDocName;

    private FundingApplicationDocTypeEntity fundingApplicationDocType;

}
