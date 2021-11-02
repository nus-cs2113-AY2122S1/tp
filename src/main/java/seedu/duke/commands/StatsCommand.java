package seedu.duke.commands;

import seedu.duke.common.Status;
import seedu.duke.data.Audio;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.data.Magazine;
import seedu.duke.data.Video;
import seedu.duke.ui.TextUI;

import java.util.ArrayList;

import static seedu.duke.common.Messages.STATS_INVALID_FORMAT;

//@@author avellinwong01
/**
 * Class that encapsulates a command to calculate and display library statistics
 * to the user
 */
public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats all";
    public static final String COMMAND_WORD_ALL = "all";
    public static final String COMMAND_WORD_CATEGORY = "category";
    public static final String COMMAND_WORD_STATUS = "status";
    protected String args;

    /**
     * Sole Constructor.
     *
     * @param args Arguments supplied by the user in the Stats Command
     */
    public StatsCommand(String args) {
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
        long totalNum = currentCatalogue.size();
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
    }

    /**
     * Calculates and displays library statistics by item status.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    public void calcStatusStats(TextUI ui, Catalogue catalogue) {
        ArrayList<Item> currentCatalogue = catalogue.getAllItems();
        long totalNum = currentCatalogue.size();
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
    }

    /**
     * Executes stats command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        String[] argsList = args.split("\\s+");
        // check if argsList[0] is "stats"? 
        switch (argsList[1]) {
        case COMMAND_WORD_ALL:
            calcCategoryStats(ui, catalogue);
            calcStatusStats(ui, catalogue);
            break;
        case COMMAND_WORD_CATEGORY:
            calcCategoryStats(ui, catalogue);
            break;
        case COMMAND_WORD_STATUS:
            calcStatusStats(ui, catalogue);
            break;
        default:
            ui.print(STATS_INVALID_FORMAT);
            break;
        }
    }

}
//@@author avellinwong01
