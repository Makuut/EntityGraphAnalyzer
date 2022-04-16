@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ParticipantAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "participant_id")
    private Integer participantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipantAttachmentKey that = (ParticipantAttachmentKey) o;
        return new EqualsBuilder()
                .append(getParticipantId(), that.getParticipantId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParticipantId(), getAttachmentId());
    }
}
