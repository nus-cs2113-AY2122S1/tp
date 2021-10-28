package utilities.parser;

import command.CommandParameters;
import inventory.Medicine;
import inventory.Stock;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

//@@author a-tph

/**
 * Manages medicines that are stock objects.
 */
public class StockManager {

    /**
     * Retrieves the total stock quantity for medicine with same name.
     *
     * @param medicines Arraylist of medicines.
     * @param name      Medicine name.
     * @return Total stock quantity for the medicine.
     */
    public static int getTotalStockQuantity(ArrayList<Medicine> medicines, String name) {
        int existingQuantity = 0;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            boolean isSameMedicineName = medicine.getMedicineName().equalsIgnoreCase(name);
            boolean isDeleted = ((Stock) medicine).isDeleted();
            if (isSameMedicineName && !isDeleted) {
                existingQuantity += medicine.getQuantity();
            }
        }
        return existingQuantity;
    }

    /**
     * Retrieves the maximum stock quantity for medicine with same name.
     *
     * @param medicines Arraylist of medicines.
     * @param name      Medicine name.
     * @return Total maximum stock quantity for the medicine.
     */
    public static int getMaxStockQuantity(ArrayList<Medicine> medicines, String name) {
        int existingMaxQuantity = 0;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            boolean isSameMedicineName = medicine.getMedicineName().equalsIgnoreCase(name);
            boolean isDeleted = ((Stock) medicine).isDeleted();
            if (isSameMedicineName && !isDeleted) {
                existingMaxQuantity = ((Stock) medicine).getMaxQuantity();
                break;
            }
        }
        assert (existingMaxQuantity > 0) : "Invalid max quantity";
        return existingMaxQuantity;
    }

    /**
     * Extracts the stock object for a given stock id.
     *
     * @param parameters HashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines  Arraylist of all medicines.
     * @return Stock object of the provided stock id by user
     */
    public static Stock extractStockObject(HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        int stockId = Integer.parseInt(parameters.get(CommandParameters.ID));
        Stock stock = null;
        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock && stockId == ((Stock) medicine).getStockId()) {
                stock = (Stock) medicine;
            }
        }
        assert (stock != null) : "Expected a stock object but none extracted";
        assert (stock.isDeleted() == false) : "Stock object should not be deleted";
        return stock;
    }

    /**
     * Extracts the stock object by a given medicine name and stock Id.
     *
     * @param medicines Arraylist of all medicines.
     * @param name      Name of the medicine.
     * @param stockId   Stock Id of the medicine.
     * @return Stock object of the provided stock id by user
     */
    public static Stock extractStockObject(ArrayList<Medicine> medicines, String name, int stockId) {
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            Stock stock = (Stock) medicine;
            boolean isSameName = name.equalsIgnoreCase(stock.getMedicineName());
            boolean isSameId = stockId == stock.getStockId();
            if (isSameName && isSameId) {
                return stock;
            }
        }
        return null;
    }

    /**
     * Extracts the filtered stock for stocks with same name.
     *
     * @param medicines Arraylist of all medicines.
     * @param stockName Stock name for a given stock.
     * @return ArrayList of filteredStocks of the same stock name.
     */
    public static ArrayList<Stock> getFilteredStocksByName(ArrayList<Medicine> medicines, String stockName) {
        ArrayList<Stock> filteredStocks = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            boolean isSameName = medicine.getMedicineName().equalsIgnoreCase(stockName);
            boolean isDeleted = ((Stock) medicine).isDeleted();
            if (isSameName && !isDeleted) {
                filteredStocks.add((Stock) medicine);
            }
        }
        return filteredStocks;
    }

    /**
     * Check if the same expiry date and same medication exist.
     *
     * @param ui             Reference to the UI object to print messages.
     * @param stockValidator Reference to StockValidator object.
     * @param filteredStocks List of medication with the same medication name as user input.
     * @param quantityToAdd  Quantity of medication to add.
     * @param formatExpiry   Formatted Expiry Date of medication to add.
     * @param totalStock     Total Quantity of the same stock.
     * @return Boolean Value indicating if the same expiry date and same medication exist.
    */
    public static boolean expiryExist(Ui ui, StockValidator stockValidator, ArrayList<Stock> filteredStocks,
                                      String quantityToAdd, Date formatExpiry, int totalStock) {
        for (Stock stock : filteredStocks) {
            int quantity = Integer.parseInt(quantityToAdd);

            boolean isValidQuantity =
                    stockValidator.quantityValidityChecker(ui, totalStock + quantity,
                            stock.getMaxQuantity());

            if (!isValidQuantity) {
                return false;
            }

            if (formatExpiry.equals(stock.getExpiry())) {
                quantity += stock.getQuantity();
                stock.setQuantity(quantity);
                ui.print("Same Medication and Expiry Date exist. Update existing quantity.");
                ui.printStock(stock);
                return true;
            }
        }
        return false;
    }

}
