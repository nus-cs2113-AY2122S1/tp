package seedu.traveller;

import org.junit.jupiter.api.Test;
import seedu.traveller.commands.DeleteCommand;
import seedu.traveller.commands.EditCommand;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.commands.ViewCommand;
import seedu.traveller.commands.ShortestCommand;
import seedu.traveller.commands.AddItemCommand;
import seedu.traveller.commands.SearchItemCommand;
import seedu.traveller.commands.EditItemCommand;
import seedu.traveller.commands.AddDayCommand;
import seedu.traveller.commands.ExitCommand;
import seedu.traveller.exceptions.CommandNotFoundException;
import seedu.traveller.exceptions.IllegalTripNameException;
import seedu.traveller.exceptions.InvalidAddDayFormatException;
import seedu.traveller.exceptions.InvalidAddItemFormatException;
import seedu.traveller.exceptions.InvalidNewFormatException;
import seedu.traveller.exceptions.InvalidNumberOfDaysException;
import seedu.traveller.exceptions.InvalidViewCommandException;
import seedu.traveller.exceptions.TravellerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@@author Uxinnn
public class ParserTest {
    @Test
    public void parse_success() {
        NewCommand newCommand = new NewCommand("trip0", "CHN", "JPN");
        EditCommand editCommand = new EditCommand("trip1", "SIN", "MLY");
        DeleteCommand deleteCommand = new DeleteCommand("trip2");
        ViewCommand viewCommand = new ViewCommand("all");
        ShortestCommand shortestCommand = new ShortestCommand("dist", "SKR", "JPN");
        AddDayCommand addDayCommand = new AddDayCommand("trip3", 1);
        AddItemCommand addItemCommand =
                new AddItemCommand("trip4", 0, "1-2am", "sleep at home");
        SearchItemCommand searchItemCommand =
                new SearchItemCommand("trip4", 0, "sleep at home");
        EditItemCommand editItemCommand =
                new EditItemCommand("trip4", 0, "7am", "wake up from bed", 1);
        ExitCommand exitCommand = new ExitCommand();

        try {
            assertEquals(newCommand.toString(), Parser.parse("new trip0 /from CHN /to JPN").toString());
            assertEquals(editCommand.toString(), Parser.parse("edit trip1 /from SIN /to MLY").toString());
            assertEquals(deleteCommand.toString(), Parser.parse("delete trip2").toString());
            assertEquals(viewCommand.toString(), Parser.parse("view all").toString());
            assertEquals(shortestCommand.toString(), Parser.parse("shortest /from SKR /to JPN").toString());
            assertEquals(addDayCommand.toString(), Parser.parse("add-day trip3").toString());
            assertEquals(addItemCommand.toString(),
                    Parser.parse("add-item trip4 /day 0 /time 1-2am /name sleep at home").toString());
            assertEquals(searchItemCommand.toString(),
                    Parser.parse("search-item trip4 /name sleep at home").toString());
            assertEquals(editItemCommand.toString(),
                    Parser.parse("edit-item 1 trip4 /time 7am /name wake up from bed").toString());
            assertEquals(exitCommand.toString(), Parser.parse("exit").toString());
        } catch (TravellerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        assertThrows(CommandNotFoundException.class, () -> {
            Parser.parse("bad command");
        });
    }

    @Test
    public void parse_newCommand_exceptionThrown() {
        // Invalid trip name, all
        assertThrows(IllegalTripNameException.class, () -> {
            Parser.parse("new all /from CHN /to JPN");
        });
        // Missing trip name
        assertThrows(IllegalTripNameException.class, () -> {
            Parser.parse("new  /from CHN /to JPN");
        });
        // Missing /to flag
        assertThrows(InvalidNewFormatException.class, () -> {
            Parser.parse("new trip /from CHN JPN");
        });
        // Missing /from flag
        assertThrows(InvalidNewFormatException.class, () -> {
            Parser.parse("new trip CHN /to JPN");
        });
        // Missing flags and values
        assertThrows(InvalidNewFormatException.class, () -> {
            Parser.parse("new trip");
        });
    }

    @Test
    public void parse_addDayCommand_exceptionThrown() {
        // Invalid /day flag input, character
        assertThrows(InvalidNumberOfDaysException.class, () -> {
            Parser.parse("add-day trip /day a");
        });
        // Invalid /day flag input, negative integer
        assertThrows(InvalidNumberOfDaysException.class, () -> {
            Parser.parse("add-day trip /day -1");
        });
        // Missing /day flag input
        assertThrows(InvalidNumberOfDaysException.class, () -> {
            Parser.parse("add-day trip /day ");
        });
        // Missing /day flag
        assertThrows(InvalidAddDayFormatException.class, () -> {
            Parser.parse("add-day trip 3");
        });
        // Missing whitespace between /day flag and value
        assertThrows(InvalidAddDayFormatException.class, () -> {
            Parser.parse("add-day trip /day3");
        });
    }

    @Test
    public void parse_addItemCommand_exceptionThrown() {
        // Invalid /day flag input, character
        assertThrows(InvalidNumberOfDaysException.class, () -> {
            Parser.parse("add-item trip /day a /time 7pm /name Eat dinner");
        });
        // Invalid /day flag input, negative integer
        assertThrows(InvalidNumberOfDaysException.class, () -> {
            Parser.parse("add-item trip /day -1 /time 7pm /name Eat dinner");
        });
        // Missing /day flag value
        assertThrows(InvalidNumberOfDaysException.class, () -> {
            Parser.parse("add-item trip /day  /time 7pm /name Eat dinner");
        });
        // Missing /day flag
        assertThrows(InvalidAddItemFormatException.class, () -> {
            Parser.parse("add-item trip /time 7pm /name Eat dinner");
        });
        // Missing /time flag
        assertThrows(InvalidAddItemFormatException.class, () -> {
            Parser.parse("add-item trip /day 0 /name Eat dinner");
        });
        // Missing /name flag
        assertThrows(InvalidAddItemFormatException.class, () -> {
            Parser.parse("add-item trip /day 0 /time 7pm");
        });
        // Wrong order of flags in add-item command
        assertThrows(InvalidAddItemFormatException.class, () -> {
            Parser.parse("add-item trip /time 7pm /name Eat dinner /day 0");
        });
        assertThrows(InvalidAddItemFormatException.class, () -> {
            Parser.parse("add-item trip /day /time /name ");
        });
    }

    @Test
    public void parse_viewCommand_exceptionThrown() {
        assertThrows(InvalidViewCommandException.class, () -> {
            Parser.parse("view ");
        });
    }
}
