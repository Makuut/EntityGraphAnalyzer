@Entity
public class ArchiveEntity extends BaseEntity<Integer> {

    private Integer id;

    private String fileName;

    private String status;

    private String description;

    private LocalDateTime startDttm;

    private LocalDateTime endDttm;

    private Integer fileCount;

    private Set<ArchiveFileEntity> archiveFileEntities = new HashSet<>();

}
