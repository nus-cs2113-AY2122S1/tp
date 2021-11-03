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
            module = NusMods.fetchMod(moduleCode);
            checkModuleExist(module);
            TextUi.printAddMessage(moduleCode);
            Semester semesterData = module.getSemester(semester);
            addLesson(semesterData, module);
        } else if (getFlag() == AddFlag.EVENT) {
            try {
                TimetableUserItem event = getEvent();
                timetable.addEvent(event.getDayOfWeek(), event);
                timetable.addToEvents(event);
                addUI.printEventMessage(event);
            } catch (AddException e) {
                throw e;
            }
        }
    }

    /**
     * Function returns an event that the user will create by calling other methods
     * to get the event details, which will be used to construct the event item.
     * @return the event that will be added to the timetable
     * @throws AddException when the event that is to added conflicts with another item in timetable
     */
    public TimetableUserItem getEvent() throws AddException {
        TimetableUserItem event;
        try {
            String description = getDescription();
            String date = getDate();
            String startTime = getStartTime();
            String endTime = getEndTime();
            verifyCorrectTime(startTime, endTime);
            String location = getLocation();
            event = new TimetableUserItem(description, date, startTime, endTime, location);
            verifyNoConflict(event);
            return event;
        } catch (AddException e) {
            throw e;
        }
    }

    public void verifyNoConflict(TimetableUserItem event) throws AddException {
        if (timetable.isConflict(event)) {
            throw new AddException("Selected timeslot is occupied, please delete before proceeding");
        }
    }

    public String getLocation() {
        return addUI.getReply(FIFTH_QN).trim();
    }

    public void verifyCorrectTime(String startTime, String endTime) throws AddException {
        if (isEndBeforeStart(startTime, endTime)) {
            throw new AddException("Invalid Input, End Time is earlier than Start Time\n"
                    + "All Events can only occur within a single day");
        }
    }

    public String getEndTime() throws AddException {
        String endTime = addUI.getReply(FOURTH_QN).trim();
        try {
            Integer.parseInt(endTime);
        } catch (NumberFormatException e) {
            throw new AddException("Invalid End Time Entered (Format: 1600)");
        }
        if (isValidTime(endTime)) {
            throw new AddException("Invalid End Time Entered (Format: 1600)");
        }
        return endTime;
    }

    public String getStartTime() throws AddException {
        String startTime = addUI.getReply(THIRD_QN).trim();
        try {
            Integer.parseInt(startTime);
        } catch (NumberFormatException e) {
            throw new AddException("Invalid Start Time Entered (Format: 1600)");
        }
        if (isValidTime(startTime)) {
            throw new AddException("Invalid Start Time Entered (Format: 1600)");
        }
        return startTime;
    }

    public String getDate() throws AddException {
        String date = addUI.getReply(SECOND_QN).trim();
        if (isValidDate(date)) {
            throw new AddException("Invalid Date Format (Format: Monday)");
        }
        return date;
    }

    public String getDescription() throws AddException {
        String description = addUI.getReply(FIRST_QN).trim();
        if (isValidDescription(description)) {
            throw new AddException("Description is Empty, please enter something valid");
        }
        return description;
    }

    public void addLesson(Semester semesterData, Module module) {
        try {
            ArrayList<Lesson> lecture;
            lecture = getLessonDetails(semesterData.getTimetable(), LECTURE);
            lecture = addRemainingLessons(semesterData.getTimetable(), lecture);

            ArrayList<Lesson> tutorial;
            tutorial = getLessonDetails(semesterData.getTimetable(), TUTORIAL);

            ArrayList<Lesson> laboratory;
            laboratory = getLessonDetails(semesterData.getTimetable(), LAB);

            addUI.printLessonDetails(lecture, tutorial, laboratory, timetable, module);
        } catch (AddException e) {
            e.printMessage();
        }
    }

    public ArrayList<Lesson> addRemainingLessons(ArrayList<Lesson> lessons,
            ArrayList<Lesson> lecture) throws AddException {
        try {
            for (Lesson lesson : lessons) {
                if (!lesson.getLessonType().equals(LECTURE) && !lesson.getLessonType().equals(TUTORIAL)
                        && !lesson.getLessonType().equals(LAB)) {
                    lecture.add(lesson);
                }
            }
        } catch (NullPointerException e) {
            throw new AddException("Module has been added into timetable, module has no lesson");
        }
        Collections.sort(lecture, new ClassNumComparator());
        return lecture;
    }

    /**
     * Function creates an ArrayList of Lessons with the specified lesson type.
     * @param lessons the list of lessons in the module
     * @param lessonType the type of lesson specified
     * @return list of lesson with the specified lesson type
     * @throws AddException If the module contains no lessons
     */
    public ArrayList<Lesson> getLessonDetails(ArrayList<Lesson> lessons, String lessonType) throws AddException {
        ArrayList<Lesson> completeList = new ArrayList<>();
        try {
            for (Lesson lesson : lessons) {
                if (lesson.getLessonType().equals(lessonType)) {
                    completeList.add(lesson);
                } else if (lessonType.equals(LECTURE)) {
                    if (!lesson.getLessonType().equals(TUTORIAL) && !lesson.getLessonType().equals(LAB)) {
                        completeList.add(lesson);
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new AddException("Module has been added into timetable, module has no lesson");
        }
        Collections.sort(completeList, new ClassNumComparator());
        return completeList;
    }

    /**
     * Check whether the current module to be added into the timetable already exist.
     * @param mod the module to check
     * @throws ModuleExistException if the module already exist in the timetable
     */
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
