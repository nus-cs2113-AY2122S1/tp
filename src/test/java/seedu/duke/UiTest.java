package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.FinancialTracker;
import seedu.utility.Messages;
import seedu.utility.Ui;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private static final String SEPARATOR_LINE = "-------------------------------------------------------------------" 
            + "----------------------------------";
    private static final String currentDate =
            "(" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyy")) + ")";
    
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String newLine = System.lineSeparator();

    private final Ui testUI = new Ui();
    private final FinancialTracker financialTracker = new FinancialTracker();
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    public void initialiseFinancialTracker() {
        financialTracker.addIncome(new Income("Paycheck August", 25.0, "Salary"));
        financialTracker.addExpense(new Expense("Bought a game", 19.73, "Game"));
        financialTracker.addExpense(new Expense("Bought cookies", 5.0, "Bakery"));
        financialTracker.addExpense(new Expense("Bought cakes", 7.0, "Bakery"));
        financialTracker.addIncome(new Income("Rob a bank", 2000.0, "Crime"));
        financialTracker.addIncome(new Income("Paycheck July", 25.0, "Salary"));
    }
    
    
    @Test
    public void listExpense_validFinancialTracker_filteredExpenses() {
        initialiseFinancialTracker();
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "Below is a list of all of your recent spending!" + newLine 
                + SEPARATOR_LINE + newLine
                + "1: [E] Bought a game - $19.73 " + currentDate + newLine
                + "2: [E] Bought cookies - $5.00 " + currentDate + newLine 
                + "3: [E] Bought cakes - $7.00 " + currentDate + newLine
                + SEPARATOR_LINE;
        
        testUI.listExpense(financialTracker.getExpenses());

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void listIncome_validFinancialTracker_filteredIncomes() {
        initialiseFinancialTracker();
        final String expectedOutput = SEPARATOR_LINE + newLine 
                + "Below is a list of all of your recent earnings!" + newLine
                + SEPARATOR_LINE + newLine 
                + "1: [I] Paycheck August - $25.00 " + currentDate + newLine
                + "2: [I] Rob a bank - $2000.00 " + currentDate + newLine
                + "3: [I] Paycheck July - $25.00 " + currentDate + newLine
                + SEPARATOR_LINE;

        testUI.listIncome(financialTracker.getIncomes());

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printExpenseAdded_addedOneItem_expenseAddedFeedback() {
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "Your most recent spending: " + newLine
                + "[E] Bought cookies - $5.00 " + currentDate + newLine
                + SEPARATOR_LINE;

        testUI.printExpenseAdded(new Expense("Bought cookies", 5.0, "Bakery"));
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printIncomeAdded_addedOneItem_incomeAddedFeedback() {
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "Your most recent earning: " + newLine
                + "[I] Salary August - $5.00 " + currentDate + newLine
                + SEPARATOR_LINE;
        testUI.printIncomeAdded(new Income("Salary August", 5.0, "Salary"));
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void printBalance_oneExpenseOneIncome_printNetBalance() {
        initialiseFinancialTracker();
        final String expectedOutput = SEPARATOR_LINE + newLine
                + "Your current balance is: $2018.27" + newLine
                + SEPARATOR_LINE;
        
        testUI.printBalance(financialTracker.getBalance());
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void listFind_givenFilteredList_printFilteredList() {
        initialiseFinancialTracker();
        final String expectedOutput = SEPARATOR_LINE + newLine
               + Messages.FOUND_LIST_MESSAGE + newLine
               + SEPARATOR_LINE + newLine
               + "1: [E] Bought a game - $19.73 (17 Oct 2021)" + newLine 
               + "2: [E] Bought cookies - $5.00 (17 Oct 2021)" + newLine
               + "3: [E] Bought cakes - $7.00 (17 Oct 2021)" + newLine
               + "4: [I] Paycheck August - $25.00 (17 Oct 2021)"  +newLine   
               + "5: [I] Rob a bank - $2000.00 (17 Oct 2021)" + newLine
               + "6: [I] Paycheck July - $25.00 (17 Oct 2021)" + newLine 
               + SEPARATOR_LINE;
        
        testUI.listFind(financialTracker.getEntries());
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
    
}
