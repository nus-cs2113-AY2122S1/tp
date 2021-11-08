package parser;

import command.Command;
import command.ExitCommand;
import command.HelpCommand;
import command.InvestigateCommand;
import command.NarrativeLinesCommand;
import command.NextCommand;
import command.NoteCommand;
import command.RestartCommand;
import command.ViewCommand;
import command.BackCommand;
import exceptions.InvalidInputException;
import exceptions.InvalidSuspectException;
import scene.Scene;
import scene.suspect.SuspectList;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String HELP = "/help";
    private static final String NOTE = "/note";
    private static final String EXIT = "/exit";
    private static final String NEXT = "/next";
    private static final String VIEW = "/view";
    private static final String BACK = "/back";
    private static final String NARRATIVE_LINES = "/narrative-lines";
    private static final String RESTART = "/restart";
    private static final String SUSPECT_FATHER = "Father";
    private static final String SUSPECT_KEVIN = "Kevin";
    private static final String SUSPECT_WENDY = "Wendy";
    private static final String SUSPECT_LING = "Ling";
    private static final String SUSPECT_ZACK = "Zack";
    private static final String INVALID_SUSPECT = "No suspect with corresponding number.";
    private static final String INPUT_SPLITTER = " ";
    private static final int NOTE_SCENE_INDEX = 1;
    private static final String INVALID_INPUT = "Invalid input!";
    private static final String INVESTIGATE = "/investigate";
    private static final String SUSPECT_FATHER_LOWER = "father";
    private static final String SUSPECT_KEVIN_LOWER = "kevin";
    private static final String SUSPECT_WENDY_LOWER = "wendy";
    private static final String SUSPECT_LING_LOWER = "ling";
    private static final String SUSPECT_ZACK_LOWER = "zack";
    private static final String NOTE_CREATE = "1";
    private static final String NOTE_OPEN = "2";
    private static final String NOTE_DELETE = "3";
    private static final String ALPHABET_PATTERN = "^[a-zA-Z]+$";
    private static final String NUMBER_PATTERN = "^[0-9]+$";

    /**
     * Returns suspect name based on the suspect number.
     *
     * @param currentScene The current scene.
     * @param suspectNumber The suspect number.
     * @throws InvalidSuspectException If the user enters an invalid input.
     */
    public String getSuspectNameFromIndex(Scene currentScene, int suspectNumber) throws InvalidSuspectException {
        SuspectList currentSceneSuspectList = currentScene.getSuspectList();
        try {
            return currentSceneSuspectList.getSuspectNames()[suspectNumber - 1];
        } catch (InvalidSuspectException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidSuspectException(INVALID_SUSPECT);
        }

    }

    /**
     * Returns the command from the user based on the input given.
     *
     * @param userInput The input given by the user
     * @throws InvalidInputException We throw this error when the user enters an invalid input.
     * @throws NumberFormatException We throw this error when the user gives a number that is too large/small.
     */
    public Command getCommandFromUser(String userInput) throws InvalidInputException, NumberFormatException {
        boolean multipleArgumentsGiven = userInput.contains(INPUT_SPLITTER);
        if (multipleArgumentsGiven) {
            return parseInputMultipleArguments(userInput);
        }
        switch (userInput) {
        case NOTE:
            return new NoteCommand();
        case EXIT:
            return new ExitCommand();
        case HELP:
            return new HelpCommand();
        case NEXT:
            return new NextCommand();
        case VIEW:
            return new ViewCommand();
        case RESTART:
            return new RestartCommand();
        case BACK:
            return new BackCommand();
        default:
            return useSuspectNameOrIndexForInvestigating(userInput);
        }
    }

    /**
     * Returns the InvestigateCommand based on the input given by the user.
     *
     * @param userInput The input from the user.
     * @throws InvalidInputException We throw this error when the user enters an invalid input.
     * @throws NumberFormatException We throw this error when the user gives a number that is too large/small.
     */
    private Command useSuspectNameOrIndexForInvestigating(String userInput) throws InvalidInputException,
            NumberFormatException {
        Pattern alphabetPattern = Pattern.compile(ALPHABET_PATTERN);
        Pattern numberPattern = Pattern.compile(NUMBER_PATTERN);
        Matcher alphabetPatternMatcher = alphabetPattern.matcher(userInput);
        Matcher numberPatternMatcher = numberPattern.matcher(userInput);

        boolean numberFound = numberPatternMatcher.find();
        boolean alphabetFound = alphabetPatternMatcher.find();

        if (numberFound) {
            int inputParsedToInt = parseUserInput(userInput);
            return new InvestigateCommand(inputParsedToInt);
        } else if (alphabetFound) {
            return parseInputForInvestigateCommand(userInput);
        } else {
            throw new InvalidInputException(INVALID_INPUT);
        }
    }

    /**
     * Parses the input given by the user if the user entered numbers only.
     *
     * @param userInput The input given by the user.
     * @throws NumberFormatException If user enters a number that is too large/small.
     */
    private int parseUserInput(String userInput) throws NumberFormatException {
        return Integer.parseInt(userInput);
    }

    /**
     * Returns a ViewCommand based on the arguments given.
     *
     * @param argsGiven The arguments given for View Command.
     * @throws InvalidInputException If user enters invalid arguments.
     */
    private Command parseInputForViewCommand(String argsGiven) throws InvalidInputException {
        if (containInvalidViewArgument(argsGiven)) {
            throw new InvalidInputException(INVALID_INPUT);
        }
        String[] argsArr = capitalizeWords(argsGiven);
        return new ViewCommand(argsArr);
    }

    private String[] capitalizeWords(String argsGiven) {
        String[] argsArr = argsGiven.toLowerCase(Locale.ROOT).split(INPUT_SPLITTER);
        for (int i = 0; i < argsArr.length; i++) {
            argsArr[i] = capitalizeWord(argsArr[i]);
        }
        return argsArr;
    }

    /**
     * Returns a NoteCommand based on the arguments given.
     *
     * @param argsGiven The arguments given for Note Command.
     * @throws InvalidInputException If user enters invalid arguments.
     */
    private Command parseInputForNoteCommand(String argsGiven) throws InvalidInputException {
        if (containInvalidNoteArgument(argsGiven)) {
            throw new InvalidInputException(INVALID_INPUT);
        }
        return new NoteCommand(argsGiven);
    }

    private Command parseInputForNarrativeLinesCommand(String argsGiven) throws InvalidInputException {
        try {
            int numLines = Integer.parseInt(argsGiven);
            if (numLines < 1) {
                throw new InvalidInputException(INVALID_INPUT);
            }
            return new NarrativeLinesCommand(numLines);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(INVALID_INPUT);
        }
    }

    /**
     * Returns a InvestigateCommand based on the suspect name.
     *
     * @param suspectName The suspect name given by the user.
     * @throws InvalidInputException If user enters invalid suspect name.
     */
    private Command parseInputForInvestigateCommand(String suspectName) throws InvalidInputException {
        String suspectNameLowerCase = suspectName.toLowerCase();
        return new InvestigateCommand(suspectNameLowerCase);
    }

    /**
     * Returns a Command based on the inputs given by the user.
     *
     * @param userInput The input given by the user.
     * @throws InvalidInputException If user enters invalid an invalid input.
     */
    private Command parseInputMultipleArguments(String userInput) throws InvalidInputException {
        String[] userInputArr = userInput.split(INPUT_SPLITTER, 2);
        String commandType = userInputArr[0];
        String argsGiven = userInputArr[1];

        switch (commandType) {
        case NOTE:
            return parseInputForNoteCommand(argsGiven);
        case VIEW:
            return parseInputForViewCommand(argsGiven);
        case INVESTIGATE:
            return useSuspectNameOrIndexForInvestigating(argsGiven);
        case NARRATIVE_LINES:
            return parseInputForNarrativeLinesCommand(argsGiven);
        default:
            throw new InvalidInputException(INVALID_INPUT);
        }
    }

    private boolean containInvalidNoteArgument(String args) {
        String[] argsArr = args.split(INPUT_SPLITTER);
        for (String arg : argsArr) {
            switch (args) {
            case NOTE_CREATE:
                // fallthrough
            case NOTE_OPEN:
                //fallthrough
            case NOTE_DELETE:
                // fallthrough
                break;
            default:
                return true;
            }
        }
        return false;
    }

    private boolean containInvalidViewArgument(String args) {
        String[] argsArr = args.split(INPUT_SPLITTER);
        for (String arg : argsArr) {
            arg = capitalizeWord(arg);
            switch (arg) {
            case SUSPECT_FATHER:
                // fallthrough
            case SUSPECT_ZACK:
                // fallthrough
            case SUSPECT_WENDY:
                // fallthrough
            case SUSPECT_KEVIN:
                // fallthrough
            case SUSPECT_LING:
                break;
            default:
                return true;
            }
        }
        return false;
    }

    private String capitalizeWord(String arg) {
        return arg.substring(0, 1).toUpperCase(Locale.ROOT) + arg.substring(1).toLowerCase(Locale.ROOT);
    }

    public static String[] parseOpenNoteCommand(String userInput) {
        String[] userInputInArray = userInput.split(" ", 3);
        return userInputInArray;
    }

}


