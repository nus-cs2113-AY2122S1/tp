package seedu.command;

import seedu.module.Lesson;
import seedu.online.NusMods;
import seedu.storage.ModStorage;
import seedu.module.Module;
import seedu.module.Semester;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableLesson;
import seedu.ui.TextUi;
import java.util.ArrayList;

import java.io.IOException;

public class AddCommand extends Command {
    private final int BALANCE_ARRAY = 1;
    private final int SERIAL_STARTING = 1;
    private final String LECTURE = "Lecture";
    private final String TUTORIAL = "Tutorial";
    private final String LAB = "Laboratory";
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
        } catch (IOException e1) {
            TextUi.printNoConnectionMessage();
            try {
                module = ModStorage.loadModInfo(moduleCode);
            } catch (IOException e2) {
                TextUi.printNotFoundMessage();
            }
        }

        Semester semesterData = module.getSemester(semester);
        if (semesterData.isLectureExist()) {
            ArrayList<Lesson> lecture = selectLesson(semesterData, LECTURE);
            for (Lesson lesson : lecture) {
                timetable.addLesson(new TimetableLesson(module, semester, lesson));
            }
        }
        if (semesterData.isTutorialExist()) {
            ArrayList<Lesson> tutorial = selectLesson(semesterData, TUTORIAL);
            for (Lesson lesson : tutorial) {
                timetable.addLesson(new TimetableLesson(module, semester, lesson));
            }
        }
        if (semesterData.isLabExist()) {
            ArrayList<Lesson> lab = selectLesson(semesterData, LAB);
            for (Lesson lesson : lab) {
                timetable.addLesson(new TimetableLesson(module, semester, lesson));
            }
        }
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
        String classNumber = lessonList.get(indexOfLesson).getLessonType();
        for (Lesson lesson : lessonList) {
            if (lesson.getClassNo().equals(classNumber)) {
                confirmList.add(lesson);
            }
        }
        return confirmList;
    }
}
