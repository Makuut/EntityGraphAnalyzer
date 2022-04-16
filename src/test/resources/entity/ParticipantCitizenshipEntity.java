@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant_citizenship")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class ParticipantCitizenshipEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_citizenship_seq")
    @SequenceGenerator(name = "participant_citizenship_seq", sequenceName = "lkau.participant_citizenship_seq",
            allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", referencedColumnName = "id", nullable = false)
    private ParticipantEntity participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{crm.validation.participant.country}", groups = CrmValidation.class)
    private CountryEntity country;
}
