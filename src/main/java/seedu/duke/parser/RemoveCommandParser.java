package seedu.duke.parser;

import seedu.duke.commands.RemoveCommand;
import seedu.duke.commands.RemoveMapCommand;
import seedu.duke.commands.RemoveModCommand;
import seedu.duke.commands.RemoveUniCommand;
import seedu.duke.constants.Constants;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveCommandParser {

    private Logger logger = Logger.getLogger("RemoveCommandParserLog");

    public RemoveCommand parse(String arguments, UniversityList universitySelectedList,
                               ModuleList moduleSelectedList) throws ParseException, IOException {

        logger.log(Level.INFO, "Start parsing remove command");

        String[] argumentsSubstrings = arguments.trim().split(" ", 2);

        if (argumentsSubstrings.length < 2) {
            logger.log(Level.INFO, "not enough arguments");
            throw new ParseException("No flags or description found.", 1);
        }
        String flag = argumentsSubstrings[0];
        int index = Integer.parseInt(argumentsSubstrings[1]);

        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            if (index > universitySelectedList.getSize()) {
                logger.log(Level.WARNING, "University not found");
                throw new ParseException("university not in list", 1);
            }
            return new RemoveUniCommand(index, universitySelectedList);
        case Constants.FLAG_MODULE:
            if (index > moduleSelectedList.getSize()) {
                logger.log(Level.WARNING, "module not found");
                throw new ParseException("module does not exist", 1);
            }
            return new RemoveModCommand(index, moduleSelectedList);
        case Constants.FLAG_MAP:
            return new RemoveMapCommand(index, universitySelectedList);
        default:
            throw new ParseException("Wrong flags passed", 1);
        }
    }
}
