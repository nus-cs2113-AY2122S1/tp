package seedu.duke.commands;

import seedu.duke.enumerations.ListType;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand(ListType type, UniversityList universitySelectedList,
                       ModuleList moduleSelectedList) throws IOException {
        super(universitySelectedList, moduleSelectedList);
        assert type != null;
        switch (type) {
        case SELECTEDMODS:
            if (moduleSelectedList.getSize() == 0) {
                assert moduleSelectedList.getSize() == 0;
                System.out.println("The module list is empty!");
            } else {
                assert moduleSelectedList.getSize() > 0;
                System.out.println("Here are the modules in your list:");
                for (int i = 0; i < moduleSelectedList.getSize(); i++) {
                    assert moduleSelectedList.get(i).getModuleCode() != null;
                    Ui.printModule(moduleSelectedList.get(i), i + 1);
                }
            }
            break;
        case SELECTEDUNIS:
            if (universitySelectedList.getSize() == 0) {
                assert moduleSelectedList.getSize() == 0;
                System.out.println("The university list is empty!");
            } else {
                assert moduleSelectedList.getSize() > 0;
                System.out.println("Here are the universities in your list:");
                for (int i = 0; i < universitySelectedList.getSize(); i++) {
                    assert universitySelectedList.get(i).getName() != null;
                    Ui.printUniversity(universitySelectedList.get(i), i + 1);
                }
            }
            break;
        case ALLUNIS:
            ArrayList<University> allUniversityList = Storage.loadUniversities();
            System.out.println("Here are all the universities available for you:");
            assert allUniversityList.size() > 0;
            for (int i = 0; i < allUniversityList.size(); i++) {
                assert allUniversityList.get(i).getName() != null;
                Ui.printUniversity(allUniversityList.get(i), i + 1);
            }
            break;
        case ALLMODS:
            ArrayList<Module> allModuleList = Storage.loadModules();
            System.out.println("Here are all the modules available for you:");
            assert allModuleList.size() > 0;
            for (int i = 0; i < allModuleList.size(); i++) {
                assert allModuleList.get(i).getModuleCode() != null;
                Ui.printModule(allModuleList.get(i), i + 1);
            }
            break;
        default:
            break;
        }
    }
}