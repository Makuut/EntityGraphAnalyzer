@Entity
public class ArchiveFileEntity extends BaseEntity<UUID> {

    private UUID id;

    private ArchiveEntity archiveEntity;

    private String fileName;

    private String status;

    private String description;

    private String idLkau;

    private String idCrm;

    private String docnumUp;

    private String developerInn;

    private Integer idErpo;

    private String stacktrace;

}
