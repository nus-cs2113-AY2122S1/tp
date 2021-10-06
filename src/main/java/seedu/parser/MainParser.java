package seedu.parser;

import seedu.command.AddContactCommand;
import seedu.command.EditContactCommand;
import seedu.command.Command;
import seedu.command.ExitCommand;
import seedu.command.FailedCommand;
import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;

public class MainParser {
    private static final String ADD_CONTACT_COMD = "add";
    private static final String EXIT_COMD = "exit";
    private static final String EDIT_CONTACT_COMD = "edit";

    private static final int COMD_WORD_INDEX = 0;
    private static final int EDIT_USER_INDEX = 1;
    private static final int EDIT_INFO_INDEX = 2;
    private static final int ISOLATE_COMD_WORD = 2;
    public static final int NUMBER_OF_EDIT_DETAILS = 3;

    private ContactParser contactParser;
    private AddContactParser addContactParser = new AddContactParser();
    private EditContactParser editContactParser = new EditContactParser();

    public Command parseCommand(String userInput) {
        String commandType = getCommandWord(userInput);
        Command command;
        switch (commandType) {
        case ADD_CONTACT_COMD:
            command = parseAddContact(userInput);
            break;
        case EDIT_CONTACT_COMD:
            command = parseEditContact(userInput); //todo change
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
}
