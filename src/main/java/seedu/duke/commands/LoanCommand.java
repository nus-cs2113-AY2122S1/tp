package seedu.duke.commands;

import seedu.duke.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import static seedu.duke.common.Messages.LOAN_MESSAGE;
import static seedu.duke.common.Messages.NO_ID;
import static seedu.duke.common.Messages.RM_INVALID_ID;

public class LoanCommand extends Command {
    private static final String BORROWED_STATUS = "Borrowed";
    protected String args; // Format: loan [ID]
    protected String id;

    public LoanCommand(String args) {
        this.args = args;
    }

    public void handleLoanCommand(TextUI ui, Catalogue catalogue) throws LibmgrException {
        if (!args.contains(" ")) {
            throw new LibmgrException(NO_ID);
        }
        id = args.split(" ")[1];
        Item toBeLoaned = catalogue.getItem(id);
        toBeLoaned.setStatus(BORROWED_STATUS);
        ui.print(LOAN_MESSAGE);
        ui.print(toBeLoaned.getID() + " " + toBeLoaned.getTitle());
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
