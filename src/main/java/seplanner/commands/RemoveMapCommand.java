package seplanner.commands;

import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.ui.UiMapping;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

//@@author Jiale-Sun
/**
 * Command to remove a module mapping under a selected university from the selected university list.
*/
public class RemoveMapCommand extends Command {
    
    /**
     * Remove a module mapping under a selected university from the selected university list.
     *
     * @param universityIndexToMap index of the university to remove the mapping under.
     * @param selectedMappingIndex index of the mapping to remove under the selected university.
     * @param universityMasterList The master university list which contains all universities.
     * @param universitySelectedList The selected university list which contains only the university selected.
     * @throws IOException If input-output operation failed.
    */
    public RemoveMapCommand(int universityIndexToMap, int selectedMappingIndex, UniversityList universityMasterList,
                             UniversityList universitySelectedList) throws IOException {
        University selectedUniversity = universityMasterList.get(universityIndexToMap - 1);
        University universityToMap = universitySelectedList.getUniversity(selectedUniversity.getName());
        ArrayList<ModuleMapping> selectedMappings = universityToMap.getList();
        System.out.println("This module mapping is removed: ");
        ModuleMapping selectedMapping = selectedMappings.get(selectedMappingIndex - 1);
        universityToMap.removeMapping(selectedMapping);
        UiMapping.printMapping(selectedMapping, selectedMappingIndex, false);
        storage.updateSelectedUniversityList(universitySelectedList);
    }
}
