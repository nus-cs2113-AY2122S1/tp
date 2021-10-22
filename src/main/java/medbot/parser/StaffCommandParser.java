package medbot.parser;

import medbot.command.Command;
import medbot.command.CommandType;
import medbot.command.HelpInfoCommand;
import medbot.command.personcommand.staffcommand.AddStaffCommand;
import medbot.command.personcommand.staffcommand.DeleteStaffCommand;
import medbot.command.personcommand.staffcommand.EditStaffCommand;
import medbot.command.personcommand.staffcommand.FindStaffCommand;
import medbot.command.personcommand.staffcommand.ListStaffCommand;
import medbot.command.personcommand.staffcommand.ViewStaffCommand;
import medbot.exceptions.MedBotParserException;
import medbot.person.Staff;

public abstract class StaffCommandParser {
    private static final String END_LINE = System.lineSeparator();

    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_SWITCH = "switch";

    private static final String ERROR_WRONG_COMMAND = "Unable to parse command." + END_LINE;
    private static final String EMPTY_STRING = "";

    /**
     * Parses the user input and returns the corresponding command when the view type is MEDICAL_STAFF_INFO.
     *
     * @param userInput String containing the full user input.
     * @return the corresponding Command object.
     * @throws MedBotParserException if user input is not a recognised command or contains invalid information.
     */
    public static Command parseStaffCommand(String userInput) throws MedBotParserException {
        if (userInput.startsWith(COMMAND_ADD)) {
            return parseAddStaffCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_DELETE)) {
            return parseDeleteStaffCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_VIEW)) {
            return parseViewStaffCommand(userInput);
        }
        if (userInput.equals(COMMAND_LIST)) {
            return new ListStaffCommand();
        }
        if (userInput.startsWith(COMMAND_EDIT)) {
            return parseEditStaffCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_FIND)) {
            return parseFindStaffCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_HELP)) {
            return parseHelpCommand(userInput);
        }
        throw new MedBotParserException(ERROR_WRONG_COMMAND);
    }

    /**
     * Parses user input and returns ViewStaffCommand with the specified staff ID.
     *
     * @param userInput String containing the full user input.
     * @return ViewStaffCommand object.
     * @throws MedBotParserException when staff id is not specified or not a number.
     */
    private static ViewStaffCommand parseViewStaffCommand(String userInput) throws MedBotParserException {
        int personId = ParserUtils.parseId(userInput.substring(4));
        return new ViewStaffCommand(personId);
    }

    /**
     * Parses user input and returns DeleteStaffCommand with the specified staff ID.
     *
     * @param userInput String containing the full user input.
     * @return DeleteStaffCommand object.
     * @throws MedBotParserException when staff id given is not specified or not a number.
     */
    private static DeleteStaffCommand parseDeleteStaffCommand(String userInput) throws MedBotParserException {
        int personId = ParserUtils.parseId(userInput.substring(6));
        return new DeleteStaffCommand(personId);
    }

    /**
     * Parses user input and returns EditStaffCommand with the specified patient ID and parameters.
     *
     * @param userInput String containing the full user input.
     * @return EditStaffCommand objects
     * @throws MedBotParserException when staff id given is not specified or not a number, or when
     *                               the parameters given cannot be parsed.
     */
    private static EditStaffCommand parseEditStaffCommand(String userInput) throws MedBotParserException {
        int staffId = ParserUtils.parseId(userInput.substring(4));
        String[] parameters = ParserUtils.getParameters(userInput);
        Staff staff = new Staff();
        staff.setNull();
        ParserUtils.updateMultiplePersonalInformation(staff, parameters);
        return new EditStaffCommand(staffId, staff);
    }

    /**
     * Parses user input and returns AddStaffCommand with the specified parameters.
     *
     * @param userInput String containing the full user input.
     * @return AddStaffCommand object.
     * @throws MedBotParserException when no parameters are specified, or when the parameters given cannot be parsed.
     */
    private static AddStaffCommand parseAddStaffCommand(String userInput) throws MedBotParserException {
        String[] parameters = ParserUtils.getParameters(userInput);
        Staff staff = new Staff();
        ParserUtils.updateMultiplePersonalInformation(staff, parameters);
        return new AddStaffCommand(staff);
    }

    private static FindStaffCommand parseFindStaffCommand(String userInput) throws MedBotParserException {
        String[] parameters = ParserUtils.getParameters(userInput);
        return new FindStaffCommand(parameters);
    }

    /**
     * Parses user input to pass relevant parameters into the HelpCommand constructor.
     *
     * @param userInput String containing the full user input.
     * @return HelpCommand object.
     * @throws MedBotParserException if parameters.length < 1 && > 2
     */
    private static Command parseHelpCommand(String userInput) throws MedBotParserException {
        String commandTypeString = EMPTY_STRING;
        try {
            commandTypeString = userInput.substring(4).strip();
        } catch (IndexOutOfBoundsException ie) {
            return new HelpInfoCommand();
        }
        if (commandTypeString.equals(EMPTY_STRING)) {
            return new HelpInfoCommand();
        }

        CommandType commandType = parseHelpInfoViewCommandType(commandTypeString);
        return new HelpInfoCommand(commandType);
    }

    private static CommandType parseHelpInfoViewCommandType(String commandTypeString) throws MedBotParserException {
        switch (commandTypeString) {
        case COMMAND_ADD:
            return CommandType.ADD_PERSON;
        case COMMAND_DELETE:
            return CommandType.DELETE_PERSON;
        case COMMAND_EDIT:
            return CommandType.EDIT_PERSON;
        case COMMAND_EXIT:
            return CommandType.EXIT;
        case COMMAND_HELP:
            return CommandType.HELP;
        case COMMAND_LIST:
            return CommandType.LIST_PERSON;
        case COMMAND_SWITCH:
            return CommandType.SWITCH;
        case COMMAND_VIEW:
            return CommandType.VIEW_PERSON;
        case COMMAND_FIND:
            return CommandType.FIND_PERSON;
        default:
            throw new MedBotParserException(ERROR_WRONG_COMMAND);
        }
    }
}
