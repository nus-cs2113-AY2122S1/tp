package seedu.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.command.flags.AddFlag;
import seedu.exceptions.AddException;
import seedu.exceptions.FetchException;
import seedu.exceptions.UniModsException;
import seedu.exceptions.ModuleExistException;
import seedu.exceptions.IntegerException;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.module.Semester;
import seedu.online.NusMods;
import seedu.timetable.Timetable;
import seedu.ui.AddUI;

import java.util.ArrayList;

class AddCommandTest {
    @Test
    public void semesterEqualTimetableSemesterForLessonFlag() {
        Timetable tt = new Timetable(1);
        AddCommand ac = new AddCommand(tt, AddFlag.LESSON);

        Timetable tt2 = new Timetable(2);
        AddCommand ac2 = new AddCommand(tt2, AddFlag.LESSON);

        assertEquals(ac.getClass(), ac2.getClass());
    }

    @Test
    public void semesterEqualTimetableSemesterForEventFlag() {
        Timetable tt = new Timetable(1);
        AddCommand ac = new AddCommand(tt, AddFlag.EVENT);

        Timetable tt2 = new Timetable(2);
        AddCommand ac2 = new AddCommand(tt2, AddFlag.EVENT);

        assertEquals(ac.getClass(), ac2.getClass());
    }

    @Test
    public void semesterEqualTimetableSemesterForInvalidFlag() {
        Timetable tt = new Timetable(1);
        AddCommand ac = new AddCommand(tt, AddFlag.INVALID);

        Timetable tt2 = new Timetable(2);
        AddCommand ac2 = new AddCommand(tt2, AddFlag.INVALID);

        assertEquals(ac.getClass(), ac2.getClass());
    }

    @Test
    public void parseDataIsAccurate() {
        Timetable tt = new Timetable(1);
        AddCommand ac = new AddCommand(tt, AddFlag.LESSON);
        String output = ac.parseDate("Monday");

        assertEquals(output, "Monday");
    }

    @Test
    public void checkModuleExistThrowsExceptionWhenAddingDuplicateModule() throws FetchException {
        Timetable tt = new Timetable(1);
        Module module = NusMods.fetchModOnline("CG2271");
        tt.addModuleToList(module);
        AddCommand ac = new AddCommand(tt, AddFlag.LESSON);
        Assertions.assertThrows(ModuleExistException.class, () -> ac.checkModuleExist(module));
    }

    @Test
    public void verifySelectionThrowsExceptionWhenInvalidInput() {
        String invalid = "Amen";
        ArrayList<Lesson> empty = new ArrayList<>();
        AddUI addUi = new AddUI();
        Assertions.assertThrows(IntegerException.class, () -> addUi.verifySelection(invalid, empty));
    }

    @Test
    public void verifySelectionThrowsExceptionWhenInputIsOutOfRange() throws FetchException,
            AddException, UniModsException {
        String invalid = "3";
        Module module = NusMods.fetchModOnline("CG2271");
        Semester semesterData = module.getSemester(1);
        Timetable tt = new Timetable(1);
        tt.addModuleToList(module);
        AddCommand ac = new AddCommand(tt, AddFlag.LESSON);
        ArrayList<Lesson> lecture = ac.getLessonDetails(semesterData.getTimetable(), "Lecture");
        AddUI addUi = new AddUI();
        Assertions.assertThrows(IntegerException.class, () -> addUi.verifySelection(invalid, lecture));
    }

}