package seplanner.commands;

import seplanner.modules.ModuleList;
import seplanner.ui.Ui;
import seplanner.ui.UiUniversity;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

/**
 * Command to search module mappings for one university or all universities in the selected university list.
*/
public class SearchMapCommand extends Command {

    private final University selectedUniversity;

    /**
     * Find module mappings for one university or all universities in the selected university list.
     *
     * @param selectedUniversity university selected to search module mappings for.
     * @param universitySelectedList The selected university list which contains only the university selected.
     * @param moduleSelectedList The selected module list which contains only the module selected by the user.
     * @param isAll refers to whether to list mappings for one university or all universities in the selected list.
    */
    public SearchMapCommand(University selectedUniversity, UniversityList universitySelectedList,
                            UniversityList universityMasterList, ModuleList moduleSelectedList, boolean isAll) {
        assert selectedUniversity.getName() != null;
        assert selectedUniversity.getClass() != null;
        this.selectedUniversity = selectedUniversity;
        if (isAll) {
            for (University uni : universitySelectedList.getList()) {
                String uniName = uni.getName();
                University uniFromMasterList = universityMasterList.getUniversity(uniName);
                UiUniversity.printMappings(uniFromMasterList, moduleSelectedList);
            }
        } else {
            UiUniversity.printMappings(selectedUniversity, moduleSelectedList);
        }
    }

    /**
     * Get the selected university.
     *
     * @return the selected university.
    */    
    public University getSelectedUniversity() {
        assert selectedUniversity.getName() != null;
        assert selectedUniversity.getClass() != null;
        return selectedUniversity;
    }

}
