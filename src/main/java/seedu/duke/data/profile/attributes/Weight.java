package seedu.duke.data.profile.attributes;

import seedu.duke.data.Verifiable;

/**
 * Weight attribute of profile.
 */
public class Weight implements Verifiable {

    public static final int LOWER_WEIGHT_LIMIT = 1;
    public static final int UPPER_WEIGHT_LIMIT = 300;

    protected double weight;

    public Weight() {

    }

    /**
     * Constructs a weight object.
     *
     * @param weight weight input by user
     */
    public Weight(double weight) {
        setWeight(weight);
    }

    /**
     * Retrieves the weight from the Weight object.
     *
     * @return the weight of Weight object
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the Weight object.
     *
     * @param weight weight input by user
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean isValid() {
        return weight >= LOWER_WEIGHT_LIMIT && weight <= UPPER_WEIGHT_LIMIT;
    }
}
