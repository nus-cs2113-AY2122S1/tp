package seplanner.commands;

import seplanner.ui.UiUniversity;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.IOException;

public class AddUniCommand extends Command {
    private final University universityToAdd;
    private final int universityIndexToAdd;

    public AddUniCommand(University universityToAdd, UniversityList universityMasterList,
                         UniversityList universitySelectedList) throws IOException {
        this.universityToAdd = universityToAdd;
        assert universityToAdd.getName() != null;
        assert universityToAdd.getClass() != null;
        universitySelectedList.addUniversity(universityToAdd);
        this.universityIndexToAdd = universityToAdd.getMasterListIndex(universityMasterList);
        assert universitySelectedList.getSize() != 0;
        assert universitySelectedList.isExistUniversity(universityToAdd.getName());
        assert universitySelectedList.get(universitySelectedList.getSize() - 1)
                .getName().equals(universityToAdd.getName());
        storage.updateSelectedUniversityList(universitySelectedList);
        System.out.println("New university added: ");
        UiUniversity.printUniversity(universityToAdd, false);
    }
}