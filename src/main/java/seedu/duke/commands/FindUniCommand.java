package seedu.duke.commands;

import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindUniCommand extends Command {

    public FindUniCommand(String userInput, UniversityList universityMasterList) {
        ArrayList<University> result = (ArrayList<University>) universityMasterList.getList().stream()
                .filter((university) -> university.getName().toLowerCase().contains(userInput.toLowerCase()))
                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) {
            Ui.printUniversity(result.get(i), i + 1, universityMasterList);
        }
    }

}
