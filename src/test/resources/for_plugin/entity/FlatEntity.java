@Entity
@Table(name = "flat")
public class FlatEntity extends BaseEntity<Integer> {

    private Integer id;

    private ParticipantDemandEntity participantDemand;

    private FlatTypeEntity flatType;

    private BigDecimal reasonDocPrice;

    private BigDecimal rightsConcessionPrice;

    private String constructionNumber;

    private BigDecimal totalAreaSq;

    private BigDecimal totalReducedSq;

    private BigDecimal totalAreaIncludingBalconySq;

    private BigDecimal loggiaSq;

    private BigDecimal balconySq;

    private BigDecimal terraceSq;

    private BigDecimal compensationAreaSq;

    private CompensationAreaTypeEntity compensationAreaType;

    private Integer floor;

    private FlatRoomTypeEntity flatRoomType;

    private String entrance;

}
