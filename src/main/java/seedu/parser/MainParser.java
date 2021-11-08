package seedu.parser;

import seedu.command.AddContactCommand;
import seedu.command.Command;
import seedu.command.DeleteContactCommand;
import seedu.command.EditContactCommand;
import seedu.command.ExitCommand;
import seedu.command.FailedCommand;
import seedu.command.HelpCommand;
import seedu.command.ImportContactCommand;
import seedu.command.InvalidDetailCommand;
import seedu.command.ListContactsCommand;
import seedu.command.PersonalContactCommand;
import seedu.command.SearchContactCommand;
import seedu.command.ViewContactCommand;
import seedu.exception.DuplicateDetailException;
import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidDeleteDetailException;
import seedu.exception.InvalidEmailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.InvalidGithubUsernameException;
import seedu.exception.InvalidLinkedinUsernameException;
import seedu.exception.InvalidNameException;
import seedu.exception.InvalidTelegramUsernameException;
import seedu.exception.InvalidTwitterUsernameException;
import seedu.exception.MissingArgAddException;
import seedu.exception.MissingArgEditException;
import seedu.exception.MissingArgSearchException;
import seedu.exception.MissingDetailException;
import seedu.exception.MissingIndexException;


import static seedu.parser.ContactParser.NUMBER_OF_FIELDS;

public class MainParser {
    public static final int NAME_INDEX = 0;
    private static final String ADD_CONTACT_COMD = "add";
    private static final String EDIT_CONTACT_COMD = "edit";
    private static final String DELETE_CONTACT_COMD = "rm";
    private static final String VIEW_CONTACT_COMD = "view";
    private static final String EXIT_COMD = "exit";
    private static final String LIST_COMD = "ls";
    private static final String HELP_COMD = "help";
    private static final String SEARCH_COMD = "search";
    private static final String IMPORT_COMD = "import";
    private static final String PERSONAL_CONTACT_COMD = "me";
    private static final int COMD_WORD_INDEX = 0;
    private static final int ISOLATE_COMD_WORD = 2;
    private final AddContactParser addContactParser = new AddContactParser();
    private final EditContactParser editContactParser = new EditContactParser();
    private final SearchContactParser searchContactParser = new SearchContactParser();
    private final DeleteContactParser deleteContactParser = new DeleteContactParser();

    public Command parseCommand(String userInput) {
        CommandType commandType = getCommandType(userInput);
        String commandWord = getCommandWord(userInput);
        Command command;
        switch (commandType) {
        case MANIPULATION:
            command = parseManipulationCommand(commandWord, userInput);
            break;
        case QUERY:
            command = parseQueryCommand(commandWord, userInput);
            break;
        case CONTROL:
            command = parseControlCommand(commandWord);
            break;
        default:
            command = new FailedCommand(FailedCommandType.GENERAL);
        }
        return command;
    }

    /**
     * Returns the command word string given in the user input.
     *
     * @param userInput String that user has input
     * @return String command word in user input
     */
    public String getCommandWord(String userInput) {
        String[] destructuredInputs = userInput.split(" ", ISOLATE_COMD_WORD);
        return destructuredInputs[COMD_WORD_INDEX];
    }

    /**
     * Returns the command type of the command given in the user input. This method
     * differentiates the command given by user into either manipulation, query or
     * control type.
     *
     * @param userInput String that user has input
     * @return CommandType command type of the command given in user input
     */
    public CommandType getCommandType(String userInput) {
        String commandWord = getCommandWord(userInput);
        CommandType commandType;
        switch (commandWord) {
        case ADD_CONTACT_COMD: // Fallthrough
        case EDIT_CONTACT_COMD: // Fallthrough
        case DELETE_CONTACT_COMD: // Fallthrough
        case IMPORT_COMD:
            commandType = CommandType.MANIPULATION;
            break;
        case VIEW_CONTACT_COMD: // Fallthrough
        case SEARCH_COMD: // Fallthrough
        case LIST_COMD: // Fallthrough
        case HELP_COMD: // Fallthrough
        case PERSONAL_CONTACT_COMD:
            commandType = CommandType.QUERY;
            break;
        case EXIT_COMD:
            commandType = CommandType.CONTROL;
            break;
        default:
            commandType = CommandType.FAILED;
        }
        return commandType;
    }

    private Command parseManipulationCommand(String commandWord, String userInput) {
        Command command;
        switch (commandWord) {
        case ADD_CONTACT_COMD:
            command = parseAddContact(userInput);
            break;
        case EDIT_CONTACT_COMD:
            command = parseEditContact(userInput);
            break;
        case DELETE_CONTACT_COMD:
            command = parseDeleteContact(userInput);
            break;
        case IMPORT_COMD:
            command = new ImportContactCommand();
            break;
        default:
            assert false;
            command = new FailedCommand(FailedCommandType.GENERAL);
        }
        return command;
    }

    private Command parseControlCommand(String commandWord) {
        Command command;
        switch (commandWord) {
        case EXIT_COMD:
            command = new ExitCommand();
            break;
        default:
            assert false;
            command = new FailedCommand(FailedCommandType.GENERAL);
        }
        return command;
    }

    private Command parseQueryCommand(String commandWord, String userInput) {
        Command command;
        switch (commandWord) {
        case VIEW_CONTACT_COMD:
            command = parseViewContact(userInput);
            break;
        case LIST_COMD:
            command = new ListContactsCommand();
            break;
        case HELP_COMD:
            command = new HelpCommand();
            break;
        case SEARCH_COMD:
            command = parseSearchCommand(userInput);
            break;
        case PERSONAL_CONTACT_COMD:
            command = parsePersonalContact();
            break;
        default:
            assert false;
            command = new FailedCommand(FailedCommandType.GENERAL);
        }
        return command;
    }

