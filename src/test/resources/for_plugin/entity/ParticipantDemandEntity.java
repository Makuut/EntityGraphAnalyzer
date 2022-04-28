@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant_demand")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
@SQLDelete(sql = "UPDATE lkau.participant_demand SET deleted_dttm = now() WHERE id = ?")
@DemandTotalOwnershipValidation(groups = CrmValidation.class)
public class ParticipantDemandEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_demand_seq")
    @SequenceGenerator(name = "participant_demand_seq", sequenceName = "lkau.participant_demand_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "id_crm")
    private String idCrm;

    @Column(name = "is_imported")
    private Boolean isImported;

    @OneToOne(mappedBy = "participantDemand", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @NotNull(message = "{crm.validation.demand-parameter}", groups = CrmValidation.class)
    @Audited(targetAuditMode = NOT_AUDITED)
    @Valid
    private DemandParameterEntity demandParameter;

    @OneToOne(mappedBy = "participantDemand", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    @Audited(targetAuditMode = NOT_AUDITED)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @NotNull(message = "{crm.validation.flat}", groups = CrmValidation.class)
    @Valid
    private FlatEntity flat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_object_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.flat.building}", groups = CrmValidation.class)
    @Valid
    @QueryInit("developer")
    private BuildingObjectEntity buildingObject;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "reason_doc_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.reason-doc}", groups = CrmValidation.class)
    @Valid
    private ReasonDocEntity reasonDoc;

    @Column(name = "participant_demand_number", nullable = false)
    private Integer participantDemandNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.participant-demand-status}", groups = CrmValidation.class)
    private ParticipantDemandStatusEntity status;

    @Column(name = "version", nullable = false)
    @NotNull(message = "{crm.validation.participant-demand-version}", groups = CrmValidation.class)
    private Integer version;

    @Column(name = "fund_comment")
    private String fundComment;

    @OneToMany(mappedBy = "participantDemand", orphanRemoval = true, cascade = CascadeType.ALL)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<ParticipantDemandCorrectionEntity> participantDemandCorrections = new HashSet<>();

    @OneToMany(mappedBy = "participantDemand", orphanRemoval = true, cascade = CascadeType.ALL)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<ParticipantDemandCorrectionDocEntity> participantDemandCorrectionDocs = new HashSet<>();

    @OneToMany(mappedBy = "participantDemand", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<ParticipantDemandEventEntity> participantDemandEvents = new HashSet<>();

    @OneToMany(mappedBy = "participantDemand", orphanRemoval = true, cascade = CascadeType.ALL)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<@Valid LoanAgreementEntity> loanAgreementEntitySet = new HashSet<>();

    @OneToMany(mappedBy = "participantDemand", orphanRemoval = true, cascade = CascadeType.ALL)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<@Valid LoanAgreementAdditionalEntity> loanAgreementAdditionalEntitySet = new HashSet<>();

    @OneToMany(mappedBy = "participantDemand", orphanRemoval = true, cascade = CascadeType.ALL)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<@Valid RightsConcessionEntity> rightsConcessionEntitySet = new HashSet<>();

    @OneToMany(mappedBy = "participantDemand", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty(message = "{crm.validation.flat.participant}", groups = CrmValidation.class)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<@Valid ParticipantFlatEntity> participantFlats = new HashSet<>();

    /**
     * Получение текущей сущности кредитного договора
     *
     * @return Сущность
     */
    public LoanAgreementEntity getLoanAgreement() {
        return loanAgreementEntitySet
                .stream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Получение текущей сущности или создание новой с привязкой к ТУС
     *
     * @return Сущность
     */
    public LoanAgreementEntity getLoanAgreementOrNew() {
        return loanAgreementEntitySet
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    var entity = new LoanAgreementEntity();
                    loanAgreementEntitySet.add(entity);
                    entity.setParticipantDemand(this);
                    return entity;
                });
    }

    /**
     * Установка текущей сущности кредитного договора
     *
     * @param loanAgreementEntity Сущность
     */
    public void setLoanAgreement(LoanAgreementEntity loanAgreementEntity) {
        if (loanAgreementEntity != null) {
            loanAgreementEntity.setParticipantDemand(this);
        }
        loanAgreementEntitySet.clear();
        loanAgreementEntitySet.add(loanAgreementEntity);
    }

    /**
     * Получение текущей сущности доп. соглашения
     *
     * @return Сущность
     */
    public LoanAgreementAdditionalEntity getLoanAgreementAdditional() {
        return loanAgreementAdditionalEntitySet
                .stream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Получение текущей сущности или создание новой с привязкой к ТУС
     *
     * @return Сущность
     */
    public LoanAgreementAdditionalEntity getLoanAgreementAdditionalOrNew() {
        return loanAgreementAdditionalEntitySet
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    var entity = new LoanAgreementAdditionalEntity();
                    loanAgreementAdditionalEntitySet.add(entity);
                    entity.setParticipantDemand(this);
                    return entity;
                });
    }

    /**
     * Установка текущей сущности доп. соглашения
     *
     * @param loanAgreementAdditionalEntity Сущность
     */
    public void setLoanAgreementAdditional(LoanAgreementAdditionalEntity loanAgreementAdditionalEntity) {
        if (loanAgreementAdditionalEntity != null) {
            loanAgreementAdditionalEntity.setParticipantDemand(this);
        }
        loanAgreementAdditionalEntitySet.clear();
        loanAgreementAdditionalEntitySet.add(loanAgreementAdditionalEntity);
    }

    /**
     * Получение текущей сущности уступки прав
     *
     * @return Сущность
     */
    public RightsConcessionEntity getRightsConcession() {
        return rightsConcessionEntitySet
                .stream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Получение текущей сущности или создание новой с привязкой к ТУС
     *
     * @return Сущность
     */
    public RightsConcessionEntity getRightsConcessionOrNew() {
        return rightsConcessionEntitySet
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    var entity = new RightsConcessionEntity();
                    rightsConcessionEntitySet.add(entity);
                    entity.setParticipantDemand(this);
                    return entity;
                });
    }

    /**
     * Установка текущей сущности уступки прав
     *
     * @param rightsConcessionEntity Сущность
     */
    public void setRightsConcession(RightsConcessionEntity rightsConcessionEntity) {
        if (rightsConcessionEntity != null) {
            rightsConcessionEntity.setParticipantDemand(this);
        }
        rightsConcessionEntitySet.clear();
        rightsConcessionEntitySet.add(rightsConcessionEntity);
    }

    /**
     * Текущая версия корректировки ТУС
     *
     * @return Сущность корректировки
     */
    @Valid
    public ParticipantDemandCorrectionEntity getCurrentParticipantDemandCorrection() {
        return participantDemandCorrections
                .stream()
                .filter(entity -> version.equals(entity.getVersion()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Изменения по документу-основания для текущей версии ТУСа
     *
     * @return Сущность
     */
    public ParticipantDemandCorrectionDocEntity getCurrentParticipantDemandCorrectionDoc() {
        return participantDemandCorrectionDocs
                .stream()
                .filter(entity -> version.equals(entity.getVersion()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Установка признака корректировки документа-основания для текущей версии ТУС
     */
    public void markCorrectionDoc() {
        if (getCurrentParticipantDemandCorrectionDoc() == null) {
            var participantDemandCorrectionDocEntity = new ParticipantDemandCorrectionDocEntity();
            participantDemandCorrectionDocEntity.setParticipantDemand(this);
            participantDemandCorrectionDocEntity.setVersion(version);
            participantDemandCorrectionDocs.add(participantDemandCorrectionDocEntity);
        }
    }
}
