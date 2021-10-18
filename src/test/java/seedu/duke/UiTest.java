//package seedu.duke;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seedu.entry.Expense;
//import seedu.entry.Income;
//import seedu.utility.Ui;
//
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class UiTest {
//    private final PrintStream standardOut = System.out;
//    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//    private final String newLine = System.lineSeparator();
//
//    @BeforeEach
//    public void setUp() {
//        System.setOut(new PrintStream(outputStreamCaptor));
//    }
//
//    @AfterEach
//    public void tearDown() {
//        System.setOut(standardOut);
//    }
//
//    @Test
//    public void listExpense_validFinancialTracker_filteredExpenses() {
//        final String expectedOutput = 
//                "---------------------------------------------------------------------------------------------"
//                + "-------- "
//                + newLine 
//                + "Below is a list of all of your recent spending!" 
//                + newLine 
//                + "-------------------------------------------------------------------------------------------" 
//                + "---------- " 
//                + newLine 
//                + "1: [E] Bought cookies - $5.00 (16 Oct 2021)"
//                + newLine 
//                + "2: [E] Bought cakes - $7.00 (16 Oct 2021)" 
//                + newLine
//                + "-------------------------------------------------------------------------------------------" 
//                + "----------";
//
//
//        ArrayList<Expense> entries = new ArrayList<>();
//        entries.add(new Expense("Bought cookies", 5.0, "Bakery"));
//        entries.add(new Expense("Bought cakes", 7.0, "Bakery"));
//        Ui testUI = new Ui();
//        testUI.listExpense(entries);
//
//        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
//    }
//
//    @Test
//    public void listIncome_validFinancialTracker_filteredIncomes() {
//        final String expectedOutput = 
//                "---------------------------------------------------------------------------------------------"
//                + "-------- "        
//                + newLine 
//                + "Below is a list of all of your recent earnings!" 
//                + newLine
//                + "-------------------------------------------------------------------------------------------" 
//                + "---------- " 
//                + newLine 
//                + "1: [I] Paycheck August - $20.00 (16 Oct 2021)" 
//                + newLine
//                + "2: [I] Paycheck July - $25.00 (16 Oct 2021)"
//                + newLine
//                + "-------------------------------------------------------------------------------------------" 
//                + "----------";
//
//
//        ArrayList<Income> entries = new ArrayList<>();
//        entries.add(new Income("Paycheck August", 20.0, "Salary"));
//        entries.add(new Income("Paycheck July", 25.0, "Salary"));
//        Ui testUI = new Ui();
//        testUI.listIncome(entries);
//
//        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
//    }
//
//    @Test
//    public void printExpenseAdded_addedOneItem_expenseAddedFeedback() {
//        final String expectedOutput = 
//                "----------------------------------------------------------------------------------------------" 
//                + "------- "
//                + newLine
//                + "Your most recent spending: "
//                + newLine
//                + "[E] Bought cookies - $5.00 (16 Oct 2021)"
//                + newLine
//                + "--------------------------------------------------------------------------------------------" 
//                + "---------";
//
//        Ui testUI = new Ui();
//        testUI.printExpenseAdded(new Expense("Bought cookies", 5.0, "Bakery"));
//        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
//    }
//
//    @Test
//    public void printIncomeAdded_addedOneItem_incomeAddedFeedback() {
//        final String expectedOutput =
//                "----------------------------------------------------------------------------------------------"
//                        + "------- "
//                        + newLine
//                        + "Your most recent earning: "
//                        + newLine
//                        + "[I] Salary August - $5.00 (16 Oct 2021)"
//                        + newLine
//                        + "--------------------------------------------------------------------"
//                        + "------------------------"
//                        + "---------";
//
//        Ui testUI = new Ui();
//        testUI.printIncomeAdded(new Income("Salary August", 5.0, "Salary"));
//        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
//    }
//}
