package org.makuut;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * Вспомогательный класс для работы с файлами
 */
public class FileUtils {

    /**
     * @param pattern   Шаблон по которому определяются искомые файлы
     * @param directory Директория для поиска
     * @param result    Список найденных файлов
     */
    public static void search(final String pattern, final File directory, List<File> result) {
        for (final File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                search(pattern, file, result);
            }
            if (file.isFile()) {
                if (file.getName().matches(pattern)) {
                    result.add(file);
                }
            }
        }
    }
}
