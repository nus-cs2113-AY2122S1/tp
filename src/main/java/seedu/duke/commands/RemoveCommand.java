package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public RemoveCommand(String type, String description,
                         UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws IOException {
        super(universitySelectedList, moduleSelectedList);
        assert type != null;
        switch (type) {
        case "m":
            if (moduleSelectedList.getSize() == 0) {
                assert moduleSelectedList.getSize() == 0;
                System.out.println("The module list is empty!");
            } else {
                assert description != null;
                assert moduleSelectedList.getSize() != 0;
                moduleSelectedList.removeModule(description);
                assert !moduleSelectedList.searchModule(description);
                SelectedModuleStorage.write(moduleSelectedList);
                System.out.println("The module: " + description + " is removed.");
            }
            break;
        case "u":
            if (universitySelectedList.getSize() == 0) {
                assert universitySelectedList.getSize() == 0;
                System.out.println("The university list is empty!");
            } else {
                assert description != null;
                assert universitySelectedList.getSize() != 0;
                universitySelectedList.removeUniversity(description);
                assert !universitySelectedList.searchUniversity(description);
                SelectedUniversityStorage.write(universitySelectedList);
                System.out.println("The university: " + description + " is removed.");
            }
            break;
        default:
            break;
        }
    }
}