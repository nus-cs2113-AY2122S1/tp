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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EditCommandTest {
    @Test
    public void parse_edit_InvalidBookMarkerExceptionThrown() {
        TextUI ui = new TextUI();
        Catalogue catalogue = new Catalogue();
        Parser parser = new Parser();
        Book b = new Book("The Hunger Games", "123", Status.AVAILABLE, null, null,"Suzanne Collins");
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
        Audio b = new Audio("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
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
        Video b = new Video("The Hunger Games", "123", Status.AVAILABLE, null, null, "Suzanne Collins", "5h");
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
        Magazine b = new Magazine("The Hunger Games", "123", Status.AVAILABLE, null, null,"Suzanne Collins", "2nd");
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
