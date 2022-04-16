@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class AccreditationStatementDocAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "accreditation_statement_doc_id")
    private Integer accreditationStatementDocId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccreditationStatementDocAttachmentKey that = (AccreditationStatementDocAttachmentKey) o;
        return new EqualsBuilder()
                .append(getAccreditationStatementDocId(), that.getAccreditationStatementDocId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccreditationStatementDocId(), getAttachmentId());
    }
}
