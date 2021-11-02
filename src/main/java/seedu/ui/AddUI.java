package seedu.ui;

import seedu.exceptions.IntegerException;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableUserItem;

import java.util.ArrayList;
import java.util.Scanner;

public class AddUI {

    private static final String FIXED_LENGTH_FORMAT = "%-56.56s";
    private static final String FIXED_TIME_FORMAT = "%04d";
    private static final String SMALL_GAP = "%14s";
    private static final String FIXED_FORMAT = "%88.88s";
    private static final String LECTURE = "Lecture";
    private static final String TUTORIAL = "Tutorial";
    private static final String LAB = "Laboratory";
    private static final String LECTURE_SLOT = "Lecture Lesson Slots";
    private static final String TUTORIAL_SLOT = "Tutorial Lesson Slots";
    private static final String LAB_SLOT = "Laboratory Lesson Slots";
    private static final int BALANCE_ARRAY = 1;
    private static final int SERIAL_STARTING = 1;
    private static final int ZERO = 0;
    private static final int TIME = 100;
    private static final String RUN = "Run";
    private static final String EXIT = "Exit";
    private static final String LINE = "_________________________________________________   |   ";
    private static final String NO_LESSON_FOUND = "No Lesson Time Slots Found";
    private static final String NO_LECTURE_FOUND = "            *Module has no Lectures*";
    private static final String NO_TUTORIAL_FOUND = "            *Module has no Tutorials*";
    private static final String NO_LAB_FOUND = "               *Module has no Labs*";
    private static final String DISCLAIMER = " [CONFLICT]";

    public static Scanner in = new Scanner(System.in);

    /**
     * Function initialises arraylist for each lesson type and add the details of
     * each lesson type into the array which is subsequently used to display lesson details.
     * Function then calls getCommand method to receive input on the choice of lesson from the user
     * and adds them into the timetable
     *
     * @param lec       the list of lecture lesson type
     * @param tt        the list of tutorial lesson type
     * @param lab       the list of laboratory lesson type
     * @param timetable the timetable where lessons wil be added into
     * @param module    the chosen module by the user
     */
    public void printLessonDetails(ArrayList<Lesson> lec, ArrayList<Lesson> tt, ArrayList<Lesson> lab,
                                   Timetable timetable, Module module) {
        ArrayList<String> lectureLessons;
        ArrayList<String> tutorialLessons;
        ArrayList<String> labLessons;
        int length = Math.max(lec.size(), Math.max(tt.size(), lab.size()));
        lectureLessons = getLessonDetails(lec, length, LECTURE, timetable);
        tutorialLessons = getLessonDetails(tt, length, TUTORIAL, timetable);
        labLessons = getLessonDetails(lab, length, LAB, timetable);
        printLessonHeader(lec, tt, lab);
        printLessons(lectureLessons, tutorialLessons, labLessons);
        try {
            getCommand(lec, LECTURE, timetable, module);
            getCommand(tt, TUTORIAL, timetable, module);
            getCommand(lab, LAB, timetable, module);
            TextUi.printLessonAdded();
        } catch (IntegerException e) {
            e.printMessage();
        }
    }

    /**
     * Function add the details of each lesson by calling the printLessonInfo
     * Lessons of different class number will be divided and if a particular lesson type
     * does not exist, a corresponding string will be used to highlight that.
     * @param lessons the list of lessons
     * @param length the maximum index of a lesson type used to ensure that the UI is neatly displayed
     * @param lessonType the lesson type of the list of lessons
     * @return returns a list of Strings containing the lesson detail of the specified lesson type
     */
    public ArrayList<String> getLessonDetails(ArrayList<Lesson> lessons, int length,
            String lessonType, Timetable timetable) {
        ArrayList<String> completeList = new ArrayList<>();
        int serial = SERIAL_STARTING;
        String detail;
        for (int i = 0; length > i; i++) {
            if (isArrayExist(lessons, i)) {
                Lesson lesson = lessons.get(i);
                detail = printLessonInfo(serial, lesson, timetable);
                completeList.add(detail);
                if (classNumberGap(lessons, lesson)) {
                    completeList.add(LINE);
                    serial++;
                }
            }
        }
        if (!isExist(completeList, ZERO)) {
            switch (lessonType) {
            case LECTURE:
                detail = String.format(FIXED_LENGTH_FORMAT, NO_LECTURE_FOUND);
                break;
            case TUTORIAL:
                detail = String.format(FIXED_LENGTH_FORMAT, NO_TUTORIAL_FOUND);
                break;
            case LAB:
                detail = String.format(FIXED_LENGTH_FORMAT, NO_LAB_FOUND);
                break;
            default:
                detail = null;
                break;
            }
            completeList.add(detail);
        }
        return completeList;
    }

