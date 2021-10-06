package seedu.parser;


import seedu.command.AddContactCommand;
import seedu.command.DeleteContactCommand;
import seedu.command.Command;
import seedu.command.ExitCommand;
import seedu.command.FailedCommand;

public class Parser {
    private static final String ADD_TASK_COMD = "add";
    private static final String DELETE_TASK_COMD = "rm";
    private static final String EXIT_COMD = "exit";

    private static final int COMD_WORD_INDEX = 0;
    private static final int ISOLATE_COMD_WORD = 2;
    private static final int CONTACT_PARAMS = 1;

    private static final String NAME_TAG = " n/";
    private static final String GITHUB_TAG = " g/";

    public Command parseCommand(String userInput) {
        String commandType = getCommandWord(userInput);
        Command command;
        switch (commandType) {
        case ADD_TASK_COMD:
            command = parseAddContact(userInput);
            break;
        case DELETE_TASK_COMD:
            command = parseDeleteContact(userInput);
            break;
        case EXIT_COMD:
            command = new ExitCommand();
            break;
        default:
            command = new FailedCommand(FailedCommandType.GENERAL);
        }
        return command;
    }

    public String getCommandWord(String userInput) {
        String[] destructuredInputs = userInput.split(" ", ISOLATE_COMD_WORD);
        return destructuredInputs[COMD_WORD_INDEX];
    }

    // Scuffed version
    private Command parseAddContact(String userInput) {
        String[] destructuredInputs = userInput.split(" ", ISOLATE_COMD_WORD);
        String contact = destructuredInputs[1];
        String name = contact.substring(contact.indexOf(NAME_TAG) + 3, contact.indexOf(GITHUB_TAG));
        String github = contact.substring(contact.indexOf(GITHUB_TAG) + 3);
        return new AddContactCommand(name, github);
    }

    private Command parseDeleteContact(String userInput) {
        String[] destructuredInputs = userInput.split(" ", ISOLATE_COMD_WORD);
        int deletedIndex = Integer.parseInt(destructuredInputs[1]) - 1;
        return new DeleteContactCommand(deletedIndex);
    }


    // Will create a function to parse details soon, hardcoding for V0.1 (7/10/21 version)
    //    private String parseDetail(String contactParameters, String tag) {
    //
    //    }
}
