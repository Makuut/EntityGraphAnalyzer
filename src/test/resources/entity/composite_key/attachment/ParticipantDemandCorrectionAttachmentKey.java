@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ParticipantDemandCorrectionAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "participant_demand_correction_id")
    private Integer participantDemandCorrectionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipantDemandCorrectionAttachmentKey that = (ParticipantDemandCorrectionAttachmentKey) o;
        return new EqualsBuilder()
                .append(getParticipantDemandCorrectionId(), that.getParticipantDemandCorrectionId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParticipantDemandCorrectionId(), getAttachmentId());
    }
}
