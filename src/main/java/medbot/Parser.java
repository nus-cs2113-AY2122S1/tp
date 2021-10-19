package medbot;

import medbot.command.patientcommand.AddPatientCommand;
import medbot.command.Command;
import medbot.command.CommandType;
import medbot.command.patientcommand.DeletePatientCommand;
import medbot.command.patientcommand.EditPatientCommand;
import medbot.command.ExitCommand;
import medbot.command.patientcommand.FindPatientCommand;
import medbot.command.HelpCommand;
import medbot.command.SwitchCommand;
import medbot.command.patientcommand.ListPatientCommand;
import medbot.command.patientcommand.ViewPatientCommand;
import medbot.exceptions.MedBotParserException;
import medbot.person.Patient;
import medbot.person.Person;
import medbot.utilities.ViewType;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String ENDLINE = System.lineSeparator();

    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_SWITCH = "switch";
    private static final String COMMAND_FIND = "find";

    private static final String PARAMETER_NAME = "n/";
    private static final String PARAMETER_PHONE = "p/";
    private static final String PARAMETER_EMAIL = "e/";
    private static final String PARAMETER_IC = "i/";
    private static final String PARAMETER_ADDRESS = "a/";
    private static final int PARAMETER_BUFFER = 2;

    private static final String VIEW_TYPE_PATIENT_VIEW = "p";
    private static final String VIEW_TYPE_MEDICAL_STAFF_VIEW = "m";
    private static final String VIEW_TYPE_SCHEDULER_VIEW = "s";

    private static final String WRONG_COMMAND_ERROR = "Unable to parse command." + ENDLINE;
    private static final String WRONG_NUMBER_ERROR = "Unable to parse number." + ENDLINE;

    private static final String ERROR_NO_PARAMETER = "No parameters given" + ENDLINE;

    private static final String ERROR_PATIENT_ID_NOT_SPECIFIED = "Patient ID not specified." + ENDLINE;

    private static final String ERROR_INVALID_VIEW_TYPE = "Invalid view type code." + ENDLINE;

    private static final String ERROR_NAME_NOT_SPECIFIED = "Name not specified" + ENDLINE;

    private static final String ERROR_IC_NUMBER_NOT_SPECIFIED = "IC number not specified" + ENDLINE;
    private static final String ERROR_IC_NUMBER_INCORRECT_FORMAT = "Incorrect IC number format" + ENDLINE;

    private static final String ERROR_PHONE_NUMBER_NOT_SPECIFIED = "Phone number not specified" + ENDLINE;
    private static final String ERROR_PHONE_NUMBER_TOO_FEW_DIGITS = "Phone number has too few digits" + ENDLINE;
    private static final String ERROR_PHONE_NUMBER_TOO_MANY_DIGITS = "Phone number has too many digits" + ENDLINE;
    private static final String ERROR_PHONE_NUMBER_UNEXPECTED_CHARACTERS =
            "Phone number contains unexpected characters" + ENDLINE;

    private static final String ERROR_EMAIL_ADDRESS_NOT_SPECIFIED = "Email address not specified" + ENDLINE;
    private static final String ERROR_EMAIL_ADDRESS_WRONG_FORMAT = "Incorrect email address format" + ENDLINE;

    private static final String ERROR_ADDRESS_NOT_SPECIFIED = "Address not specified" + ENDLINE;

    private static final String REGEX_VERTICAL_LINE = "\\|";
    private static final String REGEX_INPUT_PARAMETER = " [a-zA-Z]{1,2}/";
    private static final String REGEX_EMAIL = "(([\\w&&[^_]][\\w-\\.]*[\\w&&[^_]])|[\\w&&[^_]])\\@([\\w]+\\.)+[\\w]+";
    private static final String REGEX_IC = "[STFGM][0-9]{7}[A-Z]";
    private static final String REGEX_PHONE_NUMBER = "[\\d]{8}";
    private static final String REGEX_PHONE_NUMBER_SEPARATOR = "[- _+()]";
    private static final String REGEX_CAPITALISE_POSITION = "(\\A|[ _-])[a-z]";

    private static final String VERTICAL_LINE = "|";
    private static final String SEPARATOR_SPACE = " ";

    private static final String EMPTY_STRING = "";

    /**
     * Checks the current view type that the parser is in and returns the relevant
     * parseCommand object based on the view type.
     *
     * @param userInput String containing the full user input.
     * @return the corresponding Command object.
     * @throws MedBotParserException if command is unrecognised.
     */
    public static Command parseCommand(String userInput, ViewType viewType) throws MedBotParserException {
        switch (viewType) {
        case PATIENT_INFO:
            return parsePatientInfoCommand(userInput);
        case MEDICAL_STAFF_INFO:
            return parseMedicalStaffCommand(userInput);
        case SCHEDULER:
            return parseSchedulingCommand(userInput);
        default:
            assert false;
            throw new MedBotParserException(WRONG_COMMAND_ERROR);
        }
    }


    /**
     * Parses the user input for patient information related commands and returns the relevant Command object.
     *
     * @param userInput String containing the full user input.
     * @return the corresponding Command object.
     * @throws MedBotParserException if command is unrecognised.
     */
    public static Command parsePatientInfoCommand(String userInput) throws MedBotParserException {
        userInput = preprocessInput(userInput);
        if (userInput.startsWith(COMMAND_ADD)) {
            return parseAddPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_DELETE)) {
            return parseDeletePatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_VIEW)) {
            return parseViewPatientCommand(userInput);
        }
        if (userInput.equals(COMMAND_LIST)) {
            return new ListPatientCommand();
        }
        if (userInput.equals(COMMAND_EXIT)) {
            return new ExitCommand();
        }
        if (userInput.startsWith(COMMAND_EDIT)) {
            return parseEditPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_FIND)) {
            return parseFindPatientCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_HELP)) {
            return parseHelpCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_SWITCH)) {
            return parseSwitchCommand(userInput);
        }

        throw new MedBotParserException(WRONG_COMMAND_ERROR);
    }

    //Update with relevant scheduling commands
    public static Command parseSchedulingCommand(String userInput) throws MedBotParserException {
        userInput = preprocessInput(userInput);
        if (userInput.equals(COMMAND_SWITCH)) {
            return new SwitchCommand();
        }
        throw new MedBotParserException(WRONG_COMMAND_ERROR);
    }

    //Update with relevant medical staff commands
    public static Command parseMedicalStaffCommand(String userInput) throws MedBotParserException {
        userInput = preprocessInput(userInput);
        if (userInput.equals(COMMAND_SWITCH)) {
            return new SwitchCommand();
        }
        throw new MedBotParserException(WRONG_COMMAND_ERROR);
    }

    //Parse Command Methods

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
            return new HelpCommand();
        }
        if (commandTypeString.equals(EMPTY_STRING)) {
            return new HelpCommand();
        }
        CommandType commandType = parseHelpCommandType(commandTypeString);
        return new HelpCommand(commandType);

    }

    private static CommandType parseHelpCommandType(String commandTypeString) throws MedBotParserException {
        switch (commandTypeString) {
        case COMMAND_ADD:
            return CommandType.ADD_PATIENT;
        case COMMAND_DELETE:
            return CommandType.DELETE_PATIENT;
        case COMMAND_EDIT:
            return CommandType.EDIT_PATIENT;
        case COMMAND_EXIT:
            return CommandType.EXIT;
        case COMMAND_HELP:
            return CommandType.HELP;
        case COMMAND_LIST:
            return CommandType.LIST_PATIENT;
        case COMMAND_SWITCH:
            return CommandType.SWITCH;
        case COMMAND_VIEW:
            return CommandType.VIEW_PATIENT;
        case COMMAND_FIND:
            return CommandType.FIND_PATIENT;
        default:
            throw new MedBotParserException(WRONG_COMMAND_ERROR);
        }
    }

    /**
     * Parses user input to pass relevant parameters into the ViewPatientCommand constructor.
     *
     * @param userInput String containing the full user input.
     * @return ViewPatientCommand object.
     * @throws MedBotParserException when patient id given is out of range, or no id is specified.
     */
    private static ViewPatientCommand parseViewPatientCommand(String userInput) throws MedBotParserException {
        int patientId;
        try {
            patientId = Integer.parseInt(userInput.substring(4).strip());
        } catch (NumberFormatException ne) {
            throw new MedBotParserException(WRONG_NUMBER_ERROR);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotParserException(ERROR_PATIENT_ID_NOT_SPECIFIED);
        }
        return new ViewPatientCommand(patientId);
    }

    /**
     * Parses user input to pass relevant parameters into the DeletePatientCommand constructor.
     *
     * @param userInput String containing the full user input.
     * @return DeletePatientCommand object.
     * @throws MedBotParserException when patient id given is out of range, or no id is specified.
     */
    private static DeletePatientCommand parseDeletePatientCommand(String userInput) throws MedBotParserException {
        int patientId;
        try {
            patientId = Integer.parseInt(userInput.substring(6).strip());
        } catch (NumberFormatException ne) {
            throw new MedBotParserException(WRONG_NUMBER_ERROR);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotParserException(ERROR_PATIENT_ID_NOT_SPECIFIED);
        }
        return new DeletePatientCommand(patientId);
    }

    /**
     * Processes user input for the editPatientInformation command.
     *
     * @param userInput String containing the full user input.
     * @return EditPatientCommand objects
     * @throws MedBotParserException when the parameters given cannot be parsed
     */
    private static EditPatientCommand parseEditPatientCommand(String userInput) throws MedBotParserException {
        int patientId;
        try {
            String patientIdString = userInput.substring(4).stripLeading();
            int endSpaceIndex = patientIdString.indexOf(' ');
            if (endSpaceIndex != -1) {
                patientIdString = patientIdString.substring(0, endSpaceIndex);
            }
            patientId = Integer.parseInt(patientIdString);
        } catch (NumberFormatException ne) {
            throw new MedBotParserException(WRONG_NUMBER_ERROR);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotParserException(ERROR_PATIENT_ID_NOT_SPECIFIED);
        }
        String[] parameters = getParameters(userInput);
        Patient patient = new Patient();
        patient.setNull();
        updateMultiplePersonalInformation(patient, parameters);
        return new EditPatientCommand(patientId, patient);
    }

    /**
     * Processes user input to create a new patient object with the corresponding information.
     *
     * @param userInput String containing the full user input.
     * @return AddPatientCommand object.
     * @throws MedBotParserException when no parameters are specified.
     */
    private static AddPatientCommand parseAddPatientCommand(String userInput) throws MedBotParserException {
        String[] parameters = getParameters(userInput);
        Patient patient = new Patient();
        updateMultiplePersonalInformation(patient, parameters);
        return new AddPatientCommand(patient);
    }

    private static FindPatientCommand parseFindPatientCommand(String userInput) throws MedBotParserException {
        String[] parameters = getParameters(userInput);
        return new FindPatientCommand(parameters);
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
        String newType = EMPTY_STRING;
        try {
            newType = userInput.substring(6).strip();
        } catch (IndexOutOfBoundsException ie) {
            return new SwitchCommand();
        }
        switch (newType) {
        case EMPTY_STRING:
            return new SwitchCommand();
        case VIEW_TYPE_PATIENT_VIEW:
            return new SwitchCommand(ViewType.PATIENT_INFO);
        case VIEW_TYPE_MEDICAL_STAFF_VIEW:
            return new SwitchCommand(ViewType.MEDICAL_STAFF_INFO);
        case VIEW_TYPE_SCHEDULER_VIEW:
            return new SwitchCommand(ViewType.SCHEDULER);
        default:
            throw new MedBotParserException(ERROR_INVALID_VIEW_TYPE);
        }
    }


    /**
     * Parses user input to the correct patient information format.
     *
     * @param userInput String containing the full user input.
     * @return The parameters list given by user.
     * @throws MedBotParserException when no parameters are specified.
     */
    private static String[] getParameters(String userInput) throws MedBotParserException {
        String processedInput = preprocessMultiAttributeInput(userInput);
        String[] words = processedInput.split(REGEX_VERTICAL_LINE);
        if (words.length == 1) {
            throw new MedBotParserException(ERROR_NO_PARAMETER);
        }
        assert words.length > 1;
        String[] parameters = Arrays.copyOfRange(words, 1, words.length);
        assert parameters.length >= 1;
        return parameters;
    }

    /**
     * Preprocesses user input to remove invalid substring that can not be parsed.
     *
     * @param userInput The initial user input.
     * @return user input without leading white space and vertical lines present.
     */
    private static String preprocessInput(String userInput) {
        return userInput.stripLeading().replace(VERTICAL_LINE, EMPTY_STRING);
    }

    /**
     * Parses attributeStrings array and modifies all the corresponding attribute in person.
     *
     * @param person           Person whose personal information will be updated
     * @param attributeStrings String Array containing Strings of an attribute specifier and the corresponding
     *                         personal information
     * @throws MedBotParserException if the attributeString contains missing/invalid information
     */
    public static void updateMultiplePersonalInformation(Person person, String[] attributeStrings)
            throws MedBotParserException {
        for (String attributeString : attributeStrings) {
            updatePersonalInformation(person, attributeString);
        }
    }

    /**
     * Parses attributeString and modifies the corresponding attribute in person.
     *
     * @param person          Person whose personal information will be updated
     * @param attributeString String containing an attribute specifier and the corresponding personal information
     * @throws MedBotParserException if the attributeString contains missing/invalid information
     */
    public static void updatePersonalInformation(Person person, String attributeString) throws MedBotParserException {
        if (attributeString.startsWith(PARAMETER_NAME)) {
            String name = parseName(attributeString.substring(PARAMETER_BUFFER));
            person.setName(name);
            return;
        }
        if (attributeString.startsWith(PARAMETER_PHONE)) {
            String phoneNumber = parsePhoneNumber(attributeString.substring(PARAMETER_BUFFER));
            person.setPhoneNumber(phoneNumber);
            return;
        }
        if (attributeString.startsWith(PARAMETER_EMAIL)) {
            String email = parseEmailAddress(attributeString.substring(PARAMETER_BUFFER));
            person.setEmailAddress(email);
            return;
        }
        if (attributeString.startsWith(PARAMETER_IC)) {
            String icNumber = parseIcNumber(attributeString.substring(PARAMETER_BUFFER));
            person.setIcNumber(icNumber);
        }
        if (attributeString.startsWith(PARAMETER_ADDRESS)) {
            String address = parseResidentialAddress(attributeString.substring(PARAMETER_BUFFER));
            person.setResidentialAddress(address);
        }
    }

    /**
     * Returns a String containing the names specified in attributeString, with each name capitalised.
     *
     * @param attributeString String containing the name to be parsed
     * @return String containing the name specified in attributeString
     * @throws MedBotParserException if no name is given
     */
    private static String parseName(String attributeString) throws MedBotParserException {
        try {
            String name = attributeString.strip();
            if (name.equals(EMPTY_STRING)) {
                throw new MedBotParserException(ERROR_NAME_NOT_SPECIFIED);
            }
            return capitaliseEachWord(name);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotParserException(ERROR_NAME_NOT_SPECIFIED);
        }
    }

    /**
     * Returns a String containing the IC number specified in attributeString.
     *
     * <p>Checks if the resultant String is of the right IC number format
     *
     * @param attributeString String containing the IC number to be parsed
     * @return String containing the IC number specified in attributeString
     * @throws MedBotParserException if IC number is not specified, or is in the wrong format
     */
    private static String parseIcNumber(String attributeString) throws MedBotParserException {
        try {
            String icString = attributeString.toUpperCase().strip();
            if (icString.equals(EMPTY_STRING)) {
                throw new MedBotParserException(ERROR_IC_NUMBER_NOT_SPECIFIED);
            }
            if (!icString.matches(REGEX_IC)) {
                throw new MedBotParserException(ERROR_IC_NUMBER_INCORRECT_FORMAT);
            }
            assert icString.length() == 9;
            return icString;
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotParserException(ERROR_IC_NUMBER_NOT_SPECIFIED);
        }
    }

    /**
     * Returns a String containing the phone number specified in attributeString.
     *
     * <p>Removes special characters "- _+()" and checks if the length of the resultant String is 8
     *
     * @param attributeString String containing the phone number to be parsed
     * @return String containing the phone number specified in attributeString
     * @throws MedBotParserException if the phone number is not specified,
     *                               has too many/few digits or contains unexpected characters
     */
    private static String parsePhoneNumber(String attributeString) throws MedBotParserException {
        try {
            String numberString = attributeString.replaceAll(REGEX_PHONE_NUMBER_SEPARATOR, EMPTY_STRING).strip();
            if (numberString.equals(EMPTY_STRING)) {
                throw new MedBotParserException(ERROR_PHONE_NUMBER_NOT_SPECIFIED);
            }
            if (numberString.length() > 8) {
                throw new MedBotParserException(ERROR_PHONE_NUMBER_TOO_MANY_DIGITS);
            }
            if (numberString.length() < 8) {
                throw new MedBotParserException(ERROR_PHONE_NUMBER_TOO_FEW_DIGITS);
            }
            if (!numberString.matches(REGEX_PHONE_NUMBER)) {
                throw new MedBotParserException(ERROR_PHONE_NUMBER_UNEXPECTED_CHARACTERS);
            }
            assert numberString.length() == 8;
            return numberString;
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotParserException(ERROR_PHONE_NUMBER_NOT_SPECIFIED);
        }
    }

    /**
     * Returns a String containing the email address specified in attributeString.
     *
     * <p>Checks if the resultant String is of the right email format
     *
     * @param attributeString String containing the email address to be parsed
     * @return String containing the email address specified in attributeString
     * @throws MedBotParserException if the email address is not specified or is in the wrong format
     */
    private static String parseEmailAddress(String attributeString) throws MedBotParserException {
        try {
            String emailString = attributeString.strip();
            if (emailString.equals(EMPTY_STRING)) {
                throw new MedBotParserException(ERROR_EMAIL_ADDRESS_NOT_SPECIFIED);
            }
            if (!emailString.matches(REGEX_EMAIL)) {
                throw new MedBotParserException(ERROR_EMAIL_ADDRESS_WRONG_FORMAT);
            }
            return emailString;
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotParserException(ERROR_EMAIL_ADDRESS_NOT_SPECIFIED);
        }
    }

    /**
     * Returns the String containing the address specified in attributeString, with each word capitalised.
     *
     * <p>Capitalises each word in the address
     *
     * @param attributeString String containing the address to be parsed
     * @return String containing the address specified in attributeString
     * @throws MedBotParserException if address is not specified
     */
    private static String parseResidentialAddress(String attributeString) throws MedBotParserException {
        try {
            String addressString = attributeString.strip();
            if (addressString.equals(EMPTY_STRING)) {
                throw new MedBotParserException(ERROR_ADDRESS_NOT_SPECIFIED);
            }
            return capitaliseEachWord(addressString);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotParserException(ERROR_ADDRESS_NOT_SPECIFIED);
        }
    }

    /**
     * Sets the first letter of each word of each word to uppercase and sets all others to lowercase.
     *
     * <p>A letter is considered the first letter of a word if it is the first letter of the input String or
     * is immediately after any of the characters " _-".
     *
     * @param input String which will be capitalised
     * @return String with each word capitalised
     */
    private static String capitaliseEachWord(String input) {
        input = input.toLowerCase();
        Function<MatchResult, String> multiAttributeReplacementFunction = x -> {
            String match = x.group();
            if (match.length() == 1) {
                //if substring is the first character of the string
                return match.toUpperCase();
            } else {
                //if substring consists of one of the characters " _-" followed by a letter
                return match.charAt(0) + match.substring(1).toUpperCase();
            }
        };
        Pattern pattern = Pattern.compile(REGEX_CAPITALISE_POSITION);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(multiAttributeReplacementFunction);
    }

    /**
     * Places a "|" separator before each attribute specifier in input and returns the resultant string.
     *
     * <p>Attribute specifiers are in the following formats "a/" or "ab/" where a and b can be any uppercase
     * or lowercase alphabet.
     *
     * @param input userInput String containing attribute specifiers
     * @return String containing added separator characters
     */
    private static String preprocessMultiAttributeInput(String input) {
        //replacement function to add a "|" character before an attribute specifier
        Function<MatchResult, String> replacementFunction = x ->
                SEPARATOR_SPACE + VERTICAL_LINE + x.group().substring(1);
        Pattern pattern = Pattern.compile(REGEX_INPUT_PARAMETER);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(replacementFunction);
    }

}
