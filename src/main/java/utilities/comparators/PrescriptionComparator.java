package utilities.comparators;

import command.CommandParameters;
import inventory.Prescription;

import java.util.Comparator;

//@@author alvintan01

/**
 * Helps to sort the medicines based on the column provided.
 */
public class PrescriptionComparator implements Comparator<Prescription> {
    private String column;
    private Boolean isReversed;

    public PrescriptionComparator(String column, Boolean isReversed) {
        this.column = column;
        this.isReversed = isReversed;
    }

    @Override
    public int compare(Prescription prescription1, Prescription prescription2) {
        Prescription prescription;

        if (isReversed) { // If the user chooses to sort in reverse order
            prescription = prescription2;
            prescription2 = prescription1;
            prescription1 = prescription;
        }

        switch (column) {
        case Prescription.ID_LOWERCASE:
        case CommandParameters.ID:
            return Integer.compare(prescription1.getPrescriptionId(), prescription2.getPrescriptionId());
        case Prescription.NAME_LOWERCASE:
        case CommandParameters.NAME:
            return prescription1.getMedicineName().compareTo(prescription2.getMedicineName());
        case Prescription.QUANTITY_LOWERCASE:
        case CommandParameters.QUANTITY:
            return Integer.compare(prescription1.getQuantity(), prescription2.getQuantity());
        case Prescription.CUSTOMER_ID_LOWERCASE:
        case CommandParameters.CUSTOMER_ID:
            return prescription1.getCustomerId().compareTo(prescription2.getCustomerId());
        case Prescription.DATE_LOWERCASE:
        case CommandParameters.DATE:
            return prescription1.getDate().compareTo(prescription2.getDate());
        case Prescription.STAFF_LOWERCASE:
        case CommandParameters.STAFF:
            return prescription1.getStaff().compareTo(prescription2.getStaff());
        case Prescription.STOCK_ID_LOWERCASE:
        case CommandParameters.STOCK_ID:
            return Integer.compare(prescription1.getStockId(), prescription2.getStockId());
        default:
            return 0;
        }
    }
}
