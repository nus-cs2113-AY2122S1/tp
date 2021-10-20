package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.storage.UniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class ListUniCommand extends Command {
    public ListUniCommand(UniversityList universityList) throws IOException {
        if (universityList.getSize() == 0) {
            assert universityList.getSize() == 0;
            System.out.println("The university list is empty!");
        } else {
            assert universityList.getSize() > 0;
            System.out.println("Here are the universities and module mappings in the list:");
            for (int i = 0; i < universityList.getSize(); i++) {
                assert universityList.get(i).getName() != null;
                Ui.printUniversity(universityList.get(i), i + 1, universityList);
            }
        }
    }
}