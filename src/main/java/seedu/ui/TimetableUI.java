package seedu.ui;

import seedu.module.Module;
import seedu.timetable.TimetableItem;
import seedu.timetable.TimetableLesson;

import java.util.List;
import java.util.Objects;

public class TimetableUI {

    public enum LineType {
        TITLE, TYPE, VENUE,
    }

    private static final int TIME_MULTIPLIER = 100;
    private static final String FIXED_LENGTH_FORMAT = "%-16.16s";
    private static final int    FIXED_LENGTH = 16;
    private static final String FIXED_TIME_FORMAT = "%04d";
    private static final String DIVIDER = "----------------";
    private static final String MODULES_HEADER = "Modules taken this semester: \n";
    private static final String CREDIT_COUNT_HEADER = "\nTotal MCs taken this semester: ";
    private static final String STAR_DIVIDER = "\n*******************";
    private static int sameCounter = 0;

    /**
     * Prints the list of modules taken in the timetable, and the total number of
     * MCs.
     * 
     * @param modules the list of modules taken in the timetable
     */
    public static void printModules(List<Module> modules, int semester) {
        double counter = 0;
        System.out.println(STAR_DIVIDER);
        System.out.println(MODULES_HEADER);
        for (Module module : modules) {
            System.out.println(module + " " + module.getExam(semester));
            counter += module.getModuleCredit();
        }

        System.out.println(CREDIT_COUNT_HEADER + counter);
        System.out.println(STAR_DIVIDER);
    }

    /**
     * Displays the first row in the timetable grid starting from the earliest hour
     * in the timetable to the last.
     * 
     * @param start the starting hour of the timetable
     * @param end   the last hour of the timetable
     */
    public static void printScheduleHours(int start, int end) {
        String infoLine = String.format(FIXED_LENGTH_FORMAT,"");
        String time;
        for (int u = start; u <= end; u++) {
            time = String.format(FIXED_TIME_FORMAT, u * TIME_MULTIPLIER);
            infoLine =  infoLine.concat(String.format(FIXED_LENGTH_FORMAT, time));
        }
        System.out.println(infoLine);
    }

    /**
     * Displays the full-day schedule for a specific day. Prints three lines :
     * Module Code, Lesson Type, and Venue.
     * 
     * @param day      The day to be printed (Monday/Tuesday/Wednesday... etc.)
     * @param schedule The schedule to be printed for that day
     * @param start    the earliest hour that has any activity
     * @param end      the last hour that has any activity
     */
    public static void printDaySchedule(String day, TimetableItem[] schedule, int start, int end,
                                        boolean showUserItemsOnly) {
        TimetableItem[] displaySchedule = schedule;
        if (showUserItemsOnly) {
            displaySchedule = getUserItems(schedule);
        }
        for (int u = start; u <= end; u++) {
            System.out.print(DIVIDER);
        }
        System.out.println();
        printLine(day, displaySchedule, start, end, LineType.TITLE);
        printLine(day, displaySchedule, start, end, LineType.TYPE);
        printLine(day, displaySchedule, start, end, LineType.VENUE);

    }

    public static TimetableItem[] getUserItems(TimetableItem[] schedule) {
        TimetableItem[] userItemSchedule = schedule.clone();
        for (int i = 0; i < userItemSchedule.length; i++) {
            if (userItemSchedule[i] instanceof TimetableLesson) {
                userItemSchedule[i] = null;
            }
        }
        return userItemSchedule;
    }

    private static void printLine(String day, TimetableItem[] schedule, int start, int end, LineType type) {
        String infoLine = addHeader(day, type);
        TimetableItem prevTimetableItem = null;
        for (int i = start; i <= end; i++) {
            TimetableItem timetableItem = schedule[i];
            infoLine = infoLine.concat(addInfoToString(timetableItem,prevTimetableItem, type));
            prevTimetableItem = timetableItem;
        }
        System.out.println(infoLine);
    }

    private static String addHeader(String day, LineType type) {
        String str = "";
        if (type.equals(LineType.TYPE)) {
            str = String.format("%8s",day);
        }
        return String.format(FIXED_LENGTH_FORMAT,str);
    }

    private static String addInfoToString(TimetableItem timetableItem,
            TimetableItem prevItem, LineType type) {
        String str = "";
        if (!Objects.equals(timetableItem, prevItem)) {
            sameCounter = 0;
            str = "|   ";
            switch (type) {
            case TITLE:
                str += addTitle(timetableItem);
                break;
            case TYPE:
                str += addItemType(timetableItem);
                break;
            case VENUE:
                str += addVenue(timetableItem);
                break;
            default:
                str += "";
            }
        } else if (type == LineType.TITLE) {
            sameCounter += 1;
            str += addTitle(timetableItem);
        }
        return String.format(FIXED_LENGTH_FORMAT,str);
    }

    private static String addTitle(TimetableItem timetableItem) {
        String str = "";
        if (timetableItem != null) {
            String title = timetableItem.getTitle();
            int startIndex = sameCounter > 0 ? (sameCounter * FIXED_LENGTH) - 4 : 0;
            if (title.length() > startIndex) {
                str += title.substring(startIndex);
            }
        }
        return str;
    }



    private static String addItemType(TimetableItem timetableItem) {
        String str = "";
        if (timetableItem != null) {
            str += timetableItem.getType();
        }
        return str;
    }

    private static String addVenue(TimetableItem timetableItem) {
        String str = "";
        if (timetableItem != null) {
            String venue = timetableItem.getVenue();
            str = venue == null ? "" : venue;
        }
        return str;
    }

    /*------------- Timetable Storage ----------- */
    // TODO ADD UI TO SAVE AND LOAD TIMETABLE

}
