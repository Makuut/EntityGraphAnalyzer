@Entity
@Getter
@Setter
@Table(name = "person_doc_type", schema = "nsi")
@Audited(withModifiedFlag = true)
@AuditOverride(forClass = RdmRefBookEntity.class)
@NoArgsConstructor
public class PersonDocTypeEntity extends RdmRefBookEntity {

    @Column(name = "business_code")
    private String businessCode;
}
