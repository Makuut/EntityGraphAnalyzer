@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefBookPredicate {

    public static <T extends AbstractBaseRefBookEntity> BooleanExpression isIdEq(PathBuilder<T> entityPath, Integer id) {
        return entityPath.getNumber("id", Integer.class).eq(id);
    }

    public static <T extends AbstractBaseRefBookEntity> BooleanExpression isNameContains(PathBuilder<T> entityPath, String name) {
        return entityPath.getString("name").containsIgnoreCase(name);
    }

    public static <T extends AbstractBaseRefBookEntity> BooleanExpression isNameEq(PathBuilder<T> entityPath, String name) {
        return entityPath.getString("name").eq(name);
    }

    public static <T extends AbstractBaseRefBookEntity> BooleanExpression isCodeEq(PathBuilder<T> entityPath, String code) {
        return entityPath.getString("code").eq(code);
    }

    public static <T extends AbstractBaseRefBookEntity> BooleanExpression inCodes(PathBuilder<T> entityPath, String[] codes) {
        return entityPath.getString("code").in(codes);
    }

    public static <T extends AbstractBaseRefBookEntity> BooleanExpression isCustomEq(PathBuilder<T> entityPath, String field, String value) {
        return entityPath.getString(field).equalsIgnoreCase(value);
    }

    public static <T extends AbstractBaseRefBookEntity> BooleanExpression isNotDelete(PathBuilder<T> entityPath) {
        return entityPath.getBoolean("isDeleted").coalesce(false).asBoolean().isFalse();
    }

}
