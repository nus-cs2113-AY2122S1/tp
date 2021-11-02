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

public class ListCommandParser {

    private static Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList,
                         UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ListParseException, IOException {

        logger.log(Level.INFO, Constants.LOGMSG_PARSESTARTED);

        if (arguments.trim().length() == 0) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new ListParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
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
            throw new ListParseException("Incorrect flags passed.", 1);
        }
    }
}

