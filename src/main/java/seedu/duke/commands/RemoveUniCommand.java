package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class RemoveUniCommand extends Command {
    public RemoveUniCommand(int universityIndexToRemove, UniversityList universityMasterList,
                            UniversityList universitySelectedList) throws IOException {
        if (universitySelectedList.getSize() == 0) {
            assert universitySelectedList.getSize() == 0;
            System.out.println("The university list is empty!");
        } else {
            University universityToRemove = universityMasterList.get(universityIndexToRemove);
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