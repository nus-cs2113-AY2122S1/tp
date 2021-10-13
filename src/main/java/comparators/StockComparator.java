package comparators;

import command.CommandParameters;
import inventory.Medicine;
import inventory.Stock;

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
        case Stock.ID_LOWERCASE:
        case CommandParameters.ID:
            return Integer.compare(stock1.getStockID(), stock2.getStockID());
        case Stock.NAME_LOWERCASE:
        case CommandParameters.NAME:
            return stock1.getMedicineName().compareTo(stock2.getMedicineName());
        case Stock.PRICE_LOWERCASE:
        case CommandParameters.PRICE:
            return Double.compare(stock1.getPrice(), stock2.getPrice());
        case Stock.QUANTITY_LOWERCASE:
        case CommandParameters.QUANTITY:
            return Integer.compare(stock1.getQuantity(), stock2.getQuantity());
        case Stock.EXPIRY_DATE_LOWERCASE:
        case CommandParameters.EXPIRY_DATE:
            return stock1.getExpiry().compareTo(stock2.getExpiry());
        case Stock.DESCRIPTION_LOWERCASE:
        case CommandParameters.DESCRIPTION:
            return stock1.getDescription().compareTo(stock2.getDescription());
        case Stock.MAX_QUANTITY_LOWERCASE:
        case CommandParameters.MAX_QUANTITY:
            return Integer.compare(stock1.getMaxQuantity(), stock2.getMaxQuantity());
        default:
            return 0;
        }
    }
}
