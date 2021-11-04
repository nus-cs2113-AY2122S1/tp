package seplanner.commands;

import seplanner.modules.ModuleList;
import seplanner.ui.Ui;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

public class SearchMapCommand extends Command {

    private final University selectedUniversity;

    public SearchMapCommand(University selectedUniversity, UniversityList universitySelectedList,
                            ModuleList moduleSelectedList, boolean isAll) {
        assert selectedUniversity.getName() != null;
        assert selectedUniversity.getClass() != null;
        this.selectedUniversity = selectedUniversity;
        if (isAll) {
            System.out.println("Here are all the potential module mappings based on your selected universities:");
            for (University uni : universitySelectedList.getList()) {
                System.out.print("Potential mappings for ");
                System.out.print(uni.getName() + " ");
                Ui.printIndex(uni.getIndex(), false);
                System.out.println(":");
                uni.listSelectedMappings(moduleSelectedList);
            }
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