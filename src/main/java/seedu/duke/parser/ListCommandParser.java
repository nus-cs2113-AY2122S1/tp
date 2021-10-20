package seedu.duke.parser;

import seedu.duke.commands.ListCommand;
import seedu.duke.constants.Constants;
import seedu.duke.enumerations.ListType;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommandParser {

    private Logger logger = Logger.getLogger("ListCommandParserLog");

    public ListCommand parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList,
                             UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException, IOException {

        logger.log(Level.INFO, "Start parsing list command");

        if (arguments.trim().length() == 0) {
            throw new ParseException("no flags passed", 1);
        }
        switch (arguments.trim()) {
        case Constants.FLAG_MASTER_UNIVERSITYLIST:
            logger.log(Level.INFO, "parse success");
            return new ListUniCommand(universityMasterList);
            break;
        case Constants.FLAG_MASTER_MODULELIST:
            logger.log(Level.INFO, "parse success");
            return new ListModCommand(moduleMasterList);
            break;
        case Constants.FLAG_SELECTED_UNIVERSITYLIST:
            logger.log(Level.INFO, "parse success");
            return ListUniCommand(universitySelectedList);
            break;
        case Constants.FLAG_SELECTED_MODULELIST:
            logger.log(Level.INFO, "parse success");
            return ListModCommand(moduleSelectedList);
            break;
        default:
            logger.log(Level.WARNING, "incorrect flags");
            throw new ParseException("Incorrect flags passed.", 1);
        }
    }
}
