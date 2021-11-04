package seplanner.commands;

import seplanner.ui.UiUniversity;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.IOException;

/**
 * Command to add a university to the selected university list.
*/
public class AddUniCommand extends Command {
    private final University universityToAdd;
    private final int universityIndexToAdd;
    
    /**
     * Add a university to the selected university list.
     *
     * @param universityToAdd university to add to the selected university list.
     * @param universityMasterList The master university list which contains all universities.
     * @param universitySelectedList The selected university list which contains only the universities selected by the user.
     * @throws IOException If input-output operation failed.
    */
    public AddUniCommand(University universityToAdd, UniversityList universityMasterList,
                         UniversityList universitySelectedList) throws IOException {
        this.universityToAdd = universityToAdd;
        assert universityToAdd.getName() != null;
        assert universityToAdd.getClass() != null;
        universitySelectedList.addUniversity(universityToAdd);
        this.universityIndexToAdd = universityToAdd.getMasterListIndex(universityMasterList);
        assert universitySelectedList.getSize() != 0;
        assert universitySelectedList.isExistUniversity(universityToAdd.getName());
        assert universitySelectedList.get(universitySelectedList.getSize() - 1)
                .getName().equals(universityToAdd.getName());
        storage.updateSelectedUniversityList(universitySelectedList);
        System.out.println("New university added: ");
        UiUniversity.printUniversity(universityToAdd, false);
    }
}
