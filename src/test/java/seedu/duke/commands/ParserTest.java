package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    TextUI ui = new TextUI();
    Parser parser = new Parser();

    @Test
    public void extractArgs_multipleEmptyArgs_LibmgrException() {
        String command = "add a t/ i/ a/Michael Jackson d/42:16";
        String expectedError = "  (!) Attributes cannot be empty!";
        Exception exception = assertThrows(LibmgrException.class, () -> {
            HashMap<String, String> result = parser.extractArgs(command);
        });
        assertTrue(exception.getMessage().contains(expectedError));
    }

    @Test
    public void extractArgs_repeatedArgs_LibmgrException() {
        String command = "add a t/Thriller i/9999 i/5920 a/Michael Jackson d/42:16";
        String expectedError = "  (!) Do not specify an attribute more than once!";
        Exception exception = assertThrows(LibmgrException.class, () -> {
            HashMap<String, String> result = parser.extractArgs(command);
        });
        assertTrue(exception.getMessage().contains(expectedError));
    }

    @Test
    public void extractArgs_singleCommandNoArgs_Hashmap() {
        HashMap<String, String> expected = new HashMap<>();
        expected.put(null, "add");
        try {
            HashMap<String, String> result = parser.extractArgs("add");
            assertEquals(expected, result);
        } catch (LibmgrException e) {
            e.getMessage();
        }
    }

    @Test
    public void extractArgs_singleCommandMultipleArg_Hashmap() {
        HashMap<String, String> expected = new HashMap<>();
        expected.put(null, "add a");
        expected.put("t", "Thriller");
        expected.put("i", "5920");
        expected.put("a", "Michael Jackson");
        expected.put("d", "42:16");
        try {
            HashMap<String, String> result = parser.extractArgs("add a t/Thriller i/5920 a/Michael Jackson d/42:16");
            assertEquals(expected, result);
        } catch (LibmgrException e) {
            e.getMessage();
        }

    }

    @Test
    public void extractArgs_singleCommandMultipleArgWithDelimiter_Hashmap() {
        HashMap<String, String> expected = new HashMap<>();
        expected.put(null, "add a");
        expected.put("t", "Thriller/Beat It");
        expected.put("i", "5920");
        expected.put("a", "Michael Jackson");
        expected.put("d", "42:16/4:50");
        try {
            HashMap<String, String> result = parser.extractArgs("add a t/Thriller/Beat It "
                    + "i/5920 a/Michael Jackson d/42:16/4:50");
            assertEquals(expected, result);
        } catch (LibmgrException e) {
            e.getMessage();
        }
    }

    @Test
    public void parse_exit_ExitCommandObject() {
        Boolean isSameObject = parser.parse("exit") instanceof ExitCommand;
        assertTrue(isSameObject);
    }

    @Test
    public void parse_unknown_UnknownCommandObject() {
        Boolean isSameObject = parser.parse("foo 2") instanceof UnknownCommand;
        assertTrue(isSameObject);
    }

    @Test
    public void parse_help_HelpCommandObject() {
        Boolean isSameObject = parser.parse("help") instanceof HelpCommand;
        assertTrue(isSameObject);
    }

    @Test
    public void parse_add_AddCommandObject() {
        Boolean isSameObject = parser.parse("add a t/Thriller i/5920 "
                + "a/Michael Jackson d/42:16") instanceof AddCommand;
        assertTrue(isSameObject);
    }


    @Test
    public void parse_loan_LoanCommandObject() {
        boolean isSameObject = parser.parse("loan i/2551 d/12-11-2021 u/johnsmith") instanceof LoanCommand;
        assertTrue(isSameObject);
    }

    @Test
    public void parse_loan_FormatIncorrectExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            LoanCommand a = (LoanCommand) parser.parse("loan");
            a.handleLoanCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid/missing values" + System.lineSeparator()
                    + "  (!) Format: loan i/ID u/USER d/DUE_DATE(dd-mm-yyyy)", e.getMessage());
        }
    }

    @Test
    public void parse_loan_InvalidDateInput() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        // AddCommand add = (AddCommand) parser.parse("add b t/The Hunger Games i/123 a/Suzanne Collins");
        // add.execute(ui, catalogue);
        Book b = new Book("To Kill a Mockingbird", "2551", Status.AVAILABLE, null, null, "Harper Lee");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            LoanCommand a = (LoanCommand) parser.parse("loan i/2551 d/12-Oct-2021 u/johnsmith");
            a.handleLoanCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void parse_return_ReturnCommandObject() {
        boolean isSameObject = parser.parse("return 2551") instanceof ReturnCommand;
        assertTrue(isSameObject);
    }

    @Test
    public void parse_return_InvalidItemID() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            ReturnCommand a = (ReturnCommand) parser.parse("return");
            a.handleReturnCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid Item ID!", e.getMessage());
        }
    }

    @Test
    public void parse_return_ReturnUnloanedItem() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        // AddCommand add = (AddCommand) parser.parse("add b t/The Hunger Games i/123 a/Suzanne Collins");
        // add.execute(ui, catalogue);
        Book b = new Book("To Kill a Mockingbird", "2551", Status.AVAILABLE, null, null, "Harper Lee");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            ReturnCommand a = (ReturnCommand) parser.parse("return 2551");
            a.handleReturnCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Item is not on loan!", e.getMessage());
        }
    }

    @Test
    public void parse_deadline_DeadlineCommandObject() {
        boolean isSameObject = parser.parse("deadline today") instanceof DeadlineCommand;
        assertTrue(isSameObject);
    }

    @Test
    public void parse_deadline_EmptyDescription() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            DeadlineCommand a = (DeadlineCommand) parser.parse("deadline");
            a.handleDeadlineCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals(" (!) Oops! Please specify the due date "
                    + "(today/overdue/specific date)!", e.getMessage());
        }
    }

    @Test
    public void parse_deadline_InvalidDateInput() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            DeadlineCommand a = (DeadlineCommand) parser.parse("deadline d/12-Oct-2021");
            a.handleDeadlineCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void parse_unres_UnreserveCommandObject() {
        Boolean isSameObject = parser.parse("unres 5555") instanceof UnreserveCommand;
        assertTrue(isSameObject);
    }

    @Test
    public void parse_listCommandObject() {
        Boolean type = parser.parse("list") instanceof ListCommand;
        assertTrue(type);
    }

    @Test
    public void parse_list_FormatIncorrectExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            ListCommand l = (ListCommand) parser.parse("list all");
            l.handlesListCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals(" (!) Invalid listing command" + System.lineSeparator()
                    + " (!) Format: 'list'", e.getMessage());
        }
    }

    @Test
    public void parse_searchCommandObject() {
        Boolean type = parser.parse("search i/001") instanceof SearchCommand;
        assertTrue(type);
    }

    @Test
    public void parse_search_FormatIncorrectExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            SearchCommand s = (SearchCommand) parser.parse("search q/ssss");
            s.handlesSearchCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid searching format!" + System.lineSeparator()
                    + "  (!) Format: 'search i/ID t/TITLE s/STATUS(LOANED/AVAILABLE/RESERVED) "
                    + "c/CATEGORY(Magazine/Book/Audio/Video)' or its subset", e.getMessage());
        }
    }

    @Test
    public void parse_edit_EditCommandObject() {
        boolean type = parser.parse("edit 123 t/The Hunger Games id/124") instanceof EditCommand;
        assertTrue(type);
    }

    @Test
    public void parse_edit_FormatIncorrectExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            EditCommand a = (EditCommand) parser.parse("edit ");
            a.handlesEditCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid/missing values" + System.lineSeparator()
                    + "  (!) Format: edit ID KEY/ATTRIBUTE", e.getMessage());
        }
    }


    @Test
    public void parse_edit_InvalidItemExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        Book b = new Book("The Hunger Games", "123", Status.AVAILABLE, null, null,"Suzanne Collins");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 124 t/Harry Potter");
            a.handlesEditCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid ID entered. No such item in library", e.getMessage());
        }
    }

    @Test
    public void parse_info_InfoCommandObject() {
        boolean type = parser.parse("info all") instanceof InfoCommand;
        assertTrue(type);
    }

    @Test
    public void parse_info_InfoInvalidFormatExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        String args = "info hello";
        try {
            InfoCommand a = (InfoCommand) parser.parse(args);
            a.handlesInfoCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid Info command" + System.lineSeparator()
                    + "  (!) Format:" + System.lineSeparator()
                    + "  1. info all" + System.lineSeparator()
                    + "  2. info category" + System.lineSeparator()
                    + "  3. info status", e.getMessage());
        }
    }

}