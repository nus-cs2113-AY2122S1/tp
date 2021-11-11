package utilities.comparators;

import command.CommandParameters;
import inventory.Stock;

import java.util.Comparator;

//@@author alvintan01

/**
 * Helps to sort the medicines based on the column provided.
 */
public class StockComparator implements Comparator<Stock> {
    private String column;
    private Boolean isReversed;

    public StockComparator(String column, Boolean isReversed) {
        this.column = column;
        this.isReversed = isReversed;
    }

    @Override
    public int compare(Stock stock1, Stock stock2) {
        Stock stock;
        if (isReversed) { // If the user chooses to sort in reverse order
            stock = stock2;
            stock2 = stock1;
            stock1 = stock;
        }

        switch (column) {
        case Stock.ID_LOWERCASE:
        case CommandParameters.ID:
            return Integer.compare(stock1.getStockId(), stock2.getStockId());
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
