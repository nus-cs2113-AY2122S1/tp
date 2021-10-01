package inventory;

import java.util.Date;

/**
 * Represents a Dispensed object. A Dispensed object is represented by medicine name, quantity, customer's NRIC,
 * date and staff name.
 */

public class Dispense extends Stock {
    protected String customerNRIC;
    protected Date date;
    protected String staffName;

    public Dispense(String medicineName, int quantity, String customerName, Date date, String staffName) {
        super(medicineName, quantity);
        this.customerNRIC = customerName;
        this.date = date;
        this.staffName = staffName;
    }

    public String getCustomerNRIC() {
        return customerNRIC;
    }

    public void setCustomerNRIC(String customerNRIC) {
        this.customerNRIC = customerNRIC;
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
