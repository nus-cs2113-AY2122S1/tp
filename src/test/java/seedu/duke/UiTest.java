package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    //MethodName_StateUnderTest_ExpectedBehavior
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    //Before every test we set a stream that we read the log from
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    //After every test we set the stream back to the original sout
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    
    @Test
    public void listExpense_validFinancialTracker_filteredExpenses() {
        String correctOutput = "1: Bought cookies\n2:Bought cake\n";
        Ui testUI = new Ui();
        FinancialTracker financialTracker = new FinancialTracker();
        financialTracker.addEntry(new Expense());
        financialTracker.addEntry(new Income());
        financialTracker.addEntry(new Expense());
        financialTracker.listExpense();
        assertEquals(correctOutput, outputStreamCaptor.toString().trim());

    }
    /*
    @Test
        void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
            print("Hello Baeldung Readers!!");
            
            Assert.assertEquals("Hello Baeldung Readers!!", outputStreamCaptor.toString().trim());
        }

     */
}
