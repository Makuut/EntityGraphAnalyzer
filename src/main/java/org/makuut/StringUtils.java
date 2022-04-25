package org.makuut;

/**
 * Вспомогательный класс для работы со строками
 */
public class StringUtils {

    private static final String JAVA_FILE_EXTENSION = ".java";
    private static final String PLUS = "+";
    private static final String PLUS_PATTERN = "\\+";
    private static final String WHITESPACE = " ";
    private static final String DOT = ".";

    /**
     * Удаляет начальную и конечную двойные кавычки
     *
     * @param str Строка
     * @return Строка без начальной и конечной двойных кавычек
     */
    public static String deleteQuotes(String str) {
        str = str.trim();
        int leftQuote = str.indexOf('"');
        int rightQuote = str.lastIndexOf('"');

        if (leftQuote == -1 || rightQuote == -1) {
            return str;
        }

        return str.substring(leftQuote + 1, rightQuote);
    }

    /**
     * Возвращает строку типа для типизированного объекта
     *
     * @param str Строка типизированный объект
     * @return Строка типа из типизированного объекта
     */
    public static String getValue(String str) {
        int leftBracket = str.indexOf('<');
        int rightBracket = str.indexOf('>');

        if (leftBracket == -1 || rightBracket == -1) {
            return str;
        }

        String substring = str.substring(leftBracket + 1, rightBracket);
        if (substring.contains(WHITESPACE)) {
            String[] s = substring.split(WHITESPACE);
            return s[s.length - 1];
        }
        return substring;
    }

    /**
     * Удаление расширения из файла паттерна файла
     *
     * @param str Паттерн файла сущности
     * @return Паттерн класса сущности
     */
    public static String getEntityClassPattern(String str) {
        if (!str.contains(JAVA_FILE_EXTENSION)) {
            return str;
        }
        int index = str.indexOf(JAVA_FILE_EXTENSION);
        return str.substring(0, index);
    }

    /**
     * Объедияет граф разделеный "+"
     *
     * @param str Строка
     * @return Объединенный граф
     */
    public static String joinGraph(String str) {
        if (!str.contains(PLUS)) {
            return str;
        }
        StringBuilder joined = new StringBuilder();
        String[] split = str.split(PLUS_PATTERN);

        for (String unit : split) {
            String replace = unit.replace("\"", " ");
            replace = replace.trim();
            joined.append(replace);
        }
        return joined.toString();
    }

    /**
     * Получает имя типа удалением директорий
     *
     * @param str Имя типа
     * @return Имя типа без директорий
     */
    public static String getTypeName(String str) {
        if (str.contains(DOT)) {
            int index = str.lastIndexOf(DOT);
            return str.substring(index + 1);
        }
        return str;
    }

    /**
     * Сравнивает имена типов, если у типа полное имя, то сокращает его
     *
     * @param first Имя типа
     * @param second Имя типа
     * @return Результат сравнения
     */
    public static boolean compareTypeNames(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        return getTypeName(first).equals(getTypeName(second));
    }

}
