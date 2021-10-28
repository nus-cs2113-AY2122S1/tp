package medbot.parser;

import medbot.command.Command;
import medbot.command.personcommand.patientcommand.AddPatientCommand;
import medbot.command.personcommand.patientcommand.ArchivePatientCommand;
import medbot.command.personcommand.patientcommand.DeletePatientCommand;
import medbot.command.personcommand.patientcommand.EditPatientCommand;
import medbot.command.personcommand.patientcommand.FindPatientCommand;
import medbot.command.personcommand.patientcommand.ListPatientCommand;
import medbot.command.personcommand.patientcommand.UnarchivePatientCommand;
import medbot.command.personcommand.patientcommand.ViewPatientCommand;
import medbot.exceptions.MedBotParserException;
import medbot.person.Patient;


public abstract class PatientCommandParser {
    private static final String END_LINE = System.lineSeparator();

    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_ARCHIVE = "archive";
    private static final String COMMAND_UNARCHIVE = "unarchive";

    private static final String ERROR_WRONG_COMMAND = "Unable to parse command." + END_LINE;

    /**
     * Parses the user input and returns the corresponding command when the view type is PATIENT_INFO.
     *
     * @param userInput String containing the full user input.
     * @return the corresponding Command object.
     * @throws MedBotParserException if user input is not a recognised command or contains invalid information.
     */
    public static Command parsePatientCommand(String userInput) throws MedBotParserException {
        if (userInput.startsWith(COMMAND_ADD)) {
            return parseAddPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_DELETE)) {
            return parseDeletePatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_VIEW)) {
            return parseViewPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_LIST)) {
            return parseListPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_EDIT)) {
            return parseEditPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_FIND)) {
            return parseFindPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_ARCHIVE)) {
            return parseArchivePatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_UNARCHIVE)) {
            return parseUnarchivePatientCommand(userInput);
        }

        throw new MedBotParserException(ERROR_WRONG_COMMAND);
    }

    /**
     * Parses user input and returns ViewPatientCommand with the specified patient ID.
     *
     * @param userInput String containing the full user input.
     * @return ViewPatientCommand object.
     * @throws MedBotParserException when patient id is not specified or not a number.
     */
    private static ViewPatientCommand parseViewPatientCommand(String userInput) throws MedBotParserException {
        int personId = ParserUtils.parseId(userInput.substring(4));
        return new ViewPatientCommand(personId);
    }

    /**
     * Parses user input and returns DeletePatientCommand with the specified patient ID.
     *
     * @param userInput String containing the full user input.
     * @return DeletePatientCommand object.
     * @throws MedBotParserException when patient id given is not specified or not a number.
     */
    private static DeletePatientCommand parseDeletePatientCommand(String userInput) throws MedBotParserException {
        int personId = ParserUtils.parseId(userInput.substring(6));
        return new DeletePatientCommand(personId);
    }

    /**
     * Parses user input and returns EditPatientCommand with the specified patient ID and parameters.
     *
     * @param userInput String containing the full user input.
     * @return EditPatientCommand object.
     * @throws MedBotParserException when patient id given is not specified or not a number, or when
     *                               the parameters given cannot be parsed.
     */
    private static EditPatientCommand parseEditPatientCommand(String userInput) throws MedBotParserException {
        int patientId = ParserUtils.parseId(userInput.substring(4));
        String[] parameters = ParserUtils.getParameters(userInput);
        Patient patient = new Patient();
        patient.setNull();
        ParserUtils.updateMultiplePersonalInformation(patient, parameters);
        return new EditPatientCommand(patientId, patient);
    }

    /**
     * Parses user input and returns ListPatientCommand with the parameter to retrieve archived or unarchived patients.
     *
     * @param userInput String containing the full user input.
     * @return ListPatientCommand object.
     */
    private static ListPatientCommand parseListPatientCommand(String userInput) throws MedBotParserException {
        boolean getArchivedPersons = ParserUtils.parseListParameter(userInput.substring(4));
        return new ListPatientCommand(getArchivedPersons);
    }

    /**
     * Parses user input and returns AddPatientCommand with the specified parameters.
     *
     * @param userInput String containing the full user input.
     * @return AddPatientCommand object.
     * @throws MedBotParserException when no parameters are specified, or when the parameters given cannot be parsed.
     */
    private static AddPatientCommand parseAddPatientCommand(String userInput) throws MedBotParserException {
        String[] parameters = ParserUtils.getParameters(userInput);
        Patient patient = new Patient();
        ParserUtils.updateMultiplePersonalInformation(patient, parameters);
        return new AddPatientCommand(patient);
    }

    /**
     * Parses user input and returns FindUserCommand with the specified parameters.
     *
     * @param userInput String containing the full user input.
     * @return FindUserCommand object with the specified parameters
     * @throws MedBotParserException when no parameters are specified
     */
    private static FindPatientCommand parseFindPatientCommand(String userInput) throws MedBotParserException {
        String[] parameters = ParserUtils.getParameters(userInput);
        return new FindPatientCommand(parameters);
    }

    /**
     * Parses user input and returns ArchiveUserCommand with the specified patient ID.
     *
     * @param userInput String containing the full user input.
     * @return ArchiveUserCommand object with the specified parameters
     * @throws MedBotParserException when patient id given is not specified or not a number.
     */
    private static ArchivePatientCommand parseArchivePatientCommand(String userInput) throws MedBotParserException {
        int personId = ParserUtils.parseId(userInput.substring(7));
        return new ArchivePatientCommand(personId);
    }

    /**
     * Parses user input and returns UnarchivePatientCommand with the specified patient ID.
     *
     * @param userInput String containing the full user input.
     * @return UnarchivePatientCommand object.
     * @throws MedBotParserException when patient id given is not specified or not a number.
     */
    private static UnarchivePatientCommand parseUnarchivePatientCommand(String userInput) throws MedBotParserException {
        int personId = ParserUtils.parseId(userInput.substring(9));
        return new UnarchivePatientCommand(personId);
    }
}
