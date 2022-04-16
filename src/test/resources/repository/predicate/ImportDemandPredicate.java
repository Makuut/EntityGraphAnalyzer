@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImportDemandPredicate {

    public static Predicate isArchiveFileIdEq(Integer archiveFileId) {
        return QArchiveFileEntity.archiveFileEntity.archiveEntity.id.eq(archiveFileId);
    }

    public static Predicate isDeveloperInnEq(String developerInn) {
        return QArchiveFileEntity.archiveFileEntity.developerInn.equalsIgnoreCase(developerInn);
    }

    public static Predicate isStatusEq(String status) {
        return QArchiveFileEntity.archiveFileEntity.status.equalsIgnoreCase(status);
    }

    public static Predicate isFromDttm(LocalDateTime fromDttm) {
        return QArchiveFileEntity.archiveFileEntity.createdDttm.after(fromDttm);
    }

    public static Predicate isToDttm(LocalDateTime toDttm) {
        return QArchiveFileEntity.archiveFileEntity.createdDttm.before(toDttm);
    }
}
