@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Immutable
@Table(name = "building_object_info_view")
public class BuildingObjectInfoViewEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "developer_id")
    private Integer developerId;

    @Column(name = "building_object_address")
    private String buildingObjectAddress;

    @Column(name = "demand_count")
    private Long demandCount;

    @Column(name = "participant_count")
    private Long participantCount;

    @Column(name = "demand_amount")
    private BigDecimal demandAmount;

    @Column(name = "amount_of_loss")
    private BigDecimal amountOfLoss;

    @Column(name = "updated_dttm")
    private LocalDateTime updatedDttm;
}
