package medbot;

import medbot.command.AddPatientCommand;
import medbot.command.Command;
import medbot.command.DeletePatientCommand;
import medbot.command.ExitCommand;
import medbot.command.ListPatientCommand;
import medbot.command.ViewPatientCommand;
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

        throw new MedBotException("Unable to parse command.");
    }

    private static ViewPatientCommand parseViewPatientCommand(String userInput) throws MedBotException {
        int patientId = 0;
        try {
            patientId = Integer.parseInt(userInput.substring(4).strip());
        } catch (NumberFormatException ne) {
            throw new MedBotException("Unable to parse number.");
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException("Patient ID not specified.");
        }
        return new ViewPatientCommand(patientId);
    }


    private static DeletePatientCommand parseDeletePatientCommand(String userInput) throws MedBotException {
        int patientId = 0;
        try {
            patientId = Integer.parseInt(userInput.substring(6).strip());
        } catch (NumberFormatException ne) {
            throw new MedBotException("Unable to parse number.");
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException("Patient ID not specified.");
        }
        return new DeletePatientCommand(patientId);
    }

    private static String preprocessInput(String userInput) {
        return userInput.stripLeading().replace("|", "");
    }

    private static AddPatientCommand parseAddPatientCommand(String userInput) throws MedBotException {
        String processedInput = preprocessMultiAttributeInput(userInput);
        String[] parameters = processedInput.split("\\|");
        if (parameters.length == 1) {
            throw new MedBotException("No parameters given");
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
     * @param person Person whose personal information will be updated
     * @param attributeString String containing an attribute specifier and the corresponding personal information
     * @throws MedBotException if the attributeString contains missing/invalid information
     */
    private static void updatePersonalInformation(Person person, String attributeString) throws MedBotException {
        if (attributeString.startsWith("n/")) {
            String name = parseName(attributeString);
            person.setName(name);
            return;
        }
        if (attributeString.startsWith("p/")) {
            String phoneNumber = parsePhoneNumber(attributeString);
            person.setPhoneNumber(phoneNumber);
            return;
        }
        if (attributeString.startsWith("e/")) {
            String email = parseEmailAddress(attributeString);
            person.setEmailAddress(email);
            return;
        }
        if (attributeString.startsWith("i/")) {
            String icNumber = parseIcNumber(attributeString);
            person.setIcNumber(icNumber);
        }
        if (attributeString.startsWith("a/")) {
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
            if (name.equals("")) {
                throw new MedBotException("Name not specified");
            }
            return capitaliseEachWord(name);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException("Name not specified");
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
        String icFormat = "[STFGM][0-9]{7}[A-Z]";
        try {
            String icString = attributeString.substring(2).toUpperCase().strip();
            if (icString.equals("")) {
                throw new MedBotException("IC number not specified");
            }
            if (!icString.matches(icFormat)) {
                throw new MedBotException("Incorrect IC number format");
            }
            return icString;
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException("IC number not specified");
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
     *      has too many/few digits or contains unexpected characters
     */
    private static String parsePhoneNumber(String attributeString) throws MedBotException {
        String phoneNumberFormat = "[\\d]{8}";
        try {
            String numberString = attributeString.substring(2).replaceAll("[- _+()]", "").strip();
            if (numberString.equals("")) {
                throw new MedBotException("Phone number not specified");
            }
            if (numberString.length() > 8) {
                throw new MedBotException("Phone number has too many digits");
            }
            if (numberString.length() < 8) {
                throw new MedBotException("Phone number has too few digits");
            }
            if (!numberString.matches(phoneNumberFormat)) {
                throw new MedBotException("Phone number contains unexpected characters");
            }
            return numberString;
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException("Phone number not specified");
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
        String emailFormat = "(([\\w][\\w-\\.]*[\\w])|[\\w])\\@([\\w]+\\.)+[\\w]+";
        try {
            String emailString = attributeString.substring(2).strip();
            if (emailString.equals("")) {
                throw new MedBotException("Email address not specified");
            }
            if (!emailString.matches(emailFormat)) {
                throw new MedBotException("Incorrect email address format");
            }
            return emailString;
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException("Email address not specified");
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
            if (addressString.equals("")) {
                throw new MedBotException("Address not specified");
            }
            return capitaliseEachWord(addressString);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException("Address not specified");
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
        Pattern pattern = Pattern.compile("(\\A|[ _-])[a-z]");
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
        Pattern pattern = Pattern.compile(" [a-zA-Z]{1,2}/");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(replacementFunction);
    }
}
