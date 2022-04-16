@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flat_data")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.flat_data SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@FlatDataEntityValidation(groups = CrmFlatographyValidation.class)
public class FlatDataEntity extends BaseEntity<Integer> {

    public static final Integer REQUIRED_COMPLETE_PERCENT = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flat_data_seq")
    @SequenceGenerator(name = "flat_data_seq", sequenceName = "lkau.flat_data_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flatography_id", referencedColumnName = "id", nullable = false)
    @QueryInit("buildingObject.developer")
    private FlatographyEntity flatography;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_type_id", referencedColumnName = "id")
    private FlatTypeEntity flatType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_kind_id", referencedColumnName = "id")
    private FlatKindEntity flatKind;

    @Revision
    @Column(name = "construction_number")
    private String constructionNumber;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_room_type_id", referencedColumnName = "id")
    private FlatRoomTypeEntity flatRoomType;

    @Revision
    @Column(name = "entrance")
    private String entrance;

    @Revision
    @Column(name = "floor")
    private Integer floor;

    @Revision
    @Column(name = "total_area_sq")
    private BigDecimal totalAreaSq;

    @Revision
    @Column(name = "total_reduced_sq")
    private BigDecimal totalReducedSq;

    @Revision
    @Column(name = "total_area_including_balcony_sq")
    private BigDecimal totalAreaIncludingBalconySq;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_status_id", referencedColumnName = "id")
    private FlatStatusEntity flatStatus;

    @Revision
    @Column(name = "loggia_sq")
    private BigDecimal loggiaSq;

    @Revision
    @Column(name = "balcony_sq")
    private BigDecimal balconySq;

    @Revision
    @Column(name = "terrace_sq")
    private BigDecimal terraceSq;

    @Revision
    @Column(name = "veranda_sq")
    private BigDecimal verandaSq;

    @Column(name = "note")
    private String note;

    @Formula(value = "(select count(case when _val then 1 end) * 100 / 8 from (values " +
            "(flat_type_id is not null), (flat_kind_id is not null), (flat_type_id is not null and " +
            "(case when flat_type_id = (select ft.id from lkau.flat_type ft where ft.code = 'FLAT') " +
            "then flat_room_type_id is not null else true end)), " +
            "(flat_status_id is not null), " +
            "(construction_number is not null), (entrance is not null), (floor is not null), " +
            "(total_area_sq is not null or total_reduced_sq is not null or total_area_including_balcony_sq is not null)) as s(_val))")
    @NotAudited
    private Integer completePercent;

}
