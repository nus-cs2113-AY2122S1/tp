package seedu.ui;

import seedu.module.Module;
import seedu.timetable.TimetableLesson;

import java.util.List;
import java.util.Objects;

public class TimetableUI {

    public enum LineType {
      CODE,
      LESSONTYPE,
      VENUE,
    }

    private static final String FIXED_LENGTH_FORMAT = "%-16.16s";
    private static final String DIVIDER = "----------------";
    private static final String MODULES_HEADER = "Modules taken this semester: \n";
    private static final String CREDIT_COUNT_HEADER = "\nTotal MCs taken this semester: ";
    private static final String STAR_DIVIDER = "\n*******************";

    public static void printModules(List<Module> modules) {
        double counter = 0;
        System.out.println(STAR_DIVIDER);
        System.out.println(MODULES_HEADER);
        for (Module module : modules) {
            System.out.println(module);
            counter += module.getModuleCredit();
        }

        System.out.println(CREDIT_COUNT_HEADER + counter);
        System.out.println(STAR_DIVIDER);
    }

    public static void printScheduleHours(int start, int end) {
        String infoLine = "\t\t\t\t";
        for (int u = start; u <= end; u++) {
            infoLine += String.format(FIXED_LENGTH_FORMAT, u + "00");
        }
        System.out.println(infoLine);
    }

    public static void printDaySchedule(String day, TimetableLesson[] schedule, int start, int end) {
        for (int u = start; u <= end; u++) {
            System.out.print(DIVIDER);
        }
        System.out.println();
        printLine(day, schedule, start, end, LineType.CODE);
        printLine(day, schedule, start, end, LineType.LESSONTYPE);
        printLine(day, schedule, start, end, LineType.VENUE);

    }

    private static void printLine(String day, TimetableLesson[] schedule, int start, int end, LineType type) {
        String infoLine = addHeader(day, type);
        TimetableLesson prevTimetableLesson = null;
        for (int i = start; i <= end; i++) {
            TimetableLesson modTimetableLesson = schedule[i];
            infoLine += addInfoToString(modTimetableLesson, prevTimetableLesson, type);
            prevTimetableLesson = modTimetableLesson;
        }
        System.out.println(infoLine);
    }

    private static String addHeader(String day, LineType type) {
        if (type.equals(LineType.LESSONTYPE)) {
            return "\t\t" + day + "\t\t";
        } else {
            return "\t\t\t\t";
        }
    }

    private static String addInfoToString(TimetableLesson lesson,
                                          TimetableLesson prevLesson, LineType type) {
        String str = "";
        if (!Objects.equals(lesson, prevLesson)) {
            str = "|   ";
            switch (type) {
            case CODE:
                str += addModuleCode(lesson);
                break;
            case LESSONTYPE:
                str += addLessonType(lesson);
                break;
            case VENUE:
                str += addVenue(lesson);
                break;
            default:
                str += "";
            }
        }
        return String.format(FIXED_LENGTH_FORMAT,str);
    }

    private static String addModuleCode(TimetableLesson modTimetableLesson) {
        String str = "";
        if (modTimetableLesson != null) {
            str = modTimetableLesson.getModuleCode();
        }
        return str;
    }

    private static String addLessonType(TimetableLesson modTimetableLesson) {
        String str = "";
        if (modTimetableLesson != null) {
            str += modTimetableLesson.getLessonType().toString();
            str += "[" + modTimetableLesson.getClassNo() + "]";
        }
        return str;
    }

    private static String addVenue(TimetableLesson modTimetableLesson) {
        String str = "";
        if (modTimetableLesson != null) {
            str = modTimetableLesson.getVenue();
        }
        return str;
    }



    /*------------- Timetable Storage ----------- */
    //TODO ADD UI TO SAVE AND LOAD TIMETABLE

}
