package seedu.duke.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.commons.util.LinkUtil.formatLink;

//@@author richwill28
public class LinkUtilTest {
    @Test
    public void formatLinkTest() {
        assertEquals("https://nusmods.com", formatLink("nusmods.com"));
        assertEquals("https://nusmods.com", formatLink("www.nusmods.com"));
        assertEquals("https://nusmods.com", formatLink("http://nusmods.com"));
        assertEquals("https://nusmods.com", formatLink("https://nusmods.com"));
    }
}
