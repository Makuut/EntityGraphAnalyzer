@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class ReviseDataKey implements Serializable {

    @Column(name = "revise_id")
    private Integer reviseId;

    @Column(name = "revise_data_type")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private ReviseTypeEnum type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReviseDataKey that = (ReviseDataKey) o;
        return new EqualsBuilder()
                .append(getReviseId(), that.getReviseId())
                .append(getType(), that.getType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviseId, type);
    }
}
