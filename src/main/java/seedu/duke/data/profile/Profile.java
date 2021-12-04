package seedu.duke.data.profile;

import seedu.duke.data.profile.attributes.ActivityFactor;
import seedu.duke.data.profile.attributes.Age;
import seedu.duke.data.profile.attributes.CalorieGoal;
import seedu.duke.data.profile.attributes.Gender;
import seedu.duke.data.profile.attributes.Height;
import seedu.duke.data.profile.attributes.Name;
import seedu.duke.data.profile.attributes.Weight;

/**
 * Profile that contains the relevant details input by user.
 */
public class Profile {

    public static final String FILE_TEXT_DELIMITER = "|";

    private static final String LS = System.lineSeparator();
    private static final String TAB = "\t";
    private static final String INDENTED_LS = LS + TAB;

    public static final String MESSAGE_PROFILE = "Hello %1$s! This is your profile:" + LS
            + "*==========================================================="
            + INDENTED_LS + "Height                 %2$scm"
            + INDENTED_LS + "Weight                 %3$skg"
            + INDENTED_LS + "Gender                 %4$s"
            + INDENTED_LS + "Age                    %5$s"
            + INDENTED_LS + "Calorie Goal           %6$s cal"
            + INDENTED_LS + "Activity Factor        %7$s"
            + LS + "===========================================================*";

    protected Name name;
    protected Height height;
    protected Weight weight;
    protected Gender gender;
    protected Age age;
    protected CalorieGoal calorieGoal;
    protected ActivityFactor activityFactor;

    /**
     * Initializing a new Profile class with empty attributes.
     */
    public Profile() {
        name = new Name();
        height = new Height();
        weight = new Weight();
        gender = new Gender();
        age = new Age();
        calorieGoal = new CalorieGoal();
        activityFactor = new ActivityFactor();
    }

