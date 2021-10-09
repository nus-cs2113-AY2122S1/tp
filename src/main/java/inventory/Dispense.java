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
    public static final String CUSTOMERS_NRIC = "CUSTOMER'S NRIC";
    public static final String DATE = "DATE";
    public static final String STAFF_NAME = "STAFF NAME";

    public static final String[] COLUMNS = {ID, NAME, QUANTITY, CUSTOMERS_NRIC, DATE, STAFF_NAME};

    private static int dispenseCount = 0;
    protected int dispenseId;
    protected String customerNric;
    protected Date date;
    protected String staffName;

    public Dispense(String medicineName, int quantity, String customerNric, Date date, String staffName) {
        super(medicineName, quantity);
        dispenseCount++;
        this.dispenseId = dispenseCount;
        this.customerNric = customerNric;
        this.date = date;
        this.staffName = staffName;
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

    public String getCustomerNric() {
        return customerNric;
    }

    public void setCustomerNric(String customerNric) {
        this.customerNric = customerNric;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
