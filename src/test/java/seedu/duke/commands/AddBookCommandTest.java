package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.ui.TextUI;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddBookCommandTest {
    Parser parser = new Parser();
    Catalogue catalogue = new Catalogue();
    TextUI ui = new TextUI();

    String validInput = "add b t/To Kill a Mockingbird i/2551 a/Harper Lee";
    String missingAttribute = "add b i/2551 a/Harper Lee";
    String additionalAttributes = "add b t/To Kill a Mockingbird i/2551 a/Harper Lee extra/asd";

    @Test
    public void execute_validInput_sameObjectExists() {
        Book newBook = new Book("To Kill a Mockingbird","2551", Status.AVAILABLE,
                null, null, "Harper Lee");
        Command command = parser.parse(validInput);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newBook, (Book) catalogue.getItem("2551"));
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
        Book newBook = new Book("To Kill a Mockingbird","2551", Status.AVAILABLE,
                null, null, "Harper Lee");
        Command command = parser.parse(additionalAttributes);
        try {
            command.execute(ui, catalogue);
            Boolean isEqual = compareItem(newBook, (Book) catalogue.getItem("2551"));
            assertTrue(isEqual);
        } catch (LibmgrException e) {
            System.out.print(e);
        }
    }

    private Boolean compareItem(Book item1, Book item2) {
        if (!item1.getTitle().equals(item2.getTitle())) {
            return false;
        } else if (!item1.getID().equals(item2.getID())) {
            return false;
        } else if (!item1.getAuthor().equals(item2.getAuthor())) {
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