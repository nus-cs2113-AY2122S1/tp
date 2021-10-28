package medbot.parser;

import medbot.Appointment;
import medbot.command.Command;
import medbot.command.appointmentcommand.AddAppointmentCommand;
import medbot.command.appointmentcommand.DeleteAppointmentCommand;
import medbot.command.appointmentcommand.EditAppointmentCommand;
import medbot.command.appointmentcommand.FindAppointmentCommand;
import medbot.command.appointmentcommand.ListAppointmentCommand;
import medbot.command.appointmentcommand.ViewAppointmentCommand;
import medbot.exceptions.MedBotParserException;
import medbot.person.PersonType;
import medbot.utilities.FilterType;

public abstract class SchedulerCommandParser {
    private static final String END_LINE = System.lineSeparator();

    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_FIND = "find";

    private static final String ERROR_WRONG_COMMAND = "Unable to parse command." + END_LINE;

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
        if (userInput.startsWith(COMMAND_VIEW)) {
            return parseViewAppointmentCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_FIND)) {
            return parseFindAppointmentCommand(userInput);
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

    /**
     * Parses user input and returns a ViewAppointmentCommand with the specified appointment ID.
     *
     * @param userInput String containing the full user input.
     * @return ViewAppointmentCommand with the specified appointment ID.
     * @throws MedBotParserException if the appointment ID is not specified or not a number.
     */
    private static Command parseViewAppointmentCommand(String userInput) throws MedBotParserException {
        int appointmentId = ParserUtils.parseId(userInput.substring(4));
        return new ViewAppointmentCommand(appointmentId);
    }

    /**
     * Parses user input and returns a FindAppointmentCommand with the specified information to find.
     *
     * @param userInput String containing the full user input.
     * @return FindAppointmentCommand with the specified person ID, person type, the filter type and the dateTime code.
     * @throws MedBotParserException if the input cannot be parsed.
     */
    private static Command parseFindAppointmentCommand(String userInput) throws MedBotParserException {
        String[] attributeStrings = ParserUtils.getParametersWithoutSpecifiers(userInput).toArray(new String[0]);
        String[] attributeSpecifiers = ParserUtils.getSpecifiers(userInput);

        assert (attributeSpecifiers.length == 1 || attributeSpecifiers.length == 2);
        assert (attributeStrings.length == 1 || attributeStrings.length == 2);

        PersonType personType = ParserUtils.parsePersonType(attributeSpecifiers[0]);
        int personId = ParserUtils.parseId(attributeStrings[0]);
        FilterType filterType = FilterType.NONE;
        int dateTimeCode = -1;
        if (attributeSpecifiers.length == 2) {
            filterType = ParserUtils.parseFilterType(attributeSpecifiers[1]);
        }
        if (attributeStrings.length == 2) {
            dateTimeCode = ParserUtils.parseDateTime(attributeStrings[1]);
        }

        return new FindAppointmentCommand(personId, personType, filterType, dateTimeCode);
    }

}
