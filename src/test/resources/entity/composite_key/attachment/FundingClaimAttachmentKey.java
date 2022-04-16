@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class FundingClaimAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "funding_claim_id")
    private Integer fundingClaimId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FundingClaimAttachmentKey that = (FundingClaimAttachmentKey) o;
        return new EqualsBuilder()
                .append(getFundingClaimId(), that.getFundingClaimId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFundingClaimId(), getAttachmentId());
    }
}
