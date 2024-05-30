package org.makuut.util;

/**
 * Вспомогательный класс для работы со строками
 */
public class StringUtils {

    private StringUtils() {
    }

    private static final String PLUS = "+";
    private static final String PLUS_PATTERN = "\\+";
    private static final String WHITESPACE = " ";
    private static final String DOT = ".";
    private static final char DOUBLE_QUOTE = '"';

    /**
     * Удаляет начальную и конечную двойные кавычки
     *
     * @param str Строка
     * @return Строка без начальной и конечной двойных кавычек
     */
    public static String deleteQuotes(String str) {
        int leftQuote = str.indexOf(DOUBLE_QUOTE);
        int rightQuote = str.lastIndexOf(DOUBLE_QUOTE);

        if (leftQuote == -1 || rightQuote == -1 || leftQuote == rightQuote) {
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

        if (leftBracket == -1 || rightBracket == -1 || leftBracket == rightBracket) {
            return str;
        }

        str = str.substring(leftBracket + 1, rightBracket);
        if (str.contains(WHITESPACE)) {
            String[] split = str.split(WHITESPACE);
            return split[split.length - 1];
        }
        return str;
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
     * Получает имя типа, удалением директорий
     *
     * @param str Полное имя типа
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
     * @param first  Имя типа
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
