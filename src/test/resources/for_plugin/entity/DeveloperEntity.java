@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "developer")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class DeveloperEntity extends BaseDeveloperEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CreditorDemandEntity creditorDemand;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Audited(targetAuditMode = NOT_AUDITED)
    private FundingApplicationEntity fundingApplication;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Audited(targetAuditMode = NOT_AUDITED)
    private NonDisclosureEntity nonDisclosure;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Audited(targetAuditMode = NOT_AUDITED)
    private FundingAgreementEntity fundingAgreement;

    @Column(name = "inn", nullable = false)
    @NotNull(message = "{crm.validation.developer.inn}", groups = {CrmValidation.class, CrmFlatographyValidation.class,
            CrmDocumentValidation.class, CrmPetitionValidation.class})
    private String inn;

    @Column(name = "ogrn", nullable = false)
    private String ogrn;

    @Column(name = "address")
    private String address;

    @Column(name = "arbitration_ruling")
    @NotNull(message = "{crm.validation.developer.ruling}", groups = {CrmValidation.class, CrmPetitionValidation.class})
    private String arbitrationRuling;

    @Column(name = "bankruptcy_dt")
    @NotNull(message = "{crm.validation.developer.bankruptcy-date}", groups = {CrmValidation.class, CrmPetitionValidation.class})
    private LocalDate bankruptcyDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankruptcy_commissioner_id", referencedColumnName = "id")
    @Valid
    @NotNull(message = "{crm.validation.developer.bankruptcy-commissioner}", groups = CrmValidation.class)
    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    @Column(name = "is_imported")
    private Boolean isImported;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.developer.region}", groups = CrmPetitionValidation.class)
    private RegionEntity region;

    @OneToMany(mappedBy = "developer")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<ReasonDocEntity> reasonDocs = new HashSet<>();

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotEmpty(message = "{crm.validation.funding-application.building-objects-not-set}", groups = CrmPetitionValidation.class)
    private Set<BuildingObjectEntity> buildingObjects = new HashSet<>();

    /**
     * Сеттер для CreditorDemandEntity с автоматическим проставлением двухсторонней связи
     *
     * @param creditorDemand Сущность РТК
     */
    public void setCreditorDemand(CreditorDemandEntity creditorDemand) {
        if (creditorDemand != null) {
            creditorDemand.setDeveloper(this);
        }
        this.creditorDemand = creditorDemand;
    }
}
