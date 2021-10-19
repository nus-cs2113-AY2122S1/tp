package utilities.comparators;

import command.CommandParameters;
import inventory.Dispense;

import java.util.Comparator;

/**
 * Helps to sort the medicines based on the column provided.
 */

public class DispenseComparator implements Comparator<Dispense> {
    private String column;
    private Boolean isReversed;

    public DispenseComparator(String column, Boolean isReversed) {
        this.column = column;
        this.isReversed = isReversed;
    }

    @Override
    public int compare(Dispense dispense1, Dispense dispense2) {
        Dispense dispense;

        if (isReversed) { // If the user chooses to sort in reverse order
            dispense = dispense2;
            dispense2 = dispense1;
            dispense1 = dispense;
        }

        switch (column) {
        case Dispense.ID_LOWERCASE:
        case CommandParameters.ID:
            return Integer.compare(dispense1.getDispenseId(), dispense2.getDispenseId());
        case Dispense.NAME_LOWERCASE:
        case CommandParameters.NAME:
            return dispense1.getMedicineName().compareTo(dispense2.getMedicineName());
        case Dispense.QUANTITY_LOWERCASE:
        case CommandParameters.QUANTITY:
            return Integer.compare(dispense1.getQuantity(), dispense2.getQuantity());
        case Dispense.CUSTOMER_ID_LOWERCASE:
        case CommandParameters.CUSTOMER_ID:
            return dispense1.getCustomerId().compareTo(dispense2.getCustomerId());
        case Dispense.DATE_LOWERCASE:
        case CommandParameters.DATE:
            return dispense1.getDate().compareTo(dispense2.getDate());
        case Dispense.STAFF_LOWERCASE:
        case CommandParameters.STAFF:
            return dispense1.getStaff().compareTo(dispense2.getStaff());
        case Dispense.STOCK_ID_LOWERCASE:
        case CommandParameters.STOCK_ID:
            return Integer.compare(dispense1.getStockId(), dispense2.getStockId());
        default:
            return 0;
        }
    }
}
