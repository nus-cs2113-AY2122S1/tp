package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public RemoveCommand(String type, String description,
                         UniversityList universitySelectedList, ModuleList moduleSelectedList) {
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
                System.out.println("The university: " + description + " is removed.");
            }
            break;
        default:
            break;
        }
    }
}