@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "developer_to_commissioner")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.developer_to_commissioner SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class DeveloperToCommissionerEntity extends BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "developer_to_commissioner_seq")
    @SequenceGenerator(name = "developer_to_commissioner_seq", sequenceName = "lkau.developer_to_commissioner_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "developer_inn", length = 12, nullable = false)
    private String developerInn;

    @Column(name = "developer_ogrn", nullable = false)
    private String developerOgrn;

    @Column(name = "commissioner_reg_number", length = 50, nullable = false)
    private String commissionerRegNumber;
}
