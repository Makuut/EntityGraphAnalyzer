@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class SubmittingDocKey implements Serializable {

    @Column(name = "submitting_doc_id")
    private Integer submittingDocId;

    @Column(name = "submitting_doc_missing_id")
    private Integer submittingDocMissingId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubmittingDocKey that = (SubmittingDocKey) o;
        return new EqualsBuilder()
                .append(getSubmittingDocMissingId(), that.getSubmittingDocMissingId())
                .append(getSubmittingDocId(), that.getSubmittingDocId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(submittingDocId, submittingDocMissingId);
    }
}
