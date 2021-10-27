package seedu.duke.universities;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.ui.Ui;
import seedu.duke.constants.Constants;
import seedu.duke.ui.UiMapping;

import java.util.ArrayList;
import java.util.Comparator;

public class University implements Comparator<University> {
    protected int index;
    protected String name;
    public ArrayList<ModuleMapping> list;

    public University() {
    }

    public University(String name, ArrayList<ModuleMapping> list, UniversityList universityMasterList) {
        this.name = name;
        this.list = list;
        this.index = getMasterListIndex(universityMasterList);
    }

    public University(String name, ArrayList<ModuleMapping> list, int index) {
        this.index = index;
        this.name = name;
        this.list = list;
    }

    public void addMapping(ModuleMapping moduleMapping) {
        list.add(moduleMapping);
    }

    public void removeMapping(ModuleMapping moduleMapping) {
        list.remove(moduleMapping);
    }

    public void clearMappings() {
        list = new ArrayList<>();
    }

    public void listAllMappings() {
        for (int i = 0; i < list.size(); i++) {
            ModuleMapping currentMapping = list.get(i);
            System.out.print(Constants.INDENTATION);
            UiMapping.printMappingForList(currentMapping, i + 1);
        }
    }

    public void listSelectedMappings(ModuleList selectedModuleList) {
        ArrayList<ModuleMapping> selectedMappings = getSelectedMappings(selectedModuleList);
        for (int i = 0; i < selectedMappings.size(); i++) {
            UiMapping.printMappingForList(selectedMappings.get(i), i + 1);
        }
    }

    public ArrayList<ModuleMapping> getSelectedMappings(ModuleList selectedModuleList) {
        ArrayList<ModuleMapping> selectedMappings = new ArrayList<>();
        for (ModuleMapping currentMapping : list) {
            if (selectedModuleList.isModuleExist(currentMapping.localModule.getModuleCode())) {
                selectedMappings.add(currentMapping);
            }
        }
        return selectedMappings;
    }

    public ArrayList<ModuleMapping> getList() {
        return list;
    }

    public int getSelectedMappingListSize(ModuleList selectedModuleList) {
        return getSelectedMappings(selectedModuleList).size();
    }

    public ModuleMapping getMapping(Module selectedLocalModule, ModuleList selectedModuleList) {
        ModuleMapping selectedMapping = null;
        for (int i = 0; i < list.size(); i++) {
            ModuleMapping currentMapping = list.get(i);
            if (currentMapping.localModule.getModuleCode()
                    .equals(selectedLocalModule.getModuleCode())) {
                selectedMapping = currentMapping;
            }
        }
        return selectedMapping;
    }

    public int getMappingListSize() {
        return list.size();
    }

    public Module getMappedModule(Module selectedLocalModule, ModuleList selectedModuleList) {
        String mappedModuleCode = "";
        for (int i = 0; i < list.size(); i++) {
            ModuleMapping currentMapping = list.get(i);
            if (currentMapping.localModule.getModuleCode()
                    .equals(selectedLocalModule.getModuleCode())) {
                mappedModuleCode = currentMapping.getMappedModule().getModuleCode();
            }
        }
        return selectedModuleList.getModule(mappedModuleCode);
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public int getMasterListIndex(UniversityList universityMasterList) {
        for (int i = 0; i < universityMasterList.getSize(); i++) {
            if (name.equals(universityMasterList.get(i).getName())) {
                return i + 1;
            }
        }
        return 0;
    }

    public boolean isExistMapping(ModuleMapping mapping) {
        return list.contains(mapping);
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

    @Override
    public int compare(University u, University u1) {
        return u.index - u1.index;
    }

    public double getMC() {
        double totalMCs = 0;
        for (int i = 0; i < list.size(); i++) {
            totalMCs += list.get(i).getLocalModule().getModuleCredits();
        }
        return totalMCs;
    }
}