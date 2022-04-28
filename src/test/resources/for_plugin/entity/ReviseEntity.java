@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "revise")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.revise SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class ReviseEntity extends BaseEntity<Integer> {

    @Id
    private Integer id;

    @MapsId
    @OneToOne(mappedBy = "revise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    @QueryInit("buildingObject.developer")
    private FlatographyEntity flatography;

    @Column(name = "revise_dttm")
    @Audited(targetAuditMode = NOT_AUDITED)
    private LocalDateTime reviseDttm;

    @Revision
    @Column(name = "flat_cnt_disaccord")
    private String flatCntDisaccord;

    @Revision
    @Column(name = "studio_cnt_disaccord")
    private String studioCntDisaccord;

    @Revision
    @Audited(modifiedColumnName = "room_1_cnt_disaccord_mod")
    @Column(name = "room_1_cnt_disaccord")
    private String room1CntDisaccord;

    @Revision
    @Audited(modifiedColumnName = "room_2_cnt_disaccord_mod")
    @Column(name = "room_2_cnt_disaccord")
    private String room2CntDisaccord;

    @Revision
    @Audited(modifiedColumnName = "room_3_cnt_disaccord_mod")
    @Column(name = "room_3_cnt_disaccord")
    private String room3CntDisaccord;

    @Revision
    @Audited(modifiedColumnName = "room_4_cnt_disaccord_mod")
    @Column(name = "room_4_cnt_disaccord")
    private String room4CntDisaccord;

    @Revision
    @Audited(modifiedColumnName = "room_5_cnt_disaccord_mod")
    @Column(name = "room_5_cnt_disaccord")
    private String room5CntDisaccord;

    @Revision
    @Audited(modifiedColumnName = "room_6_cnt_disaccord_mod")
    @Column(name = "room_6_cnt_disaccord")
    private String room6CntDisaccord;

    @Revision
    @Column(name = "object_area_sq_disaccord")
    private String objectAreaSqDisaccord;

    @Revision
    @Column(name = "total_area_sq_disaccord")
    private String totalAreaSqDisaccord;

    @Revision
    @Column(name = "total_reduced_sq_disaccord")
    private String totalReducedSqDisaccord;

    @Revision
    @Column(name = "total_area_including_balcony_sq_disaccord")
    private String totalAreaIncludingBalconySqDisaccord;

    @Revision
    @Column(name = "non_residential_cnt_disaccord")
    private String nonResidentialCntDisaccord;

    @Revision
    @Column(name = "non_residential_sq_disaccord")
    private String nonResidentialSqDisaccord;

    @Revision
    @Column(name = "parking_cnt_disaccord")
    private String parkingCntDisaccord;

    @Revision
    @Column(name = "parking_sq_disaccord")
    private String parkingSqDisaccord;

    @Revision
    @Audited(modifiedColumnName = "storeroom_cnt_disaccord_mod")
    @Column(name = "storeroom_cnt_disaccord")
    private String storeRoomCntDisaccord;

    @Revision
    @Audited(modifiedColumnName = "storeroom_sq_disaccord_mod")
    @Column(name = "storeroom_sq_disaccord")
    private String storeRoomSqDisaccord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.revise.attachment.not-null}", groups = CrmFlatographyValidation.class)
    private AttachmentEntity attachment;

    @OneToMany(mappedBy = "revise", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<ReviseDataEntity> revisies = new HashSet<>();
}
