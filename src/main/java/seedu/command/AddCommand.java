package seedu.command;

import seedu.comparator.ClassNumComparator;
import seedu.exceptions.FetchException;
import seedu.exceptions.IntegerException;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.module.Semester;
import seedu.online.NusMods;
import seedu.timetable.Timetable;
import seedu.ui.AddUI;
import seedu.ui.TextUi;
import seedu.exceptions.ModuleExistException;
import java.util.ArrayList;
import java.util.Collections;

public class AddCommand extends Command {
    private static final String LECTURE = "Lecture";
    private static final String TUTORIAL = "Tutorial";
    private static final String LAB = "Laboratory";
    private final String moduleCode;
    private final int semester;
    private final Timetable timetable;
    public AddUI addUI = new AddUI();

    public AddCommand(String moduleCode, Timetable timetable) {
        this.moduleCode = moduleCode;
        this.semester = timetable.getSemester();
        this.timetable = timetable;
    }

    public void execute() throws FetchException, ModuleExistException, IntegerException {
        Module module;
        try {
            module = NusMods.fetchModOnline(moduleCode);
            checkModuleExist(module);
        } catch (FetchException | ModuleExistException e) {
            throw e;
        }
        TextUi.printAddMessage(moduleCode);

        Semester semesterData = module.getSemester(semester);
        ArrayList<Lesson> lecture;
        lecture = getLessonDetails(semesterData.getTimetable(), LECTURE);

        ArrayList<Lesson> tutorial;
        tutorial = getLessonDetails(semesterData.getTimetable(), TUTORIAL);

        ArrayList<Lesson> laboratory;
        laboratory = getLessonDetails(semesterData.getTimetable(), LAB);

        addUI.printLessonDetails(lecture, tutorial, laboratory, timetable, module);
    }

    public ArrayList<Lesson> getLessonDetails(ArrayList<Lesson> lessons, String lessonType) {
        ArrayList<Lesson> completeList = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getLessonType().equals(lessonType)) {
                completeList.add(lesson);
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
}
