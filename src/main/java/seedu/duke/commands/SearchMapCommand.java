package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

public class SearchMapCommand extends Command {

    private final University selectedUniversity;

    public SearchMapCommand(University selectedUniversity, UniversityList universitySelectedList,
                            ModuleList moduleSelectedList) {
        assert selectedUniversity.getName() != null;
        assert selectedUniversity.getClass() != null;
        this.selectedUniversity = selectedUniversity;
        if (selectedUniversity.getSelectedMappings(moduleSelectedList).isEmpty()) {
            System.out.println("There is no potential mappings for " + selectedUniversity.getName());
        } else {
            System.out.print("Potential mappings for ");

            System.out.print(selectedUniversity.getName() + " ");
            Ui.printIndex(selectedUniversity.getIndex(), false);
            System.out.println(":");
            selectedUniversity.listSelectedMappings(moduleSelectedList);
        }
    }

    public University getSelectedUniversity() {
        assert selectedUniversity.getName() != null;
        assert selectedUniversity.getClass() != null;
        return selectedUniversity;
    }

}