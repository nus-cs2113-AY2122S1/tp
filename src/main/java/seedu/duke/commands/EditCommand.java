package seedu.duke.commands;

import seedu.duke.LibmgrException;
import seedu.duke.data.*;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.EDIT_MESSAGE;

/**
 * Class encapsulating an edit command
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    protected String args; // id marker/edit. E.g. 123 a/J.K. Rowling
                           // (to edit the author of a book with ID 123)

    /**
     * Sole Constructor.
     *
     * @param args Arguments supplied by user in the edit command
     */
    public EditCommand(String args) {
        this.args = args;
    }

    /**
     * Processes Edit Command, including exceptions.
     *
     * @param ui Object that handles user IO
     * @param catalogue Object that encapsulates the library catalogue
     * @throws LibmgrException when user input is invalid
     */
    public void handlesEditCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        String parameters = args.substring(COMMAND_WORD.length() + 1);
        String[] argList = parameters.split("/");
        int stringLen = argList[0].length();
        String newAttributeMarker = argList[0].substring(stringLen - 1);
        String newAttribute = argList[1];
        argList = parameters.split(" ");
        Item toEdit = catalogue.getItem(argList[0]); // argList[0] is the ID
        if (toEdit instanceof Book) {
            handlesEditBookCommand(newAttributeMarker, newAttribute, toEdit);
        } else if (toEdit instanceof Audio) {
            handlesEditAudioCommand(newAttributeMarker, newAttribute, toEdit);
        } else if (toEdit instanceof Magazine) {
            handlesEditMagazineCommand(newAttributeMarker, newAttribute, toEdit);
        } else if (toEdit instanceof Video) {
            handlesEditVideoCommand(newAttributeMarker, newAttribute, toEdit);
        } else {
            throw new LibmgrException("Invalid Item Type");
        }
        ui.print(EDIT_MESSAGE, toEdit);

    }

    public void handlesEditVideoCommand(String newAttributeMarker, String newAttribute, Item toEdit) throws LibmgrException {
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
            throw new LibmgrException("Attribute Marker not valid for Video");
        }
    }

    public void handlesEditMagazineCommand(String newAttributeMarker, String newAttribute, Item toEdit) throws LibmgrException {
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
            throw new LibmgrException("Attribute Marker not valid for Magazine");
        }
    }

    public void handlesEditAudioCommand(String newAttributeMarker, String newAttribute, Item toEdit) throws LibmgrException {
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
            throw new LibmgrException("Attribute Marker not valid for Audio");
        }
    }


    public void handlesEditBookCommand(String newAttributeMarker, String newAttribute, Item toEdit) throws LibmgrException {
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
            throw new LibmgrException("Attribute Marker not valid for Book");
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

