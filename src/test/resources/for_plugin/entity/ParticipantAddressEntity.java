@Entity
public class ParticipantAddressEntity extends BaseAddressEntity<Integer> {

    private Integer id;

    private ParticipantEntity participant;

    private AddrTypeEntity addrType;
}
