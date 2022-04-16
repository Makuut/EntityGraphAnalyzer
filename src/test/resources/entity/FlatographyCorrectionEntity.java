@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "flatography_correction")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.flatography_correction SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class FlatographyCorrectionEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flatography_correction_seq")
    @SequenceGenerator(name = "flatography_correction_seq", sequenceName = "lkau.flatography_correction_seq",
            allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flatography_id", referencedColumnName = "id", nullable = false)
    @QueryInit("buildingObject.developer")
    private FlatographyEntity flatography;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Revision
    @Column(name = "correction")
    private String correction;
}
