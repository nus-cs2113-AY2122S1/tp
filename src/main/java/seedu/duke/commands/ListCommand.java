package seedu.duke.commands;

import seedu.duke.Classes.*;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand(String type) {
        switch (type) {
        case "m":
            if (selectedModuleList.getSize() == 0) {
                System.out.println("The module list is empty!");
            } else {
                System.out.println("Here are the modules in your list:");
                for (int i = 0; i < selectedModuleList.getSize(); i++) {
                    System.out.println("[" + (i + 1) + "] " + selectedModuleList.get(i).getModuleCode());
                }
            }
            break;
        case "u":
            if (universityList.getSize() == 0) {
                System.out.println("The university list is empty!");
            } else {
                System.out.println("Here are the universities in your list:");
                for (int i = 0; i < universityList.getSize(); i++) {
                    System.out.println("[" + (i + 1) + "] " + universityList.get(i).getName());
                }
            }
            break;
        //case "s":
        //case "t":
        }
    }
}