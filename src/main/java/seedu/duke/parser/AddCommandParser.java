package seedu.duke.parser;

import seedu.duke.commands.AddMapCommand;
import seedu.duke.commands.AddModCommand;
import seedu.duke.commands.AddUniCommand;
import seedu.duke.commands.Command;
import seedu.duke.constants.Constants;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCommandParser {

    private String flag;
    private int uniIndex;
    private int mapIndex;
    private University university;
    private Module module;

    public Command parse(String arguments, UniversityList universityMasterList,
                         ModuleList moduleMasterList, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws ParseException, IOException {

        String flagArguments = identifyFlagAndSplitArgs(arguments);

        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            handleUniFlagArgs(flagArguments, universityMasterList, universitySelectedList);
            return new AddUniCommand(university, universityMasterList, universitySelectedList);
        case Constants.FLAG_MODULE:
            handleModFlagArgs(flagArguments, moduleMasterList, moduleSelectedList);
            return new AddModCommand(module, moduleMasterList, moduleSelectedList);
        case Constants.FLAG_MAP:
            handleMapFlagArgs(flagArguments, universitySelectedList, moduleSelectedList);
            return new AddMapCommand(uniIndex, mapIndex,universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        default:
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
        }
    }

    private String identifyFlagAndSplitArgs(String arguments) throws ParseException {
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (argumentsSubstrings.length < 2) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        flag = argumentsSubstrings[0].trim();
        return argumentsSubstrings[1].trim();
    }

    private void handleUniFlagArgs(String arguments, UniversityList universityMasterList,
                                   UniversityList universitySelectedList) throws ParseException {
        boolean textMatches = isTextMatches(arguments);
        String uniName;
        if (textMatches) {
            uniName = arguments;
            // Check if university exists
            if (!universityMasterList.searchUniversity(uniName)) {
                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            university = new University(uniName, new ArrayList<>(), universityMasterList);
        } else {
            uniIndex = Integer.parseInt(arguments);
            // Check if university exists
            if (uniIndex > universityMasterList.getSize()) {
                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
            }
            uniName = universityMasterList.get(uniIndex - 1).getName();
            university = new University(uniName, new ArrayList<>(), uniIndex);
        }

        // Check if university has been added already
        if (universitySelectedList.searchUniversity(uniName)) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_DUPLICATEUNI, 1);
        }
    }

    private void handleModFlagArgs(String arguments, ModuleList moduleMasterList,
                                   ModuleList moduleSelectedList) throws ParseException {
        boolean textMatches = isTextMatches(arguments);
        if (textMatches) {
            module = moduleMasterList.getModule(arguments);
            // Check if module exists
            if (module == null) {
                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
        } else {
            int modIndex = Integer.parseInt(arguments);
            // Check if module exists
            if (modIndex > moduleMasterList.getSize()) {
                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
            }
            module = moduleMasterList.get(modIndex - 1);
        }

        // Check if module has been added already
        if (moduleSelectedList.isModuleExist(module.getModuleCode())) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_DUPLICATEMOD, 1);
        }
    }

    private void handleMapFlagArgs(String arguments, UniversityList universitySelectedList,
                                   ModuleList moduleSelectedList) throws ParseException {
        // Separate arguments
        String[] argumentSubstrings = arguments.trim().split(" ", 2);
        boolean validUni = false;
        if (argumentSubstrings.length < 2) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        uniIndex = Integer.parseInt(argumentSubstrings[0].trim());
        mapIndex = Integer.parseInt(argumentSubstrings[1].trim());
        for (University uni : universitySelectedList.getList()) {
            if (uni.getIndex() == uniIndex) {
                validUni = true;
                break;
            }
        }
        if (!validUni) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_INVALIDUNI, 1);
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