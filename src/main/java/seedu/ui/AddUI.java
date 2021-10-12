package seedu.ui;

import seedu.comparator.ClassNumComparator;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.module.Semester;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableLesson;

import java.util.ArrayList;
import java.util.Collections;

public class AddUI {
    private static final int BALANCE_ARRAY = 1;
    private static final int SERIAL_STARTING = 1;
    private static final int DIVIDER = 42;
    private static final int GAP = 46;
    private static final String SPACE = " ";
    private static final String DIV = "|";
    private static final String LECTURE = "Lecture";
    private static final String TUTORIAL = "Tutorial";
    private static final String LAB = "Laboratory";

    public void getLessonDetails(Semester semesterData, Timetable timetable, Module module) {
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

        if (isExist(lecture)) {
            String select = TextUi.getLessonCommand(LECTURE);
            int indexOfLesson = Integer.parseInt(select) - BALANCE_ARRAY;
            String classNumber = lecture.get(indexOfLesson).getClassNo();
            for (Lesson lesson : lecture) {
                if (lesson.getClassNo().equals(classNumber)) {
                    timetable.addLesson(new TimetableLesson(module, timetable.getSemester(), lesson));
                }
            }
        }

        if (isExist(tutorial)) {
            String select = TextUi.getLessonCommand(TUTORIAL);
            int indexOfLesson = Integer.parseInt(select) - BALANCE_ARRAY;
            String classNumber = tutorial.get(indexOfLesson).getClassNo();
            for (Lesson lesson : tutorial) {
                if (lesson.getClassNo().equals(classNumber)) {
                    timetable.addLesson(new TimetableLesson(module, timetable.getSemester(), lesson));
                }
            }
        }

        if (isExist(laboratory)) {
            String select = TextUi.getLessonCommand(LAB);
            int indexOfLesson = Integer.parseInt(select) - BALANCE_ARRAY;
            String classNumber = laboratory.get(indexOfLesson).getClassNo();
            for (Lesson lesson : laboratory) {
                if (lesson.getClassNo().equals(classNumber)) {
                    timetable.addLesson(new TimetableLesson(module, timetable.getSemester(), lesson));
                }
            }
        }
        TextUi.printLessonAdded();
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
