@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ird")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.ird SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@BaseEntityAnyNotNullColumnFieldsValidation(groups = CrmFlatographyValidation.class, name = "Документ ИРД")
public class IrdEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ird_seq")
    @SequenceGenerator(name = "ird_seq", sequenceName = "lkau.ird_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flatography_id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    @QueryInit("buildingObject.developer")
    private FlatographyEntity flatography;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ird_doc_type_id", referencedColumnName = "id", nullable = false)
    private IrdDocTypeEntity irdDocType;

    @Revision
    @Column(name = "doc_dt")
    private LocalDate docDt;

    @Revision
    @Column(name = "doc_number")
    private String docNumber;

    @Revision
    @Column(name = "flat_cnt")
    private Integer flatCnt;

    @Revision
    @Column(name = "studio_cnt")
    private Integer studioCnt;

    @Revision
    @Audited(modifiedColumnName = "room_1_cnt_mod")
    @Column(name = "room_1_cnt")
    private Integer room1Cnt;

    @Revision
    @Audited(modifiedColumnName = "room_2_cnt_mod")
    @Column(name = "room_2_cnt")
    private Integer room2Cnt;

    @Revision
    @Audited(modifiedColumnName = "room_3_cnt_mod")
    @Column(name = "room_3_cnt")
    private Integer room3Cnt;

    @Revision
    @Audited(modifiedColumnName = "room_4_cnt_mod")
    @Column(name = "room_4_cnt")
    private Integer room4Cnt;

    @Revision
    @Audited(modifiedColumnName = "room_5_cnt_mod")
    @Column(name = "room_5_cnt")
    private Integer room5Cnt;

    @Revision
    @Audited(modifiedColumnName = "room_6_cnt_mod")
    @Column(name = "room_6_cnt")
    private Integer room6Cnt;

    @Revision
    @Column(name = "object_area_sq")
    private BigDecimal objectAreaSq;

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
    @Column(name = "non_residential_cnt")
    private Integer nonResidentialCnt;

    @Revision
    @Column(name = "non_residential_sq")
    private BigDecimal nonResidentialSq;

    @Revision
    @Column(name = "parking_cnt")
    private Integer parkingCnt;

    @Revision
    @Column(name = "parking_sq")
    private BigDecimal parkingSq;

    @Revision
    @Audited(modifiedColumnName = "storeroom_cnt_mod")
    @Column(name = "storeroom_cnt")
    private Integer storeRoomCnt;

    @Revision
    @Audited(modifiedColumnName = "storeroom_sq_mod")
    @Column(name = "storeroom_sq")
    private BigDecimal storeRoomSq;

    @Revision
    @Audited(modifiedColumnName = "disabled_fields_mod")
    @Column(name = "disabled_fields")
    private String disabledFields;
}
