package seedu.duke.commands;

public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public RemoveCommand(String type, String description) {
        switch (type) {
        case "m":
            if (selectedModuleList.getSize() == 0) {
                System.out.println("The module list is empty!");
            } else {
                selectedModuleList.removeModule(description);
                System.out.println("The module: " + description + " is removed.");
            }
            break;
        case "u":
            if (universityList.getSize() == 0) {
                System.out.println("The university list is empty!");
            } else {
                universityList.removeUniversity(description);
                System.out.println("The module: " + description + " is removed.");
            }
            break;
        default:
            break;
        }
    }
}