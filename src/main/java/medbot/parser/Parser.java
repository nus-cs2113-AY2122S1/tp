package medbot.parser;


import medbot.command.Command;
import medbot.command.CommandType;
import medbot.command.ExitCommand;
import medbot.command.GetCurrentViewCommand;
import medbot.command.HelpCommand;
import medbot.command.SwitchCommand;
import medbot.exceptions.MedBotParserException;
import medbot.utilities.ViewType;

public abstract class Parser {
    private static final String END_LINE = System.lineSeparator();
    private static final String EMPTY_STRING = "";

    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_SWITCH = "switch";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_ARCHIVE = "archive";
    private static final String COMMAND_UNARCHIVE = "unarchive";
    private static final String COMMAND_GET_VIEW = "get view";

    private static final String VIEW_TYPE_PATIENT_VIEW = "p";
    private static final String VIEW_TYPE_PATIENT_VIEW_ALT = "1";
    private static final String VIEW_TYPE_MEDICAL_STAFF_VIEW = "m";
    private static final String VIEW_TYPE_MEDICAL_STAFF_VIEW_ALT = "2";
    private static final String VIEW_TYPE_SCHEDULER_VIEW = "s";
    private static final String VIEW_TYPE_SCHEDULER_VIEW_ALT = "3";

    private static final String ERROR_WRONG_COMMAND = "Unable to parse command." + END_LINE;
    private static final String ERROR_NO_VIEW_FOUND = "Unidentified view." + END_LINE;

    private static final String ERROR_PATIENT_ID_NOT_SPECIFIED = "Patient ID not specified." + END_LINE;
    private static final String ERROR_STAFF_ID_NOT_SPECIFIED = "Staff ID not specified." + END_LINE;

    private static final String ERROR_INVALID_VIEW_TYPE = "Invalid view type code." + END_LINE;

    private static ViewType viewType = ViewType.PATIENT_INFO;

    public static ViewType getViewType() {
        return viewType;
    }

    public static void setViewType(ViewType viewType) {
        Parser.viewType = viewType;
    }

    /**
     * Parses the user input and returns the corresponding command based on the parser's view type.
     *
     * @param userInput String containing the full user input.
     * @return the corresponding Command object.
     * @throws MedBotParserException if command is unrecognised.
     */
    public static Command parseCommand(String userInput) throws MedBotParserException {
        userInput = ParserUtils.preprocessInput(userInput);
        //commands valid in all viewTypes
        if (userInput.startsWith(COMMAND_SWITCH)) {
            return parseSwitchCommand(userInput);
        }
        if (userInput.equals(COMMAND_EXIT)) {
            return new ExitCommand();
        }
        if (userInput.startsWith(COMMAND_HELP)) {
            return parseHelpCommand(userInput);
        }

        if (userInput.startsWith(COMMAND_GET_VIEW)) {
            return new GetCurrentViewCommand(getViewType());
        }

        //commands valid in only some viewTypes
        switch (viewType) {
        case PATIENT_INFO:
            return PatientCommandParser.parsePatientCommand(userInput);
        case MEDICAL_STAFF_INFO:
            return StaffCommandParser.parseStaffCommand(userInput);
        case SCHEDULER:
            return SchedulerCommandParser.parseSchedulingCommand(userInput);
        default:
            assert false;
            throw new MedBotParserException(ERROR_NO_VIEW_FOUND);
        }
    }

    /**
     * Parses user input to pass relevant parameters into the HelpCommand constructor.
     *
     * @param userInput String containing the full user input.
     * @return HelpCommand object.
     * @throws MedBotParserException if parameters.length < 1 && > 2
     */
    private static HelpCommand parseHelpCommand(String userInput) throws MedBotParserException {
        String commandTypeString = EMPTY_STRING;
        try {
            commandTypeString = userInput.substring(4).strip();
        } catch (IndexOutOfBoundsException ie) {
            return new HelpCommand(getViewType());
        }
        if (commandTypeString.equals(EMPTY_STRING)) {
            return new HelpCommand(getViewType());
        }
        CommandType commandType = parseHelpCommandType(commandTypeString);
        return new HelpCommand(getViewType(),commandType);
    }

    private static CommandType parseHelpCommandType(String commandTypeString) throws MedBotParserException {
        switch (commandTypeString) {
        case COMMAND_ADD:
            return CommandType.ADD;
        case COMMAND_DELETE:
            return CommandType.DELETE;
        case COMMAND_EDIT:
            return CommandType.EDIT;
        case COMMAND_EXIT:
            return CommandType.EXIT;
        case COMMAND_HELP:
            return CommandType.HELP;
        case COMMAND_LIST:
            return CommandType.LIST;
        case COMMAND_SWITCH:
            return CommandType.SWITCH;
        case COMMAND_VIEW:
            return CommandType.VIEW;
        case COMMAND_FIND:
            return CommandType.FIND;
        case COMMAND_ARCHIVE:
            return CommandType.ARCHIVE;
        case COMMAND_UNARCHIVE:
            return CommandType.UNARCHIVE;
        case COMMAND_GET_VIEW:
            return CommandType.GET_VIEW;
        default:
            throw new MedBotParserException(ERROR_WRONG_COMMAND);
        }
    }

    /**
     * Processes user input and returns a SwitchCommand.
     *
     * <p>If view type is specified, returns a switch command with that new view type. If not, returns
     * a switch command that iterates through the various views.
     *
     * @param userInput String containing the full user input.
     * @return SwitchCommand with the new view type if specified
     * @throws MedBotParserException if an invalid view type code is specified
     */
    private static SwitchCommand parseSwitchCommand(String userInput) throws MedBotParserException {
        String newType;
        try {
            newType = userInput.substring(6).strip();
        } catch (IndexOutOfBoundsException ie) {
            newType = EMPTY_STRING;
        }
        switch (newType) {
        case EMPTY_STRING:
            return new SwitchCommand(ViewType.getNextView(viewType));
        case VIEW_TYPE_PATIENT_VIEW:
        case VIEW_TYPE_PATIENT_VIEW_ALT:
            return new SwitchCommand(ViewType.PATIENT_INFO);
        case VIEW_TYPE_MEDICAL_STAFF_VIEW:
        case VIEW_TYPE_MEDICAL_STAFF_VIEW_ALT:
            return new SwitchCommand(ViewType.MEDICAL_STAFF_INFO);
        case VIEW_TYPE_SCHEDULER_VIEW:
        case VIEW_TYPE_SCHEDULER_VIEW_ALT:
            return new SwitchCommand(ViewType.SCHEDULER);
        default:
            throw new MedBotParserException(ERROR_INVALID_VIEW_TYPE);
        }
    }
}
