package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.common.Status;
import seedu.duke.data.Magazine;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.data.Audio;
import seedu.duke.data.Book;
import seedu.duke.data.Video;
import seedu.duke.ui.TextUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static seedu.duke.common.Messages.KEY_CATEGORY;
import static seedu.duke.common.Messages.KEY_ID;
import static seedu.duke.common.Messages.KEY_STATUS;
import static seedu.duke.common.Messages.KEY_TITLE;
import static seedu.duke.common.Messages.SEARCH_FORMAT_INCORRECT;
import static seedu.duke.common.Messages.DIVIDER;
import static seedu.duke.common.Messages.SEARCH_MESSAGE;
import static seedu.duke.common.Messages.NO_SEARCH_RESULT;
import static seedu.duke.common.Messages.WARN_INVALID_ARGS;

//@@author silinche
/**
 * Command that lists out all items that matches the search criteria.
 * For searching by ID and STATUS, the keyword must be exactly the same.
 * For searching by TITLE, the keyword can be part of the actual title.
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    protected HashMap<String, String> args;
    private String id;
    private String title;
    private String status;
    private String category;

    /**
     * Sole Constructor.
     * @param args Arguments supplied by user in the search command
     */
    public SearchCommand(HashMap<String, String> args) {
        this.args = args;
        if (args.containsKey(KEY_ID)) {
            this.id = args.get(KEY_ID);
        } else {
            this.id = "";
        }
        if (args.containsKey(KEY_TITLE)) {
            this.title = args.get(KEY_TITLE);
        } else {
            this.title = "";
        }
        if (args.containsKey(KEY_STATUS)) {
            this.status = args.get(KEY_STATUS);
        } else {
            this.status = "";
        }
        if (args.containsKey(KEY_CATEGORY)) {
            this.category = args.get(KEY_CATEGORY);
        } else {
            this.category = "";
        }
    }

    /**
     * Checks whether there is at least one valid argument.
     * @return True if there is at least one valid argument.
     */
    private Boolean checkValidArgs() {
        for (Map.Entry<String, String> entry : args.entrySet()) {
            String k = entry.getKey();
            if (k == null) {
                continue;
            }
            if (k.equals(KEY_ID) || k.equals(KEY_TITLE) || k.equals(KEY_STATUS) || k.equals(KEY_CATEGORY)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the there are more than four arguments.
     * @return True if there are indeed more than four arguments
     */
    private Boolean checkAdditionalArgs() {
        for (Map.Entry<String, String> entry : args.entrySet()) {
            String k = entry.getKey();
            if (k == null) {
                continue;
            }
            if (!(k.equals(KEY_ID) || k.equals(KEY_TITLE) || k.equals(KEY_STATUS) || k.equals(KEY_CATEGORY))) {
                return true;
            }
        }
        return false;
    }

    public Integer checkMatches(Item temp) {
        Integer matches = 0;
        for (Map.Entry<String, String> entry : this.args.entrySet()) {
            String k = entry.getKey();
            if (k == null) {
                continue;
            }
            String v = entry.getValue();
            if (k.equals(KEY_ID) && v.equals(temp.getID())) {
                matches++;
            }
            if (k.equals(KEY_TITLE) && temp.getTitle().contains(v)) {
                matches++;
            }
            if (k.equals(KEY_STATUS) && v.equals(temp.getStatus().name())) {
                matches++;
            }
            if (k.equals(KEY_CATEGORY)) {
                switch (v) {
                case "Audio":
                    if (temp instanceof Audio) {
                        matches++;
                    }
                    break;
                case "Book":
                    if (temp instanceof Book) {
                        matches++;
                    }
                    break;
                case "Magazine":
                    if (temp instanceof Magazine) {
                        matches++;
                    }
                    break;
                case "Video":
                    if (temp instanceof Video) {
                        matches++;
                    }
                    break;
                default:
                }
            }
        }
        return matches;
    }

    private void handlesSearchCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (!checkValidArgs()) {
            throw new LibmgrException(SEARCH_FORMAT_INCORRECT);
        }
        if (checkAdditionalArgs()) {
            ui.print(WARN_INVALID_ARGS);
        }
        ArrayList<Item> fourMatch = new ArrayList<>();
        ArrayList<Item> threeMatch = new ArrayList<>();
        ArrayList<Item> twoMatch = new ArrayList<>();
        ArrayList<Item> oneMatch = new ArrayList<>();
        for (Item temp : catalogue.getAllItems()) {
            if (checkMatches(temp).equals(4)) {
                fourMatch.add(temp);
            } else if (checkMatches(temp).equals(3)) {
                threeMatch.add(temp);
            } else if (checkMatches(temp).equals(2)) {
                twoMatch.add(temp);
            } else if (checkMatches(temp).equals(1)) {
                oneMatch.add(temp);
            }
        }
        ui.print(SEARCH_MESSAGE);
        ui.print(DIVIDER);
        for (Item temp : fourMatch) {
            ui.print(temp);
        }
        for (Item temp : threeMatch) {
            ui.print(temp);
        }
        for (Item temp : twoMatch) {
            ui.print(temp);
        }
        for (Item temp : oneMatch) {
            ui.print(temp);
        }
        if (oneMatch.isEmpty() && twoMatch.isEmpty() && threeMatch.isEmpty() && fourMatch.isEmpty()) {
            ui.print(NO_SEARCH_RESULT);
        }
        ui.print(DIVIDER);
    }

    /**
     * Executes search command.
     * Checks for missing and/or additional arguments first, before trying to handle search command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesSearchCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }

}