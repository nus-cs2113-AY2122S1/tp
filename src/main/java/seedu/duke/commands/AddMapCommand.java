package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class AddMapCommand extends Command {

    public AddMapCommand(int universityIndexToMap, int moduleIndexToMap, UniversityList universityMasterList,
                         ModuleList moduleMasterList, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws IOException {
        University universityToMap = universityMasterList.get(universityIndexToMap-1);
        Module moduleToMap = moduleMasterList.get(moduleIndexToMap-1);
        Module mappedModule = universityToMap.getMappedModule(moduleToMap, moduleSelectedList);
        ModuleMapping selectedMapping = universityToMap.getMapping(moduleToMap, moduleSelectedList);
        universityToMap.addMapping(moduleToMap, mappedModule);
        SelectedUniversityStorage.write(universitySelectedList);
        Ui.printModuleMapping(selectedMapping, moduleIndexToMap);
    }
}