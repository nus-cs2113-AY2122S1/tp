package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.SelectedModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand(String type) throws IOException {
        switch (type) {
        case "t":
            if (selectedModuleList.getSize() == 0) {
                System.out.println("The module list is empty!");
            } else {
                System.out.println("Here are the modules in your list:");
                for (int i = 0; i < selectedModuleList.getSize(); i++) {
                    System.out.println("[" + (i + 1) + "] " + selectedModuleList.get(i).getModuleCode());
                }
            }
            break;
        case "s":
            if (universityList.getSize() == 0) {
                System.out.println("The university list is empty!");
            } else {
                System.out.println("Here are the universities in your list:");
                for (int i = 0; i < universityList.getSize(); i++) {
                    System.out.println("[" + (i + 1) + "] " + universityList.get(i).getName());
                }
            }
            break;
        case "u":
            ArrayList<University> allUniversityList = Storage.readUniversitiesFromCsv();
            System.out.println("Here are all the universities available for you:");
            for (int i = 0; i < allUniversityList.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + allUniversityList.get(i).getName());
            }
            break;
        case "m":
            ArrayList<Module> allModuleList = Storage.readModulesFromCsv();
            System.out.println("Here are all the modules available for you:");
            for (int i = 0; i < allModuleList.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + allModuleList.get(i).getModuleCode());
            }
            break;
        default:
            break;
        }
    }
}