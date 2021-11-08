package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Magazine;
import seedu.duke.ui.TextUI;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddMagazineCommandTest {
    Parser parser = new Parser();
    Catalogue catalogue = new Catalogue();
    TextUI ui = new TextUI();

    String validInput = "add m i/!@#$<>?/\\[] t/Time Magazine e/oct/25/2021 p/Time USA";
    String missingAttribute = "add m t/Time Magazine i/58720a e/oct252021";
    String additionalAttributes = "add m i/!@#$<>?/\\[] t/Time Magazine e/oct/25/2021 p/Time USA";

    @Test
    public void execute_validInput_sameObjectExists() {
        Magazine newMagazine = new Magazine("Time Magazine","!@#$<>?/\\[]", Status.AVAILABLE,
                null, null, "Time USA", "oct/25/2021");
        Command command = parser.parse(validInput);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newMagazine, (Magazine) catalogue.getItem("!@#$<>?/\\[]"));
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
                Magazine item = (Magazine) catalogue.getItem("5920");
            });
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    @Test
    public void execute_additionalAttribute_sameObjectExists() {
        Magazine newMagazine = new Magazine("Time Magazine","!@#$<>?/\\[]", Status.AVAILABLE,
                null, null, "Time USA", "oct/25/2021");
        Command command = parser.parse(additionalAttributes);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newMagazine, (Magazine) catalogue.getItem("!@#$<>?/\\[]"));
            assertTrue(isEqual);
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    private Boolean compareItem(Magazine item1, Magazine item2) {
        if (!item1.getTitle().equals(item2.getTitle())) {
            return false;
        } else if (!item1.getID().equals(item2.getID())) {
            return false;
        } else if (!item1.getPublisher().equals(item2.getPublisher())) {
            return false;
        } else if (!item1.getEdition().equals(item2.getEdition())) {
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