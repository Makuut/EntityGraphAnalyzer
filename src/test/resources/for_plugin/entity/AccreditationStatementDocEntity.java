@Entity
public class AccreditationStatementDocEntity extends BaseEntity<Integer> {

    private Integer id;

    private AccreditationStatementEntity accreditationStatement;

    private StatementDocTypeEntity statementDocType;

    private Set<AccreditationStatementDocAttachmentEntity> accreditationStatementDocAttachmentEntitySet = new HashSet<>();
}
