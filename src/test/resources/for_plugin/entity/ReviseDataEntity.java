@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "revise_data")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.revise SET deleted_dttm = now() WHERE revise_id = ? and revise_data_type = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class ReviseDataEntity extends BaseEntity<ReviseDataKey> implements FlatographyRevise<Number> {

    @EmbeddedId
    private ReviseDataKey id;

    @MapsId("reviseId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "revise_id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    private ReviseEntity revise;

    @Column(name = "flat_cnt")
    private Integer flatCnt;

    @Column(name = "studio_cnt")
    private Integer studioCnt;

    @Audited(modifiedColumnName = "room_1_cnt_mod")
    @Column(name = "room_1_cnt")
    private Integer room1Cnt;

    @Audited(modifiedColumnName = "room_2_cnt_mod")
    @Column(name = "room_2_cnt")
    private Integer room2Cnt;

    @Audited(modifiedColumnName = "room_3_cnt_mod")
    @Column(name = "room_3_cnt")
    private Integer room3Cnt;

    @Audited(modifiedColumnName = "room_4_cnt_mod")
    @Column(name = "room_4_cnt")
    private Integer room4Cnt;

    @Audited(modifiedColumnName = "room_5_cnt_mod")
    @Column(name = "room_5_cnt")
    private Integer room5Cnt;

    @Audited(modifiedColumnName = "room_6_cnt_mod")
    @Column(name = "room_6_cnt")
    private Integer room6Cnt;

    @Column(name = "object_area_sq")
    private BigDecimal objectAreaSq;

    @Column(name = "total_area_sq")
    private BigDecimal totalAreaSq;

    @Column(name = "total_reduced_sq")
    private BigDecimal totalReducedSq;

    @Column(name = "total_area_including_balcony_sq")
    private BigDecimal totalAreaIncludingBalconySq;

    @Column(name = "non_residential_cnt")
    private Integer nonResidentialCnt;

    @Column(name = "non_residential_sq")
    private BigDecimal nonResidentialSq;

    @Column(name = "parking_cnt")
    private Integer parkingCnt;

    @Column(name = "parking_sq")
    private BigDecimal parkingSq;

    @Audited(modifiedColumnName = "storeroom_cnt_mod")
    @Column(name = "storeroom_cnt")
    private Integer storeRoomCnt;

    @Audited(modifiedColumnName = "storeroom_sq_mod")
    @Column(name = "storeroom_sq")
    private BigDecimal storeRoomSq;
}
