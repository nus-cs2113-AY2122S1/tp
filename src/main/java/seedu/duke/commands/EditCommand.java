package seedu.duke.commands;

import seedu.duke.common.LibmgrException;
import seedu.duke.data.Audio;
import seedu.duke.data.Book;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.data.Magazine;
import seedu.duke.data.Video;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.EDIT_INVALID_FORMAT;
import static seedu.duke.common.Messages.EDIT_INVALID_ITEM;
import static seedu.duke.common.Messages.EDIT_MESSAGE;
import static seedu.duke.common.Messages.EDIT_INVALID_AUDIO;
import static seedu.duke.common.Messages.EDIT_INVALID_VIDEO;
import static seedu.duke.common.Messages.EDIT_INVALID_MAGAZINE;
import static seedu.duke.common.Messages.EDIT_INVALID_BOOK;

//@@author avellinwong01
/**
 * Class encapsulating an edit command.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    protected String args; // id marker/attribute. E.g. 123 a/J.K. Rowling
    // (to edit the author of a book with ID 123)

    /**
     * Sole Constructor.
     *
     * @param args Arguments supplied by user in the edit command
     */
    public EditCommand(String args) {
        this.args = args.strip();
    }

    /**
     * Processes Edit Command, including exceptions.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when user input is invalid
     */
    public void handlesEditCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (args.length() < COMMAND_WORD.length() + 2) {
            throw new LibmgrException(EDIT_INVALID_FORMAT);
        }
        String parameters = args.substring(COMMAND_WORD.length() + 1).strip();
        if (!parameters.contains("/")) {
            throw new LibmgrException(EDIT_INVALID_FORMAT);
        }
        String[] argList = parameters.split("/");
        if (argList.length != 2) {
            throw new LibmgrException(EDIT_INVALID_FORMAT);
        }
        int stringLen = argList[0].length();
        String newAttributeMarker = argList[0].substring(stringLen - 1).strip();
        String newAttribute = argList[1].strip();
        String[] argList2 = parameters.split("\\s+");
        Item toEdit;
        try {
            toEdit = catalogue.getItem(argList2[0]); // argList[0] is the ID
        } catch (NullPointerException e) {
            throw new LibmgrException(EDIT_INVALID_ITEM);
        }
        if (toEdit instanceof Book) {
            handlesEditBookCommand(newAttributeMarker, newAttribute, toEdit);
        } else if (toEdit instanceof Audio) {
            handlesEditAudioCommand(newAttributeMarker, newAttribute, toEdit);
        } else if (toEdit instanceof Magazine) {
            handlesEditMagazineCommand(newAttributeMarker, newAttribute, toEdit);
        } else if (toEdit instanceof Video) {
            handlesEditVideoCommand(newAttributeMarker, newAttribute, toEdit);
        }
        ui.print(EDIT_MESSAGE, toEdit);

    }

    /**
     * Processes Edit Command for a video item, including exceptions.
     *
     * @param newAttributeMarker Marker that denotes which attribute of the video to be edited
     * @param newAttribute The new attribute to update the video item with
     * @param toEdit The Video Item to edit
     * @throws LibmgrException when the attribute marker is invalid for a video item type
     */
    public void handlesEditVideoCommand(String newAttributeMarker, String newAttribute, Item toEdit)
            throws LibmgrException {
        Video video = (Video) toEdit;
        switch (newAttributeMarker) {
        case "t":
            video.setTitle(newAttribute);
            break;
        case "i":
            video.setID(newAttribute);
            break;
        case "p":
            video.setPublisher(newAttribute);
            break;
        case "d":
            video.setDuration(newAttribute);
            break;
        default:
            throw new LibmgrException(EDIT_INVALID_VIDEO);
        }
    }

    /**
     * Processes Edit Command for a magazine item, including exceptions.
     *
     * @param newAttributeMarker Marker that denotes which attribute of the magazine to be edited
     * @param newAttribute The new attribute to update the magazine item with
     * @param toEdit The Magazine Item to edit
     * @throws LibmgrException when the attribute marker is invalid for a magazine item type
     */
    public void handlesEditMagazineCommand(String newAttributeMarker, String newAttribute, Item toEdit)
            throws LibmgrException {
        Magazine magazine = (Magazine) toEdit;
        switch (newAttributeMarker) {
        case "t":
            magazine.setTitle(newAttribute);
            break;
        case "i":
            magazine.setID(newAttribute);
            break;
        case "p":
            magazine.setPublisher(newAttribute);
            break;
        case "e":
            magazine.setEdition(newAttribute);
            break;
        default:
            throw new LibmgrException(EDIT_INVALID_MAGAZINE);
        }
    }

    /**
     * Processes Edit Command for an audio item, including exceptions.
     *
     * @param newAttributeMarker Marker that denotes which attribute of the audio to be edited
     * @param newAttribute The new attribute to update the audio item with
     * @param toEdit The Audio Item to edit
     * @throws LibmgrException when the attribute marker is invalid for an audio item type
     */
    public void handlesEditAudioCommand(String newAttributeMarker, String newAttribute, Item toEdit)
            throws LibmgrException {
        Audio audio = (Audio) toEdit;
        switch (newAttributeMarker) {
        case "t":
            audio.setTitle(newAttribute);
            break;
        case "i":
            audio.setID(newAttribute);
            break;
        case "a":
            audio.setArtist(newAttribute);
            break;
        case "d":
            audio.setDuration(newAttribute);
            break;
        default:
            throw new LibmgrException(EDIT_INVALID_AUDIO);
        }
    }

    /**
     * Processes Edit Command for a book item, including exceptions.
     *
     * @param newAttributeMarker Marker that denotes which attribute of the book to be edited
     * @param newAttribute The new attribute to update the book item with
     * @param toEdit The Book Item to edit
     * @throws LibmgrException when the attribute marker is invalid for a book item type
     */
    public void handlesEditBookCommand(String newAttributeMarker, String newAttribute, Item toEdit)
            throws LibmgrException {
        Book book = (Book) toEdit;
        switch (newAttributeMarker) {
        case "t":
            book.setTitle(newAttribute);
            break;
        case "i":
            book.setID(newAttribute);
            break;
        case "a":
            book.setAuthor(newAttribute);
            break;
        default:
            throw new LibmgrException(EDIT_INVALID_BOOK);
        }
    }

    /**
     * Executes edit command.
     * Overrides method from parent class.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     */
    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handlesEditCommand(ui, catalogue);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
//@@author avellinwong01

