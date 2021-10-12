package seedu.command;

import org.w3c.dom.Text;
import seedu.comparator.ClassNumComparator;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.module.Semester;
import seedu.online.NusMods;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableLesson;
import seedu.ui.TextUi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//Indicate time clashes with current timetable in milestone v2.0 when adding lesson
public class AddCommand extends Command {
    private static final int BALANCE_ARRAY = 1;
    private static final int SERIAL_STARTING = 1;
    private static final int DIVIDER = 42;
    private static final int GAP = 46;
    private static final String SPACE = " ";
    private static final String DIV = "|";
    private static final String LECTURE = "Lecture";
    private static final String TUTORIAL = "Tutorial";
    private static final String LAB = "Laboratory";
    private final String moduleCode;
    private final int semester;
    private final Timetable timetable;

    public AddCommand(String moduleCode, Timetable timetable) {
        this.moduleCode = moduleCode;
        this.semester = timetable.getSemester();
        this.timetable = timetable;
    }

    public void execute() {
        TextUi.printAddMessage(moduleCode);
        Module module = new Module(moduleCode);
        try {
            module = NusMods.fetchModOnline(moduleCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Semester semesterData = module.getSemester(semester);
        getLessonDetails(semesterData);

//        if (semesterData.isLectureExist()) {
//            ArrayList<Lesson> lecture = selectLesson(semesterData, LECTURE);
//            addToTimetable(lecture, module);
//        }
//        if (semesterData.isTutorialExist()) {
//            ArrayList<Lesson> tutorial = selectLesson(semesterData, TUTORIAL);
//            addToTimetable(tutorial, module);
//        }
//        if (semesterData.isLabExist()) {
//            ArrayList<Lesson> lab = selectLesson(semesterData, LAB);
//            addToTimetable(lab, module);
//        }
//        TextUi.printLessonAdded();
    }

    public void addToTimetable(ArrayList<Lesson> lessonList, Module module) {
        for (Lesson lesson : lessonList) {
            timetable.addLesson(new TimetableLesson(module, semester, lesson));
        }
    }

    public void getLessonDetails(Semester semesterData) {
        ArrayList<Lesson> lecture = new ArrayList<>();
        ArrayList<Lesson> tutorial = new ArrayList<>();
        ArrayList<Lesson> laboratory = new ArrayList<>();
        ArrayList<Lesson> lessons = semesterData.getTimetable();
        for (Lesson lesson : lessons) {
            switch (lesson.getLessonType()) {
            case LECTURE:
                lecture.add(lesson);
                break;
            case TUTORIAL:
                tutorial.add(lesson);
                break;
            case LAB:
                laboratory.add(lesson);
                break;
            default:
                break;
            }
        }
        Collections.sort(lecture, new ClassNumComparator());
        Collections.sort(tutorial, new ClassNumComparator());
        Collections.sort(laboratory, new ClassNumComparator());

        ArrayList<String> lectureLessons = new ArrayList<>();
        ArrayList<String> tutorialLessons = new ArrayList<>();
        ArrayList<String> laboratoryLessons = new ArrayList<>();
        int lectureSerial = SERIAL_STARTING;
        int tutorialSerial = SERIAL_STARTING;
        int labSerial = SERIAL_STARTING;
        int index = Math.max(lecture.size(), Math.max(tutorial.size(), laboratory.size()));

        for (int i = 0; index > i; i++) {
            if (lecture.size() > i) {
                Lesson lesson = lecture.get(i);
                String detail = printLessonInfo(lectureSerial, lesson);
                lectureLessons.add(detail);
                boolean isGap = classNumberGap(lecture, lesson);
                if (isGap) {
                    lectureLessons.add("_______________________________________   |   ");
                    lectureSerial++;
                }
            }
            if (tutorial.size() > i) {
                Lesson lesson = tutorial.get(i);
                String detail = printLessonInfo(tutorialSerial, lesson);
                tutorialLessons.add(detail);
                boolean isGap = classNumberGap(tutorial, lesson);
                if (isGap) {
                    tutorialLessons.add("_______________________________________   |   ");
                    tutorialSerial++;
                }
            }
            if (laboratory.size() > i) {
                Lesson lesson = laboratory.get(i);
                String detail = printLessonInfo(labSerial, lesson);
                laboratoryLessons.add(detail);
                boolean isGap = classNumberGap(laboratory, lesson);
                if (isGap) {
                    laboratoryLessons.add("_______________________________________");
                    labSerial++;
                }
            }
        }
        TextUi.printLessonHeader(lecture, tutorial, laboratory);
        index = Math.max(lectureLessons.size(), Math.max(tutorialLessons.size(), laboratoryLessons.size()));
        for (int j = 0; index > j; j++) {
            String output = "";
            if (lectureLessons.size() > j && isExist(lecture)) {
                output = output.concat(lectureLessons.get(j));
            } else if (tutorialLessons.size() > j && isExist(lecture)) {
                for (int i = 0; GAP > i; i++) {
                    output = output.concat(SPACE);
                }
            }
            if (tutorialLessons.size() > j) {
                output = output.concat(tutorialLessons.get(j));
            } else if (laboratoryLessons.size() > j && isExist(tutorial)) {
                for (int i = 0; GAP > i; i++) {
                    output = output.concat(SPACE);
                }
            }
            if (laboratoryLessons.size() > j) {
                output = output.concat(laboratoryLessons.get(j));
            }
            System.out.println(output);
        }
    }

    public String printLessonInfo(int serial, Lesson lesson) {
        String output = serial + ": " + lesson.lessonDetails();
        if (!lesson.getLessonType().equals("Laboratory"))
            for (int index = output.length(); GAP > index; index++) {
                if (index == DIVIDER) {
                    output = output.concat(DIV);
                } else {
                    output = output.concat(SPACE);
                }
            }
        return output;
    }

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

    public static boolean isExist(ArrayList<Lesson> lesson) {
        return lesson.size() > 0;
    }

}
