package seplanner.commands;

import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.ui.UiMapping;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

//@@author Jiale-Sun
/**
 * Command to add a module mapping under a selected university to the selected university list.
*/
public class AddMapCommand extends Command {
    
    /**
     * Add a module mapping under a selected university to the selected university list.
     *
     * @param universityIndexToMap index of the university to add the mapping under.
     * @param selectedMappingIndex index of the mapping to add under the selected university.
     * @param universityMasterList The master university list which contains all universities.
     * @param moduleMasterList The master module list which contains all modules.
     * @param universitySelectedList The selected university list which contains only the university selected.
     * @param moduleSelectedList The selected module list which contains only the module selected by the user.
    */
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
