package seedu.mtracker.commands;

import seedu.mtracker.model.subinstrument.Stock;
import seedu.mtracker.ui.TextUi;

//@@author theodorekwok
/**
 * Responsible for adding Stock to the list.
 */
public class AddStockCommand extends AddInstrumentCommand {

    public static final String COMMAND_WORD = "stock";
    public static final int REMARK_INDEX = 3;

    protected String remarkParameter;

    public void setStockParameters() {
        remarkParameter = inputParameters.get(REMARK_INDEX);
    }

    public void createNewStock() {
        newInstrument = new Stock(nameParameter, currentPriceParameter, sentimentParameter, remarkParameter);
    }

    /**
     * Handles the execution of adding Stock to the list.
     *
     * @return The name of the command.
     */
    @Override
    public String execute() {
        setAddGeneralParameters();
        setStockParameters();
        createNewStock();
        instrumentManager.addInstrument(newInstrument);
        TextUi.displayInstrumentAdded(newInstrument);
        return COMMAND_WORD;
    }
}
