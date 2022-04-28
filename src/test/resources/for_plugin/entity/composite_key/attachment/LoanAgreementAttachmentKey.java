@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class LoanAgreementAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "loan_agreement_id")
    private Integer loanAgreementId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LoanAgreementAttachmentKey that = (LoanAgreementAttachmentKey) o;
        return new EqualsBuilder()
                .append(getLoanAgreementId(), that.getLoanAgreementId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoanAgreementId(), getAttachmentId());
    }
}
