package seedu.parser;


import seedu.command.AddContactCommand;
import seedu.command.DeleteContactCommand;
import seedu.command.Command;
import seedu.command.ExitCommand;
import seedu.command.FailedCommand;
import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;

public class MainParser {
    private static final String ADD_TASK_COMD = "add";
    private static final String DELETE_TASK_COMD = "rm";
    private static final String EXIT_COMD = "exit";

    private static final int COMD_WORD_INDEX = 0;
    private static final int ISOLATE_COMD_WORD = 2;

    private ContactParser contactParser;
    private AddContactParser addContactParser = new AddContactParser();

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

    private Command parseAddContact(String userInput) {
        contactParser = addContactParser;
        try {
            String[] details = contactParser.parseContactDetails(userInput);
            return new AddContactCommand(details[DetailType.NAME.getIndex()], details[DetailType.GITHUB.getIndex()]);
        } catch (InvalidFlagException e) {
            return new FailedCommand(FailedCommandType.INVALID_FLAG);
        }
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
