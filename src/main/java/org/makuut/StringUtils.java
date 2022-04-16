package org.makuut;

/**
 * Вспомогательный класс для работы со строками
 */
public class StringUtils {

    private static final String JAVA_FILE_EXTENSION = ".java";
    private static final String PLUS = "+";
    private static final String PLUS_PATTERN = "\\+";
    private static final String WHITESPACE = " ";

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
     * Объедияет строки разделеные "+"
     *
     * @param str Строка
     * @return Объединенная строка
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
}
