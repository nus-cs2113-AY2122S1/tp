package seedu.duke.universities;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleMapping;

import java.util.ArrayList;

public class University {
    protected String name;
    protected ArrayList<ModuleMapping> list;

    public University(String name, ArrayList<ModuleMapping> list) {
        this.name = name;
        this.list = list;
    }

    public void addMapping(Module localModule, Module mappedModule) {
        list.add(new ModuleMapping(localModule, mappedModule));
    }

    public void listMappings() {
        for (int i = 0; i < list.size(); i++) {
            ModuleMapping currentMapping = list.get(i);
            String output = currentMapping.localModule.getModuleName()
                    + " - " + currentMapping.mappedModule.getModuleName();
        }
    }

    public String getName() {
        return name;
    }

    public String toFileFormat() {
        String result = name + System.lineSeparator();
        for (int i = 0; i < list.size(); i++) {
            Module local = list.get(i).getLocalModule();
            Module mapped = list.get(i).getMappedModule();
            result += (" | " + local.getModuleCode() + " | " + local.getModuleName() +
                    " | " + local.getModuleCredits() + " | " + mapped.getModuleCode() +
                    " | " + mapped .getModuleName() + " | " + mapped.getModuleCredits() +
                    System.lineSeparator());
        }
        return result;
    }
}