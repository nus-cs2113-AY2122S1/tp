package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.ui.UiMapping;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

public class RemoveMapCommand extends Command {
    public RemoveMapCommand(int universityIndexToMap, int moduleIndexToMap, UniversityList universityMasterList,
                            ModuleList moduleMasterList, UniversityList universitySelectedList,
                            ModuleList moduleSelectedList) throws IOException {
        University selectedUniversity = universityMasterList.get(universityIndexToMap - 1);
        University universityToMap = universitySelectedList.getUniversity(selectedUniversity.getName());
        ArrayList<ModuleMapping> selectedMappings = universityToMap.getSelectedMappings(moduleSelectedList);
        System.out.println("This module mapping is removed: ");
        ModuleMapping selectedMapping = selectedMappings.get(moduleIndexToMap - 1);
        Module localModule = moduleMasterList.get(moduleIndexToMap - 1);
        Module mappedModule = universityToMap.getMappedModule(localModule, moduleSelectedList);
        universityToMap.removeMapping(selectedMapping);
        UiMapping.printMapping(selectedMapping, universityToMap.getMappingListSize());
        storage.updateSelectedUniversityList(universitySelectedList);
    }
}