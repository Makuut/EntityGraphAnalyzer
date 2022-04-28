@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class FundingApplicationDocAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "funding_application_doc_id")
    private Integer fundingApplicationDocId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FundingApplicationDocAttachmentKey that = (FundingApplicationDocAttachmentKey) o;
        return new EqualsBuilder()
                .append(getFundingApplicationDocId(), that.getFundingApplicationDocId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFundingApplicationDocId(), getAttachmentId());
    }
}
