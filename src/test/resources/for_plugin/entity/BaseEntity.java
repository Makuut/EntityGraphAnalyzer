public abstract class BaseEntity<I> implements BaseEntityInterface<I>,  Serializable {

    private LocalDateTime createdDttm;

    private LocalDateTime updatedDttm;

    private LocalDateTime deletedDttm;
}
