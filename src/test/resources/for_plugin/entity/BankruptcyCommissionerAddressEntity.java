@Entity
public class BankruptcyCommissionerAddressEntity extends BaseEntity<Integer> {
    private Integer id;

    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    private UUID fiasId;

    private UUID kladrId;

    private String address;

    private String fullAddress;
}
