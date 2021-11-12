package seplanner.modules;

import java.util.Comparator;

//@@author MAZJ124

/**
 * Module class used to represent NUS or partner university module.
 */
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

    /**
     * Returns the index of the module with respect to the master list.
     * @param moduleMasterList The module master list.
     * @return The master list index of the module.
     */
    public int getMasterListIndex(ModuleList moduleMasterList) {
        for (int i = 0; i < moduleMasterList.getSize(); i++) {
            if (moduleMasterList.get(i).getModuleCode().equals(moduleCode)) {
                return i + 1;
            }
        }
        return -1;
    }

    //@@author madhanse
    /**
     * Checks whether two module objects are equal.
     * @param searchModule Module to check
     * @return True if equal. Otherwise, false
     */
    public boolean isEqual(Module searchModule) {
        return (getModuleCode().equals(searchModule.getModuleCode()))
                && (getModuleName().equals(searchModule.getModuleName()))
                && (getModuleCredits() == searchModule.getModuleCredits());
    }

    //@@author madhanse
    /**
     * Converts a module object into a string used to write it into a file.
     * @return Module in a string format
     */
    public String toFileFormat() {
        return moduleCode + " # " + moduleName + " # " + moduleCredits
                + System.lineSeparator();
    }

    //@@ author titustortoiseturltle1999
    @Override
    public int compare(Module m, Module m1) {
        return m.index - m1.index;
    }


}