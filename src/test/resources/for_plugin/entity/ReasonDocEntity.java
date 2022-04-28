@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reason_doc")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@SQLDelete(sql = "UPDATE lkau.reason_doc SET deleted_dttm = now() WHERE id = ?")
@AuditOverride(forClass = BaseEntity.class)
public class ReasonDocEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reason_doc_seq")
    @SequenceGenerator(name = "reason_doc_seq", sequenceName = "lkau.reason_doc_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "multi_flat")
    private Boolean multiFlat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", referencedColumnName = "id", nullable = false)
    private DeveloperEntity developer;

    @OneToMany(mappedBy = "reasonDoc", cascade = CascadeType.MERGE, orphanRemoval = true)
    @Audited(targetAuditMode = NOT_AUDITED)
    private Set<ParticipantDemandEntity> participantDemandEntitySet = new HashSet<>();

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reason_doc_type_id", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{crm.validation.reason-doc.type}", groups = CrmValidation.class)
    private ReasonDocTypeEntity reasonDocType;

    @Revision
    @Column(name = "contract_price")
    @NotNull(message = "{crm.validation.reason-doc.contract-price}", groups = CrmValidation.class)
    private BigDecimal contractPrice;

    @Revision
    @Column(name = "price_per_sq_meter")
    private BigDecimal pricePerSqMeter;

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

    @Column(name = "is_imported")
    private Boolean isImported;

    @Revision
    @OneToMany(mappedBy = "reasonDoc", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotEmpty(message = "{crm.validation.reason-doc-attachment}", groups = CrmValidation.class)
    @AttachmentSignaturesValidation(message = "{crm.validation.reason-doc.sign-not-set}", groups = CrmValidation.class)
    private Set<ReasonDocAttachmentEntity> reasonDocAttachments = new HashSet<>();
}
