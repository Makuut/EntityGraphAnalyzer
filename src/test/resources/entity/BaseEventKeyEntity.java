@Getter
@Setter
@MappedSuperclass
public class BaseEventKeyEntity<K extends BaseEventKey> extends BaseEntity<K> {

    @EmbeddedId
    private K id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("eventId")
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private EventEntity event;
}
