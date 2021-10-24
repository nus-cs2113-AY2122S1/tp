package seedu.duke.commands;

import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class RemoveUniCommand extends Command {
    private final University universityToRemove;
    private final int universityIndexToRemove;

    public RemoveUniCommand(int universityIndexToRemove, UniversityList universityMasterList,
                            UniversityList universitySelectedList) throws IOException {
        this.universityIndexToRemove = universityIndexToRemove;
        this.universityToRemove = universityMasterList.get(universityIndexToRemove - 1);
        if (universitySelectedList.getSize() == 0) {
            assert universitySelectedList.getSize() == 0;
            System.out.println("The university list is empty!");
        } else {
            assert universityToRemove.getName() != null;
            assert universitySelectedList.getSize() != 0;
            universitySelectedList.removeUniversity(universityToRemove.getName());
            assert !universitySelectedList.searchUniversity(universityToRemove.getName());
            SelectedUniversityStorage.write(universitySelectedList);
            System.out.println("This university is removed: ");
            Ui.printUniversity(universityToRemove);
        }
    }

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
            SelectedUniversityStorage.write(universitySelectedList);
            System.out.println("This university is removed: ");
            Ui.printUniversity(universityToRemove);
        }
    }
}