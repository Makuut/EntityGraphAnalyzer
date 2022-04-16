@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class DeveloperEventKey extends BaseEventKey implements Serializable {
    @Column(name = "developer_id")
    private Integer developerId;
}
