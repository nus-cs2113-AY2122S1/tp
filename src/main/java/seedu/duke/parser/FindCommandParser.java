package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.FindModCommand;
import seedu.duke.commands.FindUniCommand;
import seedu.duke.constants.Constants;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;

import seedu.duke.constants.Constants;

public class FindCommandParser {

    private String flag;

    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList)
            throws ParseException {
        String searchString = identifyFlagAndSplitArgs(arguments);

        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            return new FindUniCommand(searchString, universityMasterList);
        case Constants.FLAG_MODULE:
            return new FindModCommand(searchString, moduleMasterList);
        default:
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
        }
    }

    private String identifyFlagAndSplitArgs(String arguments) throws ParseException {
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (argumentsSubstrings.length < 2) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        flag = argumentsSubstrings[0];
        return argumentsSubstrings[1];
    }
}
