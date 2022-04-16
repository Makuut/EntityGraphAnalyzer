@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "funding_agreement")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.funding_agreement SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class FundingAgreementEntity extends BaseEntity<Integer> {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    private Integer id;

    @MapsId("id")
    @OneToOne(mappedBy = "fundingAgreement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotNull
    @Valid
    private DeveloperEntity developer;

    @Revision
    @Column(name = "number")
    @NotBlank(message = "{crm.validation.funding-claim.agreement-number}", groups = CrmFundingValidation.class)
    private String number;

    @Revision
    @Column(name = "id_crm")
    @NotBlank(message = "{crm.put-request.funding-agreement.crm-not-set}", groups = CrmFundingAgreementValidation.class)
    private String idCrm;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    @NotNull
    @InnerRefBookCodeValidation(message = "{crm.validation.funding-claim.agreement-status}", codes = "FIN_AGREEMENT",
            groups = CrmFundingValidation.class)
    private AgreementStatusEntity status;

    @Revision
    @Column(name = "limitation_dt", nullable = false)
    @NotNull
    private LocalDate limitationDt;

    @Revision
    @Column(name = "sign_dttm")
    @NotNull(message = "{crm.validation.funding-claim.agreement-sign-dt}", groups = CrmFundingValidation.class)
    private LocalDateTime signDttm;

    @OneToMany(mappedBy = "fundingAgreement", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OrderBy(value = "status_dttm nulls first")
    private Set<@Valid FundingAgreementProcessEntity> fundingAgreementProcessEntityTreeSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FundingAgreementEntity that = (FundingAgreementEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
