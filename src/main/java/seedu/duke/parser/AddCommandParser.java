package seedu.duke.parser;

import seedu.duke.commands.AddMapCommand;
import seedu.duke.commands.AddModCommand;
import seedu.duke.commands.AddUniCommand;
import seedu.duke.commands.Command;
import seedu.duke.constants.Constants;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;

public class AddCommandParser {

    public Command parse(String arguments, UniversityList universityMasterList, ModuleList moduleMasterList,
                         UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException, IOException {

        String[] argumentsSubstrings = arguments.trim().split(" ", 2);
        if (argumentsSubstrings.length < 2) {
            throw new ParseException("not flags or index", 1);
        }
        String flag = argumentsSubstrings[0];
        int index = Integer.parseInt(argumentsSubstrings[1].trim());

        switch (flag) {
        case Constants.FLAG_MODULE:
            return new AddModCommand(index, moduleSelectedList);
        case Constants.FLAG_UNIVERSITY:
            return new AddUniCommand(index, universitySelectedList);
        case Constants.FLAG_MAP:
            return new AddMapCommand(index, universitySelectedList);
            break;
        default:
            throw new ParseException("flag not found", 1);
        }
    }
}
