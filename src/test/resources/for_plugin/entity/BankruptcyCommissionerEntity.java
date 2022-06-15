@Entity
public class BankruptcyCommissionerEntity extends BaseEntity<UUID> {
    private UUID id;

    private UUID idmId;

    private String name;

    private String surname;

    private String patronymic;

    private String snils;

    private String inn;

    private String passportSerial;

    private String passportNumber;

    private LocalDate passportDt;

    private Set<BankruptcyCommissionerAddressEntity> bankruptcyCommissionerAddress = new HashSet<>();

    private String regAddress;

    private String phone;

    private String email;

    private String commissionerRegNumber;

    private SroEntity sro;

    private String accreditationNumber;

    private LocalDate accreditationValidityDt;

    private Set<AccreditationStatementEntity> accreditationStatementEntitySet = new HashSet<>();

    private Set<EmployeeEntity> employeeEntitySet = new HashSet<>();

    private BankruptcyCommissionerEntity parentBankruptcyCommissioner;

}
