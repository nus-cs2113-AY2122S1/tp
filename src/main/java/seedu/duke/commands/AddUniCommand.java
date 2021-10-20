package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class AddUniCommand extends AddCommand {
    private final University universityToAdd;
    private final int universityIndexToAdd;

    public AddUniCommand(int universityIndexToAdd, UniversityList universitySelectedList)
            throws IOException {
        this.universityIndexToAdd = universityIndexToAdd;
        this.universityToAdd = universitySelectedList.get(universityIndexToAdd);
        assert universityToAdd.getName() != null;
        assert universityToAdd.getClass() != null;
        universitySelectedList.addUniversity(universityToAdd);
        assert universitySelectedList.getSize() != 0;
        assert universitySelectedList.searchUniversity(universityToAdd.getName());
        assert universitySelectedList.get(universitySelectedList.getSize() - 1)
                .getName().equals(universityToAdd.getName());
        SelectedUniversityStorage.write(universitySelectedList);
        System.out.println("New university added: ");
        Ui.printUniversity(universityToAdd, universityIndexToAdd, universitySelectedList);
    }

    public AddUniCommand(University universityToAdd, UniversityList universitySelectedList,
                         ModuleList moduleSelectedListint) throws IOException {
        this.universityToAdd = universityToAdd;
        assert universityToAdd.getName() != null;
        assert universityToAdd.getClass() != null;
        universitySelectedList.addUniversity(universityToAdd);
        this.universityIndexToAdd = universityToAdd.getMasterListIndex(universitySelectedList);
        assert universitySelectedList.getSize() != 0;
        assert universitySelectedList.searchUniversity(universityToAdd.getName());
        assert universitySelectedList.get(universitySelectedList.getSize() - 1)
                .getName().equals(universityToAdd.getName());
        SelectedUniversityStorage.write(universitySelectedList);
        System.out.println("New university added: ");
        Ui.printUniversity(universityToAdd, universityIndexToAdd, universitySelectedList);
    }
}