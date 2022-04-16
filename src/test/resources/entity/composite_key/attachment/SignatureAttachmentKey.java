@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class SignatureAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "signature_id")
    private Integer signatureId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SignatureAttachmentKey that = (SignatureAttachmentKey) o;
        return new EqualsBuilder()
                .append(getSignatureId(), that.getSignatureId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSignatureId(), getAttachmentId());
    }
}
