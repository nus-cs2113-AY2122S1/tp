package seedu.duke.parser;

import seedu.duke.commands.AddmodCommand;
import seedu.duke.modules.Module;

import java.text.ParseException;
import java.util.ArrayList;

public class AddmodCommandParser {

    public AddmodCommand parse(String arguments, ArrayList<Module> moduleMasterList) throws ParseException {
        String moduleCode = arguments.trim();
        if (moduleCode.length() == 0) {
            throw new ParseException("no module give", 1);
        }
        Module module = searchForModule(moduleCode, moduleMasterList);
        if (module == null) {
            throw new ParseException("module does not exist", 1);
        }
        return new AddmodCommand(module);
    }

    private Module searchForModule(String moduleCode, ArrayList<Module> moduleMasterList) {
        for (Module module : moduleMasterList) {
            if (moduleCode.equals(module.getModuleCode())) {
                return module;
            }
        }
        return null;
    }
}
