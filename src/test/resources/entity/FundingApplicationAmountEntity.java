@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funding_application_amount")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.funding_application_amount SET deleted_dttm = now() WHERE id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class FundingApplicationAmountEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funding_application_amount_seq")
    @SequenceGenerator(name = "funding_application_amount_seq",
            sequenceName = "lkau.funding_application_amount_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_application_id", referencedColumnName = "id", nullable = false)
    private FundingApplicationEntity fundingApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_type_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "{crm.validation.funding-application-amount-type}", groups = CrmPetitionValidation.class)
    @Valid
    private FundingTypeEntity fundingType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "involved_person_type_id", referencedColumnName = "id")
    @Valid
    private InvolvedPersonTypeEntity involvedPersonType;

    @Column(name = "amount", nullable = false)
    @NotNull(message = "{crm.validation.funding-application-amount}", groups = CrmPetitionValidation.class)
    private BigDecimal amount;
}
