@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class FundingAgreementEventKey extends BaseEventKey implements Serializable {

    @Column(name = "funding_agreement_id")
    private Integer fundingAgreementId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FundingAgreementEventKey that = (FundingAgreementEventKey) o;
        return new EqualsBuilder()
                .append(getFundingAgreementId(), that.getFundingAgreementId())
                .append(getEventId(), that.getEventId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFundingAgreementId(), getEventId());
    }
}
