package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class AddUniCommand extends Command {
    private final University universityToAdd;
    private final int universityIndexToAdd;

    public AddUniCommand(int universityIndexToAdd, UniversityList universityMasterList,
                         UniversityList universitySelectedList)
            throws IOException {
        this.universityIndexToAdd = universityIndexToAdd;
        this.universityToAdd = universityMasterList.get(universityIndexToAdd - 1);
        assert universityToAdd.getName() != null;
        assert universityToAdd.getClass() != null;
        universitySelectedList.addUniversity(universityToAdd);
        assert universitySelectedList.getSize() != 0;
        assert universitySelectedList.searchUniversity(universityToAdd.getName());
        assert universitySelectedList.get(universitySelectedList.getSize() - 1)
                .getName().equals(universityToAdd.getName());
        SelectedUniversityStorage.write(universitySelectedList);
        System.out.println("New university added: ");
        Ui.printUniversity(universityToAdd);
    }

    public AddUniCommand(University universityToAdd, UniversityList universityMasterList,
                         UniversityList universitySelectedList) throws IOException {
        this.universityToAdd = universityToAdd;
        assert universityToAdd.getName() != null;
        assert universityToAdd.getClass() != null;
//        universityToAdd.clearMappings();
        universitySelectedList.addUniversity(universityToAdd);
        this.universityIndexToAdd = universityToAdd.getMasterListIndex(universityMasterList);
        assert universitySelectedList.getSize() != 0;
        assert universitySelectedList.searchUniversity(universityToAdd.getName());
        assert universitySelectedList.get(universitySelectedList.getSize() - 1)
                .getName().equals(universityToAdd.getName());
        SelectedUniversityStorage.write(universitySelectedList);
        System.out.println("New university added: ");
        Ui.printUniversity(universityToAdd);
    }
}