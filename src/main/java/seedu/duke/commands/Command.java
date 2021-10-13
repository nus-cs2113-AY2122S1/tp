package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;
import seedu.duke.modules.Module;

import java.util.ArrayList;

public class Command {
    protected ModuleList moduleSelectedList;
    protected UniversityList universitySelectedList;

    public Command(UniversityList universitySelectedList, ModuleList moduleSelectedList) {
        this.moduleSelectedList = moduleSelectedList;
        this.universitySelectedList = universitySelectedList;
    }
}