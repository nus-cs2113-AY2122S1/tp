package seedu.duke.parser;

import seedu.duke.commands.AddModCommand;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddModCommandParser {

    private Logger logger = Logger.getLogger("AddModCommandParserLog");

    public AddModCommand parse(String arguments, ModuleList moduleMasterList,
                               UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException, IOException {

        logger.log(Level.INFO, "Start parsing addmod command");

        String moduleCode = arguments.trim();
        if (moduleCode.length() == 0) {
            logger.log(Level.WARNING, "no arguments given");
            throw new ParseException("no module give", 1);
        }
        Module module = searchForModule(moduleCode, moduleMasterList);
        if (module == null) {
            logger.log(Level.WARNING, "module not found");
            throw new ParseException("module does not exist", 1);
        }

        assert module.getModuleCode() != null;
        logger.log(Level.INFO, "parse success");

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
