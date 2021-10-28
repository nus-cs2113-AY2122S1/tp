package taa.module;

//@@author leyondlee
import taa.ClassChecker;

import java.util.ArrayList;

public class ModuleList implements ClassChecker {
    private final ArrayList<Module> modules;

    public ModuleList() {
        this.modules = new ArrayList<>();
    }

    public int getSize() {
        return modules.size();
    }

    /**
     * Gets the list of modules. Note: This returns a new ArrayList instance.
     *
     * @return A new ArrayList containing all the modules.
     */
    public ArrayList<Module> getModules() {
        return new ArrayList<>(modules);
    }

    /**
     * Checks if an index is valid with respect to the modules list.
     *
     * @param index The index to check.
     * @return true if valid, else false.
     */
    public boolean isValidIndex(int index) {
        return (index >= 0 && index < getSize());
    }

    /**
     * Adds a Module object to the list of modules.
     *
     * @param module The Module object to add.
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
     * Gets a module at the specified index.
     *
     * @param index The index of the module.
     * @return A module object if index is valid, else null.
     */
    public Module getModuleAt(int index) {
        if (isValidIndex(index)) {
            return modules.get(index);
        }

        return null;
    }

    /**
     * Gets the module from the list with the specified code.
     *
     * @param code The module code to search for.
     * @return A Module object if found, else null.
     */
    public Module getModuleWithCode(String code) {
        for (Module module : modules) {
            if (module.getCode().equals(code)) {
                return module;
            }
        }

        return null;
    }

    /**
     * Deletes a particular module from the list.
     *
     * @param module The module object to delete.
     * @return true if successfully removed, else false.
     */
    public boolean deleteModule(Module module) {
        assert module != null;

        return modules.remove(module);
    }

    public void deleteAllModules() {
        modules.clear();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getSize(); i += 1) {
            if (i > 0) {
                stringBuilder.append("\n");
            }

            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(modules.get(i));
        }

        return stringBuilder.toString();
    }

    /**
     * Checks if the variables in the class are valid. Filters out duplicate modules with the same module code.
     *
     * @return Always returns true.
     */
    @Override
    public boolean verify() {
        ArrayList<String> moduleCodes = new ArrayList<>();
        ArrayList<Module> duplicatedModules = new ArrayList<>();
        for (Module module : modules) {
            if (moduleCodes.contains(module.getCode())) {
                duplicatedModules.add(module);
            } else {
                moduleCodes.add(module.getCode());
            }
        }

        for (Module module : duplicatedModules) {
            modules.remove(module);
        }

        return true;
    }
}
