@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.employee SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class EmployeeEntity extends BaseEntity<UUID> {

    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    private UUID id;

    @Revision
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "bankruptcy_commissioner_id", nullable = false)
    @NotNull
    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    @Revision
    @Column(name = "snils", length = 11, nullable = false)
    @NotNull
    private String snils;

    @Revision
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private EmployeeStatusEntity status;

    @Revision
    @Column(name = "added_dttm", nullable = false)
    @NotNull
    private LocalDateTime addedDttm;
}
