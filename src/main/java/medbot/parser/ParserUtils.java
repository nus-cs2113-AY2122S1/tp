package medbot.parser;

import medbot.Appointment;
import medbot.exceptions.MedBotParserException;
import medbot.person.Person;
import medbot.person.PersonType;
import medbot.utilities.FilterType;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;


public abstract class ParserUtils {
    private static final String PARAMETER_HIDE = "-h";
    private static final String PARAMETER_NAME = "n/";
    private static final String PARAMETER_PHONE = "p/";
    private static final String PARAMETER_PATIENT = "p/";
    private static final String PARAMETER_STAFF = "s/";
    private static final String PARAMETER_EMAIL = "e/";
    private static final String PARAMETER_IC = "i/";
    private static final String PARAMETER_ADDRESS = "a/";
    private static final String PARAMETER_BEFORE = "b/";
    private static final String PARAMETER_AFTER = "a/";
    private static final int PARAMETER_BUFFER = 2;
    private static final String PARAMETER_APPOINTMENT_PATIENT_ID = "p/";
    private static final String PARAMETER_APPOINTMENT_MEDICAL_STAFF_ID = "s/";
    private static final String PARAMETER_APPOINTMENT_DATE_TIME = "d/";
    private static final String ERROR_INVALID_PARAM_SPECIFIER = "\"%s\" is not a valid attribute specifier";
    private static final String ERROR_NO_PARAMETER = "No parameters given.";
    private static final String ERROR_TOO_MANY_SPECIFIERS = "Too many attribute specifiers.";
    private static final String ERROR_ID_NOT_SPECIFIED = "ID not specified or not a number.";
    private static final String ERROR_PERSON_TYPE_INVALID = "Person type specified is not valid.";
    private static final String ERROR_FILTER_TYPE_INVALID = "Filter type specified is not valid.";
    private static final String ERROR_PARAMETER_TYPE_INVALID = "Parameter type specified is not valid.";
    private static final String ERROR_NAME_NOT_SPECIFIED = "Name not specified.";
    private static final String ERROR_IC_NUMBER_NOT_SPECIFIED = "IC number not specified.";
    private static final String ERROR_IC_NUMBER_INCORRECT_FORMAT = "Incorrect IC number format.";
    private static final String ERROR_PHONE_NUMBER_NOT_SPECIFIED = "Phone number not specified.";
    private static final String ERROR_PHONE_NUMBER_TOO_FEW_DIGITS = "Phone number has too few digits.";
    private static final String ERROR_PHONE_NUMBER_TOO_MANY_DIGITS = "Phone number has too many digits.";
    private static final String ERROR_PHONE_NUMBER_UNEXPECTED_CHARS = "Phone number contains unexpected characters.";
    private static final String ERROR_EMAIL_ADDRESS_NOT_SPECIFIED = "Email address not specified.";
    private static final String ERROR_EMAIL_ADDRESS_WRONG_FORMAT = "" + "Incorrect email address format.";
    private static final String ERROR_ADDRESS_NOT_SPECIFIED = "Address not specified.";
    private static final String ERROR_DATE_TIME_WRONG_FORMAT = "Incorrect Date/Time format.";

    private static final String REGEX_VERTICAL_LINE = "\\|";
    private static final String REGEX_INPUT_PARAMETER = "[a-zA-Z]/";
    private static final String REGEX_INPUT_PARAMETER_FILTER_COMMAND = "[abdps]/";
    private static final String REGEX_EMAIL = "(([a-zA-Z0-9][\\w-.]*[a-zA-Z0-9])|[a-zA-Z0-9])@([\\w]+\\.)+[\\w]+";
    private static final String REGEX_IC = "[STFGM][0-9]{7}[A-Z]";
    private static final String REGEX_PHONE_NUMBER = "[\\d]{8}";
    private static final String REGEX_PHONE_NUMBER_SPACERS = "[- _]";
    private static final String REGEX_ID = "([0-9]+$)|([0-9]+ )";
    private static final String REGEX_CAPITALISE_POSITION = "(\\A|[ _-])[a-z]";

    private static final String VERTICAL_LINE = "|";
    private static final String EMPTY_STRING = "";

    private static final String DATE_TIME_FORMATTER_PATTERN = "ddMMyy HHmm";
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(8);

    /**
     * Parses attributeStrings array and modifies all the corresponding attribute in appointment.
     *
     * @param appointment      Appointment whose information will be updated
     * @param attributeStrings String Array containing Strings of an attribute specifier and the corresponding
     *                         appointment information
     * @throws MedBotParserException if the attributeString contains missing/invalid information
     */
    static void updateMultipleAppointmentInformation(Appointment appointment, String[] attributeStrings)
            throws MedBotParserException {
        for (String attributeString : attributeStrings) {
            updateAppointmentInformation(appointment, attributeString);
        }
    }

