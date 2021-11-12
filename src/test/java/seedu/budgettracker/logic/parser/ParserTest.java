package seedu.budgettracker.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.logic.commands.AddBudgetCommand;
import seedu.budgettracker.logic.commands.AddExpenditureCommand;
import seedu.budgettracker.logic.commands.AddLoanCommand;
import seedu.budgettracker.logic.commands.Command;
import seedu.budgettracker.logic.commands.CsvCommand;
import seedu.budgettracker.logic.commands.DeleteAllExpenditureCommand;
import seedu.budgettracker.logic.commands.DeleteAllLoanCommand;
import seedu.budgettracker.logic.commands.DeleteBudgetCommand;
import seedu.budgettracker.logic.commands.DeleteMultipleExpenditureCommand;
import seedu.budgettracker.logic.commands.DeleteMultipleLoanCommand;
import seedu.budgettracker.logic.commands.DeleteSingleExpenditureCommand;
import seedu.budgettracker.logic.commands.DeleteSingleLoanCommand;
import seedu.budgettracker.logic.commands.EditBudgetCommand;
import seedu.budgettracker.logic.commands.EditExpenditureCommand;
import seedu.budgettracker.logic.commands.EditLoanCommand;
import seedu.budgettracker.logic.commands.ExitCommand;
import seedu.budgettracker.logic.commands.FindCommand;
import seedu.budgettracker.logic.commands.HelpCommand;
import seedu.budgettracker.logic.commands.ListRecordsCommand;
import seedu.budgettracker.logic.commands.StatCategoryCommand;
import seedu.budgettracker.logic.commands.StatYearCommand;
import seedu.budgettracker.logic.commands.YearCommand;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    void splitArgs() {
        try {
            HashMap<String, String> testMap;
            testMap = Parser.splitArgs("a/test1 b/test2 c/test3", new String[]{"a/", "b/", "c/", "d/"});
            assertEquals(testMap.get("a/"),"test1");
            assertEquals(testMap.get("b/"),"test2");
            assertEquals(testMap.get("c/"),"test3");
            assertEquals(testMap.get("d/"),"");
            assertEquals(testMap.size(),4);
        } catch (ParserException e) {
            fail();
        }
    }

    @Test
    void parseCommand_addBudget_addBudgetCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("add -b a/300 m/1");
        assertTrue(testCommand instanceof AddBudgetCommand);
    }

    @Test
    void parseCommand_addExpenditure_addExpenditureCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("add -e n/Test a/300 d/2021-11-11 c/GENERAL");
        assertTrue(testCommand instanceof AddExpenditureCommand);
    }

    @Test
    void parseCommand_addLoan_addLoanCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("add -l n/Test a/300 d/2021-11-11");
        assertTrue(testCommand instanceof AddLoanCommand);
    }

    @Test
    void parseCommand_deleteSingleExpenditure_deleteSingleExpenditureCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("delete -e m/1 i/1");
        assertTrue(testCommand instanceof DeleteSingleExpenditureCommand);
    }

    @Test
    void parseCommand_deleteAllExpenditure_deleteAllExpenditureCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("delete -e m/1");
        assertTrue(testCommand instanceof DeleteAllExpenditureCommand);
    }

    @Test
    void parseCommand_deleteMultiExpenditure_deleteMultiExpenditureCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("delete -e m/1 i/1-3");
        assertTrue(testCommand instanceof DeleteMultipleExpenditureCommand);
    }

    @Test
    void parseCommand_deleteBudget_deleteBudgetCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("delete -b m/1");
        assertTrue(testCommand instanceof DeleteBudgetCommand);
    }

    @Test
    void parseCommand_deleteSingleLoan_deleteSingleLoanCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("delete -l m/1 i/1");
        assertTrue(testCommand instanceof DeleteSingleLoanCommand);
    }

    @Test
    void parseCommand_deleteAllLoan_deleteAllLoanCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("delete -l m/1");
        assertTrue(testCommand instanceof DeleteAllLoanCommand);
    }

    @Test
    void parseCommand_deleteMultiLoan_deleteMultiLoanCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("delete -l m/1 i/1-3");
        assertTrue(testCommand instanceof DeleteMultipleLoanCommand);
    }

    @Test
    void parseCommand_listMonth_listMonthCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("list m/1");
        assertTrue(testCommand instanceof ListRecordsCommand);
    }

    @Test
    void parseCommand_listMonthCategory_listMonthCategoryCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("list m/1 c/GENERAL");
        assertTrue(testCommand instanceof ListRecordsCommand);
    }

    @Test
    void parseCommand_exit_exitCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("bye");
        assertTrue(testCommand instanceof ExitCommand);
    }

    @Test
    void parseCommand_year_yearCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("year 1999");
        assertTrue(testCommand instanceof YearCommand);
    }

    @Test
    void parseCommand_help_helpCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("help");
        assertTrue(testCommand instanceof HelpCommand);
    }

    @Test
    void parseCommand_find_findCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("find test");
        assertTrue(testCommand instanceof FindCommand);
    }

    @Test
    void parseCommand_csv_csvCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("csv");
        assertTrue(testCommand instanceof CsvCommand);
    }

    @Test
    void parseCommand_editBudget_editBudgetCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("edit -b m/1 a/500");
        assertTrue(testCommand instanceof EditBudgetCommand);
    }

    @Test
    void parseCommand_editExpenditure_editExpenditureCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("edit -e m/1 i/1 n/Test a/100 d/2021-10-10");
        assertTrue(testCommand instanceof EditExpenditureCommand);
    }

    @Test
    void parseCommand_editLoan_editLoanCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("edit -l m/1 i/1 n/Test a/100 d/2021-10-10");
        assertTrue(testCommand instanceof EditLoanCommand);
    }

    @Test
    void parseCommand_statCategory_statCategoryCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("stat -c m/1");
        assertTrue(testCommand instanceof StatCategoryCommand);
    }

    @Test
    void parseCommand_statYear_statYearCommand() {
        Parser testParser = new Parser();
        Command testCommand = testParser.parseCommand("stat -y t/1");
        assertTrue(testCommand instanceof StatYearCommand);
    }
}
