@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class RightsConcessionAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "rights_concession_id")
    private Integer rightsConcessionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RightsConcessionAttachmentKey that = (RightsConcessionAttachmentKey) o;
        return new EqualsBuilder()
                .append(getRightsConcessionId(), that.getRightsConcessionId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRightsConcessionId(), getAttachmentId());
    }
}
