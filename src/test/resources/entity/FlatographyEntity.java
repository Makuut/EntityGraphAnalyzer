@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flatography")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.flatography SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@IrdRequiredTypeDocValidation(groups = CrmFlatographyValidation.class)
public class FlatographyEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @MapsId("id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    @Valid
    private BuildingObjectEntity buildingObject;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Audited(targetAuditMode = NOT_AUDITED)
    @Valid
    private ReviseEntity revise;

    @OneToMany(mappedBy = "flatography", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<@Valid IrdEntity> irds = new HashSet<>();

    @NotNull(message = "{crm.validation.flatography-status.not-set}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    @InnerRefBookCodeValidation(message = "{crm.validation.status.draft}", codes = "DRAFT", groups = CrmFlatographyValidation.class)
    private FlatographyStatusEntity status;

    @NotNull(message = "{crm.validation.flatography-version.not-set}")
    @Column(name = "version", nullable = false)
    private Integer version;

    @OneToMany(mappedBy = "flatography", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotEmpty(message = "{crm.validation.flat-data.empty}", groups = CrmFlatographyValidation.class)
    private Set<@Valid FlatDataEntity> flatDataSet = new HashSet<>();

    @Column(name = "fund_comment")
    private String fundComment;

    @OneToMany(mappedBy = "flatography", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<FlatographyCorrectionEntity> flatographyCorrections = new HashSet<>();
}
