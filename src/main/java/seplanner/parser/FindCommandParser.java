package seplanner.parser;

import seplanner.commands.Command;
import seplanner.commands.FindModCommand;
import seplanner.commands.FindUniCommand;
import seplanner.constants.Constants;
import seplanner.modules.ModuleList;
import seplanner.universities.UniversityList;

import java.text.ParseException;

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
