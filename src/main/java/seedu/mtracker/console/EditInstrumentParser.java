package seedu.mtracker.console;

import seedu.mtracker.commands.EditInstrumentCommand;
import seedu.mtracker.commons.Validate;
import seedu.mtracker.commons.error.OperationAbortedError;
import seedu.mtracker.model.Instrument;
import seedu.mtracker.ui.TextUi;

import java.util.HashMap;
import java.util.HashSet;

public class EditInstrumentParser extends InputParser {

    protected static HashMap<String, String> editedParameters;

    protected static final String NAME_ATTRIBUTE = "name";
    protected static final String CURRENT_PRICE_ATTRIBUTE = "current-price";
    protected static final String SENTIMENT_ATTRIBUTE = "sentiment";
    protected static final String REMARK_ATTRIBUTE = "remarks";
    protected static final String RETURN_ATTRIBUTE = "past-returns";
    protected static final String ENTRY_PRICE_ATTRIBUTE = "entry-price";
    protected static final String EXIT_PRICE_ATTRIBUTE = "exit-price";
    protected static final String EXPIRY_ATTRIBUTE = "expiry";
    protected static final String DONE_ATTRIBUTE = "done-status";
    protected static final String WORKSPACE = EditInstrumentCommand.COMMAND_WORD;

    public static final double UNDEFINED_PAST_RETURN_VALUE = -101;

    public void editNameParameter(String instrumentType, HashSet<String> parametersGiven)
            throws OperationAbortedError {
        if (!parametersGiven.contains(NAME_ATTRIBUTE)) {
            return;
        }
        String inputName;
        do {
            TextUi.displayEditName();
            inputName = getUserInput(WORKSPACE);
            checkIfAbort(inputName, WORKSPACE);
        } while (!Validate.isValidName(inputName, instrumentType));
        editedParameters.put(NAME_ATTRIBUTE, inputName);
    }

    public void editCurrentPriceParameter(HashSet<String> parametersGiven)
            throws OperationAbortedError {
        if (!parametersGiven.contains(CURRENT_PRICE_ATTRIBUTE)) {
            return;
        }
        String inputCurrentPrice;
        do {
            TextUi.displayEditCurrentPrice();
            inputCurrentPrice = getUserInput(WORKSPACE);
            checkIfAbort(inputCurrentPrice, WORKSPACE);
        } while (!Validate.isValidPrice(inputCurrentPrice));
        editedParameters.put(CURRENT_PRICE_ATTRIBUTE, inputCurrentPrice);
    }

    public void editSentimentsParameter(HashSet<String> parametersGiven)
            throws OperationAbortedError {
        if (!parametersGiven.contains(SENTIMENT_ATTRIBUTE)) {
            return;
        }
        String inputSentiment;
        do {
            TextUi.displayEditSentiment();
            inputSentiment = getUserInput(WORKSPACE).toLowerCase();
            checkIfAbort(inputSentiment, WORKSPACE);
        } while (!Validate.isValidSentiment(inputSentiment));
        editedParameters.put(SENTIMENT_ATTRIBUTE, inputSentiment);
    }

    public void editRemarksParameter(HashSet<String> parametersGiven)
            throws OperationAbortedError {
        if (!parametersGiven.contains(REMARK_ATTRIBUTE)) {
            return;
        }
        TextUi.displayEditRemark();
        String inputRemark = getUserInput(WORKSPACE);
        checkIfAbort(inputRemark, WORKSPACE);
        editedParameters.put(REMARK_ATTRIBUTE, inputRemark);
    }

    public void editReturnParameter(HashSet<String> parametersGiven)
            throws OperationAbortedError {
        if (!parametersGiven.contains(RETURN_ATTRIBUTE)) {
            return;
        }
        TextUi.displayEditReturn();
        String inputReturn = getUserInput(WORKSPACE);
        checkIfAbort(inputReturn, WORKSPACE);
        if (!Validate.isValidPastReturns(inputReturn)) {
            inputReturn = String.valueOf(UNDEFINED_PAST_RETURN_VALUE);
            editedParameters.put(RETURN_ATTRIBUTE, inputReturn);
            return;
        }
        editedParameters.put(RETURN_ATTRIBUTE, inputReturn);
    }

    public void editEntryPriceParameter(HashSet<String> parametersGiven)
            throws OperationAbortedError {
        if (!parametersGiven.contains(ENTRY_PRICE_ATTRIBUTE)) {
            return;
        }
        String inputEntryPrice;
        do {
            TextUi.displayEditEntryPrice();
            inputEntryPrice = getUserInput(WORKSPACE);
            checkIfAbort(inputEntryPrice, WORKSPACE);
        } while (!Validate.isValidPrice(inputEntryPrice));
        editedParameters.put(ENTRY_PRICE_ATTRIBUTE, inputEntryPrice);
    }

    public void editExitPriceParameter(HashSet<String> parametersGiven)
            throws OperationAbortedError {
        if (!parametersGiven.contains(EXIT_PRICE_ATTRIBUTE)) {
            return;
        }
        String inputExitPrice;
        do {
            TextUi.displayEditExitPrice();
            inputExitPrice = getUserInput(WORKSPACE);
            checkIfAbort(inputExitPrice, WORKSPACE);
        } while (!Validate.isValidPrice(inputExitPrice));
        editedParameters.put(EXIT_PRICE_ATTRIBUTE, inputExitPrice);
    }

    public void editDoneStatus(HashSet<String> parametersGiven) throws OperationAbortedError {
        if (!parametersGiven.contains(DONE_ATTRIBUTE)) {
            return;
        }
        String inputStatus;
        do {
            TextUi.displayEditStatus();
            inputStatus = getUserInput(WORKSPACE).toLowerCase();
            checkIfAbort(inputStatus, WORKSPACE);
        } while (!Validate.isValidInputStatus(inputStatus));
        editedParameters.put(DONE_ATTRIBUTE, inputStatus);
    }

    public void editExpiryParameter(HashSet<String> parametersGiven)
            throws OperationAbortedError {
        if (!parametersGiven.contains(EXPIRY_ATTRIBUTE)) {
            return;
        }
        String inputExpiry;
        do {
            TextUi.displayEditExpiry();
            inputExpiry = getUserInput(WORKSPACE);
            checkIfAbort(inputExpiry, WORKSPACE);
        } while (!Validate.isValidExpiry(inputExpiry));
        editedParameters.put(EXPIRY_ATTRIBUTE, inputExpiry);
    }

    public void getEditedParameters(HashSet<String> parametersGiven, Instrument instrumentOfInterest)
            throws OperationAbortedError {
        String instrumentType = instrumentOfInterest.getType().toLowerCase();
        editNameParameter(instrumentType, parametersGiven);
        editCurrentPriceParameter(parametersGiven);
        editSentimentsParameter(parametersGiven);
        editReturnParameter(parametersGiven);
        editEntryPriceParameter(parametersGiven);
        editExitPriceParameter(parametersGiven);
        editExpiryParameter(parametersGiven);
        editRemarksParameter(parametersGiven);
        editDoneStatus(parametersGiven);
    }

    public EditInstrumentCommand createEditCommand(HashSet<String> parametersGiven,
                                                     Instrument instrumentOfInterest, int instrumentIndex)
            throws OperationAbortedError {
        EditInstrumentCommand command;
        editedParameters = new HashMap<>();
        getEditedParameters(parametersGiven, instrumentOfInterest);
        command = new EditInstrumentCommand(editedParameters);
        command.setIndex(instrumentIndex);
        return command;
    }

    public static HashMap<String, String> getEditedParametersHash() {
        return editedParameters;
    }
}
