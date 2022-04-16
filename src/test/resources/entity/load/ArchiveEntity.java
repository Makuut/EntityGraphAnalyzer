@Entity
@Table(name = "archive", schema = "load")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE load.archive SET deleted_dttm = now() WHERE id = ?")
@Where(clause = "deleted_dttm is null")
public class ArchiveEntity extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "archive_seq")
    @SequenceGenerator(name = "archive_seq", sequenceName = "load.archive_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "start_dttm")
    private LocalDateTime startDttm;

    @Column(name = "end_dttm")
    private LocalDateTime endDttm;

    @Column(name = "file_count")
    private Integer fileCount;

    @OneToMany(mappedBy = "archiveEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArchiveFileEntity> archiveFileEntities = new HashSet<>();

}
