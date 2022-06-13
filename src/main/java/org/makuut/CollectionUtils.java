package org.makuut;

import java.util.Map;

/**
 * Вспомогательный класс для работы с коллекциями
 */
public class CollectionUtils {

    /**
     * Проверяет карту на null и пустоту
     *
     * @param map Карта
     * @return Логическое выражение
     */
    public static boolean isBlank(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
