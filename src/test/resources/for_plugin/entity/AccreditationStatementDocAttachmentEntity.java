@Entity
@NamedEntityGraph(name = "accreditationStatementDoc", attributeNodes = @NamedAttributeNode("accreditationStatementDo"))

public class AccreditationStatementDocAttachmentEntity extends BaseAttachmentKeyEntity<AccreditationStatementDocAttachmentKey>{

    private AccreditationStatementDocEntity accreditationStatementDoc;
}
