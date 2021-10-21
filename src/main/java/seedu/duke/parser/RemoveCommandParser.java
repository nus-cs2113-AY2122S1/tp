package seedu.duke.parser;

import seedu.duke.commands.Command;
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

    public Command parse(String arguments, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList, UniversityList universityMasterList,
                         ModuleList moduleMasterList) throws ParseException, IOException {

        logger.log(Level.INFO, Constants.LOGMSG_PARSESTARTED);

        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
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
            return new RemoveUniCommand(index, universityMasterList, universitySelectedList);
        case Constants.FLAG_MODULE:
            index = Integer.parseInt(argumentsSubstrings[1]);
            if (index > moduleSelectedList.getSize()) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
            return new RemoveModCommand(index, moduleMasterList, moduleSelectedList);
        case Constants.FLAG_MAP:
            argumentsSubstrings = arguments.trim().split(" ", 3);
            int uniIndex = Integer.parseInt(argumentsSubstrings[1]);
            int modIndex = Integer.parseInt(argumentsSubstrings[2].trim());
            if (argumentsSubstrings.length < 3) {
                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
            }
            University university = universityMasterList.get(uniIndex - 1);
            Module moduleToMap = moduleMasterList.get(modIndex - 1);
            Module mappedModule = university.getMappedModule(moduleToMap, moduleSelectedList);
            if (mappedModule == null) {
                throw new ParseException("There is no available module mapping.", 1);
            }
            return new RemoveMapCommand(uniIndex, modIndex, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        default:
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
        }
    }
}
