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

        String[] argumentsSubstrings = arguments.trim().split(" ", 3);
        if (argumentsSubstrings.length < 2) {
            throw new ParseException("not flags or index", 1);
        }
        String flag = argumentsSubstrings[0];
        int index = Integer.parseInt(argumentsSubstrings[1].trim());

        //
        switch (flag) {
        case Constants.FLAG_MODULE:
            index = Integer.parseInt(argumentsSubstrings[1].trim());
            if (index > moduleMasterList.getSize()) {
                throw new ParseException("module not found", 1);
            }
            return new AddModCommand(index, moduleSelectedList);
        case Constants.FLAG_UNIVERSITY:
            index = Integer.parseInt(argumentsSubstrings[1].trim());
            if (index > universityMasterList.getSize()) {
                throw new ParseException("university not found", 1);
            }
            return new AddUniCommand(index, universitySelectedList);
        case Constants.FLAG_MAP:
            int uniIndex = Integer.parseInt(argumentsSubstrings[1]);
            if (argumentsSubstrings.length < 3) {
                throw new ParseException("missing argument", 1);
            }
            int mapIndex = Integer.parseInt(argumentsSubstrings[3].trim());
            return new AddMapCommand(index, universitySelectedList);
            break;
        default:
            throw new ParseException("flag not found", 1);
        }
    }
}
