package seedu.duke.commands;

import seedu.duke.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.NO_ID;
import static seedu.duke.common.Messages.RETURN_MESSAGE;
import static seedu.duke.common.Messages.RM_INVALID_ID;

public class ReturnCommand extends Command {
    private static final String AVAILABLE_STATUS = "Available";
    protected String args; // Format: loan [ID]
    protected String id;

    public ReturnCommand(String args) {
        this.args = args;
    }

    public void handleLoanCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (!args.contains(" ")) {
            throw new LibmgrException(NO_ID);
        }
        id = args.split(" ")[1];
        Item toBeReturned = catalogue.getItem(id);
        toBeReturned.setStatus(AVAILABLE_STATUS);
        ui.print(RETURN_MESSAGE);
        ui.print(toBeReturned.getID() + " " + toBeReturned.getTitle());
    }

    @Override
    public void execute(TextUI ui, Catalogue catalogue) {
        try {
            handleLoanCommand(ui, catalogue);
        } catch (IndexOutOfBoundsException e) {
            ui.print(RM_INVALID_ID);
        } catch (NullPointerException e) {
            ui.print(RM_INVALID_ID);
        } catch (LibmgrException e) {
            ui.print(e.getMessage());
        }
    }
}
