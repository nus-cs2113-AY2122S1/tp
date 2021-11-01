package utilities.parser;

import command.CommandParameters;
import inventory.Prescription;
import inventory.Medicine;
import inventory.Stock;
import utilities.comparators.StockComparator;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

//@@author a-tph
/**
 * Manages medicines that are prescription objects.
 */
public class PrescriptionManager {

    /**
     * Extracts the prescription object for a given prescription id.
     *
     * @param parameters LinkedHashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines  Arraylist of all medicines.
     * @return Stock object of the provided order id by user
     */
    public static Prescription extractPrescriptionObject(LinkedHashMap<String, String> parameters,
                                                         ArrayList<Medicine> medicines) {
        int orderId = Integer.parseInt(parameters.get(CommandParameters.ID));
        Prescription prescription = null;
        for (Medicine medicine : medicines) {
            if (medicine instanceof Prescription && orderId == ((Prescription) medicine).getPrescriptionId()) {
                prescription = (Prescription) medicine;
            }
        }
        assert (prescription != null) : "Expected a prescription object but none extracted";
        return prescription;
    }

    /**
     * Handles restoration of stock of a prescribed medication.
     *
     * @param targetRestoreStock The target stock object to restore quantity.
     * @param restoreQuantity    The quantity of the medication.
     */
    public static void restoreStock(Stock targetRestoreStock, int restoreQuantity) {
        targetRestoreStock.setDeleted(false);
        targetRestoreStock.setQuantity(restoreQuantity);
    }

    /**
     * Handles dispensing more stocks of a prescribed medication of different names.
     *
     * @param medicines                Arraylist of all medicines.
     * @param targetPrescriptionStocks The target stock array list to prescribe quantity.
     * @param prescribeQuantity        The quantity to prescribe from the stocks.
     * @param customerId               The customer Id of the associated prescription object.
     * @param date                     Date of prescription.
     * @param staffName                The staff Id of the associated prescription object.
     * @returns ArrayList of updated prescriptions.
     */
    public static ArrayList<Prescription> prescribeStock(ArrayList<Medicine> medicines,
                                                         ArrayList<Stock> targetPrescriptionStocks,
                                                         int prescribeQuantity,
                                                         String customerId, Date date, String staffName) {
        ArrayList<Prescription> newPrescriptions = new ArrayList<>();
        targetPrescriptionStocks.sort(new StockComparator(CommandParameters.EXPIRY_DATE, false));
        while (prescribeQuantity != 0) {
            for (Stock stock : targetPrescriptionStocks) {
                Prescription prescription;
                if (stock.getQuantity() <= prescribeQuantity) {
                    prescription = new Prescription(stock.getMedicineName(), stock.getQuantity(), customerId, date,
                            staffName, stock.getStockId());
                    newPrescriptions.add(prescription);
                    prescribeQuantity -= stock.getQuantity();
                    stock.setQuantity(0);
                } else {
                    int remainingStocks = stock.getQuantity() - prescribeQuantity;
                    prescription = new Prescription(stock.getMedicineName(), prescribeQuantity, customerId, date,
                            staffName, stock.getStockId());
                    newPrescriptions.add(prescription);
                    stock.setQuantity(remainingStocks);
                    prescribeQuantity = 0;
                    break;
                }
            }
        }

        // Updating quantity in stocks
        for (Stock targetStock : targetPrescriptionStocks) {
            for (Medicine medicine : medicines) {
                if (!(medicine instanceof Stock)) {
                    continue;
                }
                Stock stock = (Stock) medicine;
                boolean isSameStockId = stock.getStockId() == targetStock.getStockId();
                boolean isDeleted = stock.isDeleted();
                if (isSameStockId && !isDeleted) {
                    medicine.setQuantity(targetStock.getQuantity());
                }
            }
        }
        return newPrescriptions;
    }

    /**
     * Retrieves the total stock quantity for medicine with same name that has not expired.
     *
     * @param medicines     Arraylist of medicines.
     * @param name          Medicine name.
     * @param prescribeDate Date of Prescription.
     * @return Total stock quantity for the medicine that has not expired.
     */
    public static int getNotExpiredStockQuantity(ArrayList<Medicine> medicines, String name, Date prescribeDate) {
        int existingQuantity = 0;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            boolean isSameMedicineName = medicine.getMedicineName().equalsIgnoreCase(name);
            boolean isDeleted = ((Stock) medicine).isDeleted();
            Date existingExpiry = ((Stock) medicine).getExpiry();
            String expiryString = DateParser.dateToString(existingExpiry);
            String prescribeDateString = DateParser.dateToString(prescribeDate);
            boolean isNotExpired = (existingExpiry.after(prescribeDate) || prescribeDateString.equals(expiryString));

            if (isSameMedicineName && !isDeleted && isNotExpired) {
                existingQuantity += medicine.getQuantity();
            }
        }
        return existingQuantity;
    }
}
