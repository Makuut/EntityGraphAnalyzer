@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class SubmittingDocEventKey extends BaseEventKey implements Serializable {

    @Column(name = "submitting_doc_id")
    private Integer submittingDocId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubmittingDocEventKey that = (SubmittingDocEventKey) o;
        return new EqualsBuilder()
                .append(getSubmittingDocId(), that.getSubmittingDocId())
                .append(getEventId(), that.getEventId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubmittingDocId(), getEventId());
    }
}