    /**
     * Constructor for the Profile class.
     *
     * @param name           Name of user
     * @param height         Height of user
     * @param weight         Weight of user
     * @param calorieGoal    Calorie target of user
     * @param gender         Gender of user (M/F)
     * @param age            Age of user
     * @param activityFactor Activity level of user
     */
    public Profile(Name name, Height height, Weight weight, Gender gender,
                   Age age, CalorieGoal calorieGoal, ActivityFactor activityFactor) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
        this.calorieGoal = calorieGoal;
        this.activityFactor = activityFactor;
    }

    /**
     * A set command that enables setting of profile through passing by reference.
     *
     * @param name           Name of user
     * @param height         Height of user
     * @param weight         Weight of user
     * @param calorieGoal    Calorie target of user
     * @param gender         Gender of user (M/F)
     * @param age            Age of user
     * @param activityFactor Activity level of user
     */
    public void setProfile(Name name, Height height, Weight weight, Gender gender,
                           Age age, CalorieGoal calorieGoal, ActivityFactor activityFactor) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
        this.calorieGoal = calorieGoal;
        this.activityFactor = activityFactor;
    }

    /**
     * Sets the profile in various commands with the raw inputs if necessary.
     * Usually used for retrieving data from storage.
     *
     * @param name           Name of user
     * @param height         Height of user
     * @param weight         Weight of user
     * @param calorieGoal    Calorie target of user
     * @param gender         Gender of user (M/F)
     * @param age            Age of user
     * @param activityFactor Activity level of user
     */
    public void setProfileWithRawInputs(String name, double height, double weight,
                                        char gender, int age, int calorieGoal, int activityFactor) {
        this.name.setName(name);
        this.height.setHeight(height);
        this.weight.setWeight(weight);
        this.gender.setGender(gender);
        this.age.setAge(age);
        this.calorieGoal.setCalorieGoal(calorieGoal);
        this.activityFactor.setUserInput(activityFactor);
    }

    /**
     * Sets the profile name with a new Name object.
     *
     * @param name Name object to be set
     */
    public void setProfileName(Name name) {
        this.name = name;
    }

    /**
     * Sets the profile height with a new Height object.
     *
     * @param height Height object to be set
     */
    public void setProfileHeight(Height height) {
        this.height = height;
    }

    /**
     * Sets the profile weight with a new Weight object.
     *
     * @param weight Weight object to be set
     */
    public void setProfileWeight(Weight weight) {
        this.weight = weight;
    }

    /**
     * Sets the profile gender with a new Profile object.
     *
     * @param gender Gender object to be set
     */
    public void setProfileGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Sets the profile age with a new Age object.
     *
     * @param age Age object to be set
     */
    public void setProfileAge(Age age) {
        this.age = age;
    }

    /**
     * Sets the profile calorie goal with a new CalorieGoal object.
     *
     * @param calorieGoal CalorieGoal object to be set
     */
    public void setProfileCalorieGoal(CalorieGoal calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    /**
     * Sets the profile activity factor with a new ActivityFactor object.
     *
     * @param activityFactor ActivityFactor object to be set
     */
    public void setProfileActivityFactor(ActivityFactor activityFactor) {
        this.activityFactor = activityFactor;
    }

    /**
     * Retrieves the Name object from the profile.
     *
     * @return Name object
     */
    public Name getProfileName() {
        return this.name;
    }

    /**
     * Retrieves the Height object from the profile.
     *
     * @return Height object
     */
    public Height getProfileHeight() {
        return this.height;
    }

    /**
     * Retrieves the Weight object from the profile.
     *
     * @return Weight object
     */
    public Weight getProfileWeight() {
        return this.weight;
    }

    /**
     * Retrieves the Gender object from the profile.
     *
     * @return Gender object
     */
    public Gender getProfileGender() {
        return this.gender;
    }

    /**
     * Retrieves the Age object from the profile.
     *
     * @return Age object
     */
    public Age getProfileAge() {
        return this.age;
    }

    /**
     * Retrieves the CalorieGoal object from the profile.
     *
     * @return CalorieGoal object
     */
    public CalorieGoal getProfileCalorieGoal() {
        return this.calorieGoal;
    }

    /**
     * Retrieves the ActivityFactor object from the profile.
     *
     * @return ActivityFactor object
     */
    public ActivityFactor getProfileActivityFactor() {
        return this.activityFactor;
    }

    /**
     * Converts the Profile to a String for printing purposes.
     *
     * @return Formatted String with all Profile attributes.
     */
    public String convertToString() {
        return String.format(MESSAGE_PROFILE,
                this.name.getName(),
                this.height.getHeight(),
                this.weight.getWeight(),
                this.gender.getGender(),
                this.age.getAge(),
                this.calorieGoal.getCalorieGoal(),
                this.activityFactor.getUserInput());
    }

    /**
     * Converts the file into a string that is used for storage.
     *
     * @return String that is used for storage.
     */
    public String toFileTextString() {
        return name.getName() + FILE_TEXT_DELIMITER + height.getHeight() + FILE_TEXT_DELIMITER
                + weight.getWeight() + FILE_TEXT_DELIMITER + gender.getGender() + FILE_TEXT_DELIMITER
                + age.getAge() + FILE_TEXT_DELIMITER + calorieGoal.getCalorieGoal() + FILE_TEXT_DELIMITER
                + activityFactor.getUserInput();
    }

    /**
     * Check if all attributes of profile are valid.
     *
     * @return false if at least one of the profile attributes are invalid.
     */
    public boolean checkProfileComplete() {
        return name.isValid() && height.isValid() && weight.isValid() && gender.isValid()
                && age.isValid() && activityFactor.isValid() && calorieGoal.isValid();
    }

    /**
     * Check if any of profile attributes is valid.
     * If all profile attributes are incorrect, it will be deemed as profile not present.
     *
     * @return true if at least one of the profile attributes is valid.
     */
    public boolean checkProfilePresent() {
        return name.isValid() || height.isValid() || weight.isValid() || gender.isValid()
                || age.isValid() || activityFactor.isValid();
    }

}
