@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE lkau.participant_demand_correction_doc SET deleted_dttm = now() WHERE id = ?")
@Table(name = "participant_demand_correction_doc")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class ParticipantDemandCorrectionDocEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_demand_correction_doc_seq")
    @SequenceGenerator(name = "participant_demand_correction_doc_seq", sequenceName = "lkau.participant_demand_correction_doc_seq",
            allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_demand_id", referencedColumnName = "id", nullable = false)
    private ParticipantDemandEntity participantDemand;

    @Column(name = "version", nullable = false)
    private Integer version;

}
