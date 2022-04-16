@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "funding_agreement_process")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.funding_agreement_process SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class FundingAgreementProcessEntity extends BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funding_agreement_process_seq")
    @SequenceGenerator(name = "funding_agreement_process_seq", sequenceName = "funding_agreement_process_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @NotNull
    private Integer id;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "funding_agreement_id", nullable = false)
    @NotNull
    private FundingAgreementEntity fundingAgreement;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_type_id", nullable = false)
    @NotNull
    private ProcessTypeEntity processType;

    @Revision
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_status_id")
    private ProcessStatusEntity processStatus;

    @Revision
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id", nullable = false)
    @NotNull
    @Valid
    private AttachmentEntity attachment;

    @Revision
    @Column(name = "status_dttm")
    private LocalDateTime statusDttm;

    @Revision
    @Column(name = "comment")
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FundingAgreementProcessEntity that = (FundingAgreementProcessEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
