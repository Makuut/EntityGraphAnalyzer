@Entity
public class FundingAgreementProcessEntity extends BaseEntity<Integer> {
    private Integer id;

    private FundingAgreementEntity fundingAgreement;

    private ProcessTypeEntity processType;

    private ProcessStatusEntity processStatus;

    private AttachmentEntity attachment;

    private LocalDateTime statusDttm;

    private String comment;

}
