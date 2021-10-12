package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.item.Catalogue;
import seedu.duke.ui.TextUI;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    public void parse_exit_ExitCommandObject() {
        boolean type = Parser.parse("exit") instanceof ExitCommand;
        assertTrue(type);
    }

    @Test
    public void parse_unknown_UnknownCommandObject() {
        boolean type = Parser.parse("foo 2") instanceof UnknownCommand;
        assertTrue(type);
    }

    @Test
    public void parse_add_AddCommandObject() {
        boolean type = Parser.parse("add t/The Hunger Games i/123") instanceof AddCommand;
        assertTrue(type);
    }

    @Test
    public void parse_add_FormatIncorrectExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            AddCommand a = (AddCommand) Parser.parse("add hello");
            a.handlesAddCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("Incorrect input format. It should be 'add t/TITLE i/ID'", e.getMessage());
        }
    }

    @Test
    public void parse_add_NoTitleExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            AddCommand a = (AddCommand) Parser.parse("add t/ i/123");
            a.handlesAddCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a valid title", e.getMessage());
        }
    }

    @Test
    public void parse_add_NoIdExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        try {
            AddCommand a = (AddCommand) Parser.parse("add t/The Hunger Games i/ ");
            a.handlesAddCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a valid ID", e.getMessage());
        }
    }
}