package seedu.duke.commands;

import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.ui.UiUniversity;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class RemoveUniCommand extends Command {
    private final University universityToRemove;
    private final int universityIndexToRemove;

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
            universitySelectedList.removeUniversity(universityToRemove.getName());
            assert !universitySelectedList.searchUniversity(universityToRemove.getName());
            storage.updateSelectedUniversityList(universitySelectedList);
            System.out.println("This university is removed: ");
            UiUniversity.printUniversity(universityToRemove, true);
        }
    }
}