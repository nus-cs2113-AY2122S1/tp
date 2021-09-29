package seedu.timetable;

import java.util.ArrayList;
import seedu.module.Module;

public class Timetable {
    ArrayList<Module> moduleList = new ArrayList<>();

    public void add(Module module) {
        moduleList.add(module);
    }

    public void print() {
        for (Module m : moduleList) {
            System.out.println(m.toString());
        }
    }
}
