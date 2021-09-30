package seedu.command;

import com.google.gson.Gson;
import seedu.module.Module;
import seedu.timetable.TimeTable;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddCommand extends Command {

    private final String moduleCode;
    //public TimeTable timetable = new TimeTable();

    public AddCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void execute() {
        /*
        String path = "data/Modules/" + moduleCode + ".json";
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(path));
            Module module = gson.fromJson(reader, Module.class);
            reader.close();
            timetable.add(module);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        timetable.print();
        */
    }

}
