package seedu.command;

import seedu.module.Lesson;
import seedu.online.NusMods;
import seedu.module.Module;
import seedu.module.Semester;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableLesson;
import seedu.ui.TextUi;
import java.util.ArrayList;

import java.io.IOException;

public class AddCommand extends Command {
    private static final int BALANCE_ARRAY = 1;
    private static final int SERIAL_STARTING = 1;
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
        if (semesterData.isLectureExist()) {
            ArrayList<Lesson> lecture = selectLesson(semesterData, LECTURE);
            addToTimetable(lecture, module);
        }
        if (semesterData.isTutorialExist()) {
            ArrayList<Lesson> tutorial = selectLesson(semesterData, TUTORIAL);
            addToTimetable(tutorial, module);
        }
        if (semesterData.isLabExist()) {
            ArrayList<Lesson> lab = selectLesson(semesterData, LAB);
            addToTimetable(lab, module);
        }
        TextUi.printLessonAdded();
    }

    public ArrayList<Lesson> selectLesson(Semester semesterData, String lessonType) {
        ArrayList<Lesson> lessonList = new ArrayList<>();
        ArrayList<Lesson> confirmList = new ArrayList<>();
        int serial = SERIAL_STARTING;
        TextUi.printLessonMessage(lessonType);
        for (Lesson lesson : semesterData.getTimetable()) {
            if (lesson.getLessonType().equals(lessonType)) {
                TextUi.printLessonInfo(serial, lesson);
                lessonList.add(lesson);
                serial++;
            }
        }

        String select = TextUi.getCommand();
        int indexOfLesson = Integer.parseInt(select) - BALANCE_ARRAY;
        String classNumber = lessonList.get(indexOfLesson).getClassNo();
        for (Lesson lesson : lessonList) {
            if (lesson.getClassNo().equals(classNumber)) {
                confirmList.add(lesson);
            }
        }
        return confirmList;
    }

    public void addToTimetable(ArrayList<Lesson> lessonList, Module module) {
        for (Lesson lesson : lessonList) {
            timetable.addLesson(new TimetableLesson(module, semester, lesson));
        }
    }
}
