package seedu.duke.modules;

public class Module {
    protected String moduleCode;
    protected String moduleName;
    protected double moduleCredits;

    public Module(String moduleCode, String moduleName, double moduleCredits) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleCredits = moduleCredits;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public double getModuleCredits() {
        return moduleCredits;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setModuleCredits(int moduleCredits) {
        this.moduleCredits = moduleCredits;
    }

    public String toFileFormat() {
        return moduleCode + " # " + moduleName + " # " + moduleCredits +
                System.lineSeparator();
    }
}