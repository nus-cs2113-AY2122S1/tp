package medbot.parser;

import medbot.Appointment;
import medbot.command.Command;
import medbot.command.appointmentcommand.AddAppointmentCommand;
import medbot.command.appointmentcommand.DeleteAppointmentCommand;
import medbot.command.appointmentcommand.EditAppointmentCommand;
import medbot.command.appointmentcommand.ListAppointmentCommand;
import medbot.command.appointmentcommand.ViewAppointmentCommand;
import medbot.exceptions.MedBotParserException;

public abstract class SchedulerCommandParser {
    private static final String END_LINE = System.lineSeparator();

    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_SWITCH = "switch";

    private static final String ERROR_WRONG_COMMAND = "Unable to parse command." + END_LINE;
    private static final String EMPTY_STRING = "";

    /**
     * Parses the user input and returns the corresponding command when the view type is SCHEDULER.
     *
     * @param userInput String containing the full user input.
     * @return the corresponding Command object.
     * @throws MedBotParserException if user input is not a recognised command or contains invalid information.
     */
    public static Command parseSchedulingCommand(String userInput) throws MedBotParserException {
        if (userInput.startsWith(COMMAND_ADD)) {
            return parseAddAppointmentCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_DELETE)) {
            return parseDeleteAppointmentCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_EDIT)) {
            return parseEditAppointmentCommand(userInput);
        }
        if (userInput.equals(COMMAND_LIST)) {
            return new ListAppointmentCommand();
        }

        throw new MedBotParserException(ERROR_WRONG_COMMAND);
    }

    /**
     * Parses user input and returns an AddAppointmentCommand with the specified appointment information.
     *
     * @param userInput String containing the full user input.
     * @return AddAppointmentCommand with the specified appointment information.
     * @throws MedBotParserException when there is missing appointment information given or when the information is
     *                               invalid
     */
    private static AddAppointmentCommand parseAddAppointmentCommand(String userInput) throws MedBotParserException {
        String[] attributeStrings = ParserUtils.getParameters(userInput);
        Appointment appointment = new Appointment();
        ParserUtils.updateMultipleAppointmentInformation(appointment, attributeStrings);
        return new AddAppointmentCommand(appointment);
    }

    /**
     * Parses user input and returns a DeleteAppointmentCommand with the specified appointment ID.
     *
     * @param userInput String containing the full user input.
     * @return DeleteAppointmentCommand with the specified appointment ID.
     * @throws MedBotParserException if the appointment ID is not specified or not a number
     */
    private static DeleteAppointmentCommand parseDeleteAppointmentCommand(String userInput)
            throws MedBotParserException {
        int appointmentId = ParserUtils.parseId(userInput.substring(6));
        return new DeleteAppointmentCommand(appointmentId);
    }

    /**
     * Parses user input and returns a EditAppointmentCommand with the specified appointment ID and appointment
     * information to be updated.
     *
     * @param userInput String containing the full user input.
     * @return EditAppointmentCommand with the specified appointment ID and appointment information.
     * @throws MedBotParserException if the appointment ID is not specified or not a number, or when the appointment
     *                               information given is invalid
     */
    private static EditAppointmentCommand parseEditAppointmentCommand(String userInput) throws MedBotParserException {
        int appointmentId = ParserUtils.parseId(userInput.substring(4));
        String[] attributeStrings = ParserUtils.getParameters(userInput);
        Appointment appointment = new Appointment();
        ParserUtils.updateMultipleAppointmentInformation(appointment, attributeStrings);
        return new EditAppointmentCommand(appointmentId, appointment);
    }

}
