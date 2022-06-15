@Entity
public class ReviseEntity extends BaseEntity<Integer> {

    private Integer id;

    private FlatographyEntity flatography;

    private LocalDateTime reviseDttm;

    private String flatCntDisaccord;

    private String studioCntDisaccord;

    private String room1CntDisaccord;

    private String room2CntDisaccord;

    private String room3CntDisaccord;

    private String room4CntDisaccord;

    private String room5CntDisaccord;

    private String room6CntDisaccord;

    private String objectAreaSqDisaccord;

    private String totalAreaSqDisaccord;

    private String totalReducedSqDisaccord;

    private String totalAreaIncludingBalconySqDisaccord;

    private String nonResidentialCntDisaccord;

    private String nonResidentialSqDisaccord;

    private String parkingCntDisaccord;

    private String parkingSqDisaccord;

    private String storeRoomCntDisaccord;

    private String storeRoomSqDisaccord;

    private AttachmentEntity attachment;

    private Set<ReviseDataEntity> revisies = new HashSet<>();
}
