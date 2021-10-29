package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.constants.Constants;
import seedu.duke.enumerations.ListType;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;
import seedu.duke.commands.ListModCommand;
import seedu.duke.commands.ListUniCommand;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommandParser {

    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList,
                         UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException, IOException {

        logger.log(Level.INFO, Constants.LOGMSG_PARSESTARTED);

        if (arguments.trim().length() == 0) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
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
            throw new ParseException("Incorrect flags passed.", 1);
        }
    }
}

