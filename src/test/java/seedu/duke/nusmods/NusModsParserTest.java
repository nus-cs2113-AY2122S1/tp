package seedu.duke.nusmods;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import seedu.duke.task.type.Event;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NusModsParserTest {
    NusModsParser parser = new NusModsParser();

    @Test
    void getModuleEvents_CS2113T_success() throws IOException {
        Event[] moduleEvents = parser.getModuleEvents("CS2113T", Collections.singleton("C02"));
        assertEquals(4, moduleEvents.length);
    }

    @Test
    void getModuleEvents_noNetworkAndNoLocalCache_failure() {
        try {
            FileUtils.deleteDirectory(new File(NusModsParser.CACHEDIR)); // remove local cache if existing
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("https.proxyHost", "localhost"); // simulate network down
        assertThrows(IOException.class,
            () -> parser.getModuleEvents("CS2113T", Collections.singleton("C02")));
    }
}