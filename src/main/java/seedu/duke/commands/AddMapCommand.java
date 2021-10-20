package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class AddMapCommand extends AddCommand{

    public AddMapCommand(int universityIndexToMap, int moduleIndexToMap,
                         UniversityList universitySelectedList, ModuleList moduleSelectedList) throws IOException {
        University universityToMap = universitySelectedList.get(universityIndexToMap);
        Module moduleToMap = moduleSelectedList.get(moduleIndexToMap);
        Module mappedModule = universityToMap.getMappedModule(moduleToMap, moduleSelectedList);
        universityToMap.addMapping(moduleToMap, mappedModule);
        SelectedUniversityStorage.write(universitySelectedList);

    }
}
