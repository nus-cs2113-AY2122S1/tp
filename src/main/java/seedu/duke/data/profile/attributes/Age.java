package seedu.duke.data.profile.attributes;

import seedu.duke.data.Verifiable;

/**
 * Age attribute of profile.
 */
public class Age implements Verifiable {

    public static final int LOWER_AGE_LIMIT = 10;
    public static final int UPPER_AGE_LIMIT = 150;

    protected int age;

    public Age() {

    }

    /**
     * Constructs an age object.
     *
     * @param age age input by user
     */
    public Age(int age) {
        setAge(age);
    }

    /**
     * Retrieves age of Age object.
     *
     * @return age of Age object
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of Age object.
     *
     * @param age age input by user
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean isValid() {
        return age >= LOWER_AGE_LIMIT && age <= UPPER_AGE_LIMIT;
    }
}
