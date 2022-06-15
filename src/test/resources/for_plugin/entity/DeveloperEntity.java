@Entity
public class DeveloperEntity extends BaseDeveloperEntity {

    private CreditorDemandEntity creditorDemand;

    private FundingApplicationEntity fundingApplication;

    private NonDisclosureEntity nonDisclosure;

    private FundingAgreementEntity fundingAgreement;

    private String inn;

    private String ogrn;

    private String address;

    private String arbitrationRuling;

    private LocalDate bankruptcyDt;

    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    private Boolean isImported;

    private RegionEntity region;

    private Set<ReasonDocEntity> reasonDocs = new HashSet<>();

    private Set<BuildingObjectEntity> buildingObjects = new HashSet<>();

}
