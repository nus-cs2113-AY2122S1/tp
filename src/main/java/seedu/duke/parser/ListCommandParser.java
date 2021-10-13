package seedu.duke.parser;

import seedu.duke.commands.ListCommand;
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

    public ListCommand parse(String arguments,
                             UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException, IOException {

        logger.log(Level.INFO, "Start parsing list command");

        ListType type;
        if (arguments.trim().length() == 0) {
            throw new ParseException("no flags passed", 1);
        }
        switch (arguments.trim()) {
        case "/m":
            type = ListType.ALLMODS;
            break;
        case "/u":
            type = ListType.ALLUNIS;
            break;
        case "/s":
            type = ListType.SELECTEDUNIS;
            break;
        case "/t":
            type = ListType.SELECTEDMODS;
            break;
        default:
            logger.log(Level.WARNING, "incorrect flags");
            throw new ParseException("Incorrect flags passed.", 1);
        }

        logger.log(Level.INFO, "parse success");

        return new ListCommand(type, universitySelectedList, moduleSelectedList);
    }
}
