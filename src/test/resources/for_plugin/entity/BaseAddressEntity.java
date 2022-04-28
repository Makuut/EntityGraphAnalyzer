@Getter
@Setter
@MappedSuperclass
public abstract class BaseAddressEntity<I> extends BaseEntity<I> {
    @Column(name = "fias_id")
    private UUID fiasId;

    @Column(name = "kladr_id")
    private UUID kladrId;

    @Column(name = "address")
    private String address;

    @Column(name = "full_address")
    private String fullAddress;
}
