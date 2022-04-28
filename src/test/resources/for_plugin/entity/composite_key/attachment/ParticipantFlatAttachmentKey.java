@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ParticipantFlatAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "participant_flat_id")
    private Integer participantFlatId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipantFlatAttachmentKey that = (ParticipantFlatAttachmentKey) o;
        return new EqualsBuilder()
                .append(getParticipantFlatId(), that.getParticipantFlatId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParticipantFlatId(), getAttachmentId());
    }
}
