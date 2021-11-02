package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Magazine;
import seedu.duke.data.Video;
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
        Book b = new Book("To Kill a Mockingbird", "2551", Status.AVAILABLE, "Harper Lee");
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
        Book b = new Book("To Kill a Mockingbird", "2551", Status.AVAILABLE, "Harper Lee");
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
    public void parse_unres_UnreserveCommandObject() {
        Boolean isSameObject = parser.parse("unres 5555") instanceof UnreserveCommand;
        assertTrue(isSameObject);
    }

    @Test
    public void parse_edit_EditCommandObject() {
        boolean type = parser.parse("edit 123 t/The Hunger Games") instanceof EditCommand;
        assertTrue(type);
    }

    @Test
    public void parse_edit_FormatIncorrectExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            EditCommand a = (EditCommand) parser.parse("edit hello");
            a.handlesEditCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid/missing values" + System.lineSeparator()
                    + "  (!) Format: edit ID marker/attribute", e.getMessage());
        }
    }

    @Test
    public void parse_add_InvalidItemExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        // AddCommand add = (AddCommand) parser.parse("add b t/The Hunger Games i/123 a/Suzanne Collins");
        // add.execute(ui, catalogue);
        Book b = new Book("The Hunger Games", "123", Status.AVAILABLE, "Suzanne Collins");
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
    public void parse_edit_InvalidBookMarkerExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        Book b = new Book("The Hunger Games", "123", Status.AVAILABLE, "Suzanne Collins");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 x/Harry Potter");
            a.handlesEditCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Attribute Marker not valid for Book" + System.lineSeparator()
                    + "  (!) Should only be t/, i/ or a/", e.getMessage());
        }
    }

    @Test
    public void parse_edit_InvalidAudioMarkerExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        Audio b = new Audio("The Hunger Games", "123", Status.AVAILABLE, "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 x/Harry Potter");
            a.handlesEditCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Attribute Marker not valid for Audio" + System.lineSeparator()
                    + "  (!) Should only be t/, i/, a/ or d/", e.getMessage());
        }
    }

    @Test
    public void parse_edit_InvalidVideoMarkerExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        Video b = new Video("The Hunger Games", "123", Status.AVAILABLE, "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 x/Harry Potter");
            a.handlesEditCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Attribute Marker not valid for Video" + System.lineSeparator()
                    + "  (!) Should only be t/, i/, p/ or d/", e.getMessage());
        }
    }

    @Test
    public void parse_edit_InvalidMagazineMarkerExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        Magazine b = new Magazine("The Hunger Games", "123", Status.AVAILABLE, "Suzanne Collins", "2nd");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 x/Harry Potter");
            a.handlesEditCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Attribute Marker not valid for Magazine" + System.lineSeparator()
                    + "  (!) Should only be t/, i/, p/ or e/", e.getMessage());
        }
    }

}