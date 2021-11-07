package seplanner.modules;

public class ModuleMapping {
    protected Module localModule;
    protected Module mappedModule;

    //@@author titustortoiseturtle1999
    public ModuleMapping(Module localModule, Module mappedModule) {
        this.localModule = localModule;
        this.mappedModule = mappedModule;
    }

    //@@author madhanse
    /**
     * Checks if two module mapping objects are equal.
     * @param searchMapping Module mapping to check.
     * @return True if equal. Otherwise, false.
     */
    public boolean isEqual(ModuleMapping searchMapping) {
        return getLocalModule().isEqual(searchMapping.localModule)
                && getMappedModule().isEqual(searchMapping.mappedModule);
    }

    public Module getLocalModule() {
        return localModule;
    }

    public Module getMappedModule() {
        return mappedModule;
    }

}