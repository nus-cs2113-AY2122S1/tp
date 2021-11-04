package seplanner.parser;

import seplanner.commands.SearchMapCommand;
import seplanner.constants.Constants;
import seplanner.exceptions.AddParseException;
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
        if (ParseCondition.isEmptyInput(input)) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1, true);
        }

        University university = new University();

        if (input.equals("all")) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new SearchMapCommand(university, universitySelectedList, moduleSelectedList, true);
        }

        if (ParseCondition.isNumeric(input)) {
            int uniIndex = Integer.parseInt(input);
            if (ParseCondition.isIndexOutOfBounds(uniIndex, universityMasterList)) {
                throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNIINDEXNOTAVAILABLE, 1, false);
            }
            university = universityMasterList.get(uniIndex - 1);
        } else {
            university = universityMasterList.getUniversity(input);
        }

        if (ParseCondition.isNoPotentialMapping(university, moduleSelectedList)) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_NOMAPPING, 1, false);
        }

        if (ParseCondition.isNullUniversity(university)) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1, false);
        }
        assert university.getName() != null;
        logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
        return new SearchMapCommand(university, universitySelectedList, moduleSelectedList, false);
    }

}
