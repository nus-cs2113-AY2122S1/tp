package seplanner.parser;

import seplanner.commands.AddMapCommand;
import seplanner.commands.AddModCommand;
import seplanner.commands.AddUniCommand;
import seplanner.commands.Command;
import seplanner.constants.Constants;
import seplanner.exceptions.AddParseException;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.universities.University;
import seplanner.universities.UniversityList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCommandParser {

    private String flag;
    private int uniIndex;
    private int mapIndex;
    private University university;
    private Module module;
    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    public Command parse(String arguments, UniversityList universityMasterList,
                         ModuleList moduleMasterList, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws AddParseException, IOException {

        logger.log(Level.INFO, Constants.LOGMSG_PARSESTARTED);

        String flagArguments = identifyFlagAndSplitArgs(arguments);

        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            handleUniFlagArgs(flagArguments, universityMasterList, universitySelectedList);
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new AddUniCommand(university, universityMasterList, universitySelectedList);
        case Constants.FLAG_MODULE_CODE:
        case Constants.FLAG_MODULE:
            handleModFlagArgs(flagArguments, moduleMasterList, moduleSelectedList);
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new AddModCommand(module, moduleMasterList, moduleSelectedList);
        case Constants.FLAG_MAP:
            handleMapFlagArgs(flagArguments, universitySelectedList, moduleSelectedList, universityMasterList);
            logger.log(Level.INFO, Constants.LOGMSG_PARSESUCCESS);
            return new AddMapCommand(uniIndex, mapIndex,universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        default:
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
        }
    }

    private String identifyFlagAndSplitArgs(String arguments) throws AddParseException {
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (argumentsSubstrings.length < 2) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        flag = argumentsSubstrings[0].trim();
        return argumentsSubstrings[1].trim();
    }

    private void handleUniFlagArgs(String arguments, UniversityList universityMasterList,
                                   UniversityList universitySelectedList) throws AddParseException {
        boolean textMatches = isTextMatches(arguments);
        String uniName;
        if (textMatches) {
            uniName = arguments;
            // Check if university exists
            if (!universityMasterList.searchUniversity(uniName)) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            university = new University(uniName, new ArrayList<>(), universityMasterList);
        } else {
            try {
                uniIndex = Integer.parseInt(arguments);
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            // Check if university exists
            if (uniIndex > universityMasterList.getSize() || uniIndex <= 0) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            uniName = universityMasterList.get(uniIndex - 1).getName();
            university = new University(uniName, new ArrayList<>(), uniIndex);
        }

        // Check if university has been added already
        if (universitySelectedList.searchUniversity(uniName)) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_DUPLICATEUNI, 1);
        }
    }

    private void handleModFlagArgs(String arguments, ModuleList moduleMasterList,
                                   ModuleList moduleSelectedList) throws AddParseException {
        boolean textMatches = isTextMatches(arguments);
        if (textMatches) {
            module = moduleMasterList.getModule(arguments.toUpperCase());
            // Check if module exists
            if (module == null) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
        } else {
            int modIndex;
            try {
                modIndex = Integer.parseInt(arguments);
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
            // Check if module exists
            if (modIndex > moduleMasterList.getSize() || modIndex <= 0) {
                logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
                throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
            module = moduleMasterList.get(modIndex - 1);
        }

        // Check if module has been added already
        if (moduleSelectedList.isModuleExist(module.getModuleCode())) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_DUPLICATEMOD, 1);
        }
    }

    private void handleMapFlagArgs(String arguments, UniversityList universitySelectedList,
                                   ModuleList moduleSelectedList,
                                   UniversityList universityMasterList) throws AddParseException {
        // Separate arguments
        String[] argumentSubstrings = arguments.trim().split(" ", 2);
        University currentUni = new University();
        boolean validUni = false;
        if (argumentSubstrings.length < 2) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        try {
            uniIndex = Integer.parseInt(argumentSubstrings[0].trim());
            mapIndex = Integer.parseInt(argumentSubstrings[1].trim());
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_MAPPINGNOTFOUND, 1);
        }
        for (University uni : universitySelectedList.getList()) {
            if (uni.getIndex() == uniIndex) {
                validUni = true;
                //university object from selected list
                currentUni = uni;
                break;
            }
        }
        if (!validUni) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_INVALIDUNI, 1);
        }
        if (universityMasterList.get(uniIndex - 1).getSelectedMappingListSize(moduleSelectedList) == 0) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_NOMAPPING, 1);
        }
        if (universityMasterList.get(uniIndex - 1).getSelectedMappingListSize(moduleSelectedList)
                < mapIndex || mapIndex < 1) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_INVALIDMAPPING, 1);
        }
        if (currentUni.isExistMapping(universityMasterList.get(uniIndex - 1).getSelectedMappings(moduleSelectedList)
                .get(mapIndex - 1))) {
            logger.log(Level.WARNING, Constants.LOGMSG_PARSEFAILED);
            throw new AddParseException(Constants.ERRORMSG_PARSEEXCEPTION_DUPLICATEMAP, 1);
        }
    }

    private boolean isTextMatches(String arguments) {
        String regex = ".*[a-zA-Z].*";  // regex to check if string contains any letters
        Pattern pattern = Pattern.compile(regex);  // compiles the regex
        Matcher matcherText = pattern.matcher(arguments);
        return matcherText.matches();
    }

    public boolean isUniversityExist(String uniName, UniversityList universityMasterList) {
        return universityMasterList.searchUniversity(uniName);
    }

    public Module searchForModule(String moduleCode, ModuleList moduleMasterList) {
        return moduleMasterList.getModule(moduleCode);
    }
}