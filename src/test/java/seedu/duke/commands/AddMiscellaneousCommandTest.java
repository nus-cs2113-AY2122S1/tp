package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Magazine;
import seedu.duke.data.Miscellaneous;
import seedu.duke.ui.TextUI;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddMiscellaneousCommandTest {
    Parser parser = new Parser();
    Catalogue catalogue = new Catalogue();
    TextUI ui = new TextUI();

    String validInput = "add i t/Scrabble i/0513895";
    String missingAttribute = "add i t/Scrabble";
    String additionalAttributes = "add i i/0513895 t/Scrabble ";

    @Test
    public void execute_validInput_sameObjectExists() {
        Miscellaneous newItem = new Miscellaneous("Scrabble","0513895", Status.AVAILABLE,
                null, null);
        Command command = parser.parse(validInput);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newItem, (Miscellaneous) catalogue.getItem("0513895"));
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
                Miscellaneous item = (Miscellaneous) catalogue.getItem("0513895");
            });
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    @Test
    public void execute_additionalAttribute_sameObjectExists() {
        Miscellaneous newItem = new Miscellaneous("Scrabble","0513895", Status.AVAILABLE,
                null, null);
        Command command = parser.parse(additionalAttributes);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newItem, (Miscellaneous) catalogue.getItem("0513895"));
            assertTrue(isEqual);
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    private Boolean compareItem(Miscellaneous item1, Miscellaneous item2) {
        if (!item1.getTitle().equals(item2.getTitle())) {
            return false;
        } else if (!item1.getID().equals(item2.getID())) {
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