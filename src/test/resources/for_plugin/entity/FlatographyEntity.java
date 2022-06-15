@Entity
public class FlatographyEntity extends BaseEntity<Integer> {

    private Integer id;

    private BuildingObjectEntity buildingObject;

    private ReviseEntity revise;

    private Set<@Valid IrdEntity> irds = new HashSet<>();

    private FlatographyStatusEntity status;

    private Integer version;

    private Set<@Valid FlatDataEntity> flatDataSet = new HashSet<>();

    private String fundComment;

    private Set<FlatographyCorrectionEntity> flatographyCorrections = new HashSet<>();
}
