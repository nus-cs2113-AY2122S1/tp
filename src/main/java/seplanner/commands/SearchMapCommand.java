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
            for (University uni : universitySelectedList.getList()) {
                printMappings(uni, moduleSelectedList);
            }
        } else {
            printMappings(selectedUniversity, moduleSelectedList);
        }
    }

    public void printMappings(University uni, ModuleList moduleSelectedList) {
        assert uni.getName() != null;
        assert uni.getClass() != null;
        System.out.print("Potential mappings for ");
        System.out.print(uni.getName() + " ");
        Ui.printIndex(uni.getIndex(), false);
        System.out.println(":");
        uni.listSelectedMappings(moduleSelectedList);
    }

    public University getSelectedUniversity() {
        assert selectedUniversity.getName() != null;
        assert selectedUniversity.getClass() != null;
        return selectedUniversity;
    }

}