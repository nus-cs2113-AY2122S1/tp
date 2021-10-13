package seedu.duke.storage;


import seedu.duke.textfiletools.WriteToTextFile;

public class ExpenditureStorage extends Storage {
    public void loadExpenditure() {


    }

    public void saveNewlyAddedExpenditure(String description, double spending) {
        String expenditureToAdd = "add e/";
        expenditureToAdd += (description + " a/" + spending);
        WriteToTextFile.writeToStorage(expenditureToAdd, "./data/BudgetList1.txt");
    }
}
