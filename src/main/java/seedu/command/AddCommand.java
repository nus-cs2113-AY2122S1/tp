package seedu.command;

import seedu.command.flags.AddFlag;
import seedu.comparator.ClassNumComparator;
import seedu.exceptions.AddException;
import seedu.exceptions.FetchException;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.module.Semester;
import seedu.online.NusMods;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableUserItem;
import seedu.ui.AddUI;
import seedu.ui.TextUi;
import seedu.exceptions.ModuleExistException;
import java.util.ArrayList;
import java.util.Collections;

public class AddCommand extends Command {
    private static final String LECTURE = "Lecture";
    private static final String TUTORIAL = "Tutorial";
    private static final String LAB = "Laboratory";
    private static final String FIRST_QN = "Description of Event (E.g. Read Micah): ";
    private static final String SECOND_QN = "Date of Event (E.g. Monday): ";
    private static final String THIRD_QN = "Starting time of Event (E.g. 1600): ";
    private static final String FOURTH_QN = "Ending time of Event (E.g. 1800): ";
    private static final String FIFTH_QN = "Location of Event (Optional): ";
    private final int semester;
    private final Timetable timetable;
    private final AddFlag flag;
    public AddUI addUI = new AddUI();

    public static final String commandSyntax = "add";
    public static final String commandAction = "Add modules or tasks to the Timetable";

    public AddCommand(Timetable timetable, AddFlag flag) {
        this.semester = timetable.getSemester();
        this.timetable = timetable;
        this.flag = flag;
    }

    public void execute() throws FetchException, ModuleExistException, AddException {
        if (getFlag() == AddFlag.LESSON) {
            Module module;
            String moduleCode = addUI.getModuleCode();
            module = NusMods.fetchModOnline(moduleCode);
            checkModuleExist(module);
            TextUi.printAddMessage(moduleCode);

            Semester semesterData = module.getSemester(semester);
            ArrayList<Lesson> lecture;
            lecture = getLessonDetails(semesterData.getTimetable(), LECTURE);

            ArrayList<Lesson> tutorial;
            tutorial = getLessonDetails(semesterData.getTimetable(), TUTORIAL);

            ArrayList<Lesson> laboratory;
            laboratory = getLessonDetails(semesterData.getTimetable(), LAB);

            addUI.printLessonDetails(lecture, tutorial, laboratory, timetable, module);
        } else if (getFlag() == AddFlag.EVENT) {
            String description = addUI.getReply(FIRST_QN).trim();
            if (isValidDescription(description)) {
                throw new AddException("Description is Empty, please enter something valid");
            }

            String date = addUI.getReply(SECOND_QN).trim();
            if (isValidDate(date)) {
                throw new AddException("Invalid Date Format (Format: Monday)");
            }

            String startTime = addUI.getReply(THIRD_QN).trim();
            try {
                Integer.parseInt(startTime);
            } catch (NumberFormatException e) {
                throw new AddException("Invalid Start Time Entered (Format: 1600)");
            }
            if (isValidTime(startTime)) {
                throw new AddException("Invalid Start Time Entered (Format: 1600)");
            }

            String endTime = addUI.getReply(FOURTH_QN).trim();
            try {
                Integer.parseInt(endTime);
            } catch (NumberFormatException e) {
                throw new AddException("Invalid End Time Entered (Format: 1600)");
            }
            if (isValidTime(endTime)) {
                throw new AddException("Invalid End Time Entered (Format: 1600)");
            }

            if (isEndBeforeStart(startTime, endTime)) {
                throw new AddException("Invalid Input, End Time is earlier than Start Time");
            }

            String location = addUI.getReply(FIFTH_QN).trim();

            TimetableUserItem event = new TimetableUserItem(description, date, startTime, endTime, location);
            if (timetable.isConflict(event)) {
                throw new AddException("Selected timeslot is occupied, please delete before proceeding");
            }

            timetable.addEvent(event.getDayOfWeek(), event);
            timetable.addToEvents(event);

            addUI.printEventMessage(event, date);
        }
    }

    public ArrayList<Lesson> getLessonDetails(ArrayList<Lesson> lessons, String lessonType) {
        ArrayList<Lesson> completeList = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getLessonType().equals(lessonType)) {
                completeList.add(lesson);
            } else if (lessonType == LECTURE) {
                if (lesson.getLessonType() != TUTORIAL && lesson.getLessonType() != LAB) {
                    completeList.add(lesson);
                }
            }
        }
        Collections.sort(completeList, new ClassNumComparator());
        return completeList;
    }

    public void checkModuleExist(Module mod) throws ModuleExistException {
        for (Module module : timetable.getModules()) {
            String moduleCode = module.getModuleCode();
            if (moduleCode.equals(mod.getModuleCode())) {
                throw new ModuleExistException("Module currently already exist in your timetable");
            }
        }
    }

    public AddFlag getFlag() {
        return flag;
    }

    public String parseDate(String input) {
        switch (input) {
        case "Monday":
            return "Monday";
        case "Tuesday":
            return "Tuesday";
        case "Wednesday":
            return "Wednesday";
        case "Thursday":
            return "Thursday";
        case "Friday":
            return "Friday";
        case "Saturday":
            return "Saturday";
        case "Sunday":
            return "Sunday";
        default:
            return "Invalid";
        }
    }

    public boolean isValidDescription(String input) {
        return !(input.length() > 0);
    }

    public boolean isValidDate(String input) {
        return parseDate(input).equals("Invalid");
    }

    public boolean isValidTime(String input) {
        return Integer.parseInt(input) < 0 || Integer.parseInt(input) >= 2400;
    }

    public boolean isEndBeforeStart(String startTime, String endTime) {
        return Integer.parseInt(startTime) >= Integer.parseInt(endTime);
    }

}
