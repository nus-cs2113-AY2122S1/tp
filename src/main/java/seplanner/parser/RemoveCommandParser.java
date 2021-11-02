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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveCommandParser {

    private static Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
    private String flag;
    private int uniIndex;
    private int mapIndex;
    private University university;
    private Module module;

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
            return new RemoveMapCommand(uniIndex, mapIndex, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        default:
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
        }
    }


    private String identifyFlagAndSplitArgs(String arguments) throws RemoveParseException {
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (argumentsSubstrings.length < 2) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        flag = argumentsSubstrings[0].trim();
        return argumentsSubstrings[1].trim();
    }

    private void handleUniFlagArgs(String arguments, UniversityList universityMasterList,
                                   UniversityList universitySelectedList) throws RemoveParseException {
        boolean textMatches = isTextMatches(arguments);
        String uniName;
        if (textMatches) {
            uniName = arguments;
            // Check if university has been added
            if (!universitySelectedList.searchUniversity(uniName)) {
                logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            university = universitySelectedList.getUniversity(uniName);
        } else {
            try {
                uniIndex = Integer.parseInt(arguments);
            } catch (NumberFormatException e) {
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            // Check if university exists
            if (uniIndex > universityMasterList.getSize() || uniIndex <= 0) {
                logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            uniName = universityMasterList.get(uniIndex - 1).getName();
            university = universitySelectedList.getUniversity(uniName);

        }
        // Check if university has been added already
        if (!universitySelectedList.searchUniversity(uniName)) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
        }
    }

    private void handleModFlagArgs(String arguments, ModuleList moduleMasterList,
                                   ModuleList moduleSelectedList) throws RemoveParseException {
        boolean textMatches = isTextMatches(arguments);
        if (textMatches) {
            module = moduleSelectedList.getModule(arguments);
            // Check if module has been added already
            if (module == null) {
                logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
        } else {
            int modIndex;
            try {
                modIndex = Integer.parseInt(arguments);
            } catch (NumberFormatException e) {
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
            // Check if module exists
            if (modIndex > moduleMasterList.getSize() || modIndex <= 0) {
                logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
                throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
            module = moduleMasterList.get(modIndex - 1);
        }
        // Check if module has been added already
        if (!moduleSelectedList.isModuleExist(module.getModuleCode())) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
        }
    }

    private void handleMapFlagArgs(String arguments, UniversityList universitySelectedList,
                                   ModuleList moduleSelectedList,
                                   UniversityList universityMasterList) throws RemoveParseException {
        // Separate arguments
        String[] argumentSubstrings = arguments.trim().split(" ", 2);
        University currentUni = new University();
        boolean validUni = false;
        if (argumentSubstrings.length < 2) {
            logger.log(Level.INFO, Constants.LOGMSG_PARSEFAILED);
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        try {
            uniIndex = Integer.parseInt(argumentSubstrings[0].trim());
            mapIndex = Integer.parseInt(argumentSubstrings[1].trim());
        } catch (NumberFormatException e) {
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_MAPPINGNOTFOUND, 1);
        }
        for (University uni : universitySelectedList.getList()) {
            if (uni.getIndex() == uniIndex) {
                validUni = true;
                currentUni = uni;
                break;
            }
        }
        if (!validUni) {
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_INVALIDUNI, 1);
        }
        if (universityMasterList.get(uniIndex - 1).getSelectedMappingListSize(moduleSelectedList) == 0) {
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_NOMAPPING, 1);
        }
        if (currentUni.getMappingListSize() < mapIndex || mapIndex < 1) {
            throw new RemoveParseException(Constants.ERRORMSG_PARSEEXCEPTION_INVALIDMAPPING, 1);
        }

    }

    private boolean isTextMatches(String arguments) {
        String regex = ".*[a-zA-Z].*";  // regex to check if string contains any letters
        Pattern pattern = Pattern.compile(regex);  // compiles the regex
        Matcher matcherText = pattern.matcher(arguments);
        return matcherText.matches();
    }
}
