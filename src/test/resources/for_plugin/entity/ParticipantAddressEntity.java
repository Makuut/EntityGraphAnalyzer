@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant_address")
@SQLDelete(sql = "UPDATE lkau.participant_address SET deleted_dttm = now() WHERE participant_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAddressEntity.class)
public class ParticipantAddressEntity extends BaseAddressEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_address_seq")
    @SequenceGenerator(name = "participant_address_seq", sequenceName = "lkau.participant_address_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", referencedColumnName = "id", nullable = false)
    private ParticipantEntity participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addr_type_id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    @NotNull(message = "{participant-address-entity.addr-type.not-set}")
    private AddrTypeEntity addrType;
}
