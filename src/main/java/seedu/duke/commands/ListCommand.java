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
            showSelectedMods(moduleSelectedList);
            break;
        case SELECTEDUNIS:
            showSelectedUnis(universitySelectedList, moduleSelectedList);
            break;
        case ALLUNIS:
            showAllUnis();
            break;
        case ALLMODS:
            showAllMods();
            break;
        default:
            break;
        }
    }

    private void showAllMods() throws IOException {
        ArrayList<Module> allModuleList = Storage.loadModules();
        System.out.println("Here are all the modules available for you:");
        assert allModuleList.size() > 0;
        for (int i = 0; i < allModuleList.size(); i++) {
            assert allModuleList.get(i).getModuleCode() != null;
            Ui.printModule(allModuleList.get(i), i + 1);
        }
    }

    private void showAllUnis() throws IOException {
        UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
        System.out.println("Here are all the universities available for you:");
        assert universityMasterList.getSize() > 0;
        for (int i = 0; i < universityMasterList.getSize(); i++) {
            assert universityMasterList.get(i).getName() != null;
            Ui.printUniversity(universityMasterList.get(i), i + 1, universityMasterList);
        }
    }

    private void showSelectedUnis(UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws IOException {
        UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
        if (universitySelectedList.getSize() == 0) {
            assert moduleSelectedList.getSize() == 0;
            System.out.println("The university list is empty!");
        } else {
            assert moduleSelectedList.getSize() > 0;
            getUnisAndMods(universitySelectedList);
        }
    }

    private void getUnisAndMods(UniversityList universitySelectedList) throws IOException {
        UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
        System.out.println("Here are the universities and module mappings in your list:");
        for (int i = 0; i < universitySelectedList.getSize(); i++) {
            assert universitySelectedList.get(i).getName() != null;
            showUnisAndMods(i + 1, universitySelectedList.get(i), universityMasterList);
        }
    }

    private void showUnisAndMods(int index, University university, UniversityList universityMasterList) {
        Ui.printUniversity(university, index, universityMasterList);
        university.listMappings();
    }

    private void showSelectedMods(ModuleList moduleSelectedList) {
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
    }
}