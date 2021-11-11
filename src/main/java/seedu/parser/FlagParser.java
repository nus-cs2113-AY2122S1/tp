package seedu.parser;

import seedu.command.flags.SearchFlags;

import static seedu.command.flags.SearchFlags.INVALID_FLAGS;
import static seedu.command.flags.SearchFlags.INVALID_MC;
import static seedu.command.flags.SearchFlags.MC_LENGTH;

public class FlagParser {

    private static final String QUICK_FLAG = "q";
    private static final String LEVEL_FLAG = "l";
    private static final String MC_FLAG = "mc";
    private static final String FACULTY_FLAG = "f";
    private static final String EXAM_FLAG = "e";
    private static final String DEPARTMENT_FLAG = "d";
    private static final String SEMESTER_FLAG = "s";

    /**
     * Converts a String array of flags and regexes into a SearchFlags object.
     *
     * @param flags String array containing each flag and its regex.
     * @return SearchFlags, an object that contains all flags and regexes.
     */
    public static SearchFlags parseSearchFlags(String[] flags) {
        SearchFlags searchFlags = new SearchFlags();
        for (String flag : flags) {
            checkAllFlags(flag, searchFlags);
        }
        return searchFlags;
    }

    /**
     * Attempts to identify the given flag, and update the given SearchFlags object if identified.
     *
     * @param flag Flag to identify.
     * @param searchFlags SearchFlags object to update.
     */
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

    /**
     * Checks if given flag is a Quick flag (-q). Updates searchFlag if true.
     *
     * @param flag Flag to check.
     * @param searchFlags SearchFlags object to update.
     * @return true if flag is a Quick flag, false otherwise.
     */
    private static boolean isQuickFlag(String flag, SearchFlags searchFlags) {
        if (QUICK_FLAG.equals(flag)) {
            searchFlags.setHasQuickFlag(true);
            return true;
        }
        return false;
    }

    /**
     * Checks if given flag is a MC flag (-mc). Updates searchFlag if true.
     *
     * @param flag Flag to check.
     * @param searchFlags SearchFlags object to update.
     * @return true if flag is a MC flag, false otherwise.
     */
    private static boolean isMcFlag(String flag, SearchFlags searchFlags) {
        if (flag.startsWith(MC_FLAG)) {
            flag = flag.substring(MC_LENGTH).trim();
            int mcs = tryParseInt(flag);
            if (mcs == -1) {
                searchFlags.setErrorFlag(INVALID_MC);
            }
            searchFlags.setHasMcFlag(true);
            searchFlags.setMcs(mcs);
            return true;
        }
        return false;
    }

    /**
     * Tries to parse a String to an int. Will return -1 if String cannot be parsed into int.
     *
     * @param str String to be parsed.
     * @return the String parsed into an int, -1 if invalid.
     */
    private static int tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Checks the given flag to see if it matches all remaining flags (level, faculty, department, exam and semester),
     * and updates the SearchFlags object.
     *
     * @param flag Flag to be checked.
     * @param searchFlags SearchFlags object to update.
     */
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
            searchFlags.setErrorFlag(INVALID_FLAGS);
            break;
        }
    }

}
