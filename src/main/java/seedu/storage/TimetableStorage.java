package seedu.storage;

import seedu.timetable.Timetable;
import seedu.timetable.TimetableDto;
import seedu.timetable.TimetableItem;
import seedu.timetable.TimetableLesson;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TimetableStorage {
    private final File file;


    /**
     * Initialize TimetableStorage.
     */
    public TimetableStorage(String path) {
        this.file = new File(path);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets timetable schedule from storage.
     * 
     * @return
     */
    public Timetable loadSchedule() {
        try {
            FileReader timetableSaveReader = new FileReader(file);
            TimetableDto timetable = new Gson().fromJson(timetableSaveReader, TimetableDto.class);
            if (timetable == null) {
                return new Timetable(1);
            }
            return timetable.toTimetable();
        } catch (IOException e) {
            System.out.println(e);
            return new Timetable(1);
        }
    }

    /**
     * Save timetable.
     * 
     * @param timetable timetable
     */
    public void save(Timetable timetable) {
        try {
            FileWriter fw = new FileWriter(file);
            Gson gson = new Gson();
            gson.toJson(new TimetableDto(timetable), fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("failed to save timetable");
        }
    }


}
