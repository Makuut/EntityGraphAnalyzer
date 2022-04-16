@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submitting_doc_missing_temporary")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.submitting_doc_missing_temporary SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class SubmittingDocMissingTemporaryEntity extends BaseEntity<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "submitting_doc_missing_temporary_seq")
    @SequenceGenerator(name = "submitting_doc_missing_temporary_seq", sequenceName = "lkau.submitting_doc_missing_temporary_seq",
            allocationSize = 1)
    private Integer id;
    @Column(name = "json_data")
    private String jsonData;
}
