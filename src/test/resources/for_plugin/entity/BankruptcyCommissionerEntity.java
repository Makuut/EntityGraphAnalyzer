@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bankruptcy_commissioner", schema = "lkau")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class BankruptcyCommissionerEntity extends BaseEntity<UUID> {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "idm_id", nullable = false)
    private UUID idmId;

    @Column(name = "name", nullable = false)
    @NotNull(message = "{crm.validation.bankruptcy-commissioner-name}", groups = {CrmValidation.class, CrmPetitionValidation.class})
    private String name;

    @Column(name = "surname", nullable = false)
    @NotNull(message = "{crm.validation.bankruptcy-commissioner-surname}", groups = {CrmValidation.class, CrmPetitionValidation.class})
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "snils", length = 11)
    @NotBlank(message = "{crm.validation.bankruptcy-commissioner-snils}", groups = CrmPetitionValidation.class)
    private String snils;

    @Column(name = "inn", length = 12)
    @NotEmpty(message = "{crm.validation.bankruptcy-commissioner-inn}", groups = CrmPetitionValidation.class)
    private String inn;

    @Column(name = "passport_serial", length = 4)
    @NotEmpty(message = "{crm.validation.bankruptcy-commissioner-serial}", groups = CrmPetitionValidation.class)
    private String passportSerial;

    @Column(name = "passport_number", length = 6)
    @NotEmpty(message = "{crm.validation.bankruptcy-commissioner-number}", groups = CrmPetitionValidation.class)
    private String passportNumber;

    @Column(name = "passport_dt")
    @NotNull(message = "{crm.validation.bankruptcy-commissioner-date}", groups = CrmPetitionValidation.class)
    private LocalDate passportDt;

    @OneToMany(mappedBy = "bankruptcyCommissioner")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<BankruptcyCommissionerAddressEntity> bankruptcyCommissionerAddress = new HashSet<>();

    @Column(name = "reg_address")
    @NotEmpty(message = "{crm.validation.bankruptcy-commissioner-reg-address}", groups = CrmPetitionValidation.class)
    private String regAddress;

    @Column(name = "phone", length = 11)
    @NotEmpty(message = "{crm.validation.bankruptcy-commissioner-phone}", groups = CrmPetitionValidation.class)
    private String phone;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "{crm.validation.bankruptcy-commissioner-email}", groups = CrmPetitionValidation.class)
    private String email;

    @Column(name = "commissioner_reg_number", length = 50)
    @NotNull(message = "{crm.validation.bankruptcy-commissioner-reg-number}", groups = {CrmValidation.class, CrmPetitionValidation.class})
    private String commissionerRegNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sro_id", referencedColumnName = "id")
    @Valid
    @NotNull(message = "{crm.validation.bankruptcy-commissioner-sro}", groups = CrmPetitionValidation.class)
    private SroEntity sro;

    @Column(name = "accreditation_number", length = 50)
    private String accreditationNumber;

    @Column(name = "accreditation_validity_dt")
    private LocalDate accreditationValidityDt;

    @OneToMany(mappedBy = "bankruptcyCommissioner")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<AccreditationStatementEntity> accreditationStatementEntitySet = new HashSet<>();

    @OneToMany(mappedBy = "bankruptcyCommissioner", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<EmployeeEntity> employeeEntitySet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private BankruptcyCommissionerEntity parentBankruptcyCommissioner;

    @Transient
    public String getFio() {
        return Stream.of(surname, name, patronymic)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BankruptcyCommissionerEntity entity = (BankruptcyCommissionerEntity) o;
        return id != null && Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
