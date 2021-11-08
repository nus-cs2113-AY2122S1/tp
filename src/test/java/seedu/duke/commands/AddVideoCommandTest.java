package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Magazine;
import seedu.duke.data.Video;
import seedu.duke.ui.TextUI;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddVideoCommandTest {
    Parser parser = new Parser();
    Catalogue catalogue = new Catalogue();
    TextUI ui = new TextUI();

    String validInput = "add v t/Casino Royale i/095680 p/Sony Pictures d/144 minutes";
    String missingAttribute = "add v t/Casino Royale i/095680";
    String additionalAttributes = "add v t/Casino Royale i/095680 p/Sony Pictures d/144 minutes qqq/asd";

    @Test
    public void execute_validInput_sameObjectExists() {
        Video newVideo = new Video("Casino Royale","095680", Status.AVAILABLE,
                null, null, "Sony Pictures", "144 minutes");
        Command command = parser.parse(validInput);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newVideo, (Video) catalogue.getItem("095680"));
            assertTrue(isEqual);
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    @Test
    public void execute_missingAttribute_nullPointerException() {
        Command command = parser.parse(missingAttribute);
        try {
            command.execute(ui, catalogue);
            assertThrows(NullPointerException.class, () ->  {
                Video item = (Video) catalogue.getItem("095680");
            });
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    @Test
    public void execute_additionalAttribute_sameObjectExists() {
        Video newVideo = new Video("Casino Royale","095680", Status.AVAILABLE,
                null, null, "Sony Pictures", "144 minutes");
        Command command = parser.parse(additionalAttributes);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newVideo, (Video) catalogue.getItem("095680"));
            assertTrue(isEqual);
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    private Boolean compareItem(Video item1, Video item2) {
        if (!item1.getTitle().equals(item2.getTitle())) {
            return false;
        } else if (!item1.getID().equals(item2.getID())) {
            return false;
        } else if (!item1.getTitle().equals(item2.getTitle())) {
            return false;
        } else if (!item1.getDuration().equals(item2.getDuration())) {
            return false;
        } else if (!item1.getPublisher().equals(item2.getPublisher())) {
            return false;
        } else if (!item1.getStatus().equals(item2.getStatus())) {
            return false;
        } else if (!Objects.equals(item1.getDueDate(), item2.getDueDate())) {
            return false;
        } else if (!Objects.equals(item1.getLoanee(), item2.getLoanee())) {
            return false;
        } else {
            return true;
        }
    }
}