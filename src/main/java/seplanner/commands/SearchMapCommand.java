package seplanner.commands;

import seplanner.modules.ModuleList;
import seplanner.ui.Ui;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

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