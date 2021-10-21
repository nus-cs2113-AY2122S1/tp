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

    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList)
            throws ParseException {

        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (argumentsSubstrings.length < 2) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }

        String flag = argumentsSubstrings[0];
        String input = argumentsSubstrings[1];

        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            return new FindUniCommand(input, universityMasterList);
        case Constants.FLAG_MODULE:
            return new FindModCommand(input, moduleMasterList);
        default:
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
        }
    }

}
