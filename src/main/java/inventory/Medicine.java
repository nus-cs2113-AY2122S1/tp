package inventory;

import java.util.ArrayList;

/**
 * Represents the generic stock for the application. It contains the medicine name and quantity.
 * It is inherited by Prescription, Medicine and Order objects.
 */
public abstract class Medicine {
    protected String medicineName;
    protected int quantity;
    private static ArrayList<Medicine> medicines = null;

    /**
     * Helps to create the medicine arraylist or returns the arraylist if it exists.
     *
     * @return The medicine's arraylist.
     */
    public static ArrayList<Medicine> getInstance() {
        if (medicines == null) {
            medicines = new ArrayList<>();
        }
        return medicines;
    }

    public Medicine(String medicineName, int quantity) {
        this.medicineName = medicineName;
        this.quantity = quantity;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public abstract String toFileFormat();

    public abstract String toArchiveFormat();
}
