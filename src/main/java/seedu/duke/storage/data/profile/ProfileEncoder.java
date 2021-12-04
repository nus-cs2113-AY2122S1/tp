package seedu.duke.storage.data.profile;

import seedu.duke.data.profile.Profile;

import java.util.ArrayList;

/**
 * Encodes the profile attributes into an ArrayList to be saved.
 */
public class ProfileEncoder {

    /**
     * Encodes profile for storage.
     *
     * @param profile The profile object to be stored.
     * @return An arraylist that contains the profile details to save.
     */
    public static ArrayList<String> encode(Profile profile) {
        return new ArrayList<String>() {
            {
                add(profile.toFileTextString());
            }
        };
    }
}
