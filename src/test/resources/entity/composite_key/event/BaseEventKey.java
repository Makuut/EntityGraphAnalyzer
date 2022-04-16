@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@MappedSuperclass
public class BaseEventKey implements Serializable {

    @Column(name = "event_id")
    private Integer eventId;
}
