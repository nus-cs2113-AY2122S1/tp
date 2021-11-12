package seplanner.parser;

import seplanner.commands.Command;
import seplanner.constants.Constants;
import seplanner.modules.ModuleList;
import seplanner.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// @@author leowyy99

/**
 * Main parser class that handles the command word.
 */
public class Parser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    protected UniversityList universityMasterList;
    protected ModuleList moduleMasterList;
    protected ModuleList moduleSelectedList;
    protected UniversityList universitySelectedList;

    /**
     * Constructor for Parser.
     *
     * @param universityMasterList The master list of all available universities.
     * @param moduleMasterList The master list of all available modules.
     * @param universitySelectedList The list of user selected universities.
     * @param moduleSelectedList The list of user selected modules.
     */
    public Parser(UniversityList universityMasterList, ModuleList moduleMasterList,
                  UniversityList universitySelectedList, ModuleList moduleSelectedList) {
        this.universityMasterList = universityMasterList;
        this.moduleMasterList = moduleMasterList;
        this.moduleSelectedList = moduleSelectedList;
        this.universitySelectedList = universitySelectedList;
    }

    /**
     * Handle the command word and extract arguments.
     *
     * @param userInput The raw input given by the user.
     * @return The respective parser for the respective commands.
     * @throws ParseException If inputs are invalid.
     * @throws IOException If IO exceptions are present.
     */
    public Command parseCommand(String userInput) throws ParseException, IOException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MATCHERCLASS, 1);
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        switch (commandWord) {
        case Constants.COMMAND_LIST:
            return new ListCommandParser().parse(arguments, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        case Constants.COMMAND_REMOVE:
            return new RemoveCommandParser().parse(arguments, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        case Constants.COMMAND_SEARCHMAP:
            return new SearchMapCommandParser().parse(arguments, universityMasterList,
                    universitySelectedList, moduleSelectedList);
        case Constants.COMMAND_ADD:
            return new AddCommandParser().parse(arguments, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        case Constants.COMMAND_FIND:
            return new FindCommandParser().parse(arguments, universityMasterList, moduleMasterList);
        case Constants.COMMAND_HELP:
            return new HelpCommandParser().parse();
        case Constants.COMMAND_EXIT:
            return new ExitCommandParser().parse();
        default:
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTCOMMAND, 1);
        }
    }
}
