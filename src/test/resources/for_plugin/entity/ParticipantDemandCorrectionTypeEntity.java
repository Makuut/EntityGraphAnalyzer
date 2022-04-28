@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant_demand_correction_type")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class ParticipantDemandCorrectionTypeEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_demand_correction_type_seq")
    @SequenceGenerator(name = "participant_demand_correction_type_seq", sequenceName = "lkau.participant_demand_correction_type_seq",
            allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_demand_correction_id", referencedColumnName = "id", nullable = false)
    private ParticipantDemandCorrectionEntity participantDemandCorrection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correction_type_id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{participant-demand-correction-type-entity.correction-type.not-set}")
    private CorrectionTypeEntity correctionType;


}
