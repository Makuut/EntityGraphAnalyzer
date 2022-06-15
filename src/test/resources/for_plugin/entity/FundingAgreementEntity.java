@Entity
public class FundingAgreementEntity extends BaseEntity<Integer> {
    private Integer id;

    private DeveloperEntity developer;

    private String number;

    private String idCrm;

    private AgreementStatusEntity status;

    private LocalDate limitationDt;

    private LocalDateTime signDttm;

    private Set<@Valid FundingAgreementProcessEntity> fundingAgreementProcessEntityTreeSet = new HashSet<>();

}
