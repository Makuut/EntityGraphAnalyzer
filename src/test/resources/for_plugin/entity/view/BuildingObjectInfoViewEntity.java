@Entity
public class BuildingObjectInfoViewEntity {
    private Integer id;

    private Integer developerId;

    private String buildingObjectAddress;

    private Long demandCount;

    private Long participantCount;

    private BigDecimal demandAmount;

    private BigDecimal amountOfLoss;

    private LocalDateTime updatedDttm;
}
