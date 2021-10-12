package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.Ui;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String newLine = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void listExpense_validFinancialTracker_filteredExpenses() {
        final String expectedOutput = "---------------------------------------------------------------------------" 
                + "-------------------------- " + newLine + "Below is a list of all of your recent spending!" + newLine 
                + "---------------------------------------------------------------------------------------------------" 
                + "-- " + newLine + "1: [E] Bought cookies - $5.0" + newLine + "2: [E] Bought cakes - $7.0" + newLine
                + "---------------------------------------------------------------------------------------------------" 
                + "--";
        
        
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Expense("Bought cookies", 5.0));
        entries.add(new Income("Paycheck August", 20.0));
        entries.add(new Expense("Bought cakes", 7.0));
        Ui testUI = new Ui();
        testUI.listExpense(entries);
        
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void listIncome_validFinancialTracker_filteredIncomes() {
        final String expectedOutput = "---------------------------------------------------------------------------" 
                + "-------------------------- " + newLine + "Below is a list of all of your recent earnings!" + newLine
                + "---------------------------------------------------------------------------------------------------" 
                + "-- " + newLine + "1: [I] Paycheck August - $20.0" + newLine
                + "2: [I] Paycheck July - $25.0" + newLine
                + "---------------------------------------------------------------------------------------------------" 
                + "--";
        
        
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Expense("Bought cookies", 5.0));
        entries.add(new Income("Paycheck August", 20.0));
        entries.add(new Expense("Bought cakes", 7.0));
        entries.add(new Income("Paycheck July", 25.0));
        Ui testUI = new Ui();
        testUI.listIncome(entries);

        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
