package comparators;

import command.CommandParameters;
import inventory.Stock;
import inventory.Medicine;

import java.util.Comparator;

/**
 * Helps to sort the medicines based on the column provided.
 */

public class StockComparator implements Comparator<Medicine> {
    private String column;
    private Boolean isReversed;

    public StockComparator(String column, Boolean isReversed) {
        this.column = column;
        this.isReversed = isReversed;
    }

    @Override
    public int compare(Medicine medicine1, Medicine medicine2) {
        Stock stock1;
        Stock stock2;
        if (isReversed) { // If the user chooses to sort in reverse order
            stock1 = (Stock) medicine2;
            stock2 = (Stock) medicine1;
        } else {
            stock1 = (Stock) medicine1;
            stock2 = (Stock) medicine2;
        }

        switch (column) {
        case Stock.ID:
        case CommandParameters.STOCK_ID:
            return Integer.compare(stock1.getStockID(), stock2.getStockID());
        case Stock.NAME:
        case CommandParameters.NAME:
            return stock1.getMedicineName().compareTo(stock2.getMedicineName());
        case Stock.PRICE:
        case CommandParameters.PRICE:
            return Double.compare(stock1.getPrice(), stock2.getPrice());
        case Stock.QUANTITY:
        case CommandParameters.QUANTITY:
            return Integer.compare(stock1.getQuantity(), stock2.getQuantity());
        case Stock.EXPIRY_DATE:
        case CommandParameters.EXPIRY_DATE:
            return stock1.getExpiry().compareTo(stock2.getExpiry());
        case Stock.DESCRIPTION:
        case CommandParameters.DESCRIPTION:
            return stock1.getDescription().compareTo(stock2.getDescription());
        case Stock.MAX_QUANTITY:
        case CommandParameters.MAX_QUANTITY:
            return Integer.compare(stock1.getMaxQuantity(), stock2.getMaxQuantity());
        default:
            return 0;
        }
    }
}
