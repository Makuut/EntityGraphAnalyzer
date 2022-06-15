@Entity
public class EmployeeEntity extends BaseEntity<UUID> {

    private UUID id;

    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    private String snils;

    private EmployeeStatusEntity status;

    private LocalDateTime addedDttm;
}
