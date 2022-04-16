\@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funding_application_doc")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.funding_application_doc SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class FundingApplicationDocEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funding_application_doc_seq")
    @SequenceGenerator(name = "funding_application_doc_seq", sequenceName = "lkau.funding_application_doc_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_application_id", referencedColumnName = "id")
    private FundingApplicationEntity fundingApplication;

    @Column(name = "other_doc_name")
    private String otherDocName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_application_doc_type_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.funding-application-attachment-type}",
            groups = {CrmPetitionValidation.class, CrmFundingValidation.class})
    private FundingApplicationDocTypeEntity fundingApplicationDocType;

    @OneToMany(mappedBy = "fundingApplicationDoc", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<FundingApplicationDocAttachmentEntity> fundingApplicationDocAttachments = new HashSet<>();
}
