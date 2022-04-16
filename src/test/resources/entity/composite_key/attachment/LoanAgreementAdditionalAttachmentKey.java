@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class LoanAgreementAdditionalAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "loan_agreement_additional_id")
    private Integer loanAgreementAdditionalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoanAgreementAdditionalAttachmentKey that = (LoanAgreementAdditionalAttachmentKey) o;
        return new EqualsBuilder()
                .append(getLoanAgreementAdditionalId(), that.getLoanAgreementAdditionalId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoanAgreementAdditionalId(), getAttachmentId());
    }
}
