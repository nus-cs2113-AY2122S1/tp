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
        ArrayList<ModuleMapping> selectedMappings = getSelectedMappings(selectedModuleList);
        for (int i = 0; i < selectedMappings.size(); i++) {
            System.out.print((i + 1) + ". ");
            selectedMappings.get(i).printModuleMapping();
        }
    }

    public ArrayList<ModuleMapping> getSelectedMappings(ModuleList selectedModuleList) {
        ArrayList<ModuleMapping> selectedMappings = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ModuleMapping currentMapping = list.get(i);
            if (selectedModuleList.searchModule(currentMapping.localModule.getModuleCode())) {
                selectedMappings.add(currentMapping);
            }
        }
        return selectedMappings;
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

    public String toFileFormat() {
        String result = name + System.lineSeparator();
        for (int i = 0; i < list.size(); i++) {
            Module local = list.get(i).getLocalModule();
            Module mapped = list.get(i).getMappedModule();
            result += (local.getModuleCode() + " # " + local.getModuleName()
                    + " # " + local.getModuleCredits() + " # " + mapped.getModuleCode()
                    + " # " + mapped.getModuleName() + " # " + mapped.getModuleCredits()
                    + System.lineSeparator());
        }
        return result;
    }
}