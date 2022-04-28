@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class SubmittingDocAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "submitting_doc_id")
    private Integer submittingDocId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubmittingDocAttachmentKey that = (SubmittingDocAttachmentKey) o;
        return new EqualsBuilder()
                .append(getSubmittingDocId(), that.getSubmittingDocId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubmittingDocId(), getAttachmentId());
    }
}
