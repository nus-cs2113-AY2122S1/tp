package seplanner.commands;

import seplanner.ui.UiUniversity;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.IOException;

/**
 * Command to remove a university from the selected university list.
*/
public class RemoveUniCommand extends Command {
    private final University universityToRemove;
    private final int universityIndexToRemove;

    /**
     * Remove a university from the selected university list.
     *
     * @param universityToRemove university to remove from the selected university list.
     * @param universityMasterList The master university list which contains all universities.
     * @param universitySelectedList The selected university list which contains only the universities selected by the user.
     * @throws IOException If input-output operation failed.
    */
    public RemoveUniCommand(University universityToRemove, UniversityList universityMasterList,
                            UniversityList universitySelectedList) throws IOException {
        this.universityToRemove = universityToRemove;
        this.universityIndexToRemove = universityToRemove.getMasterListIndex(universityMasterList);
        if (universitySelectedList.getSize() == 0) {
            assert universitySelectedList.getSize() == 0;
            System.out.println("The university list is empty!");
        } else {
            assert universityToRemove.getName() != null;
            assert universitySelectedList.getSize() != 0;
            System.out.println("This university is removed: ");
            UiUniversity.printUniversity(universityToRemove, true);
            universitySelectedList.removeUniversity(universityToRemove.getName());
            assert !universitySelectedList.isExistUniversity(universityToRemove.getName());
            storage.updateSelectedUniversityList(universitySelectedList);
        }
    }
}
