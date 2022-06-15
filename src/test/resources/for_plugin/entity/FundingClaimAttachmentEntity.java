@Entity
public class FundingClaimAttachmentEntity extends BaseAttachmentKeyEntity<FundingClaimAttachmentKey> {

    private FundingClaimEntity fundingClaim;

    private FundingClaimDocTypeEntity fundingClaimDocType;
}
