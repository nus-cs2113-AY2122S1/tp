package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AddAudioCommandTest {
    Parser parser = new Parser();
    Catalogue catalogue = new Catalogue();
    TextUI ui = new TextUI();

    String validInput = "add a t/Thriller i/5920 a/Michael Jackson d/42:16";
    String missingAttribute = "add a t/Thriller a/Michael Jackson d/42:16";
    String additionalAttributes = "add a t/Thriller i/5920 a/Michael Jackson d/42:16 extra/asd";

    @Test
    public void execute_validInput_sameObjectExists() {
        Audio newAudio = new Audio("Thriller","5920", Status.AVAILABLE,
                null, null, "Michael Jackson", "42:16");
        Command command = parser.parse(validInput);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newAudio, (Audio) catalogue.getItem("5920"));
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
                Audio item = (Audio) catalogue.getItem("5920");
            });
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    @Test
    public void execute_additionalAttribute_sameObjectExists() {
        Audio newAudio = new Audio("Thriller","5920", Status.AVAILABLE,
                null, null, "Michael Jackson", "42:16");
        Command command = parser.parse(additionalAttributes);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newAudio, (Audio) catalogue.getItem("5920"));
            assertTrue(isEqual);
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    private Boolean compareItem(Audio item1, Audio item2) {
        if (!item1.getTitle().equals(item2.getTitle())) {
            return false;
        } else if (!item1.getID().equals(item2.getID())) {
            return false;
        } else if (!item1.getArtist().equals(item2.getArtist())) {
            return false;
        } else if (!item1.getDuration().equals(item2.getDuration())) {
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