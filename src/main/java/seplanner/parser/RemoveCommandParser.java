package seplanner.parser;

import seplanner.commands.Command;
import seplanner.commands.RemoveMapCommand;
import seplanner.commands.RemoveModCommand;
import seplanner.commands.RemoveUniCommand;
import seplanner.constants.Constants;
import seplanner.exceptions.RemoveParseException;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.universities.University;
import seplanner.universities.UniversityList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// @@author leowyy99

/**
 * Handle remove command arguments.
 */
public class RemoveCommandParser {

    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
    private String flag;
    private int uniIndex;
    private int mapIndex;
    private University university;
    private Module module;

    /**
     * Handle add command arguments.
     *
     * @param arguments The string of user input without the command word.
     * @param universityMasterList The master list of all available universities.
     * @param moduleMasterList The master list of all available modules.
     * @param universitySelectedList The list of user selected universities.
     * @param moduleSelectedList The list of user selected modules.
     * @return The Command object corresponding to the flag.
     * @throws RemoveParseException If inputs are invalid.
     * @throws IOException If IO exception exists.
     */
    public Command parse(String arguments, UniversityList universityMasterList,
                         ModuleList moduleMasterList, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws RemoveParseException, IOException {

        logger.log(Level.INFO, Constants.LOGMSG_PARSESTARTED);
        String flagArguments = identifyFlagAndSplitArgs(arguments);
        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            handleUniFlagArgs(flagArguments, universityMasterList, universitySelectedList);
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new RemoveUniCommand(university, universityMasterList, universitySelectedList);
        case Constants.FLAG_MODULE:
            handleModFlagArgs(flagArguments, moduleMasterList, moduleSelectedList);
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new RemoveModCommand(module, moduleMasterList, moduleSelectedList);
        case Constants.FLAG_MAP:
            handleMapFlagArgs(flagArguments, universitySelectedList, moduleSelectedList, universityMasterList);
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new RemoveMapCommand(uniIndex, mapIndex, universityMasterList, universitySelectedList);
        default:
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1, true);
        }
    }


    /**
     * Extract the flag from the rest of the arguments.
     *
     * @param arguments The user input without the command word.
     * @return The String containing the arguments for AddCommand.
     * @throws RemoveParseException If inputs are invalid.
     */
    private String identifyFlagAndSplitArgs(String arguments) throws RemoveParseException {
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (ParseCondition.isMissingArguments(argumentsSubstrings)) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1, true);
        }
        flag = argumentsSubstrings[0].trim();
        return argumentsSubstrings[1].trim();
    }

    /**
     * Handle the arguments for /uni flag.
     *
     * @param arguments The argument for /uni flag.
     * @param universityMasterList The master list of all available universities.
     * @param universitySelectedList The list of user selected universities.
     * @throws RemoveParseException If inputs are invalid.
     */
    private void handleUniFlagArgs(String arguments, UniversityList universityMasterList,
                                   UniversityList universitySelectedList) throws RemoveParseException {
        String uniName;
        if (ParseCondition.isText(arguments)) {
            uniName = arguments;
            // Check if university has been added
            if (!ParseCondition.isDuplicateUniversity(universitySelectedList, uniName)) {
                logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTSELECTED, 1, false);
            }
            university = universitySelectedList.getUniversity(uniName);
        } else if (ParseCondition.isNumeric(arguments)) {
            uniIndex = Integer.parseInt(arguments);
            // Check if university exists
            if (ParseCondition.isIndexOutOfBounds(uniIndex, universityMasterList)) {
                logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNIINDEXNOTAVAILABLE, 1, false);
            }
            uniName = universityMasterList.get(uniIndex - 1).getName();
            university = universitySelectedList.getUniversity(uniName);
        } else {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNIINVALID, 1, false);
        }

        // Check if university has been added already
        if (!ParseCondition.isDuplicateUniversity(universitySelectedList, uniName)) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1, false);
        }
    }

    /**
     * Handle the arguments for /mod flag.
     *
     * @param arguments The arguments for /mod flag.
     * @param moduleMasterList The master list of all available modules.
     * @param moduleSelectedList The list of user selected modules.
     * @throws RemoveParseException If inputs are invalid.
     */
    private void handleModFlagArgs(String arguments, ModuleList moduleMasterList,
                                   ModuleList moduleSelectedList) throws RemoveParseException {
        if (ParseCondition.isText(arguments)) {
            module = moduleSelectedList.getModule(arguments);
            // Check if module has been added already
            if (ParseCondition.isNullModule(module)) {
                logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTSELECTED, 1, false);
            }
        } else if (ParseCondition.isNumeric(arguments)) {
            int modIndex = Integer.parseInt(arguments);
            // Check if module exists
            if (ParseCondition.isIndexOutOfBounds(modIndex, moduleMasterList)) {
                logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODINDEXNOTAVAILABLE, 1, false);
            }
            module = moduleMasterList.get(modIndex - 1);
        } else {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODINVALID, 1, false);
        }
        // Check if module has been added already
        if (!ParseCondition.isDuplicateModule(moduleSelectedList, module)) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1, false);
        }
    }

    /**
     * Handle arguments for /map flag.
     * @param arguments The arguments for /map flag.
     * @param universityMasterList The master list of all available universities.
     * @param universitySelectedList The list of user selected universities.
     * @param moduleSelectedList The list of user selected modules.    * @param universityMasterList
     * @throws RemoveParseException If inputs are invalid.
     */
    private void handleMapFlagArgs(String arguments, UniversityList universitySelectedList,
                                   ModuleList moduleSelectedList,
                                   UniversityList universityMasterList) throws RemoveParseException {
        // Separate arguments
        String[] argumentSubstrings = arguments.trim().split(" ", 2);
        if (ParseCondition.isMissingArguments(argumentSubstrings)) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1, true);
        }
        String firstParam = argumentSubstrings[0].trim();
        String secondParam = argumentSubstrings[1].trim();
        if (ParseCondition.isNumeric(firstParam) && ParseCondition.isNumeric(secondParam)) {
            uniIndex = Integer.parseInt(firstParam);
            mapIndex = Integer.parseInt(secondParam);
        } else {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            String error = (ParseCondition.isNumeric(firstParam)) ? Constants.ERRORMSG_PARSEEXCEPTION_INVALIDMAPPING
                    : Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND;
            throw new RemoveParseException(error, 1, false);
        }
        if (!ParseCondition.isInSelectedUniList(uniIndex, universitySelectedList, universityMasterList)) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);  
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTSELECTED, 1, false);
        }
        if (ParseCondition.isMissingAvailableMapping(uniIndex, universityMasterList, moduleSelectedList)) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_NOMAPPING, 1, false);
        }
        if (ParseCondition.isIndexOutOfBounds(uniIndex, mapIndex, universityMasterList, moduleSelectedList)) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_INVALIDMAPPING, 1, false);
        }
    }

}
