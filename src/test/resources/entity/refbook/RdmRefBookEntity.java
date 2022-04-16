@Getter
@Setter
@MappedSuperclass
@AuditOverride(forClass = AbstractBaseRefBookEntity.class)
public class RdmRefBookEntity extends AbstractBaseRefBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_deleted", updatable = false, insertable = false)
    private Boolean isDeleted;
}
