@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bankruptcy_commissioner_address", schema = "lkau")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class BankruptcyCommissionerAddressEntity extends BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    private Integer id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankruptcy_commissioner_id", referencedColumnName = "id", nullable = false)
    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    @Column(name = "fias_id")
    private UUID fiasId;

    @Column(name = "kladr_id")
    private UUID kladrId;

    @Column(name = "address")
    private String address;

    @Column(name = "full_address")
    private String fullAddress;
}
