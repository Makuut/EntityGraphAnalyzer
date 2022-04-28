@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE lkau.demand_parameter_attachment SET deleted_dttm = now() WHERE demand_parameter_id = ? and attachment_id = ?")
@Table(name = "demand_parameter_attachment")
@Where(clause = BaseEntity.WHERE_CAUSE)
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseAttachmentKeyEntity.class)
public class DemandParameterAttachmentEntity extends BaseAttachmentKeyEntity<DemandParameterAttachmentKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_doc_type_id", referencedColumnName = "id", nullable = false)
    @Audited(targetAuditMode = NOT_AUDITED)
    private DemandDocTypeEntity demandDocType;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("demandParameterId")
    @JoinColumn(name = "demand_parameter_id", referencedColumnName = "id")
    private DemandParameterEntity demandParameter;
}
