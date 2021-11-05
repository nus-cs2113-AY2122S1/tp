package seedu.duke.command;

import seedu.duke.command.exception.ShelfNotExistException;
import seedu.duke.model.ShelfList;

public class RemoveShelfCommand extends Command {
    public static final String REMOVE_DATA_ARGS_FORMAT_STRING = "remove shlv/SHELF_NAME";
    public static final String REMOVE_STRING = "remove";
    public static final String PARSE_REMOVE_SUCCESS_MESSAGE_FORMAT = "shelfname: %s\n";
    private static final String REMOVE_COMPLETE_MESSAGE =
            "Shelf \"%s\" has been deleted.";
    private final String shelfName;

    public RemoveShelfCommand(String shelfName) {
        this.shelfName = shelfName;
    }

    @Override
    public String execute() throws ShelfNotExistException {
        try {
            if (ShelfList.getShelfList().getShelf(shelfName).getItemCount() != 0) {
                throw new IllegalArgumentException("Cannot remove shelf with existing items");
            }

            ShelfList.getShelfList().deleteShelf(shelfName);
            return String.format(REMOVE_COMPLETE_MESSAGE, shelfName);
        } catch (seedu.duke.model.exception.ShelfNotExistException e) {
            throw new ShelfNotExistException(e.getMessage());
        }
    }
}
