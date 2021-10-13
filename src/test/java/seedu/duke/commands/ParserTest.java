package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    Parser parser = new Parser();

    @Test
    public void parse_exit_ExitCommandObject() {
        boolean type = parser.parse("exit") instanceof ExitCommand;
        assertTrue(type);
    }

    @Test
    public void parse_unknown_UnknownCommandObject() {
        boolean type = parser.parse("foo 2") instanceof UnknownCommand;
        assertTrue(type);
    }

    @Test
    public void parse_add_AddCommandObject() {
        boolean type = parser.parse("add t/The Hunger Games i/123") instanceof AddCommand;
        assertTrue(type);
    }

    @Test
    public void parse_loan_LoanCommandObject() {
        boolean type = parser.parse("loan 123") instanceof LoanCommand;
        assertTrue(type);
    }

    @Test
    public void parse_return_ReturnCommandObject() {
        boolean type = parser.parse("return 123") instanceof ReturnCommand;
        assertTrue(type);
    }

    @Test
    public void parse_add_FormatIncorrectExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            AddCommand a = (AddCommand) parser.parse("add hello");
            a.handlesAddCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid/missing values!" + System.lineSeparator()
                    + "  (!) Format: add t/TITLE i/ID", e.getMessage());
        }
    }

    @Test
    public void parse_add_NoTitleExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            AddCommand a = (AddCommand) parser.parse("add t/ i/123");
            a.handlesAddCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Please enter a valid title", e.getMessage());
        }
    }

    @Test
    public void parse_add_NoIdExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            AddCommand a = (AddCommand) parser.parse("add t/The Hunger Games i/ ");
            a.handlesAddCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid Item ID!", e.getMessage());
        }
    }
}