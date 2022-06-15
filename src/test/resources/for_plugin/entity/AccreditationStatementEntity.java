@Entity
public class AccreditationStatementEntity extends BaseEntity<Integer> {

    private Integer id;

    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    private AccreditationStatementTypeEntity accreditationStatementType;

    private AccreditationStatementStatusEntity status;

    private String inn;

    private String snils;

    private String passportSerial;

    private String passportNumber;

    private LocalDate passportDt;

    private String regAddress;

    private String phone;

    private String email;

    private String commissionerRegNumber;

    private String sroName;

    private String sroRegNumber;

    private String accreditationNumber;

    private Integer number;

    private LocalDateTime sendDttm;

    private String incomingNumber;

    private String fundComment;

    private LocalDate registrationDt;

    private Set<AccreditationStatementDocEntity> accreditationStatementDocEntitySet = new HashSet<>();

    private LocalDate birthDt;

    private String departmentCode;

    private String experience;

    private String numberOfCases;

    private String lawViolation;

    private String reason;

    private String certificateSection;

    private String postalAddress;

    private LocalDate certificateDt;
}
