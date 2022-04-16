@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building_object")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.building_object SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class BuildingObjectEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "buildingObject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Audited(targetAuditMode = NOT_AUDITED)
    private FlatographyEntity flatography;

    @Column(name = "egisz_id")
    private Integer egiszId;

    @Column(name = "address")
    private String address;

    @Column(name = "pd_id")
    private Integer pdId;

    @Column(name = "house_number")
    @NotNull(message = "{crm.validation.building.house-number}", groups = CrmFlatographyValidation.class)
    private String houseNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.developer}", groups = {CrmValidation.class, CrmDocumentValidation.class})
    @Valid
    private DeveloperEntity developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    private AttachmentEntity attachment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "construction_type_id", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{crm.validation.building.construction-type}", groups = CrmFlatographyValidation.class)
    private ConstructionTypeEntity constructionType;

    @Column(name = "construction_type_is_locked")
    private Boolean constructionTypeIsLocked;

    @Column(name = "is_imported")
    private Boolean isImported;

    @OneToMany(mappedBy = "buildingObject")
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<ParticipantDemandEntity> demandEntities = new HashSet<>();

    @OneToMany(mappedBy = "buildingObject", cascade = CascadeType.ALL)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<SubmittingDocEntity> submittingDocEntities = new HashSet<>();
}
