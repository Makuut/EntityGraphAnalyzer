@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_agreement_attachment")
@SQLDelete(sql = "UPDATE lkau.loan_agreement_attachment SET deleted_dttm = now() WHERE loan_agreement_id = ? and attachment_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class LoanAgreementAttachmentEntity extends BaseAttachmentKeyEntity<LoanAgreementAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("loanAgreementId")
    @JoinColumn(name = "loan_agreement_id", referencedColumnName = "id")
    private LoanAgreementEntity loanAgreement;

}