    //@@author marcusbory

    /**
     * Returns an AddContactCommand after parsing the user's input if the specified input command was for adding a
     * contact.
     *
     * @param userInput String that user has input
     * @return Command AddContactCommand if the parsing was successful
     */
    private Command parseAddContact(String userInput) {
        try {
            String[] details = addContactParser.parseContactDetails(userInput);
            //check if name is specified in input
            if (details[NAME_INDEX] == null) {
                throw new MissingArgAddException();
            }
            assert details.length == NUMBER_OF_FIELDS;
            return new AddContactCommand(details);
        } catch (InvalidFlagException e) {
            return new FailedCommand(FailedCommandType.INVALID_FLAG);
        } catch (MissingDetailException | MissingArgAddException e) {
            return new FailedCommand(FailedCommandType.MISSING_ARGS_ADD);
        } catch (DuplicateDetailException e) {
            return new FailedCommand(FailedCommandType.DUPLICATE_DETAIL);
        } catch (InvalidNameException | InvalidGithubUsernameException | InvalidEmailException
                | InvalidLinkedinUsernameException | InvalidTelegramUsernameException
                | InvalidTwitterUsernameException | ForbiddenDetailException e) {
            return parseInvalidDetailCommand(e);
        }
    }

    /**
     * Parses the edit command and returns a new command with the required parameters.
     *
     * @param userInput The command input from the user in full.
     * @return new EditContactCommand with the edited details and contact index
     */
    //@@author ng-andre
    private Command parseEditContact(String userInput) { // userInput is raw user input
        try {
            String[] details = editContactParser.parseContactDetails(userInput);
            int userIndex = IndexParser.getIndexFromInput(userInput); //throws MissingIndexException
            //throws InvalidFlagException, MissingDetailException, MissingArgEditException
            return new EditContactCommand(details, userIndex);
        } catch (InvalidFlagException e) {
            return new FailedCommand(FailedCommandType.INVALID_FLAG);
        } catch (NumberFormatException e) {
            return new FailedCommand(FailedCommandType.INVALID_INDEX_EDIT);
        } catch (MissingIndexException | MissingDetailException | MissingArgEditException e) {
            return new FailedCommand(FailedCommandType.MISSING_ARGS_EDIT);
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(FailedCommandType.NUM_OUT_OF_BOUND_EDIT);
        } catch (DuplicateDetailException e) {
            return new FailedCommand(FailedCommandType.DUPLICATE_DETAIL);
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

    //@@author ashrafjfr

    /**
     * This method parses the userInput according to the view command. After parsing for the viewedIndex, it will
     * initialise a new ViewContactCommand.
     *
     * @param userInput String that user has input
     * @return new ViewContactCommand(viewedIndex)
     */
    private Command parseViewContact(String userInput) {
        try {
            int viewedIndex = IndexParser.getIndexFromInput(userInput);
            return new ViewContactCommand(viewedIndex);
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(FailedCommandType.NUM_OUT_OF_BOUND);
        } catch (NumberFormatException | MissingIndexException e) {
            return new FailedCommand(FailedCommandType.MISSING_INDEX_VIEW);
        }
    }

    //@@author lezongmun
    /**
     * Returns a DeleteContactCommand after parsing the user's input if the
     * specified input command was for deletion.
     *
     * @param userInput String that user has input
     * @return Command DeleteContactCommand
     */
    private Command parseDeleteContact(String userInput) {
        try {
            boolean[] hasDeletedDetail = deleteContactParser.hasDeletedDetail(userInput);
            int deletedIndex = IndexParser.getIndexFromInput(userInput);
            return new DeleteContactCommand(deletedIndex, hasDeletedDetail);
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(FailedCommandType.NUM_OUT_OF_BOUND);
        } catch (MissingIndexException | NumberFormatException e) {
            return new FailedCommand(FailedCommandType.MISSING_INDEX_DELETE);
        } catch (InvalidFlagException e) {
            return new FailedCommand(FailedCommandType.INVALID_FLAG);
        } catch (InvalidDeleteDetailException e) {
            return new FailedCommand(FailedCommandType.INVALID_DELETE);
        }
    }

    /**
     * Parses the search command and returns a new command with the required parameters.
     *
     * @param userInput The command input from the user in full.
     * @return new SearchContactCommand with query and detailFlag
     */
    //@@author ng-andre
    private Command parseSearchCommand(String userInput) {
        try {
            //string contains at least "search"
            String searchInput = searchContactParser.getSearchInput(userInput);
            int detailFlag = searchContactParser.getDetailFlag(searchInput);
            String query = searchContactParser.parseSearchQuery(searchInput);
            return new SearchContactCommand(query, detailFlag);
        } catch (MissingArgSearchException e) {
            return new FailedCommand(FailedCommandType.MISSING_ARGS_SEARCH);
        } catch (InvalidFlagException e) {
            return new FailedCommand(FailedCommandType.INVALID_FLAG);
        }
    }

    //@author lezongmun
    /**
     * Returns a PersonalContactCommand after parsing the user's input if the specified
     * input command was for viewing personal contact.
     *
     * @return Command PersonalContactCommand
     */
    private Command parsePersonalContact() {
        return new PersonalContactCommand();
    }
}
