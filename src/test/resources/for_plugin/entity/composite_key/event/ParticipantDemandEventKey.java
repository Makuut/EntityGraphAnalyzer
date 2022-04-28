@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ParticipantDemandEventKey extends BaseEventKey implements Serializable {

    @Column(name = "participant_demand_id")
    private Integer participantDemandId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipantDemandEventKey that = (ParticipantDemandEventKey) o;
        return new EqualsBuilder()
                .append(getParticipantDemandId(), that.getParticipantDemandId())
                .append(getEventId(), that.getEventId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParticipantDemandId(), getEventId());
    }
}
