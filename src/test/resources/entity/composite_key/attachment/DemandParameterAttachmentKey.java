@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class DemandParameterAttachmentKey extends BaseAttachmentKey implements Serializable {

    @Column(name = "demand_parameter_id")
    private Integer demandParameterId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DemandParameterAttachmentKey that = (DemandParameterAttachmentKey) o;
        return new EqualsBuilder()
                .append(getDemandParameterId(), that.getDemandParameterId())
                .append(getAttachmentId(), that.getAttachmentId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDemandParameterId(), getAttachmentId());
    }
}
