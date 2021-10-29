package seplanner.commands;

import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.ui.UiMapping;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

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