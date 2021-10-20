package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.constants.Constants;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;
import seedu.duke.commands.ListModCommand;
import seedu.duke.commands.ListUniCommand;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommandParser {

    private final Logger logger = Logger.getLogger("ListCommandParserLog");

    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList,
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
        case Constants.FLAG_MASTER_MODULELIST:
            logger.log(Level.INFO, "parse success");
            return new ListModCommand(moduleMasterList);
        case Constants.FLAG_SELECTED_UNIVERSITYLIST:
            logger.log(Level.INFO, "parse success");
            return new ListUniCommand(universitySelectedList);
        case Constants.FLAG_SELECTED_MODULELIST:
            logger.log(Level.INFO, "parse success");
            return new ListModCommand(moduleSelectedList);
        default:
            logger.log(Level.WARNING, "incorrect flags");
            throw new ParseException("Incorrect flags passed.", 1);
        }
    }
}
