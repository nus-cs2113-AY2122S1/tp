package seedu.duke.parser;

import seedu.duke.commands.RemoveCommand;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;

public class RemoveCommandParser {

    public RemoveCommand parse(String arguments, UniversityList universitySelectedList, ModuleList moduleSelectedList) throws ParseException {

        String type;
        String[] argumentsSubstrings = arguments.trim().split(" ", 2);

        if (argumentsSubstrings.length < 2) {
            throw new ParseException("No flags or description found.", 1);
        }
        String flag = argumentsSubstrings[0];
        String description = argumentsSubstrings[1].trim();

        switch (flag) {
        case "/u":
            if (!isUniversityExist(description, universitySelectedList)) {
                throw new ParseException("university not in list", 1);
            }
            type = "u";
            break;
        case "/m":
            if (!isModuleExist(description, moduleSelectedList)) {
                throw new ParseException("module does not exist", 1);
            }
            type = "m";
            break;
        default:
            throw new ParseException("Wrong flags passed", 1);
        }

        return new RemoveCommand(type, description, universitySelectedList, moduleSelectedList);
    }

    public boolean isUniversityExist(String universityName, UniversityList universitySelectedList) {
        for (University university : universitySelectedList.getList()) {
            if (universityName.equals(university.getName())) {
                return true;
            }
        }
        return false;
    }


    public boolean isModuleExist(String moduleCode, ModuleList moduleSelectedList) {
        for (int i = 0; i < moduleSelectedList.getSize(); i++) {
            if (moduleCode.equals(moduleSelectedList.get(i).getModuleCode())) {
                return true;
            }
        }
        return false;
    }

}
