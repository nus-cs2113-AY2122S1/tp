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

//@@ author MAZJ124
/**
 * Handle find command arguments.
 */
public class FindCommandParser {

    private String flag;
    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    /**
     * Handle find command arguments.
     *
     * @param arguments The string of user input without the command word.
     * @param universityMasterList The master list of all available universities.
     * @param moduleMasterList The master list of all available modules.
     * @return The Command object corresponding to the flag.
     * @throws FindParseException If inputs are invalid.
     */
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
            throw new FindParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1, true);
        }
    }

    /**
     * Extract the flag from the rest of the arguments.
     *
     * @param arguments The user input without the command word.
     * @return The String containing the arguments for AddCommand.
     * @throws FindParseException If inputs are invalid.
     */
    private String identifyFlagAndSplitArgs(String arguments) throws FindParseException {
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (ParseCondition.isMissingArguments(argumentsSubstrings)) {

            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new FindParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1, true);
        }
        flag = argumentsSubstrings[0];
        return argumentsSubstrings[1];
    }
}
