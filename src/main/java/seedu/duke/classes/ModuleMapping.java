package seedu.duke.classes;

public class ModuleMapping {
    protected java.lang.Module localModule;
    protected java.lang.Module mappedModule;

    public ModuleMapping(java.lang.Module localModule, java.lang.Module mappedModule) {
        this.localModule = localModule;
        this.mappedModule = mappedModule;
    }

    public java.lang.Module getLocalModule() {
        return localModule;
    }

    public void setLocalModule(java.lang.Module localModule) {
        this.localModule = localModule;
    }

    public java.lang.Module getMappedModule() {
        return mappedModule;
    }

    public void setMappedModule(java.lang.Module mappedModule) {
        this.mappedModule = mappedModule;
    }

}