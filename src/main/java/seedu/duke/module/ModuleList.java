package seedu.duke.module;

import java.util.ArrayList;

public class ModuleList {
    private static final String MESSAGE_MODULE_LIST_HEADER = "Module List:";

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

    public boolean isValidIndex(int index) {
        return (index >= 0 && index < getSize());
    }

    public Module getModuleAt(int index) {
        if (isValidIndex(index)) {
            return modules.get(index);
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_MODULE_LIST_HEADER);
        for (int i = 0; i < getSize(); i += 1) {
            stringBuilder.append("\n");
            stringBuilder.append(i + 1);
            stringBuilder.append(": ");
            stringBuilder.append(getModuleAt(i));
        }

        return stringBuilder.toString();
    }
}
