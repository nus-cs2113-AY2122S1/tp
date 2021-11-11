package utilities.comparators;

import command.CommandParameters;
import inventory.Order;

import java.util.Comparator;

//@@author alvintan01

/**
 * Helps to sort the order based on the column provided.
 */
public class OrderComparator implements Comparator<Order> {
    private String column;
    private Boolean isReversed;

    public OrderComparator(String column, Boolean isReversed) {
        this.column = column;
        this.isReversed = isReversed;
    }

    @Override
    public int compare(Order order1, Order order2) {
        Order order;

        if (isReversed) { // If the user chooses to sort in reverse order
            order = order2;
            order2 = order1;
            order1 = order;
        }

        switch (column) {
        case Order.ID_LOWERCASE:
        case CommandParameters.ID:
            return Integer.compare(order1.getOrderId(), order2.getOrderId());
        case Order.NAME_LOWERCASE:
        case CommandParameters.NAME:
            return order1.getMedicineName().compareTo(order2.getMedicineName());
        case Order.QUANTITY_LOWERCASE:
        case CommandParameters.QUANTITY:
            return Integer.compare(order1.getQuantity(), order2.getQuantity());
        case Order.DATE_LOWERCASE:
        case CommandParameters.DATE:
            return order1.getDate().compareTo(order2.getDate());
        case Order.STATUS_LOWERCASE:
        case CommandParameters.STATUS:
            return order1.getStatus().compareTo(order2.getStatus());
        default:
            return 0;
        }
    }
}
