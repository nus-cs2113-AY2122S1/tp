package seplanner.parser;

import seplanner.commands.Command;
import seplanner.constants.Constants;
import seplanner.enumerations.ListType;
import seplanner.exceptions.ListParseException;
import seplanner.modules.ModuleList;
import seplanner.universities.UniversityList;
import seplanner.commands.ListModCommand;
import seplanner.commands.ListUniCommand;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// @@author leowyy99

/**
 * Handle list command arguments.
 */
public class ListCommandParser {

    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    /**
     * Handle list command arguments.
     *
     * @param arguments The string of user input without the command word.
     * @param universityMasterList The master list of all available universities.
     * @param moduleMasterList The master list of all available modules.
     * @param universitySelectedList The list of user selected universities.
     * @param moduleSelectedList The list of user selected modules.
     * @return The Command object corresponding to the flag.
     * @throws ListParseException If inputs are invalid.
     * @throws IOException If IO exception exists.
     */
    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList,
                         UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ListParseException, IOException {

        logger.log(Level.INFO, Constants.LOGMSG_PARSESTARTED);

        if (arguments.trim().length() == 0) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new ListParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1, true);
        }
        switch (arguments.trim()) {
        case Constants.FLAG_MASTER_UNIVERSITYLIST:
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new ListUniCommand(universityMasterList, universityMasterList, ListType.MASTER);
        case Constants.FLAG_MASTER_MODULELIST:
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new ListModCommand(moduleMasterList, ListType.MASTER);
        case Constants.FLAG_SELECTED_UNIVERSITYLIST:
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new ListUniCommand(universitySelectedList, universityMasterList, ListType.SELECTED);
        case Constants.FLAG_SELECTED_MODULELIST:
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new ListModCommand(moduleSelectedList, ListType.SELECTED);
        default:
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new ListParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1, true);
        }
    }
}

