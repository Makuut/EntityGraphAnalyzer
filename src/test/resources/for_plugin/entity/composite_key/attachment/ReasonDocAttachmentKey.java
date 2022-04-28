@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ReasonDocAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "reason_doc_id")
    private Integer reasonDocId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReasonDocAttachmentKey that = (ReasonDocAttachmentKey) o;
        return new EqualsBuilder()
                .append(getReasonDocId(), that.getReasonDocId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReasonDocId(), getAttachmentId());
    }
}
