@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funding_application_doc_attachment")
@SQLDelete(sql = "UPDATE lkau.funding_application_doc_attachment SET deleted_dttm = now() WHERE funding_application_doc_id = ? and attachment_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class FundingApplicationDocAttachmentEntity extends BaseAttachmentKeyEntity<FundingApplicationDocAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("fundingApplicationDocId")
    @JoinColumn(name = "funding_application_doc_id", referencedColumnName = "id")
    private FundingApplicationDocEntity fundingApplicationDoc;
}
