@Entity
public class ParticipantCitizenshipEntity extends BaseEntity<Integer> {

    private Integer id;

    private ParticipantEntity participant;

    private CountryEntity country;
}