    /**
     * Functions prints out the lesson detail for all lessons.
     * @param lec the list of Strings representing the lesson detail
     * @param tt  the list of Strings representing the tutorial details
     * @param lab the list of Strings representing the lab details
     */
    public void printLessons(ArrayList<String> lec, ArrayList<String> tt, ArrayList<String> lab) {
        int index = Math.max(lec.size(), Math.max(tt.size(), lab.size()));
        for (int j = 0; index > j; j++) {
            String output = "";
            if (isExist(lec, j)) {
                output = lec.get(j);
            } else if ((isExist(tt, j) && !isExist(lec, j)) || isExist(lab, j)) {
                output = String.format(FIXED_LENGTH_FORMAT, "");
            }
            if (isExist(tt, j)) {
                output = output.concat(tt.get(j));
            } else if (isExist(lab, j) && !isExist(tt, j)) {
                output = output.concat(String.format(FIXED_LENGTH_FORMAT, ""));
            }
            if (isExist(lab, j)) {
                output = output.concat(lab.get(j));
            }
            System.out.println(output);
        }
    }

    /**
     * Function attempts to get user command of their preferred lesson.
     * @param lessons the list of lessons
     * @param lessonType the type of lesson
     * @param timetable  the timetable that the lesson will be added to
     * @param module     the module of the lesson
     * @throws IntegerException when the input is invalid
     */
    public void getCommand(ArrayList<Lesson> lessons, String lessonType,
                           Timetable timetable, Module module) throws IntegerException {
        if (isArrayExist(lessons, ZERO)) {
            try {
                addLessonInfo(lessons, lessonType, timetable, module);
            } catch(IntegerException e) {
                throw e;
            }
        }
    }

    /**
     * Add lesson(s) from the list of lessons with the same class number selected
     * into the timetable.
     * @param lessons the list of lessons
     * @param timetable the timetable where the lesson is to be added
     * @param module the module of the lesson
     * @param classNumber the class number of the lesson
     */
    public void addLessonToTimetable(ArrayList<Lesson> lessons,
                                     Timetable timetable, Module module, String classNumber) {
        assert (lessons.size() > ZERO);
        for (Lesson lesson : lessons) {
            if (lesson.getClassNo().equals(classNumber)) {
                timetable.addLesson(module, timetable.getSemester(), lesson);
            }
        }
    }

    public String printLessonInfo(int serial, Lesson lesson, Timetable timetable) {
        String output = serial + ": " + lesson.lessonDetails();
        if (timetable.isConflict(lesson)) {
            output = output.concat(DISCLAIMER);
        }
        output = String.format(FIXED_LENGTH_FORMAT, output);
        return output;
    }

    /**
     * Function checks whether the current lesson in the list of lessons has the
     * same class number as the next lesson in the index.
     * @param lessonList the list of lesson
     * @param lesson     the specified lesson
     * @return true if the next lesson in the index has the same class number, false otherwise
     */
    public boolean classNumberGap(ArrayList<Lesson> lessonList, Lesson lesson) {
        int index = lessonList.indexOf(lesson);
        if (lessonList.size() != index + BALANCE_ARRAY) {
            String classNumber = lessonList.get(index).getClassNo();
            int nextIndex = index + BALANCE_ARRAY;
            String nextClassNumber = lessonList.get(nextIndex).getClassNo();
            return !classNumber.equals(nextClassNumber);
        }
        return true;
    }

    /**
     * Function checks whether the array of lesson details
     * exist and a String in the index specified contains a lesson.
     * @param lesson the list of Strings that contain lesson details
     * @param index  the index specified
     * @return return true if the array and the lesson detail exist, otherwise false
     */
    public boolean isExist(ArrayList<String> lesson, int index) {
        return lesson.size() > ZERO && lesson.size() > index;
    }

