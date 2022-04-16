@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sro", schema = "lkau")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class SroEntity extends BaseEntity<UUID> {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @NotEmpty(message="{crm.validation.bankruptcy-commissioner-sro-name}", groups = CrmPetitionValidation.class)
    @Column(name = "short_name", nullable = false, length = 100)
    private String shortName;

    @Column(name = "reg_number", nullable = false)
    @NotEmpty(message="{crm.validation.bankruptcy-commissioner-sro-reg-number}", groups = CrmPetitionValidation.class)
    private String regNumber;
}
