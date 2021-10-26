package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.ui.UiMapping;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

public class AddMapCommand extends Command {
    public AddMapCommand(int universityIndexToMap, int selectedMappingIndex, UniversityList universityMasterList,
                         ModuleList moduleMasterList, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws IOException {
        // selectedUniversity is the uni object from the master list
        University selectedUniversity = universityMasterList.get(universityIndexToMap - 1);
        // universityToMap is the uni object from the selected list
        University universityToMap = universitySelectedList.getUniversity(selectedUniversity.getName());
        ArrayList<ModuleMapping> selectedMappings = selectedUniversity.getSelectedMappings(moduleSelectedList);
        System.out.println("New module mapping added: ");
        ModuleMapping selectedMapping = selectedMappings.get(selectedMappingIndex - 1);
        universityToMap.addMapping(selectedMapping);
        UiMapping.printMapping(selectedMapping, selectedMappingIndex);
        storage.updateSelectedUniversityList(universitySelectedList);
    }
}