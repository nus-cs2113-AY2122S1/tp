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