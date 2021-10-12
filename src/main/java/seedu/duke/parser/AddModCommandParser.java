package seedu.duke.parser;

import seedu.duke.commands.AddModCommand;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;
import java.util.ArrayList;

public class AddModCommandParser {

    public AddModCommand parse(String arguments, ModuleList moduleMasterList,
                               UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException {
        String moduleCode = arguments.trim();
        if (moduleCode.length() == 0) {
            throw new ParseException("no module give", 1);
        }
        Module module = searchForModule(moduleCode, moduleMasterList);
        if (module == null) {
            throw new ParseException("module does not exist", 1);
        }
        return new AddModCommand(module, universitySelectedList, moduleSelectedList);
    }

    public Module searchForModule(String moduleCode, ModuleList moduleMasterList) {
        for (int i = 0; i < moduleMasterList.getSize(); i++) {
            if (moduleCode.equals(moduleMasterList.get(i).getModuleCode())) {
                return moduleMasterList.get(i);
            }
        }
        return null;
    }
}
