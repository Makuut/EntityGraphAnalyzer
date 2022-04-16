@Entity
@Table(name = "archive_file", schema = "load")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE load.archive_file SET deleted_dttm = now() WHERE id = ?")
@Where(clause = "deleted_dttm is null")
public class ArchiveFileEntity extends BaseEntity<UUID> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archive_id", referencedColumnName = "id")
    private ArchiveEntity archiveEntity;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "id_lkau")
    private String idLkau;

    @Column(name = "id_crm")
    private String idCrm;

    @Column(name = "docnum_up")
    private String docnumUp;

    @Column(name = "developer_inn")
    private String developerInn;

    @Column(name = "id_erpo")
    private Integer idErpo;

    @Column(name = "stacktrace")
    private String stacktrace;

}
