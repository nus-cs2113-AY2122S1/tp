package seedu.duke.modules;

import java.util.Comparator;

public class Module implements Comparator<Module> {
    protected int index;
    protected String moduleCode;
    protected String moduleName;
    protected double moduleCredits;

    public Module() {
    }

    public Module(String moduleCode, String moduleName, double moduleCredits, ModuleList moduleMasterList) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleCredits = moduleCredits;
        index = getMasterListIndex(moduleMasterList);
    }

    public Module(String moduleCode, String moduleName, double moduleCredits, int index) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleCredits = moduleCredits;
        this.index = index;
    }

    public int getIndex() {
        return index;
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

    public int getMasterListIndex(ModuleList moduleMasterList) {
        for (int i = 0; i < moduleMasterList.getSize(); i++) {
            if (moduleMasterList.get(i).getModuleCode().equals(moduleCode)) {
                return i + 1;
            }
        }
        return 0;
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
        return moduleCode + " # " + moduleName + " # " + moduleCredits
                + System.lineSeparator();
    }

    @Override
    public int compare(Module m, Module m1) {
        return m.index - m1.index;
    }


}