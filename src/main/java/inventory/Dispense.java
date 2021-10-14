package inventory;

import java.util.Date;

/**
 * Represents a Dispensed object. A Dispensed object is represented by medicine name, quantity, customer's NRIC,
 * date and staff name.
 */

public class Dispense extends Medicine {
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String QUANTITY = "QUANTITY";
    public static final String CUSTOMERS_ID = "CUSTOMER'S ID";
    public static final String DATE = "DATE";
    public static final String STAFF = "STAFF";
    public static final String STOCK_ID = "STOCK ID";

    // Used for sorting
    public static final String ID_LOWERCASE = "id";
    public static final String NAME_LOWERCASE = "name";
    public static final String QUANTITY_LOWERCASE = "quantity";
    public static final String CUSTOMERS_ID_LOWERCASE = "customer's id";
    public static final String DATE_LOWERCASE = "date";
    public static final String STAFF_LOWERCASE = "staff";
    public static final String STOCK_ID_LOWERCASE = "stock id";

    public static final String[] COLUMNS = {ID, NAME, QUANTITY, CUSTOMERS_ID, DATE, STAFF, STOCK_ID};

    private static int dispenseCount = 0;
    protected int dispenseId;
    protected String customerId;
    protected Date date;
    protected String staff;
    protected int stockId;

    public Dispense(String medicineName, int quantity, String customerId, Date date, String staff, int stockId) {
        super(medicineName, quantity);
        dispenseCount++;
        this.dispenseId = dispenseCount;
        this.customerId = customerId;
        this.date = date;
        this.staff = staff;
        this.stockId = stockId;
    }

    public static int getDispenseCount() {
        return dispenseCount;
    }

    public static void setDispenseCount(int dispenseCount) {
        Dispense.dispenseCount = dispenseCount;
    }

    public int getDispenseId() {
        return dispenseId;
    }

    public void setDispenseId(int dispenseId) {
        this.dispenseId = dispenseId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

}
