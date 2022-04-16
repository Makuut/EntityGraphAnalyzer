@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rights_concession_attachment")
@SQLDelete(sql = "UPDATE lkau.rights_concession_attachment SET deleted_dttm = now() WHERE rights_concession_id = ? and attachment_id = ?")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class RightsConcessionAttachmentEntity extends BaseAttachmentKeyEntity<RightsConcessionAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rightsConcessionId")
    @JoinColumn(name = "rights_concession_id", referencedColumnName = "id")
    private RightsConcessionEntity rightsConcession;

}
