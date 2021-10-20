package seedu.online;

import org.junit.jupiter.api.Test;
import seedu.exceptions.FetchException;
import seedu.module.Module;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NusModsTest {

    @Test
    public void load_Invalid_Mod_Exception_Thrown() throws FetchException {
        assertThrows(FetchException.class, () ->
                NusMods.fetchModOnline("This mod does not exist"));
    }

    @Test
    public void load_Valid_Mod() throws Exception {
        Module modCS2113T = NusMods.fetchModOnline("CS2113T");
        Module mod = new Module("Dummy mod");
        assertEquals(modCS2113T.getClass(), mod.getClass());
    }

}
