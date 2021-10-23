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
    private int modIndex;
    private University university;
    private Module module;

    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList,
                         UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException, IOException {


//        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
//        if (argumentsSubstrings.length < 2) {
//            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
//        }
//        String flag = argumentsSubstrings[0];
//        int index;
//        String regex = ".*[a-zA-Z].*";  // regex to check if string contains any letters
//        Pattern pattern = Pattern.compile(regex);  // compiles the regex
//        Matcher matcherText = pattern.matcher(argumentsSubstrings[1]);
//        boolean textMatches = matcherText.matches();

        String flagArguments = identifyFlagAndSplitArgs(arguments);
        switch (flag) {
        case Constants.FLAG_UNIVERSITY:
            handleUniFlagArgs(flagArguments, universityMasterList);
            return new AddUniCommand(university, universityMasterList, universitySelectedList);
        case Constants.FLAG_MODULE:
            handleModFlagArgs(flagArguments, moduleMasterList);
            return new AddModCommand(module, moduleMasterList, moduleSelectedList);
        case Constants.FLAG_MAP:
            handleMapFlagArgs(flagArguments);
            return new AddMapCommand(modIndex, uniIndex,universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        default:
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);

        }

//        switch (flag) {
//        case Constants.FLAG_MODULE:
//            if (!textMatches) {
//                index = Integer.parseInt(argumentsSubstrings[1].trim());
//                if (index > moduleMasterList.getSize()) {
//                    throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
//                }
//                return new AddModCommand(index, moduleMasterList, moduleSelectedList);
//            } else {
//                String moduleCode = argumentsSubstrings[1].trim();
//                if (moduleCode.length() == 0) {
//                    throw new ParseException("no module give", 1);
//                }
//                Module module = moduleMasterList.getModule(moduleCode);
//                if (module == null) {
//                    throw new ParseException("module does not exist", 1);
//                }
//                assert module.getModuleCode() != null;
//                return new AddModCommand(module, moduleMasterList, moduleSelectedList);
//            }
//        case Constants.FLAG_UNIVERSITY:
//            if (!textMatches) {
//                index = Integer.parseInt(argumentsSubstrings[1].trim());
//                if (index > universityMasterList.getSize()) {
//                    throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
//                }
//                return new AddUniCommand(index, universityMasterList, universitySelectedList);
//            } else {
//                String universityName = argumentsSubstrings[1].trim();
//                if (universityName.length() == 0) {
//                    throw new ParseException("no university given", 1);
//                }
//                University university = universityMasterList.getUniversity(universityName);
//                if (university == null) {
//                    throw new ParseException("university does not exist", 1);
//                }
//                university.list = new ArrayList<>();
//                assert university.getName() != null;
//                return new AddUniCommand(university, universityMasterList, universitySelectedList);
//            }
//        case Constants.FLAG_MAP:
//            argumentsSubstrings = arguments.trim().split(" ", 3);
//            int uniIndex = Integer.parseInt(argumentsSubstrings[1].trim());
//            int modIndex = Integer.parseInt(argumentsSubstrings[2].trim());
//            if (argumentsSubstrings.length < 3) {
//                throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
//            }
//            University university = universityMasterList.get(uniIndex - 1);
//            Module moduleToMap = moduleMasterList.get(modIndex - 1);
//            Module mappedModule = university.getMappedModule(moduleToMap, moduleSelectedList);
//            if (mappedModule == null) {
//                throw new ParseException("There is no available module mapping.", 1);
//            }
//            return new AddMapCommand(uniIndex, modIndex, universityMasterList,
//                    moduleMasterList, universitySelectedList, moduleSelectedList);
//        default:
//            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_INCORRECTFLAGS, 1);
//        }
    }

    private String identifyFlagAndSplitArgs(String arguments) throws ParseException {
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (argumentsSubstrings.length < 2) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        flag = argumentsSubstrings[0].trim();
        return argumentsSubstrings[1].trim();
    }

    private void handleUniFlagArgs(String arguments, UniversityList universityMasterList) throws ParseException {
        boolean textMatches = isTextMatches(arguments);
        String uniName;
        if (textMatches) {
            uniName = arguments;
        } else {
            uniIndex = Integer.parseInt(arguments);
            uniName = universityMasterList.get(uniIndex - 1).getName();
        }

        if (!isUniversityExist(uniName, universityMasterList)) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_UNINOTFOUND, 1);
        }
        university = new University(uniName, new ArrayList<>(), uniIndex);
    }

    private void handleModFlagArgs(String arguments, ModuleList moduleMasterList) throws ParseException {
        boolean textMatches = isTextMatches(arguments);
        if (textMatches) {
            module = moduleMasterList.getModule(arguments);
        } else {
            modIndex = Integer.parseInt(arguments);
            module = moduleMasterList.get(modIndex);
        }
        if (module == null) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MODNOTFOUND, 1);
        }
    }

    private void handleMapFlagArgs(String arguments) throws ParseException {
        String[] argumentSubstrings = arguments.trim().split(" ", 2);
        if (argumentSubstrings.length < 2) {
            throw new ParseException(Constants.ERRORMSG_PARSEEXCEPTION_MISSINGARGUMENTS, 1);
        }
        uniIndex = Integer.parseInt(argumentSubstrings[0].trim());
        modIndex = Integer.parseInt(argumentSubstrings[1].trim());
    }

    private boolean isTextMatches(String arguments) {
        String regex = ".*[a-zA-Z].*";  // regex to check if string contains any letters
        Pattern pattern = Pattern.compile(regex);  // compiles the regex
        Matcher matcherText = pattern.matcher(arguments);
        return matcherText.matches();
    }

    public boolean isUniversityExist(String universityName, UniversityList universityMasterList) {
        return universityMasterList.searchUniversity(universityName);
    }

    public Module searchForModule(String moduleCode, ModuleList moduleMasterList) {
        return moduleMasterList.getModule(moduleCode);
    }
}