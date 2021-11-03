package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Magazine;
import seedu.duke.data.Miscellaneous;
import seedu.duke.data.Video;
import seedu.duke.ui.TextUI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author avellinwong01
public class EditCommandTest {
    TextUI ui = new TextUI();
    Parser parser = new Parser();

    @Test
    public void parse_edit_InvalidBookFormatExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Book b = new Book("The Hunger Games", "123", Status.AVAILABLE, null, null,"Suzanne Collins");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123");
            a.processArgs(catalogue);
            new EditBookCommand(a.args, a.toEdit).handlesEditBookCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid/missing values" + System.lineSeparator()
                    + "  (!) Format: edit ID [t|i|a]/ATTRIBUTE", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedBookTitleExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Book b = new Book("The Hunger Games", "123", Status.AVAILABLE, null, null,"Suzanne Collins");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 t/The Hunger Games");
            a.processArgs(catalogue);
            new EditBookCommand(a.args, a.toEdit).handlesEditBookCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New title is the same as the existing title", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedBookIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Book b = new Book("The Hunger Games", "123", Status.AVAILABLE, null, null,"Suzanne Collins");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/123");
            a.processArgs(catalogue);
            new EditBookCommand(a.args, a.toEdit).handlesEditBookCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New ID is the same as the existing ID", e.getMessage());
        }
    }

    @Test
    public void parse_edit_DuplicateBookIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Book b = new Book("The Hunger Games", "123", Status.AVAILABLE, null, null,"Suzanne Collins");
        Book c = new Book("Harry Potter", "124", Status.AVAILABLE, null, null,"JK Rowling");
        try {
            catalogue.add(b);
            catalogue.add(c);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/124");
            a.processArgs(catalogue);
            new EditBookCommand(a.args, a.toEdit).handlesEditBookCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) ID cannot be a duplicate of an existing ID!", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedBookAuthorExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Book b = new Book("The Hunger Games", "123", Status.AVAILABLE, null, null,"Suzanne Collins");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 a/Suzanne Collins");
            a.processArgs(catalogue);
            new EditBookCommand(a.args, a.toEdit).handlesEditBookCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New author is the same as the existing author", e.getMessage());
        }
    }

    @Test
    public void parse_edit_InvalidAudioFormatExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Audio b = new Audio("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123");
            a.processArgs(catalogue);
            new EditAudioCommand(a.args, a.toEdit).handlesEditAudioCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid/missing values" + System.lineSeparator()
                    + "  (!) Format: edit ID [t|i|a|d]/ATTRIBUTE", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedAudioTitleExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Audio b = new Audio("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 t/The Hunger Games");
            a.processArgs(catalogue);
            new EditAudioCommand(a.args, a.toEdit).handlesEditAudioCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New title is the same as the existing title", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedAudioIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Audio b = new Audio("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/123");
            a.processArgs(catalogue);
            new EditAudioCommand(a.args, a.toEdit).handlesEditAudioCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New ID is the same as the existing ID", e.getMessage());
        }
    }

    @Test
    public void parse_edit_DuplicateAudioIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Audio b = new Audio("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        Audio c = new Audio("Harry Potter", "124", Status.AVAILABLE, null, null,"JK Rowling", "5h");
        try {
            catalogue.add(b);
            catalogue.add(c);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/124");
            a.processArgs(catalogue);
            new EditAudioCommand(a.args, a.toEdit).handlesEditAudioCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) ID cannot be a duplicate of an existing ID!", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedAudioArtistExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Audio b = new Audio("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 a/Suzanne Collins");
            a.processArgs(catalogue);
            new EditAudioCommand(a.args, a.toEdit).handlesEditAudioCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New artist is the same as the existing artist", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedAudioDurationExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Audio b = new Audio("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 d/5h");
            a.processArgs(catalogue);
            new EditAudioCommand(a.args, a.toEdit).handlesEditAudioCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New duration is the same as the existing duration", e.getMessage());
        }
    }

    @Test
    public void parse_edit_InvalidVideoFormatExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Video b = new Video("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123");
            a.processArgs(catalogue);
            new EditVideoCommand(a.args, a.toEdit).handlesEditVideoCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid/missing values" + System.lineSeparator()
                    + "  (!) Format: edit ID [t|i|p|d]/ATTRIBUTE", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedVideoTitleExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Video b = new Video("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 t/The Hunger Games");
            a.processArgs(catalogue);
            new EditVideoCommand(a.args, a.toEdit).handlesEditVideoCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New title is the same as the existing title", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedVideoIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Video b = new Video("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/123");
            a.processArgs(catalogue);
            new EditVideoCommand(a.args, a.toEdit).handlesEditVideoCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New ID is the same as the existing ID", e.getMessage());
        }
    }

    @Test
    public void parse_edit_DuplicateVideoIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Video b = new Video("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        Video c = new Video("Harry Potter", "124", Status.AVAILABLE, null, null,
                "JK Rowling", "5h");
        try {
            catalogue.add(b);
            catalogue.add(c);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/124");
            a.processArgs(catalogue);
            new EditVideoCommand(a.args, a.toEdit).handlesEditVideoCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) ID cannot be a duplicate of an existing ID!", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedVideoPublisherExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Video b = new Video("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 p/Suzanne Collins");
            a.processArgs(catalogue);
            new EditVideoCommand(a.args, a.toEdit).handlesEditVideoCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New publisher is the same as the existing publisher", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedVideoDurationExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Video b = new Video("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "5h");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 d/5h");
            a.processArgs(catalogue);
            new EditVideoCommand(a.args, a.toEdit).handlesEditVideoCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New duration is the same as the existing duration", e.getMessage());
        }
    }

    @Test
    public void parse_edit_InvalidMagazineFormatExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Magazine b = new Magazine("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "2nd");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123");
            a.processArgs(catalogue);
            new EditMagazineCommand(a.args, a.toEdit).handlesEditMagazineCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid/missing values" + System.lineSeparator()
                    + "  (!) Format: edit ID [t|i|p|e]/ATTRIBUTE", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedMagazineTitleExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Magazine b = new Magazine("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "2nd");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 t/The Hunger Games");
            a.processArgs(catalogue);
            new EditMagazineCommand(a.args, a.toEdit).handlesEditMagazineCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New title is the same as the existing title", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedMagazineIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Magazine b = new Magazine("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "2nd");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/123");
            a.processArgs(catalogue);
            new EditMagazineCommand(a.args, a.toEdit).handlesEditMagazineCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New ID is the same as the existing ID", e.getMessage());
        }
    }

    @Test
    public void parse_edit_DuplicateMagazineIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Magazine b = new Magazine("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "2nd");
        Magazine c = new Magazine("Harry Potter", "124", Status.AVAILABLE, null, null,
                "JK Rowling", "2nd");
        try {
            catalogue.add(b);
            catalogue.add(c);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/124");
            a.processArgs(catalogue);
            new EditMagazineCommand(a.args, a.toEdit).handlesEditMagazineCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) ID cannot be a duplicate of an existing ID!", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedMagazinePublisherExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Magazine b = new Magazine("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "2nd");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 p/Suzanne Collins");
            a.processArgs(catalogue);
            new EditMagazineCommand(a.args, a.toEdit).handlesEditMagazineCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New publisher is the same as the existing publisher", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedMagazineEditionExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Magazine b = new Magazine("The Hunger Games", "123", Status.AVAILABLE, null, null,
                "Suzanne Collins", "2nd");
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 e/2nd");
            a.processArgs(catalogue);
            new EditMagazineCommand(a.args, a.toEdit).handlesEditMagazineCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New edition is the same as the existing edition", e.getMessage());
        }
    }

    @Test
    public void parse_edit_InvalidMiscellaneousFormatExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Miscellaneous b = new Miscellaneous("The Hunger Games", "123", Status.AVAILABLE, null, null);
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123");
            a.processArgs(catalogue);
            new EditMiscellaneousCommand(a.args, a.toEdit).handlesEditMiscellaneousCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) Invalid/missing values" + System.lineSeparator()
                    + "  (!) Format: edit ID [t|i]/ATTRIBUTE", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedMiscellaneousTitleExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Miscellaneous b = new Miscellaneous("The Hunger Games", "123", Status.AVAILABLE, null, null);
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 t/The Hunger Games");
            a.processArgs(catalogue);
            new EditMiscellaneousCommand(a.args, a.toEdit).handlesEditMiscellaneousCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New title is the same as the existing title", e.getMessage());
        }
    }

    @Test
    public void parse_edit_UnchangedMiscellaneousIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Miscellaneous b = new Miscellaneous("The Hunger Games", "123", Status.AVAILABLE, null, null);
        try {
            catalogue.add(b);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/123");
            a.processArgs(catalogue);
            new EditMiscellaneousCommand(a.args, a.toEdit).handlesEditMiscellaneousCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) New ID is the same as the existing ID", e.getMessage());
        }
    }

    @Test
    public void parse_edit_DuplicateMiscellaneousIdExceptionThrown() {
        Catalogue catalogue = new Catalogue();
        Miscellaneous b = new Miscellaneous("The Hunger Games", "123", Status.AVAILABLE, null, null);
        Miscellaneous c = new Miscellaneous("Harry Potter", "124", Status.AVAILABLE, null, null);
        try {
            catalogue.add(b);
            catalogue.add(c);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
        try {
            EditCommand a = (EditCommand) parser.parse("edit 123 i/124");
            a.processArgs(catalogue);
            new EditMiscellaneousCommand(a.args, a.toEdit).handlesEditMiscellaneousCommand(ui, catalogue);
            fail();
        } catch (Exception e) {
            assertEquals("  (!) ID cannot be a duplicate of an existing ID!", e.getMessage());
        }
    }
}
//@@author avellinwong01
