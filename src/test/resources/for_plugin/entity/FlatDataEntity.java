@Entity
public class FlatDataEntity extends BaseEntity<Integer> {

    private Integer id;

    private FlatographyEntity flatography;

    private FlatTypeEntity flatType;

    private FlatKindEntity flatKind;

    private String constructionNumber;

    private FlatRoomTypeEntity flatRoomType;

    private String entrance;

    private Integer floor;

    private BigDecimal totalAreaSq;

    private BigDecimal totalReducedSq;

    private BigDecimal totalAreaIncludingBalconySq;

    private FlatStatusEntity flatStatus;

    private BigDecimal loggiaSq;

    private BigDecimal balconySq;

    private BigDecimal terraceSq;

    private BigDecimal verandaSq;

    private String note;

    private Integer completePercent;
}
