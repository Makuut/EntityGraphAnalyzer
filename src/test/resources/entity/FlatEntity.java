@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flat")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.flat SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@FlatRoomTypeNotNullIfIsFlatEntity(groups = CrmValidation.class)
public class FlatEntity extends BaseEntity<Integer> {

    @Id
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    private ParticipantDemandEntity participantDemand;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_type_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.flat.flat-type}", groups = CrmValidation.class)
    @Audited(targetAuditMode = NOT_AUDITED)
    private FlatTypeEntity flatType;

    @Revision
    @Column(name = "reason_doc_price")
    @NotNull(message = "{crm.validation.flat.reason-doc-price}", groups = CrmValidation.class)
    private BigDecimal reasonDocPrice;

    @Revision
    @Column(name = "rights_concession_price")
    private BigDecimal rightsConcessionPrice;

    @Revision
    @Column(name = "construction_number")
    @NotNull(message = "{crm.validation.flat.construction-number}", groups = CrmValidation.class)
    private String constructionNumber;

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
    @Column(name = "loggia_sq")
    private BigDecimal loggiaSq;

    @Revision
    @Column(name = "balcony_sq")
    private BigDecimal balconySq;

    @Revision
    @Column(name = "terrace_sq")
    private BigDecimal terraceSq;

    @Revision
    @Column(name = "compensation_area_sq")
    @NotNull(message = "{crm.validation.flat.compensation-area}", groups = CrmValidation.class)
    private BigDecimal compensationAreaSq;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compensation_area_type_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.flat.compensation-area-type}", groups = CrmValidation.class)
    private CompensationAreaTypeEntity compensationAreaType;

    @Revision
    @Column(name = "floor")
    private Integer floor;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_room_type_id", referencedColumnName = "id")
    private FlatRoomTypeEntity flatRoomType;

    @Revision
    @Column(name = "entrance")
    private String entrance;

}
