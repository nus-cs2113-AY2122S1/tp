package seedu.duke.parser;

import seedu.duke.commands.AddUniCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;
import java.util.ArrayList;

@SuppressWarnings("checkstyle:WhitespaceAround")
public class AddUniCommandParser {

    public AddUniCommand parse(String arguments, UniversityList universityMasterList,
                               UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException {
        String universityName = arguments.trim();
        if (universityName.length() == 0) {
            throw new ParseException("no university given", 1);
        }
        if (!isUniversityExist(universityName, universityMasterList)) {
            throw new ParseException("university does not exist", 1);
        }

        ArrayList<ModuleMapping> list = new ArrayList<>();
        University university = new University(universityName, list);
        assert university.getName() != null;
        return new AddUniCommand(university, universitySelectedList, moduleSelectedList);
    }

    public boolean isUniversityExist(String universityName, UniversityList universityMasterList) {
        for (University university : universityMasterList.getList()) {
            if (universityName.equals(university.getName())) {
                return true;
            }
        }
        return false;
    }
}
