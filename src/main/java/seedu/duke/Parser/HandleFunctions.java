package seedu.duke.Parser;

import seedu.duke.Exceptions.ForceCancelException;
import seedu.duke.Exceptions.InvalidAmountException;
import seedu.duke.Exceptions.TripNotOpenException;
import seedu.duke.Storage;
import seedu.duke.Ui;

public abstract class HandleFunctions extends ExecuteFunctions {

    /**
     * Confirms that the user entered paramaters, and calls {@link Parser#executeCreateTrip(String)}.
     *
     * @param inputParams attributes of the trip to be created.
     */
    protected static void handleCreateTrip(String inputParams) throws ForceCancelException {
        try {
            assert inputParams != null;
            executeCreateTrip(inputParams);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            Ui.printCreateFormatError();
        }
    }

    protected static void handleEditTrip(String inputParams) throws ForceCancelException {
        try {
            assert inputParams != null;
            executeEditTrip(inputParams);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            Ui.printUnknownTripIndexError();
        }
    }

    protected static void handleOpenTrip(String inputParams) {
        try {
            assert inputParams != null;
            executeOpen(inputParams);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.printSingleUnknownTripIndexError();
            System.out.println();
        } catch (NullPointerException e) {
            Ui.emptyArgForOpenCommand();
        }
    }

    protected static void handleTripSummary(String inputParams) {
        try {
            executeSummary(inputParams);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printUnknownTripIndexError();
        }
    }

    protected static void handleViewTrip(String inputParams) {
        try {
            executeView(inputParams);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printFilterFormatError();
        }
    }

    protected static void handleDelete(String inputParams) {
        try {
            assert inputParams != null;
            executeDelete(inputParams);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printUnknownTripIndexError();
        } catch (NullPointerException e) {
            Ui.emptyArgForDeleteCommand();
        }
    }

    protected static void handleList() {
        try {
            executeList();
        } catch (TripNotOpenException e){
            Ui.printExpensesInList(Storage.getOpenTrip().getListOfExpenses());
        }
    }

    /**
     * Confirms that the user had entered parameters for creating a new expense, and redirects to
     * {@link Parser#executeCreateExpense(String)} to create the expense.
     *
     * @param inputParams attributes of expense to be created.
     */
    protected static void handleCreateExpense(String inputParams) {
        try {
            assert inputParams != null;
            executeCreateExpense(inputParams);
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printExpenseFormatError();
        } catch (InvalidAmountException e) {
            Ui.printInvalidAmountError();
        } catch (ForceCancelException e) {
            Ui.printForceCancelled();
        }
    }

    protected static void handleAmount(String inputParams) {
        try {
            executeAmount(inputParams);
        } catch (NullPointerException e) {
            Ui.invalidArgForAmount();
        }
    }


    /**
     * Prints out the list of people in the trip if trip is open.
     * Otherwise, informs the user no trip open.
     */
    protected static void handlePeople() {
        try {
            executePeople();
        } catch (TripNotOpenException e) {
            Ui.printNoOpenTripError();
        }
    }
}
