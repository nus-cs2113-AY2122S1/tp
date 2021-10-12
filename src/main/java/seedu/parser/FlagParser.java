package seedu.parser;

import seedu.command.flags.SearchFlags;
import seedu.ui.TextUi;


public class FlagParser {

    private static final String QUICK_FLAG = "q";
    private static final String LEVEL_FLAG = "l";
    private static final String MC_FLAG = "mc";
    private static final String FACULTY_FLAG = "f";
    private static final String EXAM_FLAG = "e";
    private static final String DEPARTMENT_FLAG = "d";
    private static final String SEMESTER_FLAG = "s";
    private static final int MC_LENGTH = 2;

    public static SearchFlags parseSearchFlags(String[] flags) {
        SearchFlags searchFlags = new SearchFlags();
        for (String flag : flags) {
            checkAllFlags(flag, searchFlags);
        }
        return searchFlags;
    }

    private static void checkAllFlags(String flag, SearchFlags searchFlags) {
        flag = flag.trim();
        if (flag.length() < 1) {
            return;
        }
        if (isQuickFlag(flag, searchFlags)) {
            return;
        }
        if (isMcFlag(flag, searchFlags)) {
            return;
        }
        checkRemainingFlags(flag, searchFlags);
    }

    private static boolean isQuickFlag(String flag, SearchFlags searchFlags) {
        if (QUICK_FLAG.equals(flag)) {
            searchFlags.setHasQuickFlag(true);
            return true;
        }
        return false;
    }

    private static boolean isMcFlag(String flag, SearchFlags searchFlags) {
        if (flag.startsWith(MC_FLAG)) {
            flag = flag.substring(MC_LENGTH).trim();
            int mcs = tryParseInt(flag);
            System.out.println(mcs);
            searchFlags.setHasMcFlag(true);
            searchFlags.setMcs(mcs);
            return true;
        }
        return false;
    }

    private static int tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            TextUi.printErrorMessage();
        }
        return -1;
    }

    private static void checkRemainingFlags(String flag, SearchFlags searchFlags) {
        assert flag.length() >= 1 : "Flag should not be empty";
        String flagLetter = flag.substring(0, 1);
        String flagTerm = flag.substring(1).trim();
        switch (flagLetter) {
        case LEVEL_FLAG:
            searchFlags.setHasLevelFlag(true);
            searchFlags.setLevel(tryParseInt(flagTerm));
            break;
        case FACULTY_FLAG:
            searchFlags.setHasFacultyFlag(true);
            searchFlags.setFaculty(flagTerm);
            break;
        case DEPARTMENT_FLAG:
            searchFlags.setHasDepartmentFlag(true);
            searchFlags.setDepartment(flagTerm);
            break;
        case EXAM_FLAG:
            searchFlags.setHasExamFlag(true);
            searchFlags.setHasExam(Boolean.parseBoolean(flagTerm));
            break;
        case SEMESTER_FLAG:
            searchFlags.setHasSemesterFlag(true);
            searchFlags.setSemester(tryParseInt(flagTerm));
            break;
        default:
            break;
        }
    }

}
