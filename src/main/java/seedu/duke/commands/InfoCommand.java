package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.data.Magazine;
import seedu.duke.data.Video;
import seedu.duke.ui.TextUI;

import java.util.ArrayList;

import static seedu.duke.common.Messages.DIVIDER;
import static seedu.duke.common.Messages.INFO_INVALID_FORMAT;

//@@author avellinwong01
/**
 * Class that encapsulates a command to calculate and display library information
 * to the user.
 */
public class InfoCommand extends Command {
    public static final String COMMAND_WORD = "info";
    public static final String COMMAND_WORD_ALL = "all";
    public static final String COMMAND_WORD_CATEGORY = "category";
    public static final String COMMAND_WORD_STATUS = "status";
    protected String args;

    /**
     * Sole Constructor.
     *
     * @param args Arguments supplied by the user in the Info Command
     */
    public InfoCommand(String args) {
        this.args = args.strip();
    }

    /**
     * Calculates and displays library statistics by item category.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    public void calcCategoryStats(TextUI ui, Catalogue catalogue) {
        ArrayList<Item> currentCatalogue = catalogue.getAllItems();
        long miscellaneousNum = 0;
        long audioNum = 0;
        long bookNum = 0;
        long magazineNum = 0;
        long videoNum = 0;
        for (Item i: currentCatalogue) {
            if (i instanceof Audio) {
                audioNum += 1;
            } else if (i instanceof Book) {
                bookNum += 1;
            } else if (i instanceof Magazine) {
                magazineNum += 1;
            } else if (i instanceof Video) {
                videoNum += 1;
            } else {
                miscellaneousNum += 1;
            }
        }
        // Print out numbers
        ui.print("  (+) Statistics of Library by Item Category");
        ui.print(DIVIDER);
        ui.print("  (+) Number of Audio Items: " + audioNum);
        ui.print("  (+) Number of Book Items: " + bookNum);
        ui.print("  (+) Number of Magazine Items: " + magazineNum);
        ui.print("  (+) Number of Video Items: " + videoNum);
        ui.print("  (+) Number of Miscellaneous Items: " + miscellaneousNum);

    }

    /**
     * Calculates and displays library statistics by item status.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    public void calcStatusStats(TextUI ui, Catalogue catalogue) {
        ArrayList<Item> currentCatalogue = catalogue.getAllItems();
        long availableNum = 0;
        long loanedNum = 0;
        long reservedNum = 0;
        for (Item i: currentCatalogue) {
            if (i.getStatus().equals(Status.AVAILABLE)) {
                availableNum += 1;
            } else if (i.getStatus().equals(Status.LOANED)) {
                loanedNum += 1;
            } else if (i.getStatus().equals(Status.RESERVED)) {
                reservedNum += 1;
            }
        }
        // Print out numbers
        ui.print("  (+) Statistics of Library by Item Status");
        ui.print(DIVIDER);
        ui.print("  (+) Number of Available Items: " + availableNum);
        ui.print("  (+) Number of Loaned Items: " + loanedNum);
        ui.print("  (+) Number of Reserved Items: " + reservedNum);
    }

    /**
     * Calculates and displays the total number of items in the library.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    public void calcTotalItems(TextUI ui, Catalogue catalogue) {
        ArrayList<Item> currentCatalogue = catalogue.getAllItems();
        long totalNum = currentCatalogue.size();
        ui.print("  (+) Total Number of Items in Library: " + totalNum + System.lineSeparator());
    }

    /**
     * Processes Info Command, including exceptions.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when the user input is invalid
     */
    public void handlesInfoCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        String[] argsList = args.split("\\s+");
        if (argsList.length != 2) {
            throw new LibmgrException(INFO_INVALID_FORMAT);
        }
        if (argsList[0].equals("info")) {
            switch (argsList[1]) {
            case COMMAND_WORD_ALL:
                calcTotalItems(ui, catalogue);
                calcCategoryStats(ui, catalogue);
                ui.print("");
                calcStatusStats(ui, catalogue);
                break;
            case COMMAND_WORD_CATEGORY:
                calcTotalItems(ui, catalogue);
                calcCategoryStats(ui, catalogue);
                break;
            case COMMAND_WORD_STATUS:
                calcTotalItems(ui, catalogue);
                calcStatusStats(ui, catalogue);
                break;
            default:
                throw new LibmgrException(INFO_INVALID_FORMAT);
            }
        } else {
            throw new LibmgrException(INFO_INVALID_FORMAT);
        }
    }

    /**
     * Executes info command, including exception handling.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesInfoCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
//@@author avellinwong01
