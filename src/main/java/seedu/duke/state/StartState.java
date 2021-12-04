package seedu.duke.state;

import seedu.duke.data.profile.Profile;
import seedu.duke.logic.Statistics;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author tttyyzzz

public class StartState {

    private Profile profile;
    private StorageManager storageManager;
    private Ui ui;
    private static Logger logger = Logger.getLogger(Statistics.class.getName());

    public StartState(Profile profile, StorageManager storageManager, Ui ui) {
        this.profile = profile;
        this.storageManager = storageManager;
        this.ui = ui;
    }

    /**
     * Check whether user's profile is complete.
     * If profile is complete, the program will exit this method.
     * If the profile is partially complete, it will assist user in completing the profile.
     * If all parameters of profile is incorrect or a new user, user is required to complete
     * all the particulars before saving their profile data.
     */
    public Profile checkAndCreateProfile() {
        if (profile.checkProfileComplete()) {
            logger.log(Level.FINE,"profile is complete");
            return profile;
        }
        if (profile.checkProfilePresent()) {
            assert !profile.checkProfileComplete() : "profile is incomplete";
            logger.log(Level.FINE,"profile is partially complete");
            repairProfile();
        } else {
            logger.log(Level.FINE,"profile is totally wrong or incomplete");
            createNewProfile();
        }

        System.out.println(Ui.MESSAGE_CREATE_PROFILE_SUCCESSFUL + ui.LS + ui.MESSAGE_DIRECT_HELP);
        return profile;
    }

    /**
     * Assists user in fixing remaining profile particulars.
     * The profile changes will be saved on every update.
     */
    private void repairProfile() {
        ui.formatMessageWithTopDivider();
        while (!profile.checkProfileComplete()) {
            try {
                if (!profile.getProfileName().isValid()) {
                    createNewProfileName(profile); // if user just enter and exit, it will cause his name to be null
                } else if (!profile.getProfileHeight().isValid()) {
                    createNewProfileHeight(profile);
                } else if (!profile.getProfileWeight().isValid()) {
                    createNewProfileWeight(profile);
                } else if (!profile.getProfileGender().isValid()) {
                    createNewProfileGender(profile);
                } else if (!profile.getProfileAge().isValid()) {
                    createNewProfileAge(profile);
                } else if (!profile.getProfileActivityFactor().isValid()) {
                    createNewProfileActivityFactor(profile);
                } else if (!profile.getProfileCalorieGoal().isValid()) {
                    createNewProfileCalorieGoal(profile);
                }
                storageManager.saveProfile(this.profile);
            } catch (MissingParamException e) {
                System.out.println(e.getMessage());
            } catch (UnableToWriteFileException e) {
                ui.formatMessageFramedWithDivider(e.getMessage());
            }
        }
        ui.formatMessageWithBottomDivider();
    }

    /**
     * Creates a new profile instance for new user.
     * Profile will be lost if user exits the program without setting up the profile.
     * Upon completing profile, the profile instance in Main will be replaced and stored in storage.
     */
    private void createNewProfile() {
        Profile newProfile = new Profile();
        ui.formatMessageWithTopDivider();
        while (!newProfile.checkProfileComplete()) {
            try {
                if (!newProfile.getProfileName().isValid()) {
                    createNewProfileName(newProfile); // if user just enter and exit, it will cause his name to be null
                } else if (!newProfile.getProfileHeight().isValid()) {
                    createNewProfileHeight(newProfile);
                } else if (!newProfile.getProfileWeight().isValid()) {
                    createNewProfileWeight(newProfile);
                } else if (!newProfile.getProfileGender().isValid()) {
                    createNewProfileGender(newProfile);
                } else if (!newProfile.getProfileAge().isValid()) {
                    createNewProfileAge(newProfile);
                } else if (!newProfile.getProfileActivityFactor().isValid()) {
                    createNewProfileActivityFactor(newProfile);
                } else if (!newProfile.getProfileCalorieGoal().isValid()) {
                    createNewProfileCalorieGoal(newProfile);
                }
            } catch (MissingParamException e) {
                System.out.println(e.getMessage());
            }
        }
        this.profile = newProfile;
        try {
            storageManager.saveProfile(this.profile);
        } catch (UnableToWriteFileException e) {
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
    }

    /**
     * Creates a valid profile activity factor for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws MissingParamException if user input a string of 0 characters.
     */
    private void createNewProfileActivityFactor(Profile newProfile) throws MissingParamException {
        logger.log(Level.FINE,"creating activity factor");
        newProfile.setProfileActivityFactor(new ActivityFactorCreator(ui).createActivityFactor());
    }

    /**
     * Creates a valid profile calorie goal for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws MissingParamException if user input a string of 0 characters.
     */
    private void createNewProfileCalorieGoal(Profile newProfile) throws MissingParamException {
        logger.log(Level.FINE,"creating calorie goal");
        newProfile.setProfileCalorieGoal(new CalorieGoalCreator(ui).createNewCalorieGoal());
    }

    /**
     * Creates a valid profile age for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws MissingParamException if user input a string of 0 characters.
     */
    private void createNewProfileAge(Profile newProfile) throws MissingParamException {
        logger.log(Level.FINE,"creating age");
        newProfile.setProfileAge(new AgeCreator(ui).createNewAge());
    }

    /**
     * Creates a valid profile gender for the profile instance.
     *
     * @param newProfile instance of a profile class.
     */
    private void createNewProfileGender(Profile newProfile) throws MissingParamException {
        logger.log(Level.FINE,"creating gender");
        newProfile.setProfileGender(new GenderCreator(ui).createNewGender());
    }

    /**
     * Creates a valid profile weight for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws MissingParamException if user input a string of 0 characters.
     */
    private void createNewProfileWeight(Profile newProfile) throws MissingParamException {
        logger.log(Level.FINE,"crating weight");
        newProfile.setProfileWeight(new WeightCreator(ui).createNewWeight());
    }

    /**
     * Creates a valid profile height for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws MissingParamException if user input a string of 0 characters.
     */
    private void createNewProfileHeight(Profile newProfile) throws MissingParamException {
        logger.log(Level.FINE,"creating height");
        newProfile.setProfileHeight(new HeightCreator(ui).createNewHeight());
    }

    /**
     * Creates a valid profile name for the profile instance.
     *
     * @param newProfile instance of a profile class.
     * @throws MissingParamException if user input a string of 0 characters.
     */
    private void createNewProfileName(Profile newProfile) throws MissingParamException {
        logger.log(Level.FINE,"crating name");
        newProfile.setProfileName(new NameCreator(ui).createNewName());
    }
}
