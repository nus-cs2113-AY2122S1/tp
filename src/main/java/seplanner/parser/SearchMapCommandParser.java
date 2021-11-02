package seplanner.parser;

import seplanner.commands.SearchMapCommand;
import seplanner.constants.Constants;
import seplanner.exceptions.SearchMapParseException;
import seplanner.modules.ModuleList;
import seplanner.universities.University;
import seplanner.universities.UniversityList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchMapCommandParser {

    private static Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    public SearchMapCommand parse(String arguments, UniversityList universityMasterList,
                                  UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws SearchMapParseException {
        logger.log(Level.INFO, Constants.LOGMSG_PARSESTARTED);
        String input = arguments.trim();
        if (input.length() == 0) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }

        University university;

        if (isNumeric(input)) {
            int index = Integer.parseInt(input);
            if (index > universityMasterList.getSize() || index < 1) {
                throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            university = universityMasterList.get(index - 1);
        } else {
            university = searchForUniversity(input, universityMasterList);
        }

        if (university == null) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
        }
        assert university.getName() != null;
        logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
        return new SearchMapCommand(university, universitySelectedList, moduleSelectedList);
    }

    private static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private University searchForUniversity(String universityName, UniversityList universityMasterList) {
        for (University university : universityMasterList.getList()) {
            if (universityName.equals(university.getName())) {
                return university;
            }
        }
        return null;
    }
}
