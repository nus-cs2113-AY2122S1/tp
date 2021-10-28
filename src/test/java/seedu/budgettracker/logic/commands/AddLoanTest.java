package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.RecordList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddLoanTest {
    @Test
    void addLoan_commandInput_loanList_sizeOf_1() {
        String name = "Borrower";
        double amount = 35.00;
        LocalDate date = LocalDate.of(2021, 10, 8);
        int month = date.getMonthValue();

        RecordList currentLoanList = new RecordList(month);
        currentLoanList.addLoan(name, amount, date, false);
        assertEquals(1, currentLoanList.getLoanListSize());
    }
}
