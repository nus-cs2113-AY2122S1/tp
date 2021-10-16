package seedu.duke.model.module;

import seedu.duke.commons.util.JsonUtil;
import seedu.duke.commons.util.exceptions.ModuleLoadException;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class FullModuleList {
    private List<Module> fullModuleList; // a full list of all available modules on NUSMods

    private void setFullModuleList(JsonUtil moduleArray) {
        this.fullModuleList = Arrays.asList(moduleArray.getModules()); // convert Module[] to ArrayList<Module>
    }

    public List<Module> getFullModuleList() {
        return fullModuleList;
    }

    public FullModuleList() throws ModuleLoadException, FileNotFoundException {
        setFullModuleList(new JsonUtil());
    }
}
