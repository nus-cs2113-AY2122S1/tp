package seedu.duke.parser.module;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.module.StorageModuleException;
import seedu.duke.exceptions.storage.StorageException;
import seedu.duke.module.Module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author nvbinh15

/**
 * A test class for ParserModule.
 */
class ParserModuleTest {

    /**
     * Checks formatModuleToStore return the expected data with valid input Module.
     */
    @Test
    void formatModuleToStore_sampleModule_respectiveStringRepOfSampleModule() {
        Module sampleModule = new Module("CS2113T", "Software Engineering", 4, "A");
        String data = ParserModule.formatModuleToStore(sampleModule);
        String expectedData = "CS2113T|Software Engineering|A|4\n";
        assertEquals(data, expectedData);
    }

    /**
     * Checks retrieveStoredModule return the expected Module with valid data in the storage file.
     *
     * @throws StorageException If there is something wrong with the storage file.
     */
    @Test
    void retrieveStoredModule_validData_expectedModule() throws StorageModuleException {
        String data = "CS2113T|Software Engineering|A|4";
        Module retrievedModule = ParserModule.retrieveStoredModule(data);
        Module expectedModule = new Module("CS2113T", "Software Engineering", 4, "A");
        assertEquals(retrievedModule.toString(), expectedModule.toString());
    }

    /**
     * Checks StorageExceptionThrown when the data in the storage file passed into retrievedStoredModule is invalid.
     */
    @Test
    void retrieveStoredModule_invalidData_StorageExceptionThrown() {
        String invalidData = "CS2113T|Software Engineering|A";
        assertThrows(StorageModuleException.class, () -> ParserModule.retrieveStoredModule(invalidData));
    }

    /**
     * Checks formatCapInfoToStore return the expected data with valid input.
     */
    @Test
    void formatCapInfoToStore_sampleCapInfo_respectiveFormattedData() {
        double cap = 4.5;
        int totalMc = 50;
        String data = ParserModule.formatCapInfoToStore(cap, totalMc);
        String expectedData = "4.5|50\n";
        assertEquals(data, expectedData);
    }

    /**
     * Checks StorageException thrown when data in the storage file passed into retrieveStoreData is invalid.
     */
    @Test
    void retrieveStoredCapInfo_invalidData_StorageExceptionThrown() {
        String invalidData = "4.5|";
        assertThrows(StorageModuleException.class, () -> ParserModule.retrieveStoredCapInfo(invalidData));
    }

}