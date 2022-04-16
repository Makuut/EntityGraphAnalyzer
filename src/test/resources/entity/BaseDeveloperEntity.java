@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseDeveloperEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "short_name", nullable = false)
    private String shortName;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "opf_short_name")
    private String opfShortName;

    @Column(name = "opf_full_name")
    private String opfFullName;

    @Column(name = "inn")
    private String inn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankruptcy_commissioner_id", referencedColumnName = "id")
    private BankruptcyCommissionerEntity bankruptcyCommissioner;
}
