@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submitting_doc_event")
@Where(clause = BaseEntity.WHERE_CAUSE)
@SQLDelete(sql = "UPDATE lkau.submitting_doc_event SET deleted_dttm = now() WHERE submitting_doc_id = ? and event_id = ?")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = BaseEntity.class)
public class SubmittingDocEventEntity extends BaseEventKeyEntity<SubmittingDocEventKey> {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("submittingDocId")
    @JoinColumn(name = "submitting_doc_id", referencedColumnName = "id")
    private SubmittingDocEntity submittingDoc;
}
