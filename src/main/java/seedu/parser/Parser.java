package seedu.parser;

import seedu.command.AddContactCommand;
import seedu.command.Command;
import seedu.command.ExitCommand;
import seedu.command.FailedCommand;

public class Parser {
    private final String ADD_TASK_COMMAND = "add";
    private final String EXIT_COMMAND = "exit";

    private final int COMMAND_WORD_INDEX = 0;
    private final int ISOLATE_COMMAND_WORD = 2;
    private final int CONTACT_PARAMS = 1;

    private final String NAME_TAG = " n/";
    private final String GITHUB_TAG = " g/";

    public Command parseCommand(String userInput) {
        String commandType = getCommandWord(userInput);
        Command command;
        switch (commandType) {
        case ADD_TASK_COMMAND:
            command = parseAddContact(userInput);
            break;
        case EXIT_COMMAND:
            command = new ExitCommand();
            break;
        default:
            command = new FailedCommand(FailedCommandType.GENERAL);
        }
        return command;
    }

    public String getCommandWord(String userInput) {
        String[] destructuredInputs = userInput.split(" ", ISOLATE_COMMAND_WORD);
        return destructuredInputs[COMMAND_WORD_INDEX];
    }

    // Scuffed version
    private Command parseAddContact(String userInput) {
        String[] destructuredInputs = userInput.split(" ", ISOLATE_COMMAND_WORD);
        String contact = destructuredInputs[1];
        String name = contact.substring(contact.indexOf(NAME_TAG) + 3, contact.indexOf(GITHUB_TAG));
        String github = contact.substring(contact.indexOf(GITHUB_TAG) + 3);
        return new AddContactCommand(name, github);
    }


    // Will create a function to parse details soon, hardcoding for V0.1 (7/10/21 version)
//    private String parseDetail(String contactParameters, String tag) {
//
//    }
}
