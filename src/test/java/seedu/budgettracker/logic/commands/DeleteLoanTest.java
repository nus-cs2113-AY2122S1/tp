package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.Category;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteLoanTest {

    @Test
    void deleteLoan_loanList_sizeOf1() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();

        RecordList currentLoanList = new RecordList(month);
        currentLoanList.addLoan("TestLoan1", 08.00, date);
        currentLoanList.addLoan("TestLoan2", 10.00, date);
        currentLoanList.deleteLoan(1);
        assertEquals(1, currentLoanList.getLoanListSize());
    }
}
