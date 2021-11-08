package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.Loan;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddLoanTest {
    @Test
    void addLoan_newLoan_loanExists() {
        String name = "Borrower";
        double amount = 35.00;
        LocalDate date = LocalDate.of(2021, 10, 8);

        AllRecordList currentLoanList = new AllRecordList();
        currentLoanList.addLoan(name,amount,date,true);
        assertNotNull(currentLoanList.getLoan(0, 10));
    }
}
