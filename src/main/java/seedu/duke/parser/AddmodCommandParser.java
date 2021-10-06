package seedu.duke.parser;

import seedu.duke.commands.AddmodCommand;
import seedu.duke.modules.Module;

import java.text.ParseException;

public class AddmodCommandParser implements Parser<AddmodCommand> {

    @Override
    public AddmodCommand parse(String arguments) throws ParseException {
        String moduleCode = arguments.trim();
        if (moduleCode.length() == 0) {
            throw new ParseException("no module give", 1);
        }
        String moduleName = "";
        int moduleCredits = 0;
        Module module = new Module(moduleCode, moduleName, moduleCredits);
        return new AddmodCommand(module);
    }
}
