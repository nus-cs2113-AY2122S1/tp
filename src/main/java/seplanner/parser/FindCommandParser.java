package seplanner.parser;

import seplanner.commands.Command;
import seplanner.commands.FindModCommand;
import seplanner.commands.FindUniCommand;
import seplanner.constants.Constants;
import seplanner.enumerations.FindModInputType;
import seplanner.exceptions.FindParseException;
import seplanner.modules.ModuleList;
import seplanner.universities.UniversityList;

public class FindCommandParser {

    private String flag;

    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList)
            throws FindParseException {
        String searchString = identifyFlagAndSplitArgs(arguments);

        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            return new FindUniCommand(searchString, universityMasterList);
        case Constants.FLAG_MODULE_CODE:
            return new FindModCommand(searchString, moduleMasterList, FindModInputType.MODULECODE);
        case Constants.FLAG_MODULE:
            return new FindModCommand(searchString, moduleMasterList, FindModInputType.MODULENAME);
        default:
            throw new FindParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
        }
    }

    private String identifyFlagAndSplitArgs(String arguments) throws FindParseException {
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (argumentsSubstrings.length < 2) {
            throw new FindParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        flag = argumentsSubstrings[0];
        return argumentsSubstrings[1];
    }
}
