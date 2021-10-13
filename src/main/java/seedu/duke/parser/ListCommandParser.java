package seedu.duke.parser;

import seedu.duke.commands.ListCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommandParser {

    private Logger logger = Logger.getLogger("ListCommandParserLog");

    public ListCommand parse(String arguments,
                             UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException, IOException {

        logger.log(Level.INFO, "Start parsing list command");

        String type;
        if (arguments.trim().length() == 0) {
            throw new ParseException("no flags passed", 1);
        }

        switch (arguments.trim()) {
        case "/m":
            type = "m";
            break;
        case "/u":
            type = "u";
            break;
        case "/s":
            type = "s";
            break;
        case "/t":
            type = "t";
            break;
        default:
            logger.log(Level.WARNING, "incorrect flags");
            throw new ParseException("Incorrect flags passed.", 1);
        }

        logger.log(Level.INFO, "parse success");

        return new ListCommand(type, universitySelectedList, moduleSelectedList);
    }
}
