package utilities.parser;

import command.CommandParameters;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Stock;
import utilities.comparators.StockComparator;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Manages medicines that are dispense objects.
 */
public class DispenseManager {

    /**
     * Extracts the dispense object for a given dispense id.
     *
     * @param parameters LinkedHashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines  Arraylist of all medicines.
     * @return Stock object of the provided order id by user
     */
    public static Dispense extractDispenseObject(LinkedHashMap<String, String> parameters,
                                                 ArrayList<Medicine> medicines) {
        int orderId = Integer.parseInt(parameters.get(CommandParameters.ID));
        Dispense dispense = null;
        for (Medicine medicine : medicines) {
            if (medicine instanceof Dispense && orderId == ((Dispense) medicine).getDispenseId()) {
                dispense = (Dispense) medicine;
            }
        }
        assert (dispense != null) : "Expected a dispense object but none extracted";
        return dispense;
    }

    /**
     * Handles restoration of stock of a dispensed medication.
     *
     * @param targetRestoreStock The target stock object to restore quantity.
     * @param restoreQuantity    The quantity of the medication.
     */
    public static void restoreStock(Stock targetRestoreStock, int restoreQuantity) {
        targetRestoreStock.setDeleted(false);
        targetRestoreStock.setQuantity(restoreQuantity);
    }

    /**
     * Handles dispensing more stocks of a dispensed medication of different names.
     *
     * @param medicines            Arraylist of all medicines.
     * @param targetDispenseStocks The target stock array list to dispense quantity.
     * @param dispenseQuantity     The quantity to dispense from the stocks.
     * @param customerId           The customer Id of the associated dispense object.
     * @param date                 Date of dispense.
     * @param staffName            The staff Id of the associated dispense object.
     * @returns ArrayList of updated dispenses.
     */
    public static ArrayList<Dispense> dispenseStock(ArrayList<Medicine> medicines,
                                     ArrayList<Stock> targetDispenseStocks, int dispenseQuantity,
                                     String customerId, Date date, String staffName) {
        ArrayList<Dispense> newDispenses = new ArrayList<>();
        targetDispenseStocks.sort(new StockComparator(CommandParameters.EXPIRY_DATE, false));
        while (dispenseQuantity != 0) {
            for (Stock stock : targetDispenseStocks) {
                Dispense dispense;
                if (stock.getQuantity() <= dispenseQuantity) {
                    dispense = new Dispense(stock.getMedicineName(), stock.getQuantity(), customerId, date, staffName,
                            stock.getStockId());
                    newDispenses.add(dispense);
                    dispenseQuantity -= stock.getQuantity();
                    stock.setQuantity(0);
                } else {
                    int remainingStocks = stock.getQuantity() - dispenseQuantity;
                    dispense = new Dispense(stock.getMedicineName(), dispenseQuantity, customerId, date, staffName,
                            stock.getStockId());
                    newDispenses.add(dispense);
                    stock.setQuantity(remainingStocks);
                    dispenseQuantity = 0;
                    break;
                }
            }
        }

        // Updating quantity in stocks
        for (Stock targetStock : targetDispenseStocks) {
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
        return newDispenses;
    }
}
