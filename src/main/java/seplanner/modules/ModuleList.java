package seplanner.modules;

import java.util.ArrayList;

public class ModuleList {
    protected ArrayList<Module> list;

    public ModuleList(ArrayList<Module> list) {
        this.list = list;
    }

    public ModuleList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds module with the given module code to the list.
     * @param module Module code of the module to be added
     */
    public void addModule(Module module) {
        assert module != null;
        list.add(module);
        list.sort(new Module());
        assert !list.isEmpty();
    }

    /**
     * Removes module with the given module code from the list.
     * @param moduleCode Module code of the module to be removed
     */
    public void removeModule(String moduleCode) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).moduleCode.equals(moduleCode)) {
                list.remove(i);
                break;
            }
        }
    }

    public int getSize() {
        return list.size();
    }

    /**
     * Looks for the module with the specific index from the list.
     * @param index Index of the module to look for
     * @return The module object at the given index position in the list
     */
    public Module get(int index) {
        assert index <= list.size();
        return list.get(index);
    }

    /**
     * Checks whether a module exists in the list.
     * @param moduleCode Module code of the module to check for
     * @return True if the modules exists, false otherwise
     */
    public boolean isModuleExist(String moduleCode) {
        for (Module module : list) {
            if (module.moduleCode.equals(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the module exists in the list.
     * @param searchModule Module to check
     * @return True if exists. Otherwise, false
     */
    public boolean isModuleExist(Module searchModule) {
        for (Module module : list) {
            if (module.isEqual(searchModule)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Finds the module object with the specified module code from the list.
     * @param moduleCode The module code of the target module
     * @return The module object with the given module code
     */
    public Module getModule(String moduleCode) {
        for (Module module : list) {
            if (module.moduleCode.equals(moduleCode)) {
                return module;
            }
        }
        return null;
    }

    public ArrayList<Module> getList() {
        return list;
    }

}