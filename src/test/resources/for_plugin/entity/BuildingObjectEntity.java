@Entity
public class BuildingObjectEntity extends BaseEntity<Integer> {

    private Integer id;

    private FlatographyEntity flatography;

    private Integer egiszId;

    private String address;

    private Integer pdId;

    private String houseNumber;

    private DeveloperEntity developer;

    private AttachmentEntity attachment;

    private ConstructionTypeEntity constructionType;

    private Boolean constructionTypeIsLocked;

    private Boolean isImported;

    private Set<ParticipantDemandEntity> demandEntities = new HashSet<>();

    private Set<SubmittingDocEntity> submittingDocEntities = new HashSet<>();
}
