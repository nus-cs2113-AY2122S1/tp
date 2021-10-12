package seedu.duke.parser;

import seedu.duke.commands.SearchMapCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;

public class SearchMapCommandParser {

    public SearchMapCommand parse(String arguments, UniversityList universityMasterList,
                                  UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException {
        String universityName = arguments.trim();
        if (universityName.length() == 0) {
            throw new ParseException("no description given", 1);
        }

        University university = searchForUniversity(universityName, universityMasterList);
        if (university == null) {
            throw new ParseException("university does not exist", 1);
        }
        return new SearchMapCommand(university, universitySelectedList, moduleSelectedList);
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
