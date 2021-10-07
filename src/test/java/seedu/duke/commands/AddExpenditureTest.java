package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.RecordList;
import seedu.duke.data.records.Expenditure;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddExpenditureTest {
    @Test
    void getDescription_rawCommand_expectDescription() {
        //String inputCommand = "AddExpenditure JanuaryBudget 20.00 12";
        AddExpenditureCommand currentExpenditure = new AddExpenditureCommand();
        assertEquals("JanuaryBudget", currentExpenditure.getDescription("AddExpenditure JanuaryBudget 20.00 12"));
    }

    @Test
    void getAmount_rawCommand_expectAmount() {
        //String inputCommand = "AddExpenditure JanuaryBudget 20.00 12";
        AddExpenditureCommand currentExpenditure = new AddExpenditureCommand();
        assertEquals(20.00, currentExpenditure.getSpending("AddExpenditure JanuaryBudget 20.00 12"));
    }

    @Test
    void getMonth_rawCommand_expectMonth() {
        //String inputCommand = "AddExpenditure JanuaryBudget 20.00 12";
        AddExpenditureCommand currentExpenditure = new AddExpenditureCommand();
        assertEquals(12, currentExpenditure.getMonth("AddExpenditure JanuaryBudget 20.00 12"));
    }

    @Test
    void addBudget_rawCommand_ExpenditureList_sizeOf1() {
        String description = "JanuaryExpenditure";
        double spending = 20.00;
        int month = 12;

        RecordList currentExpenditureList = new RecordList();
        currentExpenditureList.addBudget(description, spending, month);
        assertEquals(1, currentExpenditureList.getSize());
    }
}
