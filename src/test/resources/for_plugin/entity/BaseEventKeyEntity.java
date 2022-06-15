public class BaseEventKeyEntity<K extends BaseEventKey> extends BaseEntity<K> {

    private K id;

    private EventEntity event;
}
