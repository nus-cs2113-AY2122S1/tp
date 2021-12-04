package seedu.duke.storage.data.profile;

import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.attributes.ActivityFactor;
import seedu.duke.data.profile.attributes.Age;
import seedu.duke.data.profile.attributes.CalorieGoal;
import seedu.duke.data.profile.attributes.Gender;
import seedu.duke.data.profile.attributes.Height;
import seedu.duke.data.profile.attributes.Name;
import seedu.duke.data.profile.attributes.Weight;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Decodes the profile from storage data.
 */
public class ProfileDecoder {

    /**
     * Retrieves profile data from profile.txt
     *
     * @return The profile object with its corresponding characteristics
     * @throws FileNotFoundException          If the file is misplaced/missing
     */
    public static Profile retrieveProfileFromData(String filePath) throws FileNotFoundException {
        try {
            File file = new File(filePath);
            Scanner in = new Scanner(file);
            if (in.hasNext()) {
                return decodeProfile(in.nextLine());
            }
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
        throw new FileNotFoundException();
    }

    private static Profile decodeProfile(String line) throws InvalidDataException {
        try {
            final String[] profileDetails = line.split(Storage.FILE_TEXT_DELIMITER);
            final Name name = decodeName(profileDetails[0]);
            final Height height = decodeHeight(profileDetails[1]);
            final Weight weight = decodeWeight(profileDetails[2]);
            final Gender gender = decodeGender(profileDetails[3]);
            final Age age = decodeAge(profileDetails[4]);
            final CalorieGoal calorieGoal = decodeCalorieGoal(profileDetails[5]);
            final ActivityFactor activityFactor = decodeActivityFactor(profileDetails[6]);
            return new Profile(name, height, weight, gender, age, calorieGoal, activityFactor);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDataException(Storage.FILENAME_PROFILE, line);
        }
    }

    private static Name decodeName(String detail) {
        return new Name(detail);
    }

    private static Height decodeHeight(String detail) {
        try {
            return new Height(Double.parseDouble(detail));
        } catch (NumberFormatException e) {
            //Returns an invalid height for startup to detect
            return new Height(Double.MIN_VALUE);
        }
    }

    private static Weight decodeWeight(String detail) {
        try {
            return new Weight(Double.parseDouble(detail));
        } catch (NumberFormatException e) {
            //Returns an invalid weight for startup to detect
            return new Weight(Double.MIN_VALUE);
        }
    }

    private static Gender decodeGender(String detail) {
        if (detail.length() > 1) {
            //Returns an invalid gender for startup to detect
            return new Gender('X');
        }
        return new Gender(detail.charAt(0));
    }

    private static Age decodeAge(String detail) {
        try {
            return new Age(Integer.parseInt(detail));
        } catch (NumberFormatException e) {
            //Returns an invalid Age for startup to detect
            return new Age(Integer.MIN_VALUE);
        }
    }

    private static CalorieGoal decodeCalorieGoal(String detail) {
        try {
            return new CalorieGoal(Integer.parseInt(detail));
        } catch (NumberFormatException e) {
            //Returns an invalid CalorieGoal for startup to detect
            return new CalorieGoal(Integer.MIN_VALUE);
        }
    }

    private static ActivityFactor decodeActivityFactor(String detail) {
        try {
            return new ActivityFactor(Integer.parseInt(detail));
        } catch (NumberFormatException e) {
            //Returns an invalid ActivityFactor for startup to detect
            return new ActivityFactor(Integer.MIN_VALUE);
        }
    }
}
