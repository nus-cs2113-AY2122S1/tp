package seedu.duke.universities;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.modules.ModuleMapping;

import java.util.ArrayList;

public class University {
    protected String name;
    public ArrayList<ModuleMapping> list;

    public University(String name, ArrayList<ModuleMapping> list) {
        this.name = name;
        this.list = list;
    }

    public void addMapping(Module localModule, Module mappedModule) {
        list.add(new ModuleMapping(localModule, mappedModule));
    }

    public void listAllMappings() {
        for (int i = 0; i < list.size(); i++) {
            ModuleMapping currentMapping = list.get(i);
            String output = (i + 1) + ". " +
                    currentMapping.localModule.getModuleCode()
                    + " - " + currentMapping.mappedModule.getModuleCode()
                    + " : " + currentMapping.mappedModule.getModuleName();
            System.out.println(output);
        }
    }

    public void listSelectedMappings(ModuleList selectedModuleList) {
        int mapCount = 0;
        for (int i = 0; i < list.size(); i++) {
            ModuleMapping currentMapping = list.get(i);
            if (!selectedModuleList.searchModule(currentMapping.localModule.getModuleCode())) {
                continue;
            }
            mapCount++;
            String output = (mapCount) + ". " +
                    currentMapping.localModule.getModuleCode()
                    + " - " + currentMapping.mappedModule.getModuleCode()
                    + " : " + currentMapping.mappedModule.getModuleName();
            System.out.println(output);
        }
    }

    public String getName() {
        return name;
    }

    public int getMasterListIndex(UniversityList universityMasterList) {
        for (int i = 0; i < universityMasterList.getSize(); i++) {
            if (name.equals(universityMasterList.get(i).getName())) {
                return i + 1;
            }
        }
        return 0;
    }

}