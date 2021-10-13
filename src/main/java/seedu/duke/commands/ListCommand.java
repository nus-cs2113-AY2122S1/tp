package seedu.duke.commands;

import seedu.duke.enumerations.ListType;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand(ListType type, UniversityList universitySelectedList,
                       ModuleList moduleSelectedList) throws IOException {
        super(universitySelectedList, moduleSelectedList);
        switch (type) {
        case SELECTEDMODS:
            if (moduleSelectedList.getSize() == 0) {
                System.out.println("The module list is empty!");
            } else {
                assert moduleSelectedList.getSize() > 0;
                System.out.println("Here are the modules in your list:");
                for (int i = 0; i < moduleSelectedList.getSize(); i++) {
                    System.out.println("[" + (i + 1) + "] " + moduleSelectedList.get(i).getModuleCode());
                }
            }
            break;
        case SELECTEDUNIS:
            if (universitySelectedList.getSize() == 0) {
                System.out.println("The university list is empty!");
            } else {
                assert moduleSelectedList.getSize() > 0;
                System.out.println("Here are the universities in your list:");
                for (int i = 0; i < universitySelectedList.getSize(); i++) {
                    System.out.println("[" + (i + 1) + "] " + universitySelectedList.get(i).getName());
                }
            }
            break;
        case ALLUNIS:
            ArrayList<University> allUniversityList = Storage.readUniversitiesFromCsv();
            System.out.println("Here are all the universities available for you:");
            for (int i = 0; i < allUniversityList.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + allUniversityList.get(i).getName());
            }
            break;
        case ALLMODS:
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