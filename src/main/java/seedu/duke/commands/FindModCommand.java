package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindModCommand {

    public FindModCommand(String userInput, ModuleList moduleMasterList) {
        ArrayList<Module> result = (ArrayList<Module>) moduleMasterList.getList().stream()
                .filter((module) -> module.getModuleName().toLowerCase().contains(userInput.toLowerCase()))
                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) {
            Ui.printModule(result.get(i), i + 1);
        }
    }
}
