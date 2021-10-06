package seedu.duke.parser;

import seedu.duke.commands.AdduniCommand;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;
import java.util.ArrayList;

@SuppressWarnings("checkstyle:WhitespaceAround")
public class AdduniCommandParser {

    public AdduniCommand parse(String arguments, UniversityList universityList) throws ParseException {
        String universityName = arguments.trim();
        if (universityName.length() == 0) {
            throw new ParseException("no university given", 1);
        }
        if (!isUniversityExist(universityName, universityList)) {
            throw new ParseException("university does not exist", 1);
        }

        ArrayList<ModuleMapping> list = new ArrayList<>();
        University university = new University(universityName, list);
        return new AdduniCommand(university);
    }

    private boolean isUniversityExist(String universityName, UniversityList universityList) {
        for (University university : universityList.getList()) {
            if (universityName.equals(university.getName())) {
                return true;
            }
        }
        return false;
    }
}
