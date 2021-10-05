package seedu.duke.commands;

import duke.Classes.*;

public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public RemoveCommand(String type, String description) {
        switch (type) {
        case "m":
            if (selectedModuleList.size() == 0) {
                System.out.println("The module list is empty!");
            } else {
                selectedModuleList.removeModule(description);
                System.out.println("The module: " + description + " is removed.");
            }
            break;
        case "u":
            if (universityList.size() == 0) {
                System.out.println("The university list is empty!");
            } else {
                universityList.removeUniversity(description);
                System.out.println("The module: " + description + " is removed.");
            }
            break;
        }
    }
}