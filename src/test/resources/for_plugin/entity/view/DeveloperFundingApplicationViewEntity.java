@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Immutable
@Table(name = "developer_funding_application_view")
public class DeveloperFundingApplicationViewEntity extends BaseDeveloperEntity {

    @Column(name = "last_updated_dttm")
    private LocalDateTime lastUpdatedDttm;

    @Column(name = "paid_applications_sum")
    private BigDecimal paidApplicationsSum;

    @Column(name = "paid_applications_count")
    private Long paidApplicationsCount;

    @Column(name = "applications_count")
    private Long applicationsCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_application_status_id", referencedColumnName = "id")
    private FundingApplicationStatusEntity fundingApplicationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_agreement_status_id", referencedColumnName = "id")
    private AgreementStatusEntity agreementStatusEntity;
}
