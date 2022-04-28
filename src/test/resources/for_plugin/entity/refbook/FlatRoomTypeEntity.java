@Entity
@Getter
@Setter
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = InnerRefBookEntity.class)
@NoArgsConstructor
@Table(name = "flat_room_type")
public class FlatRoomTypeEntity extends InnerRefBookEntity {

    @Column(name = "cnt", nullable = false)
    private Integer cnt;
}
