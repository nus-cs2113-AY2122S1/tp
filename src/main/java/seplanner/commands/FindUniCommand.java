package seplanner.commands;

import seplanner.ui.UiInvalid;
import seplanner.ui.UiUniversity;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Command to find a university in the master module list.
*/
public class FindUniCommand extends Command {

    /**
     * Find a university in the master university list, and print its information.
     *
     * @param userInput input from the user, which contains the university name.
     * @param universityMasterList The master university list which contains all universities.
    */
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