    /**
     * Function checks whether the array exist and a lesson in
     * the index specified contains a lesson.
     * @param lesson the list of lessons
     * @param index  the index specified
     * @return return true if the array and the lesson exist, otherwise false.
     */
    public boolean isArrayExist(ArrayList<Lesson> lesson, int index) {
        return lesson.size() > ZERO && lesson.size() > index;
    }

    /**
     * Function prints out the header to label each corresponding lesson
     * type in the module lessons.
     * @param lt The list of lessons that are lectures
     * @param tt The list of lessons that are tutorials
     * @param lb The list of lessons that are laboratory
     */
    public void printLessonHeader(ArrayList<Lesson> lt, ArrayList<Lesson> tt, ArrayList<Lesson> lb) {
        String header;
        if (isArrayExist(lt, ZERO) || isArrayExist(tt, ZERO) || isArrayExist(lb, ZERO)) {
            header = String.format(SMALL_GAP, "") + String.format(FIXED_LENGTH_FORMAT, LECTURE_SLOT)
                    + String.format(FIXED_LENGTH_FORMAT, TUTORIAL_SLOT) + LAB_SLOT;
        } else {
            header = String.format(FIXED_FORMAT, NO_LESSON_FOUND);
        }
        System.out.println(header);
    }

    public String getReply(String question) {
        System.out.print(question);
        String input = in.nextLine();
        if (input.equals("")) {
            return "";
        } else {
            return input;
        }
    }

    public void printEventMessage(TimetableUserItem event) {
        String startTime = String.format(FIXED_TIME_FORMAT, event.getStartHour() * TIME);
        String endTime = String.format(FIXED_TIME_FORMAT, event.getEndHour() * TIME);

        String output = "Alright!! Event: " + event.getTitle() + " on " + event.getDay() + ", from "
                + startTime + " to " + endTime;
        if (event.isDescription()) {
            output = output.concat(" at " + event.getDescription());
        }
        output = output.concat(" has been added to your timetable");
        System.out.println(output);
    }

    public String getModuleCode() {
        System.out.print("Enter Module Code to add it into Timetable: ");
        String input = in.nextLine();
        while (input.isEmpty()) {
            input = in.next();
        }
        return input.toUpperCase();
    }

    public Lesson lessonEqualizer(ArrayList<Lesson> lessons, int index) {
        int tally = 0;
        for (int i = 1; lessons.size() > i; i++) {
            Lesson prev = lessons.get(i - BALANCE_ARRAY);
            Lesson curr = lessons.get(i);
            if (!prev.getClassNo().equals(curr.getClassNo())) {
                tally++;
            }
            if (tally == index) {
                return lessons.get(i);
            }
        }
        return lessons.get(tally);
    }

    public void addLessonInfo(ArrayList<Lesson> lessons, String lessonType,
            Timetable timetable, Module module) throws IntegerException {
        String flag = RUN;
        String classNumber = "";
        while (flag.equals(RUN)) {
            String select = TextUi.getLessonCommand(lessonType);
            try {
                verifySelection(select, lessons);
            } catch (IntegerException e) {
                throw e;
            }
            int indexOfLesson = Integer.parseInt(select) - BALANCE_ARRAY;
            Lesson selectedLesson = lessonEqualizer(lessons, indexOfLesson);
            classNumber = selectedLesson.getClassNo();
            flag = checkFlag(timetable, selectedLesson);
        }
        addLessonToTimetable(lessons, timetable, module, classNumber);
    }

    public String checkFlag(Timetable timetable, Lesson lesson) {
        if (timetable.isConflict(lesson)) {
            String choice = TextUi.printAskConfirmation(lesson);
            if (choice.equals("y") || choice.equals("yes")) {
                return EXIT;
            } else if (choice.equals("n") || choice.equals("no")) {
                System.out.println("Alright, returning to lesson selection");
                return RUN;
            } else {
                System.out.println("Invalid Command, Try Again");
                return RUN;
            }
        } else {
            return EXIT;
        }
    }

    public void verifySelection(String select, ArrayList<Lesson> lessons) throws IntegerException {
        try {
            int indexOfLesson = Integer.parseInt(select) - BALANCE_ARRAY;
            Lesson test = lessons.get(indexOfLesson);
        } catch (NumberFormatException e) {
            throw new IntegerException("Input is not an integer, try adding the module again");
        } catch (IndexOutOfBoundsException e) {
            throw new IntegerException("Input is out of range, try adding the module");
        }
    }
}
