@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipantPredicate {

    public static Predicate isNameEq(String name) {
        return QParticipantEntity.participantEntity.personName.equalsIgnoreCase(name);
    }

    public static Predicate isSurNameEq(String surname) {
        return QParticipantEntity.participantEntity.personSurname.equalsIgnoreCase(surname);
    }

    public static Predicate isUlNameEq(String ulName) {
        return QParticipantEntity.participantEntity.ulName.equalsIgnoreCase(ulName);
    }

    public static Predicate isBirthDtEq(LocalDate birthDt) {
        return QParticipantEntity.participantEntity.personBirthday.eq(birthDt);
    }

    public static Predicate isParticipantTypeEq(String participantType) {
        return QParticipantEntity.participantEntity.participantType.code.equalsIgnoreCase(participantType);
    }

    public static Predicate isCommissionerRegNumberEq(String commissionerRegNumber) {
        return QParticipantEntity.participantEntity.commissionerRegNumber.equalsIgnoreCase(commissionerRegNumber);
    }

    public static Predicate isDocumentNumberEq(String documentNumber) {
        return QParticipantEntity.participantEntity.personDocNumber.equalsIgnoreCase(documentNumber);
    }

    public static Predicate isInnEq(String inn) {
        return QParticipantEntity.participantEntity.inn.eq(inn);
    }

    public static Predicate isDocumentTypeEq(String type) {
        return QParticipantEntity.participantEntity.personDocType.businessCode.equalsIgnoreCase(type);
    }
}
