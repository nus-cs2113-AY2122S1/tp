package terminus.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModuleManagerTest {

    public static final String TEMP_MODULE = "Test";
    private ModuleManager moduleManager;

    @BeforeEach
    void setUp() {
        moduleManager = new ModuleManager();
    }

    @Test
    void getModule_success() {
        moduleManager.addModule(TEMP_MODULE);
        assertNotNull(moduleManager.getModule(TEMP_MODULE));
        moduleManager.removeModule(TEMP_MODULE);
        assertNull(moduleManager.getModule(TEMP_MODULE));
    }

    @Test
    void getAllModules_success() {
        IntStream.range(0, 5).forEach(i -> moduleManager.addModule(TEMP_MODULE + i));
        String[] listOfModules = moduleManager.getAllModules();
        assertEquals(5, listOfModules.length);
        assertTrue(Arrays.asList(listOfModules).contains(TEMP_MODULE.toUpperCase() + 3));
    }

    @Test
    void updateModule_success() {
        moduleManager.addModule(TEMP_MODULE);
        NusModule tempMod = moduleManager.getModule(TEMP_MODULE);
        moduleManager.removeModule(TEMP_MODULE);
        moduleManager.setModule("newName", tempMod);
        assertNotNull(moduleManager.getModule("newName"));
        moduleManager.removeModule("newName");
        assertNull(moduleManager.getModule("newName"));
    }
}
