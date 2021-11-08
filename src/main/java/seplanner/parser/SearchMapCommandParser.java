package seplanner.parser;

import seplanner.commands.SearchMapCommand;
import seplanner.constants.Constants;
import seplanner.exceptions.SearchMapParseException;
import seplanner.modules.ModuleList;
import seplanner.universities.University;
import seplanner.universities.UniversityList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author MAZJ124

/**
 * Handle searchmap command arguments.
 */
public class SearchMapCommandParser {

    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    /**
     * Handle searchmap command arguments.
     *
     * @param arguments The string of user input without the command word.
     * @param universityMasterList The master list of all available universities.
     * @param universitySelectedList The list of user selected universities.
     * @param moduleSelectedList The list of user selected modules.
     * @return The SearchMapCommand object.
     * @throws SearchMapParseException If inputs are invalid.
     */
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
            if (universitySelectedList.getSize() == 0) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_NOSELECTEDUNI, 1, false);
            }
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new SearchMapCommand(university, universitySelectedList, universityMasterList,
                    moduleSelectedList, true);
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

        if (ParseCondition.isNullUniversity(university)) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1, false);
        }

        if (ParseCondition.isNoPotentialMapping(university, moduleSelectedList)) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new SearchMapParseException(Constants.ERRORMSG_PARSEEXCEPTION_NOMAPPING, 1, false);
        }

        assert university.getName() != null;
        logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
        return new SearchMapCommand(university, universitySelectedList, universityMasterList,
                moduleSelectedList, false);
    }

}
