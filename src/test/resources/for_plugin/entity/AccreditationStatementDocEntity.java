@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accreditation_statement_doc")
@SQLDelete(sql = "UPDATE lkau.accreditation_statement_doc SET deleted_dttm = now() WHERE id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class AccreditationStatementDocEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accreditation_statement_doc_seq")
    @SequenceGenerator(name = "accreditation_statement_doc_seq", sequenceName = "lkau.accreditation_statement_doc_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accreditation_statement_id", referencedColumnName = "id", nullable = false)
    private AccreditationStatementEntity accreditationStatement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statement_doc_type_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.accreditation-statement-statement-doc-type}", groups = CrmAccreditationValidation.class)
    private StatementDocTypeEntity statementDocType;

    @OneToMany(mappedBy = "accreditationStatementDoc", cascade = CascadeType.ALL, orphanRemoval = true)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<AccreditationStatementDocAttachmentEntity> accreditationStatementDocAttachmentEntitySet = new HashSet<>();
}
