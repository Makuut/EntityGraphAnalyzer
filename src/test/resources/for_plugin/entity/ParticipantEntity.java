@Entity(name = "participant")
public class ParticipantEntity extends BaseEntity<Integer> {

    private Integer id;

    private BankruptcyCommissionerEntity bankruptcyCommissioner;

    private ParticipantTypeEntity participantType;

    private String ulName;

    private String personName;

    private String personSurname;

    private String personPatronymic;

    private LocalDate personBirthday;

    private String inn;

    private String ogrn;

    private String bic;

    private String correspondentAccount;

    private String checkingAccount;

    private String bankName;

    private PersonDocTypeEntity personDocType;

    private String personDocSerial;

    private String personDocNumber;

    private LocalDate personDocumentIssueDt;

    private String personDocIssueDept;

    private String personDocDeptCode;

    private String phoneNumber;

    private String email;

    private String commissionerRegNumber;

    private Boolean isImported;

    private Set<ParticipantFlatEntity> participantFlats = new HashSet<>();

    private Set<@Valid ParticipantCitizenshipEntity> participantCitizenships = new HashSet<>();

    private Set<@Valid ParticipantAddressEntity> participantAddresses = new HashSet<>();

    private Set<@Valid ParticipantAttachmentEntity> participantAttachments = new HashSet<>();

    private Set<ParticipantAttachmentEntity> egrulAttachments;

    private String regParticipantAddress;

    private String postalParticipantAddress;

    private String ulAddress;
}
