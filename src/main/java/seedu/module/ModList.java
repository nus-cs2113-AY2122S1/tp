package seedu.module;

import seedu.ui.TextUi;

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

    public Module fetchMod(String searchTerm) {
        for (int i = 0; i < moduleList.size(); i++) {
            Module module = moduleList.get(i);
            if (module.getModuleCode().equalsIgnoreCase(searchTerm)) {
                return module;
            }
        }
        TextUi.printNotFoundMessage();
        return null;
    }
}
