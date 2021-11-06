package inventory;

import utilities.parser.DateParser;

import java.util.Date;

/**
 * Represents an Order. An Order is represented by order_id, medicine name, quantity, date and isDelivered.
 */
public class Order extends Medicine {
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String QUANTITY = "QUANTITY";
    public static final String DATE = "DATE";
    public static final String STATUS = "STATUS";

    // Used for sorting
    public static final String ID_LOWERCASE = "id";
    public static final String NAME_LOWERCASE = "name";
    public static final String QUANTITY_LOWERCASE = "quantity";
    public static final String DATE_LOWERCASE = "date";
    public static final String STATUS_LOWERCASE = "status";

    public static final String[] COLUMNS = {ID, NAME, QUANTITY, DATE, STATUS};

    private static int orderCount = 0;
    protected int orderId;
    protected Date date;
    protected boolean isDelivered;

    public Order(String medicineName, int quantity, Date date) {
        super(medicineName, quantity);
        orderCount++;
        this.orderId = orderCount;
        this.date = date;
        this.isDelivered = false;
    }

    public static int getOrderCount() {
        return orderCount;
    }

    public static void setOrderCount(int orderCount) {
        Order.orderCount = orderCount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered() {
        isDelivered = true;
    }

    public String getStatus() {
        if (isDelivered) {
            return "DELIVERED";
        } else {
            return "PENDING";
        }
    }

    public String toFileFormat() {
        String fileFormat = getOrderId() + "|" + getMedicineName().toUpperCase() + "|" + getQuantity() + "|"
                + DateParser.dateToString(getDate()) + "|" + getStatus();
        return fileFormat;
    }

    public String toArchiveFormat() {
        String archiveFormat = "[ORDER ID: " + getOrderId() + "] " + getQuantity() + " "
                + getMedicineName().toUpperCase() + " WAS ORDERED ON " + DateParser.dateToString(getDate())
                + ". STATUS: " + getStatus();
        return archiveFormat;
    }
}
