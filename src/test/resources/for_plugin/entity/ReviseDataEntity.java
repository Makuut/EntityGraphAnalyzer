@Entity
public class ReviseDataEntity extends BaseEntity<ReviseDataKey> implements FlatographyRevise<Number> {

    private ReviseDataKey id;

    private ReviseEntity revise;

    private Integer flatCnt;

    private Integer studioCnt;

    private Integer room1Cnt;

    private Integer room2Cnt;

    private Integer room3Cnt;

    private Integer room4Cnt;

    private Integer room5Cnt;

    private Integer room6Cnt;

    private BigDecimal objectAreaSq;

    private BigDecimal totalAreaSq;

    private BigDecimal totalReducedSq;

    private BigDecimal totalAreaIncludingBalconySq;

    private Integer nonResidentialCnt;

    private BigDecimal nonResidentialSq;

    private Integer parkingCnt;

    private BigDecimal parkingSq;

    private Integer storeRoomCnt;

    private BigDecimal storeRoomSq;
}
