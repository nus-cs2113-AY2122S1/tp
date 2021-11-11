package seedu.storage;

import seedu.exceptions.ProfileException;

import com.google.gson.Gson;
import seedu.user.Profile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileStorage {
    private final File file;
    private static final String PATH = "data/profile.json";

    /**
     * Initialize TimetableStorage.
     */
    public ProfileStorage() {
        this.file = new File(PATH);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets the locally saved profile.
     *
     * @return The profile saved.
     * @throws ProfileException if no profile can be found locally.
     *     Always throws on first-time startup.
     */
    public Profile loadProfile() throws ProfileException {
        try {
            FileReader timetableSaveReader = new FileReader(file);
            Profile profile = new Gson().fromJson(timetableSaveReader, Profile.class);
            if (profile == null) {
                throw new ProfileException(ProfileException.Cause.NO_PROFILE_FOUND);
            }
            return profile;
        } catch (IOException e) {
            throw new ProfileException(ProfileException.Cause.NO_PROFILE_FOUND);
        }
    }

    /**
     * Saves the profile in use.
     *
     * @param profile Profile to be saved
     */
    public void save(Profile profile) {
        try {
            FileWriter fw = new FileWriter(file);
            Gson gson = new Gson();
            gson.toJson(profile, fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("failed to save timetable");
        }
    }
}
