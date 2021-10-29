package seplanner.commands;

import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.ui.UiMapping;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

public class RemoveMapCommand extends Command {
    public RemoveMapCommand(int universityIndexToMap, int moduleIndexToMap, UniversityList universityMasterList,
                            ModuleList moduleMasterList, UniversityList universitySelectedList,
                            ModuleList moduleSelectedList) throws IOException {
        University selectedUniversity = universityMasterList.get(universityIndexToMap - 1);
        University universityToMap = universitySelectedList.getUniversity(selectedUniversity.getName());
        ArrayList<ModuleMapping> selectedMappings = universityToMap.getList();
        System.out.println("This module mapping is removed: ");
        ModuleMapping selectedMapping = selectedMappings.get(moduleIndexToMap - 1);
        Module localModule = moduleMasterList.get(moduleIndexToMap - 1);
        Module mappedModule = universityToMap.getMappedModule(localModule, moduleSelectedList);
        universityToMap.removeMapping(selectedMapping);
        UiMapping.printMapping(selectedMapping, moduleIndexToMap);
        storage.updateSelectedUniversityList(universitySelectedList);
    }
}