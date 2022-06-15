@Entity
public class SubmittingDocSubmittingDocMissingEntity extends BaseEntity<SubmittingDocKey> {
    private SubmittingDocKey id;

    private LocalDate limitationDt;

    private SubmittingDocEntity submittingDoc;

    private SubmittingDocMissingEntity submittingDocMissing;
}
