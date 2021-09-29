package seedu.module;

import java.util.ArrayList;

public class ModList {

    private ArrayList<Module> moduleList;

    public ModList() {
        this.moduleList = new ArrayList<Module>();
    }

    public int getSize() {
        return moduleList.size();
    }

    public void addMod(Module module) {
        moduleList.add(module);
    }

    public Module getMod(int index) {
        return moduleList.get(index);
    }

    public void clearMods() {
        moduleList.clear();
    }
}