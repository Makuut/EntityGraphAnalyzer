package org.makuut;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    @DisplayName("Проверка удаления двойных кавычек")
    void deleteQuotesTest() {
        String source = "\"participant.participantType\"";
        String expected = "participant.participantType";

        String actual = StringUtils.deleteQuotes(source);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка получения строки типа из строки типизированного объекта")
    void getValueTest() {
        String source = "Set<@Valid Attachment>";
        String expected = "Attachment";

        String actual = StringUtils.getValue(source);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Удаление расширения из паттерна файла")
    void getEntityClassPatternTest() {
        String source = ".*Entity.java";
        String expected = ".*Entity";

        String actual = StringUtils.getEntityClassPattern(source);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Объединение двух строк")
    void twoString() {
        String source = "accreditationStatementEntitySet.accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature";
        String expected = "accreditationStatementEntitySet.accreditationStatementDocEntitySet.accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature";

        String actual = StringUtils.joinGraph(source);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Объединение трех строк")
    void threeString() {
        String source = "accreditationStatementEntitySet.accreditationStatementDocEntitySet\" + \".accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature\" + \".something";
        String expected = "accreditationStatementEntitySet.accreditationStatementDocEntitySet.accreditationStatementDocAttachmentEntitySet.attachment.signatureAttachments.signature.something";

        String actual = StringUtils.joinGraph(source);

        assertEquals(expected, actual);
    }
}