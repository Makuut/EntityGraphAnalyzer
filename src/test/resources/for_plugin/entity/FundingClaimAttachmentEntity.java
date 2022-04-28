@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funding_claim_attachment")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.funding_claim_attachment SET deleted_dttm = now() WHERE funding_claim_id = ? and attachment_id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class FundingClaimAttachmentEntity extends BaseAttachmentKeyEntity<FundingClaimAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("fundingClaimId")
    @JoinColumn(name = "funding_claim_id", referencedColumnName = "id")
    private FundingClaimEntity fundingClaim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_claim_doc_type_id", referencedColumnName = "id", nullable = false)
    private FundingClaimDocTypeEntity fundingClaimDocType;
}
