@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AuditOverride(forClass = AbstractBaseRefBookEntity.class)
public class InnerRefBookEntity extends AbstractBaseRefBookEntity {
    @Id
    @Column(name = "id")
    private Integer id;
}
