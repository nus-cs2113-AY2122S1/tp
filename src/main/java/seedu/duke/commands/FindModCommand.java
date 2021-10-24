package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindModCommand extends Command {

    public FindModCommand(String userInput, ModuleList moduleMasterList) {
        ArrayList<Module> result = (ArrayList<Module>) moduleMasterList.getList().stream()
                .filter((module) -> module.getModuleCode().toLowerCase().contains(userInput.toLowerCase()))
                .collect(Collectors.toList());
        if (result.size() == 0) {
            Ui.printFindModNull();
        } else {
            for (int i = 0; i < result.size(); i++) {
                Ui.printModule(result.get(i), result.get(i).getModuleIndex(moduleMasterList));
            }
        }
    }
}
