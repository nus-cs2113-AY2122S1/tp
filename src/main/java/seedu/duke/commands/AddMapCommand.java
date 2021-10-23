package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

public class AddMapCommand extends Command {
    public AddMapCommand(int universityIndexToMap, int selectedMappingIndex, UniversityList universityMasterList,
                         ModuleList moduleMasterList, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws IOException {
        University universityToMap = universityMasterList.get(universityIndexToMap - 1);
        ArrayList<ModuleMapping> selectedMappings = universityToMap.getSelectedMappings(moduleSelectedList);
//        Module moduleToMap = moduleMasterList.get(moduleIndexToMap - 1);
//        Module mappedModule = universityToMap.getMappedModule(moduleToMap, moduleSelectedList);
//        ModuleMapping selectedMapping = universityToMap.getMapping(moduleToMap, moduleSelectedList);
//        universityToMap.addMapping(moduleToMap, mappedModule);
        System.out.println("New module mapping added: ");
        ModuleMapping selectedMapping = selectedMappings.get(selectedMappingIndex);
        universityToMap.addMapping(selectedMapping);
        Ui.printModuleMapping(selectedMapping, );
//        for (ModuleMapping selectedMapping : selectedMappings) {
//            Ui.printModuleMapping(selectedMapping, moduleIndexToMap);
//            universityToMap.addMapping(selectedMapping);
//        }

        SelectedUniversityStorage.write(universitySelectedList);
    }
}