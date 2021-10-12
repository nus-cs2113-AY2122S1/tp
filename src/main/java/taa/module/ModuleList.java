package taa.module;

import taa.ClassChecker;

import java.util.ArrayList;

public class ModuleList implements ClassChecker {
    private final ArrayList<Module> modules;

    public ModuleList() {
        this.modules = new ArrayList<>();
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public int getSize() {
        return modules.size();
    }

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
    public Module getModule(String code) {
        for (Module module : modules) {
            if (module.getCode().equals(code)) {
                return module;
            }
        }

        return null;
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

    @Override
    public boolean verify() {
        ArrayList<String> moduleCodes = new ArrayList<>();
        for (Module module : modules) {
            if (moduleCodes.contains(module.getCode())) {
                modules.remove(module);
            } else {
                moduleCodes.add(module.getCode());
            }
        }

        return true;
    }
}
