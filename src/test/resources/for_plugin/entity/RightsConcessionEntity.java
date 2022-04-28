@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rights_concession")
@SQLDelete(sql = "UPDATE lkau.rights_concession SET deleted_dttm = now() WHERE id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class RightsConcessionEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rights_concession_seq")
    @SequenceGenerator(name = "rights_concession_seq", sequenceName = "lkau.rights_concession_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_demand_id", referencedColumnName = "id", nullable = false)
    private ParticipantDemandEntity participantDemand;

    @Revision
    @Column(name = "concession_amt")
    @NotNull(message = "{crm.validation.rights-concession.concession-amt}", groups = CrmValidation.class)
    private BigDecimal concessionAmt;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transferor_type_id", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{crm.validation.rights-concession.transferor}", groups = CrmValidation.class)
    private ParticipantTypeEntity transferorType;

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
    @OneToMany(mappedBy = "rightsConcession", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotEmpty(message = "{crm.validation.rights-concession-attachment}", groups = CrmValidation.class)
    @AttachmentSignaturesValidation(message = "{crm.validation.rights-concession.sign-not-set}", groups = CrmValidation.class)
    private Set<RightsConcessionAttachmentEntity> rightsConcessionAttachments = new HashSet<>();
}
