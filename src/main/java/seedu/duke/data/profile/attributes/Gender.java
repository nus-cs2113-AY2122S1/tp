package seedu.duke.data.profile.attributes;

import seedu.duke.data.Verifiable;

/**
 * Gender attribute of profile.
 */
public class Gender implements Verifiable {

    private static final char GENDER_M = 'M';
    private static final char GENDER_F = 'F';

    protected char gender;

    public Gender() {

    }

    /**
     * Constructs a gender object.
     *
     * @param gender gender input by user.
     */
    public Gender(char gender) {
        this.gender = Character.toUpperCase(gender);
    }

    /**
     * Retrieves the gender of the Gender object.
     *
     * @return the gender of the gender object.
     */
    public char getGender() {
        return gender;
    }

    /**
     * Sets the gender of the Gender object.
     *
     * @param gender gender input by user
     */
    public void setGender(char gender) {
        this.gender = Character.toUpperCase(gender);
    }

    @Override
    public boolean isValid() {
        return gender == GENDER_F || gender == GENDER_M;
    }


}
