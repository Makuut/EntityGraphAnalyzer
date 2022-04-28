@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_agreement_additional_attachment")
@SQLDelete(sql = "UPDATE lkau.loan_agreement_additional_attachment SET deleted_dttm = now() WHERE loan_agreement_additional_id = ? and attachment_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class LoanAgreementAdditionalAttachmentEntity extends BaseAttachmentKeyEntity<LoanAgreementAdditionalAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("loanAgreementAdditionalId")
    @JoinColumn(name = "loan_agreement_additional_id", referencedColumnName = "id")
    @Valid
    private LoanAgreementAdditionalEntity loanAgreementAdditional;

}
