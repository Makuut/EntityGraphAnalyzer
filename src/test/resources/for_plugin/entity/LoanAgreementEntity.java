@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_agreement")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.loan_agreement SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class LoanAgreementEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_agreement_seq")
    @SequenceGenerator(name = "loan_agreement_seq", sequenceName = "lkau.loan_agreement_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_demand_id", referencedColumnName = "id", nullable = false)
    private ParticipantDemandEntity participantDemand;

    @Revision
    @Column(name = "mortgagee_name")
    @NotNull(message = "{crm.validation.loan-agreement.mortgagee-name}", groups = CrmValidation.class)
    private String mortgageeName;

    @Revision
    @Column(name = "mortgagee_inn")
    @NotNull(message = "{crm.validation.loan-agreement.mortgagee-inn}", groups = CrmValidation.class)
    private String mortgageeInn;

    @Revision
    @Column(name = "doc_number")
    @NotNull(message = "{crm.validation.document-number}", groups = CrmValidation.class)
    private String docNumber;

    @Revision
    @Column(name = "doc_dt")
    @NotNull(message = "{crm.validation.document-date}", groups = CrmValidation.class)
    private LocalDate docDt;

    @Revision
    @OneToMany(mappedBy = "loanAgreement", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotEmpty(message = "{crm.validation.loan-agreement-attachment}", groups = CrmValidation.class)
    private Set<LoanAgreementAttachmentEntity> loanAgreementAttachments = new HashSet<>();
}
