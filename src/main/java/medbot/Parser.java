package medbot;

import medbot.command.AddPatientCommand;
import medbot.command.Command;
import medbot.command.DeletePatientCommand;
import medbot.command.ExitCommand;
import medbot.command.ListPatientCommand;
import medbot.command.ViewPatientCommand;
import medbot.command.EditPatientCommand;

import medbot.person.Patient;
import medbot.person.Person;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_VIEW = "view";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_EXIT = "exit";

    private static final String PARAMETER_NAME = "n/";
    private static final String PARAMETER_PHONE = "p/";
    private static final String PARAMETER_EMAIL = "e/";
    private static final String PARAMETER_IC = "i/";
    private static final String PARAMETER_ADDRESS = "a/";

    private static final String WRONG_COMMAND_ERROR = "Unable to parse command.";
    private static final String WRONG_NUMBER_ERROR = "Unable to parse number.";

    private static final String ERROR_NO_PARAMETER = "No parameters given";

    private static final String ERROR_PATIENT_ID_NOT_SPECIFIED = "Patient ID not specified.";

    private static final String ERROR_NAME_NOT_SPECIFIED = "Name not specified";

    private static final String ERROR_IC_NUMBER_NOT_SPECIFIED = "IC number not specified";
    private static final String ERROR_IC_NUMBER_INCORRECT_FORMAT = "Incorrect IC number format";

    private static final String ERROR_PHONE_NUMBER_NOT_SPECIFIED = "Phone number not specified";
    private static final String ERROR_PHONE_NUMBER_TOO_FEW_DIGITS = "Phone number has too few digits";
    private static final String ERROR_PHONE_NUMBER_TOO_MANY_DIGITS = "Phone number has too many digits";
    private static final String ERROR_PHONE_NUMBER_UNEXPECTED_CHARACTERS = "Phone number contains unexpected characters";

    private static final String ERROR_EMAIL_ADDRESS_NOT_SPECIFIED = "Email address not specified";
    private static final String ERROR_EMAIL_ADDRESS_WRONG_FORMAT = "Incorrect email address format";

    private static final String ERROR_ADDRESS_NOT_SPECIFIED = "Address not specified";

    private static final String REGEX_VERTICAL_LINE = "\\|";
    private static final String REGEX_INPUT_PARAMETER = " [a-zA-Z]{1,2}/";
    private static final String REGEX_EMAIL = "(([\\w][\\w-\\.]*[\\w])|[\\w])\\@([\\w]+\\.)+[\\w]+";
    private static final String REGEX_IC = "[STFGM][0-9]{7}[A-Z]";
    private static final String REGEX_PHONE_NUMBER_SEPARATOR = "[- _+()]";
    private static final String REGEX_CAPITALISE_POSITION = "(\\A|[ _-])[a-z]";

    private static final String VERTICAL_LINE = "|";

    private static final String EMPTY_STRING = "";

    public static Command parseCommand(String userInput) throws MedBotException {
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
        if (userInput.startsWith(COMMAND_LIST)) {
            return new ListPatientCommand();
        }
        if (userInput.startsWith(COMMAND_EXIT)) {
            return new ExitCommand();
        }
        if (userInput.startsWith(COMMAND_EDIT)) {
            return parseEditPatientCommand(userInput);
        }


        throw new MedBotException(WRONG_COMMAND_ERROR);
    }

    private static ViewPatientCommand parseViewPatientCommand(String userInput) throws MedBotException {
        int patientId = 0;
        try {
            patientId = Integer.parseInt(userInput.substring(4).strip());
        } catch (NumberFormatException ne) {
            throw new MedBotException(WRONG_NUMBER_ERROR);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException(ERROR_PATIENT_ID_NOT_SPECIFIED);
        }
        return new ViewPatientCommand(patientId);
    }


    private static DeletePatientCommand parseDeletePatientCommand(String userInput) throws MedBotException {
        int patientId = 0;
        try {
            patientId = Integer.parseInt(userInput.substring(6).strip());
        } catch (NumberFormatException ne) {
            throw new MedBotException(WRONG_NUMBER_ERROR);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException(ERROR_PATIENT_ID_NOT_SPECIFIED);
        }
        return new DeletePatientCommand(patientId);
    }

    /**
     * Parses the user input for the editPatientInformation command.
     *
     * @param userInput the preprocessed user input
     * @return the Edit Command
     * @throws MedBotException when the parameters given cannot be parsed
     */
    private static EditPatientCommand parseEditPatientCommand(String userInput) throws MedBotException {
        int patientId = 0;
        try {
            patientId = Integer.parseInt(userInput.substring(4, 6).strip());
        } catch (NumberFormatException ne) {
            throw new MedBotException(WRONG_NUMBER_ERROR);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException(ERROR_PATIENT_ID_NOT_SPECIFIED);
        }
        String processedInput = preprocessMultiAttributeInput(userInput);
        String[] parameters = processedInput.split(REGEX_VERTICAL_LINE);
        if (parameters.length == 1) {
            throw new MedBotException(ERROR_NO_PARAMETER);
        }
        Patient patient = new Patient();
        preprocessEditPersonInformation(patient);
        for (int i = 1; i < parameters.length; i++) {
            updatePersonalInformation(patient, parameters[i]);
        }
        return new EditPatientCommand(patientId, patient);
    }

    /**
     * Sets all the information of the new patient data to null to avoid overwrite old information
     * with blank space.
     *
     * @param person Person who all information will be set to null
     */
    private static void preprocessEditPersonInformation(Person person) {
        person.setName(null);
        person.setPhoneNumber(null);
        person.setEmailAddress(null);
        person.setIcNumber(null);
        person.setResidentialAddress(null);
    }

    private static String preprocessInput(String userInput) {
        return userInput.stripLeading().replace(VERTICAL_LINE, EMPTY_STRING);
    }

    private static AddPatientCommand parseAddPatientCommand(String userInput) throws MedBotException {
        String processedInput = preprocessMultiAttributeInput(userInput);
        String[] parameters = processedInput.split(REGEX_VERTICAL_LINE);
        if (parameters.length == 1) {
            throw new MedBotException(ERROR_NO_PARAMETER);
        }
        Patient patient = new Patient();
        for (int i = 1; i < parameters.length; i++) {
            updatePersonalInformation(patient, parameters[i]);
        }
        return new AddPatientCommand(patient);
    }


    /**
     * Parses attributeString and modifies the corresponding attribute in person.
     *
     * @param person          Person whose personal information will be updated
     * @param attributeString String containing an attribute specifier and the corresponding personal information
     * @throws MedBotException if the attributeString contains missing/invalid information
     */
    private static void updatePersonalInformation(Person person, String attributeString) throws MedBotException {
        if (attributeString.startsWith(PARAMETER_NAME)) {
            String name = parseName(attributeString);
            person.setName(name);
            return;
        }
        if (attributeString.startsWith(PARAMETER_PHONE)) {
            String phoneNumber = parsePhoneNumber(attributeString);
            person.setPhoneNumber(phoneNumber);
            return;
        }
        if (attributeString.startsWith(PARAMETER_EMAIL)) {
            String email = parseEmailAddress(attributeString);
            person.setEmailAddress(email);
            return;
        }
        if (attributeString.startsWith(PARAMETER_IC)) {
            String icNumber = parseIcNumber(attributeString);
            person.setIcNumber(icNumber);
        }
        if (attributeString.startsWith(PARAMETER_ADDRESS)) {
            String address = parseResidentialAddress(attributeString);
            person.setResidentialAddress(address);
        }
    }

    /**
     * Returns a String containing the names specified in attributeString, with each name capitalised.
     *
     * <p>Removes the attribute specifier "n/" from the start of the String
     *
     * @param attributeString String containing the name to be parsed
     * @return String containing the name specified in attributeString
     * @throws MedBotException if no name is given
     */
    private static String parseName(String attributeString) throws MedBotException {
        try {
            //removes "n/" attribute specifier
            String name = attributeString.substring(2).strip();
            if (name.equals(EMPTY_STRING)) {
                throw new MedBotException(ERROR_NAME_NOT_SPECIFIED);
            }
            return capitaliseEachWord(name);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException(ERROR_NAME_NOT_SPECIFIED);
        }
    }

    /**
     * Returns a String containing the IC number specified in attributeString.
     *
     * <p>Removes the attribute specifier "i/" and checks if the resultant String is of the right
     * IC number format
     *
     * @param attributeString String containing the IC number to be parsed
     * @return String containing the IC number specified in attributeString
     * @throws MedBotException if IC number is not specified, or is in the wrong format
     */
    private static String parseIcNumber(String attributeString) throws MedBotException {
        try {
            String icString = attributeString.substring(2).toUpperCase().strip();
            if (icString.equals(EMPTY_STRING)) {
                throw new MedBotException(ERROR_IC_NUMBER_NOT_SPECIFIED);
            }
            if (!icString.matches(REGEX_IC)) {
                throw new MedBotException(ERROR_IC_NUMBER_INCORRECT_FORMAT);
            }
            return icString;
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException(ERROR_IC_NUMBER_NOT_SPECIFIED);
        }
    }

    /**
     * Returns a String containing the phone number specified in attributeString.
     *
     * <p>Removes the attribute specifier "n/", removes special characters "- _+()" and checks if the length of
     * the resultant String is 8
     *
     * @param attributeString String containing the phone number to be parsed
     * @return String containing the phone number specified in attributeString
     * @throws MedBotException if the phone number is not specified,
     *                         has too many/few digits or contains unexpected characters
     */
    private static String parsePhoneNumber(String attributeString) throws MedBotException {
        String phoneNumberFormat = "[\\d]{8}";
        try {
            String numberString = attributeString.substring(2).replaceAll(REGEX_PHONE_NUMBER_SEPARATOR, EMPTY_STRING).strip();
            if (numberString.equals("")) {
                throw new MedBotException(ERROR_PHONE_NUMBER_NOT_SPECIFIED);
            }
            if (numberString.length() > 8) {
                throw new MedBotException(ERROR_PHONE_NUMBER_TOO_MANY_DIGITS);
            }
            if (numberString.length() < 8) {
                throw new MedBotException(ERROR_PHONE_NUMBER_TOO_FEW_DIGITS);
            }
            if (!numberString.matches(phoneNumberFormat)) {
                throw new MedBotException(ERROR_PHONE_NUMBER_UNEXPECTED_CHARACTERS);
            }
            return numberString;
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException(ERROR_PHONE_NUMBER_NOT_SPECIFIED);
        }
    }

    /**
     * Returns a String containing the email address specified in attributeString.
     *
     * <p>Removes the attribute specifier "e/" and checks if the resultant String is of the right email format
     *
     * @param attributeString String containing the email address to be parsed
     * @return String containing the email address specified in attributeString
     * @throws MedBotException if the email address is not specified or is in the wrong format
     */
    private static String parseEmailAddress(String attributeString) throws MedBotException {
        try {
            String emailString = attributeString.substring(2).strip();
            if (emailString.equals(EMPTY_STRING)) {
                throw new MedBotException(ERROR_EMAIL_ADDRESS_NOT_SPECIFIED);
            }
            if (!emailString.matches(REGEX_EMAIL)) {
                throw new MedBotException(ERROR_EMAIL_ADDRESS_WRONG_FORMAT);
            }
            return emailString;
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException(ERROR_EMAIL_ADDRESS_NOT_SPECIFIED);
        }
    }

    /**
     * Returns the String containing the address specified in attributeString, with each word capitalised.
     *
     * <p>Removes the attribute specifier "a/" from the start of the String
     *
     * @param attributeString String containing the address to be parsed
     * @return String containing the address specified in attributeString
     * @throws MedBotException if address is not specified
     */
    private static String parseResidentialAddress(String attributeString) throws MedBotException {
        try {
            String addressString = attributeString.substring(2).strip();
            if (addressString.equals(EMPTY_STRING)) {
                throw new MedBotException(ERROR_ADDRESS_NOT_SPECIFIED);
            }
            return capitaliseEachWord(addressString);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException(ERROR_ADDRESS_NOT_SPECIFIED);
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
        Function<MatchResult, String> multiAttributeReplacementFunction = x -> {
            String match = x.group();
            if (match.length() == 1) {
                return match.toUpperCase();
            } else {
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
        Function<MatchResult, String> replacementFunction = x -> " |" + x.group().substring(1);
        Pattern pattern = Pattern.compile(REGEX_INPUT_PARAMETER);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(replacementFunction);
    }
}
