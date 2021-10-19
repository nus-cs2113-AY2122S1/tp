package seedu.parser;

import seedu.command.AddContactCommand;
import seedu.command.EditContactCommand;
import seedu.command.DeleteContactCommand;
import seedu.command.Command;
import seedu.command.HelpCommand;
import seedu.command.InvalidDetailCommand;
import seedu.command.ViewContactCommand;
import seedu.command.FailedCommand;
import seedu.command.ExitCommand;
import seedu.command.ListContactsCommand;
import seedu.command.SearchContactCommand;

import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidEmailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.exception.MissingArgException;
import seedu.exception.MissingDetailException;
import seedu.exception.MissingNameException;

import static seedu.parser.ContactParser.NUMBER_OF_FIELDS;

public class MainParser {
    private static final String ADD_CONTACT_COMD = "add";
    private static final String EDIT_CONTACT_COMD = "edit";
    private static final String DELETE_CONTACT_COMD = "rm";
    private static final String VIEW_CONTACT_COMD = "view";
    private static final String EXIT_COMD = "exit";
    private static final String LIST_COMD = "list";
    private static final String HELP_COMD = "help";
    private static final String SEARCH_COMD = "search";

    private static final int COMD_WORD_INDEX = 0;
    private static final int ISOLATE_COMD_WORD = 2;
    public static final int NAME_INDEX = 0;

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
            command = parseEditContact(userInput);
            break;
        case DELETE_CONTACT_COMD:
            command = parseDeleteContact(userInput);
            break;
        case VIEW_CONTACT_COMD:
            command = parseViewContact(userInput);
            break;
        case EXIT_COMD:
            command = new ExitCommand();
            break;
        case LIST_COMD:
            command = new ListContactsCommand();
            break;
        case HELP_COMD:
            command = new HelpCommand();
            break;
        case SEARCH_COMD:
            command = new parseSearchCommand(userInput);
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
            //check if name is specified in input
            if (details[NAME_INDEX] == null) {
                throw new MissingNameException();
            }
            assert details.length == NUMBER_OF_FIELDS;
            return new AddContactCommand(details);
        } catch (InvalidFlagException e) {
            return new FailedCommand(FailedCommandType.INVALID_FLAG);
        } catch (MissingArgException e) {
            return new FailedCommand(FailedCommandType.MISSING_ARG);
        } catch (MissingNameException e) {
            return new FailedCommand(FailedCommandType.MISSING_NAME);
        } catch (MissingDetailException e) {
            return new FailedCommand(FailedCommandType.MISSING_DETAIL);
        } catch (InvalidNameException | InvalidGithubUsernameException | InvalidEmailException
                | InvalidLinkedinUsernameException | InvalidTelegramUsernameException
                | InvalidTwitterUsernameException | ForbiddenDetailException e) {
            return parseInvalidDetailCommand(e);
        }
    }

    private Command parseEditContact(String userInput) { // userInput is raw user input
        contactParser = editContactParser;
        try {
            String[] details = editContactParser.parseContactDetails(userInput);
            //throws InvalidFlagException, MissingDetailException, MissingArgException
            int userIndex = IndexParser.getIndexFromInput(userInput); //throws MissingArgException
            return new EditContactCommand(details, userIndex);
        } catch (InvalidFlagException e) {
            return new FailedCommand(FailedCommandType.INVALID_FLAG);
        } catch (MissingArgException e) {
            return new FailedCommand(FailedCommandType.MISSING_ARG);
        } catch (NumberFormatException e) {
            return new FailedCommand(FailedCommandType.INVALID_INDEX);
        } catch (MissingDetailException e) {
            return new FailedCommand(FailedCommandType.MISSING_DETAIL);
        } catch (InvalidNameException | InvalidGithubUsernameException | InvalidEmailException
                | InvalidLinkedinUsernameException | InvalidTelegramUsernameException
                | InvalidTwitterUsernameException | ForbiddenDetailException e) {
            return parseInvalidDetailCommand(e);
        }
    }

    //@@author mayankp291
    private Command parseInvalidDetailCommand(Exception e) {
        if (e instanceof InvalidNameException) {
            return new InvalidDetailCommand(FailedCommandType.INVALID_NAME);
        }
        if (e instanceof InvalidGithubUsernameException) {
            return new InvalidDetailCommand(FailedCommandType.INVALID_GITHUB_USERNAME);
        }
        if (e instanceof InvalidEmailException) {
            return new InvalidDetailCommand(FailedCommandType.INVALID_MAIL);
        }
        if (e instanceof InvalidTelegramUsernameException) {
            return new InvalidDetailCommand(FailedCommandType.INVALID_TELEGRAM);
        }
        if (e instanceof InvalidTwitterUsernameException) {
            return new InvalidDetailCommand(FailedCommandType.INVALID_TWITTER);
        }
        if (e instanceof InvalidLinkedinUsernameException) {
            return new InvalidDetailCommand(FailedCommandType.INVALID_LINKEDIN);
        }
        if (e instanceof ForbiddenDetailException) {
            return new InvalidDetailCommand(FailedCommandType.FORBIDDEN_DETAIL);
        }
        assert false; // Exception should be caught before this
        return new FailedCommand(FailedCommandType.GENERAL);
    }

    //@@author
    private Command parseViewContact(String userInput) {
        try {
            int viewedIndex = IndexParser.getIndexFromInput(userInput);
            return new ViewContactCommand(viewedIndex);
        } catch (MissingArgException e) {
            return new FailedCommand(FailedCommandType.MISSING_ARG);
        } catch (NumberFormatException e) {
            return new FailedCommand(FailedCommandType.INVALID_INDEX);
        }
    }

    private Command parseDeleteContact(String userInput) {
        try {
            int deletedIndex = IndexParser.getIndexFromInput(userInput);
            return new DeleteContactCommand(deletedIndex);
        } catch (MissingArgException e) {
            return new FailedCommand(FailedCommandType.MISSING_ARG);
        } catch (NumberFormatException e) {
            return new FailedCommand(FailedCommandType.INVALID_INDEX);
        }
    }
}
