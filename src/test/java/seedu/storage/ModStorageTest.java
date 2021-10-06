package seedu.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.storage.ModStorage.loadModInfo;

import org.junit.jupiter.api.Test;
import seedu.module.Module;

import java.io.IOException;


public class ModStorageTest {

    @Test
    public void load_Invalid_File_Exception_Thrown() throws Exception {
        assertThrows(IOException.class, () ->
                loadModInfo("CS2113S"));
    }

    @Test
    public void load_Valid_File() throws Exception {
        Module modCS2113T = loadModInfo("CS2113T");
        Module mod = new Module("Dummy mod");
        assertEquals(modCS2113T.getClass(), mod.getClass());
    }

}
