package seedu.duke.ingredients;

//import java.time.LocalDate;

/**
 * Represents an Ingredient.
 */
public class Ingredient {
    protected String name;
    protected Double amount;
    protected String units;
    protected String expiry; //For v1.0, the expiry date will be stored as a string

    public Ingredient(String name, double amount, String units, String expiry) {
        this.name = name;
        this.amount = amount;
        this.units = units;
        this.expiry = expiry;
    }

    public String getName() {
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

    public Double getAmount() {
        return amount;
    }

    public String getUnits() {
        return units;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
    @Override
    public String toString() {
        return getName() + " | Amount Left: " + getAmount() + " " + getUnits() + " | Expiry Date: " + getExpiry();
    }
}
