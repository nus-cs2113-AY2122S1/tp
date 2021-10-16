package seedu.duke.storage;


import seedu.duke.textfiletools.WriteToTextFile;

import java.time.LocalDate;

public class ExpenditureStorage extends Storage {
    public void loadExpenditure() {


    }

    public void saveNewlyAddedExpenditure(String description, double spending, LocalDate date) {
        String expenditureToAdd = "add e/";
        expenditureToAdd += (description + " a/" + spending + "d/" + date);

        assert expenditureToAdd.length() >= description.length();

        WriteToTextFile.writeToStorage(expenditureToAdd, "./data/BudgetList1.txt");
    }


}
