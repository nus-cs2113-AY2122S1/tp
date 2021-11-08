package command.storage;

import exceptions.NoCapExceptions;
import module.Module;
import module.ModuleList;
import schedule.Schedule;
import org.junit.jupiter.api.Test;
import semester.SemesterList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StorageDecoderTest {
    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");

    @Test
    public void decodeSemesterList_normalSemesterList_success() {
        SemesterList semesters = new SemesterList();
        ModuleList modules = semesters.extractAccessedSemester().getModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 21/08/2022 1600");
        //Change semester index
        semesters.setAccessedSemesterIndex(1);
        modules = semesters.extractAccessedSemester().getModuleList();
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.get(0).addCredits(4);
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.get(0).addClass(new Schedule("WED", "1200", "E3", "Tutorial"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        //Change semester index
        semesters.setAccessedSemesterIndex(2);
        modules = semesters.extractAccessedSemester().getModuleList();
        try {
            modules.add(new Module("CS2132"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.get(0).addClass(new Schedule("MON", "1200", "D3", "Lecture"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.get(0).addClass(new Schedule("TUE", "1200", "D3", "Lecture"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        StorageEncoder.encodeAndSaveSemesterListToJson(semesters);
        SemesterList loadedSemesters = StorageDecoder.decodeJsonToSemesterList();
        assertEquals(semesters.toString(), loadedSemesters.toString());
    }

    @Test
    public void decodeSemesterList_emptyList_success() {
        SemesterList modules = new SemesterList();
        StorageEncoder.encodeAndSaveSemesterListToJson(modules);
        SemesterList loadedSemesters = StorageDecoder.decodeJsonToSemesterList();
        assertEquals(loadedSemesters.toString(), modules.toString());
    }

    @Test
    public void decodeSemesterList_noFile_success() {
        try {
            if (Files.exists(FILE_PATH)) {
                Files.delete(FILE_PATH);
            }
        } catch (IOException e) {
            fail(e);
        }
        SemesterList loadedSemesters = StorageDecoder.decodeJsonToSemesterList();
        assertEquals(loadedSemesters.toString(), new SemesterList().toString());

    }
}