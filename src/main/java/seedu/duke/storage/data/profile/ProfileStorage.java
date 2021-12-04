package seedu.duke.storage.data.profile;

import seedu.duke.data.profile.Profile;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

/**
 * Interface that ensures the storage device has a load and save profile method.
 */
public interface ProfileStorage {
    /**
     * Loads the profile file from data storage.
     *
     * @return Profile object from data storage
     * @throws UnableToReadFileException if the filepath given is inaccessible or I/O was interrupted
     */
    Profile loadProfile() throws UnableToReadFileException;

    /**
     * Saves the profile details into storage.
     * Used when there is an update to any profile attribute.
     *
     * @param profile Profile of the current user
     * @throws UnableToWriteFileException if the filepath given is inaccessible or I/O was interrupted
     */
    void saveProfile(Profile profile) throws UnableToWriteFileException;
}
