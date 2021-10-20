package seedu.duke.commands;

import seedu.duke.storage.UniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class ListUniCommand extends Command {
    public ListUniCommand(UniversityList universityList) throws IOException {
        UniversityList universityMasterList = new UniversityList(UniversityStorage.load());
        System.out.println("Here are all the universities available for you:");
        assert universityMasterList.getSize() > 0;
        for (int i = 0; i < universityMasterList.getSize(); i++) {
            assert universityMasterList.get(i).getName() != null;
            Ui.printUniversity(universityMasterList.get(i), i + 1, universityMasterList);

        }
    }
}
