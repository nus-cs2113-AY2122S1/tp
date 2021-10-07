package seedu.duke.parser;

import seedu.duke.commands.SearchmapCommand;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;
import java.util.ArrayList;

public class SearchmapCommandParser {

    public SearchmapCommand parse(String arguments, UniversityList universityMasterList) throws ParseException {
        String universityName = arguments.trim();
        if (universityName.length() == 0) {
            throw new ParseException("no description given", 1);
        }

        University university = searchForUniversity(universityName, universityMasterList);
        return new SearchmapCommand(university);
    }

    private University searchForUniversity(String universityName, UniversityList universityMasterList) {
        for (University university : universityMasterList.getList()) {
            if (universityName.equals(university.getName())) {
                return university;
            }
        }
        return null;
    }
}
