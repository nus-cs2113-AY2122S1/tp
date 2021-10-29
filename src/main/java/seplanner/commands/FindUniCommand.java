package seplanner.commands;

import seplanner.ui.UiInvalid;
import seplanner.ui.UiUniversity;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindUniCommand extends Command {

    public FindUniCommand(String userInput, UniversityList universityMasterList) {
        ArrayList<University> result = (ArrayList<University>) universityMasterList.getList().stream()
                .filter((university) -> university.getName().toLowerCase().contains(userInput.toLowerCase()))
                .collect(Collectors.toList());
        if (result.size() == 0) {
            UiInvalid.printFindUniNull();
        } else {
            for (University university : result) {
                UiUniversity.printUniversity(university, false);
            }
        }
    }

}
