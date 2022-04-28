@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "non_disclosure")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.non_disclosure SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class NonDisclosureEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @MapsId("id")
    @OneToOne(mappedBy = "nonDisclosure", fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotNull(message = "{crm.validation.developer}", groups = CrmPetitionValidation.class)
    @Valid
    private DeveloperEntity developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private AgreementStatusEntity status;

    @Column(name = "limitation_dt")
    private LocalDate limitationDt;

    @Column(name = "received_dttm")
    private LocalDateTime receivedDttm;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    @NotNull(message = "{crm.validation.document-not-set}", groups = CrmNonDisclosureValidation.class)
    @Valid
    private AttachmentEntity attachment;

    @Column(name = "send_dttm")
    private LocalDateTime sendDttm;

    @Column(name = "id_crm")
    private String idCrm;
}
