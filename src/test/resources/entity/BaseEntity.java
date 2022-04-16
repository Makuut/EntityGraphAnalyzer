@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<I> implements BaseEntityInterface<I>,  Serializable {

    protected static final String WHERE_CAUSE = "deleted_dttm is null";

    @Column(name = "created_dttm", nullable = false)
    private LocalDateTime createdDttm;

    @Column(name = "updated_dttm", nullable = false)
    private LocalDateTime updatedDttm;

    @Column(name = "deleted_dttm")
    private LocalDateTime deletedDttm;

    @PrePersist
    protected void prePersist() {
        var now = LocalDateTime.now();
        setCreatedDttm(now);
        setUpdatedDttm(now);
    }

    @PreUpdate
    protected void preUpdate() {
        setUpdatedDttm(LocalDateTime.now());
    }

    @PreRemove
    protected void preRemove() {
        setDeletedDttm(LocalDateTime.now());
    }
}
