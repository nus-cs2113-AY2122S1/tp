package inventory;

import utilities.parser.DateParser;

import java.util.Date;

/**
 * Represents a Dispensed object. A Dispensed object is represented by medicine name, quantity, customer's NRIC,
 * date and staff name.
 */
public class Prescription extends Medicine {
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String QUANTITY = "QUANTITY";
    public static final String CUSTOMER_ID = "CUSTOMER ID";
    public static final String DATE = "DATE";
    public static final String STAFF = "STAFF";
    public static final String STOCK_ID = "STOCK ID";

    // Used for sorting
    public static final String ID_LOWERCASE = "id";
    public static final String NAME_LOWERCASE = "name";
    public static final String QUANTITY_LOWERCASE = "quantity";
    public static final String CUSTOMER_ID_LOWERCASE = "customer id";
    public static final String DATE_LOWERCASE = "date";
    public static final String STAFF_LOWERCASE = "staff";
    public static final String STOCK_ID_LOWERCASE = "stock id";

    public static final String[] COLUMNS = {ID, NAME, QUANTITY, CUSTOMER_ID, DATE, STAFF, STOCK_ID};

    private static int prescriptionCount = 0;
    protected int prescriptionId;
    protected String customerId;
    protected Date date;
    protected String staff;
    protected int stockId;

    public Prescription(String medicineName, int quantity, String customerId, Date date, String staff, int stockId) {
        super(medicineName, quantity);
        prescriptionCount++;
        this.prescriptionId = prescriptionCount;
        this.customerId = customerId;
        this.date = date;
        this.staff = staff;
        this.stockId = stockId;
    }

    public static int getPrescriptionCount() {
        return prescriptionCount;
    }

    public static void setPrescriptionCount(int prescriptionCount) {
        Prescription.prescriptionCount = prescriptionCount;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
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

    public String toFileFormat() {
        String fileFormat = getPrescriptionId() + "|" + getMedicineName().toUpperCase() + "|" + getQuantity() + "|"
                + getCustomerId().toUpperCase() + "|" + DateParser.dateToString(getDate()) + "|"
                + getStaff().toUpperCase() + "|" + getStockId();
        return fileFormat;
    }

    public String toArchiveFormat() {
        String archiveFormat = "[PRESCRIPTION ID: " + getPrescriptionId() + "] " + getQuantity() + " "
                + getMedicineName().toUpperCase() + " [STOCK ID: " + getStockId() + "] WAS PRESCRIBED BY "
                + getStaff().toUpperCase() + " TO " + getCustomerId().toUpperCase() + " ON "
                + DateParser.dateToString(getDate());
        return archiveFormat;
    }

}
