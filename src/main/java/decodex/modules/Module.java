package decodex.modules;

import decodex.data.Data;
import decodex.data.exception.ModuleException;

// @@author arraysius
/**
 * Module serves as a foundation for other modules to be built upon.
 */
public abstract class Module {

    protected String name;
    protected String description;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    // @@author

    // @@author Kair0s3
    /**
     * Returns a String formatted Module.
     *
     * @return The string formatted Module.
     */
    @Override
    public String toString() {
        return name;
    }
    // @@author

    // @@author arraysius
    /**
     * Performs data processing on the provided Data object. Abstract method to be implemented by other modules.
     *
     * @return Data object containing new processed data.
     * @throws ModuleException If module execution failed.
     */
    public abstract Data run(Data data) throws ModuleException;
}
// @@author
