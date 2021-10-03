package seedu.command;

import seedu.module.Lesson;
import seedu.online.NusMods;
import seedu.storage.ModStorage;
import seedu.module.Module;
import seedu.module.Semester;
import seedu.ui.TextUi;
import java.util.ArrayList;

import java.io.IOException;

public class AddCommand extends Command {
    private final Integer BALANCE_ARRAY = 1;
    private final String moduleCode;
    private final Integer semester;

    public AddCommand(String moduleCode, Integer semester) {
        this.moduleCode = moduleCode;
        this.semester = semester;
    }

    public void execute() {
        TextUi.printAddMessage(moduleCode);
        Module module = new Module(moduleCode);
        try {
            module = NusMods.fetchModOnline(moduleCode);
            System.out.println("fetchOnlineWorks");
        } catch (IOException e1) {
            TextUi.printNoConnectionMessage();
            try {
                module = ModStorage.loadModInfo(moduleCode);
                System.out.println("fetchOfflineWorks");
            } catch (IOException e2) {
                TextUi.printNotFoundMessage();
            }
        }

        ArrayList<Lesson> addedLesson = new ArrayList<>();
        Semester semesterData = module.getSemester(semester);
        System.out.println(semesterData.isTutorialExist());
        if (semesterData.isLectureExist()) {
            Lesson lecture = selectLecture(semesterData);
            addedLesson.add(lecture);
        }
        if (semesterData.isTutorialExist()) {
            Lesson tutorial = selectTutorial(semesterData);
            addedLesson.add(tutorial);
        }
        if (semesterData.isLabExist()) {
            Lesson lab = selectLab(semesterData);
            addedLesson.add(lab);
        }
        Module addedModule = module;
        addedModule.getSemester(semester).changeTimetable(addedLesson);
        try {
            ModStorage.addTimetableFile(addedModule);
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public Lesson selectLecture(Semester semesterData) {
        ArrayList<Lesson> lectureList = new ArrayList<>();
        TextUi.printLectureMessage();
        int serial = 1;
        for (Lesson lesson : semesterData.getTimetable()) {
            if (lesson.getLessonType().equals("Lecture")) {
                TextUi.printLessonInfo(serial, lesson);
                lectureList.add(lesson);
                serial++;
            }
        }
        String select = TextUi.getCommand();
        int indexOfLecture = Integer.parseInt(select) - BALANCE_ARRAY;
        return lectureList.get(indexOfLecture);
    }

    public Lesson selectTutorial(Semester semesterData) {
        ArrayList<Lesson> tutorialList = new ArrayList<>();
        TextUi.printTutorialMessage();
        int serial = 1;
        for (Lesson lesson : semesterData.getTimetable()) {
            if (lesson.getLessonType().equals("Tutorial")) {
                TextUi.printLessonInfo(serial, lesson);
                tutorialList.add(lesson);
                serial++;
            }
        }
        String select = TextUi.getCommand();
        int indexOfTutorial = Integer.parseInt(select) - BALANCE_ARRAY;
        return tutorialList.get(indexOfTutorial);
    }

    public Lesson selectLab(Semester semesterData) {
        ArrayList<Lesson> labList = new ArrayList<>();
        TextUi.printLaboratoryMessage();
        int serial = 1;
        for (Lesson lesson : semesterData.getTimetable()) {
            if (lesson.getLessonType().equals("Laboratory")) {
                TextUi.printLessonInfo(serial, lesson);
                labList.add(lesson);
                serial++;
            }
        }
        String select = TextUi.getCommand();
        int indexOfLab = Integer.parseInt(select) -  BALANCE_ARRAY;
        return labList.get(indexOfLab);
    }
}
