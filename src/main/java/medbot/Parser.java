package medbot;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import medbot.command.AddPatientCommand;
import medbot.command.Command;
import medbot.command.DeletePatientCommand;
import medbot.command.ListPatientCommand;
import medbot.person.Patient;
import medbot.person.Person;

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
        if (userInput.startsWith(COMMAND_LIST)) {
            return new ListPatientCommand();
        } else {
            throw new MedBotException("Unable to parse command.");
        }
    }

    private static DeletePatientCommand parseDeletePatientCommand(String userInput) throws MedBotException{
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

    private static void updatePersonalInformation(Person person, String attributeString) throws MedBotException {
        if (attributeString.startsWith("n/")) {
            String name = parseName(attributeString);
            person.setName(name);
            return;
        }
        if (attributeString.startsWith("p/")) {
            int phoneNumber = parsePhoneNumber(attributeString);
            person.setPhoneNumber(phoneNumber);
            return;
        }
        if (attributeString.startsWith("e/")) {
            String email = parseEmailAddress(attributeString);
            person.setEmailAddress(email);
            return;
        }
    }

    private static String parseName(String attributeString) throws MedBotException {
        try {
            String name = attributeString.substring(2).strip();
            if (name.equals("")) {
                throw new MedBotException("Name not specified");
            }
            return capitaliseEachWord(name);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException("Name not specified");
        }
    }

    private static int parsePhoneNumber(String attributeString) throws MedBotException {
        try {
            String numberString = attributeString.substring(2).replace(" ", "");
            if (numberString.equals("")) {
                throw new MedBotException("Phone number not specified");
            }
            return Integer.parseInt(numberString);
        } catch (IndexOutOfBoundsException ie) {
            throw new MedBotException("Phone number not specified");
        } catch (NumberFormatException ne) {
            throw new MedBotException("Incorrect phone number format");
        }
    }

    private static String parseEmailAddress(String attributeString) throws MedBotException {
        String emailFormat = "[\\w-\\.]*[\\w-]\\@([\\w]+\\.)+[\\w]+";
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

    private static String capitaliseEachWord(String input) {
        Function<MatchResult, String> multiAttributeReplacementFunction = x -> {
            String match = x.group();
            if (match.length() == 1) {
                return match.toUpperCase();
            } else {
                return match.charAt(0) + match.substring(1).toUpperCase();
            }
        };
        Pattern pattern = Pattern.compile("(\\A|[ -])[a-z]");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(multiAttributeReplacementFunction);
    }

    private static String preprocessMultiAttributeInput(String input) {
        Function<MatchResult, String> multiAttributeReplacementFunction = x -> " |" + x.group().substring(1);
        Pattern pattern = Pattern.compile(" \\w/");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(multiAttributeReplacementFunction);
    }
}
