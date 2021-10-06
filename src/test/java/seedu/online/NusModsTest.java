package seedu.online;

import org.junit.jupiter.api.Test;
import seedu.module.Module;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.online.NusMods.fetchModOnline;

public class NusModsTest {

    @Test
    public void load_Invalid_Mod_Exception_Thrown() throws Exception {
        assertThrows(IOException.class, () ->
                fetchModOnline("This mod does not exist"));
    }

    @Test
    public void load_Valid_Mod() throws Exception {
        Module modCS2113T = fetchModOnline("CS2113T");
        Module mod = new Module("Dummy mod");
        assertEquals(modCS2113T.getClass(), mod.getClass());
    }

}