    /**
     * Parses attributeString and modifies the corresponding attribute in appointment.
     *
     * @param appointment     Appointment whose information will be updated
     * @param attributeString String containing an attribute specifier and the corresponding appointment information
     * @throws MedBotParserException if the attributeString contains missing/invalid information
     */
    public static void updateAppointmentInformation(Appointment appointment, String attributeString)
            throws MedBotParserException {
        if (attributeString.startsWith(PARAMETER_APPOINTMENT_PATIENT_ID)) {
            int patientId = parseId(attributeString.substring(PARAMETER_BUFFER));
            appointment.setPatientId(patientId);
            return;
        }
        if (attributeString.startsWith(PARAMETER_APPOINTMENT_MEDICAL_STAFF_ID)) {
            int medicalStaffId = parseId(attributeString.substring(PARAMETER_BUFFER));
            appointment.setMedicalStaffId(medicalStaffId);
            return;
        }
        if (attributeString.startsWith(PARAMETER_APPOINTMENT_DATE_TIME)) {
            int dateTimeCode = parseDateTime(attributeString.substring(PARAMETER_BUFFER).strip());
            appointment.setDateTimeCode(dateTimeCode);
        }
    }

    /**
     * Parses user input and separates it into a list of Strings containing the given parameters.
     *
     * @param userInput String containing the full user input.
     * @return list of parameters given by user.
     * @throws MedBotParserException when no parameters are specified.
     */
    static String[] getParameters(String userInput) throws MedBotParserException {
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

    static List<String> getParametersWithoutSpecifiers(String userInput) throws MedBotParserException {
        String processedInput = preprocessMultiAttributeInput(userInput);
        String[] words = processedInput.split(REGEX_VERTICAL_LINE);
        if (words.length == 1) {
            throw new MedBotParserException(ERROR_NO_PARAMETER);
        }
        assert words.length > 1;
        String[] parameters = Arrays.copyOfRange(words, 1, words.length);
        List<String> p = Arrays.asList(parameters);
        assert parameters.length >= 1;

        return p.stream()
                .map(s -> s.substring(PARAMETER_BUFFER))
                .collect(toList());
    }

    static List<String> getSpecifiers(String userInput) throws MedBotParserException {
        Pattern pattern = Pattern.compile(REGEX_INPUT_PARAMETER_FILTER_COMMAND);
        Matcher matcher = pattern.matcher(userInput);
        List<String> specifiers = new ArrayList<>();

        while (matcher.find()) {
            specifiers.add(matcher.group());
        }
        if (specifiers.size() > 2) {
            throw new MedBotParserException(ERROR_TOO_MANY_SPECIFIERS);
        }

        return specifiers;
    }

    /**
     * Preprocesses user input to remove invalid substring that can not be parsed.
     *
     * @param userInput The initial user input.
     * @return user input without leading white space and vertical lines present.
     */
    static String preprocessInput(String userInput) {
        return userInput.strip().replace(VERTICAL_LINE, EMPTY_STRING);
    }

    /**
     * Parses attributeStrings array and modifies all the corresponding attribute in person.
     *
     * @param person           Person whose personal information will be updated
     * @param attributeStrings String Array containing Strings of an attribute specifier and the corresponding
     *                         personal information
     * @throws MedBotParserException if the attributeString contains missing/invalid information
     */
    static void updateMultiplePersonalInformation(Person person, String[] attributeStrings)
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
            return;
        }
        if (attributeString.startsWith(PARAMETER_ADDRESS)) {
            String address = parseResidentialAddress(attributeString.substring(PARAMETER_BUFFER));
            person.setResidentialAddress(address);
            return;
        }
        throw new MedBotParserException(String.format(ERROR_INVALID_PARAM_SPECIFIER, attributeString.substring(0, 2)));
    }

    /**
     * Returns a String containing the names specified in attributeString, with each name capitalised.
     *
     * @param attributeString String containing the name to be parsed
     * @return String containing the name specified in attributeString
     * @throws MedBotParserException if no name is given
     */
    public static String parseName(String attributeString) throws MedBotParserException {
        String name = attributeString.strip();
        if (name.equals(EMPTY_STRING)) {
            throw new MedBotParserException(ERROR_NAME_NOT_SPECIFIED);
        }
        return capitaliseEachWord(name);
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
    public static String parseIcNumber(String attributeString) throws MedBotParserException {
        String icString = attributeString.toUpperCase().strip();
        if (icString.equals(EMPTY_STRING)) {
            throw new MedBotParserException(ERROR_IC_NUMBER_NOT_SPECIFIED);
        }
        if (!icString.matches(REGEX_IC)) {
            throw new MedBotParserException(ERROR_IC_NUMBER_INCORRECT_FORMAT);
        }
        assert icString.length() == 9;
        return icString;
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
    public static String parsePhoneNumber(String attributeString) throws MedBotParserException {
        String numberString = attributeString.replaceAll(REGEX_PHONE_NUMBER_SPACERS, EMPTY_STRING).strip();
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
            throw new MedBotParserException(ERROR_PHONE_NUMBER_UNEXPECTED_CHARS);
        }
        return numberString;
    }

    /**
     * Returns a String containing the email address specified in attributeString.
     *
     * <p>Checks if the resultant String is of the right email format.
     *
     * @param attributeString String containing the email address to be parsed
     * @return String containing the email address specified in attributeString
     * @throws MedBotParserException if the email address is not specified or is in the wrong format
     */
    public static String parseEmailAddress(String attributeString) throws MedBotParserException {
        String emailString = attributeString.strip();
        if (emailString.equals(EMPTY_STRING)) {
            throw new MedBotParserException(ERROR_EMAIL_ADDRESS_NOT_SPECIFIED);
        }
        if (!emailString.matches(REGEX_EMAIL)) {
            throw new MedBotParserException(ERROR_EMAIL_ADDRESS_WRONG_FORMAT);
        }
        return emailString;
    }

    /**
     * Returns the String containing the address specified in attributeString, with each word capitalised.
     *
     * <p>Capitalises each word in the address.
     *
     * @param attributeString String containing the address to be parsed
     * @return String containing the address specified in attributeString
     * @throws MedBotParserException if address is not specified
     */
    public static String parseResidentialAddress(String attributeString) throws MedBotParserException {
        String addressString = attributeString.strip();
        if (addressString.equals(EMPTY_STRING)) {
            throw new MedBotParserException(ERROR_ADDRESS_NOT_SPECIFIED);
        }
        return capitaliseEachWord(addressString);
    }

    /**
     * Reads the String and returns the non-negative integer at the start of the String.
     *
     * <p>Finds an integer at the start of the String that is immediately followed by a space character or the
     * end of the String. Returns that integer.
     *
     * @param string String that starts with an integer
     * @return integer that was found
     * @throws MedBotParserException if no integer is found
     */
    public static int parseId(String string) throws MedBotParserException {
        string = string.strip();
        if (string.equals(EMPTY_STRING)) {
            throw new MedBotParserException(ERROR_ID_NOT_SPECIFIED);
        }
        Pattern pattern = Pattern.compile(REGEX_ID);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.lookingAt()) {
            throw new MedBotParserException(ERROR_ID_NOT_SPECIFIED);
        }
        try {
            return Integer.parseInt(matcher.group().stripTrailing());
        } catch (NumberFormatException ne) {
            //matched substring should only consist of [0-9], exception should not be thrown by parseInt method
            assert false;
            throw new MedBotParserException(ERROR_ID_NOT_SPECIFIED);
        }
    }

    /**
     * Parses the person type from the input string.
     *
     * @param string the String to be parsed.
     * @return the personType (Patient or Staff).
     * @throws MedBotParserException when the person type cannot be parsed.
     */
    public static PersonType parsePersonType(String string) throws MedBotParserException {
        String attributeSpecifier = string.substring(0, PARAMETER_BUFFER);
        if (attributeSpecifier.equals(PARAMETER_PATIENT)) {
            return PersonType.PATIENT;
        }
        if (attributeSpecifier.equals(PARAMETER_STAFF)) {
            return PersonType.STAFF;
        }

        throw new MedBotParserException(ERROR_PERSON_TYPE_INVALID);
    }

    /**
     * Returns the filter type of the appointment class.
     *
     * @param attributeSpecifier the String to be parsed.
     * @return the FilterType
     * @throws MedBotParserException when the filter type cannot be
     */
    public static FilterType parseFilterType(String attributeSpecifier) throws MedBotParserException {

        if (attributeSpecifier.equals(PARAMETER_BEFORE)) {
            return FilterType.BEFORE;
        }
        if (attributeSpecifier.equals(PARAMETER_AFTER)) {
            return FilterType.AFTER;
        }

        throw new MedBotParserException(ERROR_FILTER_TYPE_INVALID);
    }

    /**
     * Returns the flag in the list command (show hidden listItem or not).
     *
     * @param attributeString the String to be parsed.
     * @return the boolean indicate whether we show hidden listItem or not.
     * @throws MedBotParserException when the String is of wrong format.
     */
    public static boolean parseListParameter(String attributeString) throws MedBotParserException {
        if (attributeString.trim().equals(PARAMETER_HIDE)) {
            return true;
        }

        if (attributeString.trim().equals(EMPTY_STRING)) {
            return false;
        }

        throw new MedBotParserException(ERROR_PARAMETER_TYPE_INVALID);
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
        Function<MatchResult, String> replacementFunction = x -> VERTICAL_LINE + x.group();
        Pattern pattern = Pattern.compile(REGEX_INPUT_PARAMETER);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(replacementFunction);
    }

    /**
     * Parses a String that corresponds to a date and time and returns the number of hours since Unix epoch that
     * it corresponds to, rounded down.
     *
     * @param dateTimeString String corresponding to a date and time
     * @return the number of hours since Unix epoch, rounded down to the nearest hour
     */
    public static int parseDateTime(String dateTimeString) throws MedBotParserException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN);
        LocalDateTime parsedDate;
        try {
            parsedDate = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
            parsedDate = parsedDate.withMinute(0);
        } catch (DateTimeParseException dte) {
            throw new MedBotParserException(ERROR_DATE_TIME_WRONG_FORMAT);
        }
        return (int) (parsedDate.toEpochSecond(ZONE_OFFSET) / 60);
    }
}
