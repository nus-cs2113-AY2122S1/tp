package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.RemoveMapCommand;
import seedu.duke.commands.RemoveModCommand;
import seedu.duke.commands.RemoveUniCommand;
import seedu.duke.constants.Constants;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveCommandParser {

    private Logger logger = Logger.getLogger("RemoveCommandParserLog");

    public Command parse(String arguments, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws ParseException, IOException {

        logger.log(Level.INFO, Constants.LOGMSG_PARSESTARTED);

        String[] argumentsSubstrings = arguments.trim().split(" ", 3);
        if (argumentsSubstrings.length < 2) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        String flag = argumentsSubstrings[0];
        int index;

        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            index = Integer.parseInt(argumentsSubstrings[1]);
            if (index > universitySelectedList.getSize()) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            return new RemoveUniCommand(index, universitySelectedList);
        case Constants.FLAG_MODULE:
            index = Integer.parseInt(argumentsSubstrings[1]);
            if (index > moduleSelectedList.getSize()) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
            return new RemoveModCommand(index, moduleSelectedList);
        case Constants.FLAG_MAP:
            int uniIndex = Integer.parseInt(argumentsSubstrings[1]);
            if (argumentsSubstrings.length < 3) {
                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
            }
            int mapIndex = Integer.parseInt(argumentsSubstrings[3].trim());
            return new RemoveMapCommand(uniIndex, mapIndex, universitySelectedList);
        default:
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
        }
    }
}
