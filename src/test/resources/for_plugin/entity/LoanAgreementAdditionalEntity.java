@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_agreement_additional")
@SQLDelete(sql = "UPDATE lkau.loan_agreement_additional SET deleted_dttm = now() WHERE id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class LoanAgreementAdditionalEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_agreement_additional_seq")
    @SequenceGenerator(name = "loan_agreement_additional_seq", sequenceName = "lkau.loan_agreement_additional_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_demand_id", referencedColumnName = "id", nullable = false)
    private ParticipantDemandEntity participantDemand;

    @Revision
    @Column(name = "doc_number")
    @NotNull(message = "{crm.validation.document-number}", groups = CrmValidation.class)
    private String docNumber;

    @Revision
    @Column(name = "doc_dt")
    @NotNull(message = "{crm.validation.document-date}", groups = CrmValidation.class)
    private LocalDate docDt;

    @Revision
    @Column(name = "doc_reg_number")
    @NotNull(message = "{crm.validation.document-reg-number}", groups = CrmValidation.class)
    private String docRegNumber;

    @Revision
    @Column(name = "doc_reg_dt")
    @NotNull(message = "{crm.validation.document-reg-date}", groups = CrmValidation.class)
    private LocalDate docRegDt;

    @Revision
    @OneToMany(mappedBy = "loanAgreementAdditional", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotEmpty(message = "{crm.validation.loan-agreement-additional-attachment}", groups = CrmValidation.class)
    private Set<LoanAgreementAdditionalAttachmentEntity> loanAgreementAdditionalAttachments = new HashSet<>();
}
