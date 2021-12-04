package seedu.duke.logic.commands;

import seedu.duke.data.profile.attributes.ActivityFactor;
import seedu.duke.data.profile.attributes.Age;
import seedu.duke.data.profile.attributes.CalorieGoal;
import seedu.duke.data.profile.attributes.Gender;
import seedu.duke.data.profile.attributes.Height;
import seedu.duke.data.profile.attributes.Name;
import seedu.duke.data.profile.attributes.Weight;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.data.profile.utilities.ProfileUtils;

/**
 * Represents the command that when executed, changes the value of attributes in the Profile.
 */
public class ProfileUpdateCommand extends Command {

    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_NAME,
            COMMAND_PREFIX_HEIGHT,
            COMMAND_PREFIX_WEIGHT,
            COMMAND_PREFIX_GOAL,
            COMMAND_PREFIX_AGE,
            COMMAND_PREFIX_ACTIVITY_FACTOR,
            COMMAND_PREFIX_GENDER
    };

    public static final String MESSAGE_SUCCESS = "Your profile has been updated!" + CommandMessages.LS + "%s";

    private Name name;
    private Weight weight;
    private Height height;
    private CalorieGoal calorieGoal;
    private Age age;
    private ActivityFactor activityFactor;
    private Gender gender;


    public ProfileUpdateCommand(String name, Double height, Double weight, Integer calorieGoal, Integer age,
                                Integer activityFactor, Character gender) {
        this.name = name == null ? null : new Name(name);
        this.height = height == null ? null : new Height(height);
        this.weight = weight == null ? null : new Weight(weight);
        this.calorieGoal = calorieGoal == null ? null : new CalorieGoal(calorieGoal);
        this.gender = gender == NULL_CHAR ? null : new Gender(gender);
        this.age = age == null ? null : new Age(age);
        this.activityFactor = activityFactor == null ? null : new ActivityFactor(activityFactor);

    }

    private void checkIfCommandShouldExecute() throws InvalidCharacteristicException {
        if (!height.isValid()) {
            throw new InvalidCharacteristicException(ProfileUtils.ERROR_HEIGHT);
        }
        if (!weight.isValid()) {
            throw new InvalidCharacteristicException(ProfileUtils.ERROR_WEIGHT);
        }
        if (!gender.isValid()) {
            throw new InvalidCharacteristicException(ProfileUtils.ERROR_GENDER);
        }
        if (!age.isValid()) {
            throw new InvalidCharacteristicException(ProfileUtils.ERROR_AGE);
        }
        if (!calorieGoal.isValid()) {
            throw new InvalidCharacteristicException(ProfileUtils.ERROR_CALORIE_GOAL);
        }
        if (!activityFactor.isValid()) {
            throw new InvalidCharacteristicException(ProfileUtils.ERROR_ACTIVITY_FACTOR);
        }
    }

    @Override
    public CommandResult execute() {
        try {
            this.name = name == null ? super.profile.getProfileName() : name;
            this.height = height == null ? super.profile.getProfileHeight() : height;
            this.weight = weight == null ? super.profile.getProfileWeight() : weight;
            this.gender = gender == null ? super.profile.getProfileGender() : gender;
            this.age = age == null ? super.profile.getProfileAge() : age;
            this.calorieGoal = calorieGoal == null
                    ? super.profile.getProfileCalorieGoal()
                    : calorieGoal;
            this.activityFactor = activityFactor == null
                    ? super.profile.getProfileActivityFactor()
                    : activityFactor;

            checkIfCommandShouldExecute();
            super.profile.setProfile(this.name, this.height, this.weight,
                    this.gender, this.age, this.calorieGoal, this.activityFactor);
            return new CommandResult(String.format(
                    MESSAGE_SUCCESS, super.profile.convertToString()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
