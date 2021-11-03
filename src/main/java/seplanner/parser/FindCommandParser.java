package seplanner.parser;

import seplanner.commands.Command;
import seplanner.commands.FindModCommand;
import seplanner.commands.FindUniCommand;
import seplanner.constants.Constants;
import seplanner.enumerations.FindModInputType;
import seplanner.exceptions.FindParseException;
import seplanner.modules.ModuleList;
import seplanner.universities.UniversityList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindCommandParser {

    private String flag;
    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList)
            throws FindParseException {

        logger.log(Level.INFO, Constants.LOGMSG_PARSESTARTED);
        String searchString = identifyFlagAndSplitArgs(arguments);

        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new FindUniCommand(searchString, universityMasterList);
        case Constants.FLAG_MODULE_CODE:
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new FindModCommand(searchString, moduleMasterList, FindModInputType.MODULECODE);
        case Constants.FLAG_MODULE:
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new FindModCommand(searchString, moduleMasterList, FindModInputType.MODULENAME);
        default:
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new FindParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
        }
    }

    private String identifyFlagAndSplitArgs(String arguments) throws FindParseException {
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (argumentsSubstrings.length < 2) {

            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new FindParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        flag = argumentsSubstrings[0];
        return argumentsSubstrings[1];
    }
}
