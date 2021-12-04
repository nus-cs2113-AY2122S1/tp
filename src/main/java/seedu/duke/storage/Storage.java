package seedu.duke.storage;

import seedu.duke.data.DataManager;
import seedu.duke.storage.data.exercise.exercisebank.ExerciseBankStorage;
import seedu.duke.storage.data.exercise.exerciselist.ExerciseListStorage;
import seedu.duke.storage.data.exercise.futurelist.UpcomingStorage;
import seedu.duke.storage.data.food.foodbank.FoodBankStorage;
import seedu.duke.storage.data.food.foodlist.FoodListStorage;
import seedu.duke.storage.data.profile.ProfileStorage;

/**
 * API of the Storage component.
 */
public interface Storage extends ProfileStorage, FoodListStorage, ExerciseListStorage,
        UpcomingStorage, FoodBankStorage, ExerciseBankStorage {

    String FILE_TEXT_DELIMITER = "\\|";
    String FILEPATH = "./data/";
    String FILENAME_PROFILE = "profile.txt";
    String FILEPATH_PROFILE = FILEPATH + FILENAME_PROFILE;
    String FILENAME_BANK_FOOD = "food_bank.txt";
    String FILEPATH_BANK_FOOD = FILEPATH + FILENAME_BANK_FOOD;
    String FILENAME_LIST_FOOD = "food_list.txt";
    String FILEPATH_LIST_FOOD = FILEPATH + FILENAME_LIST_FOOD;
    String FILENAME_BANK_EXERCISE = "exercise_bank.txt";
    String FILEPATH_BANK_EXERCISE = FILEPATH + FILENAME_BANK_EXERCISE;
    String FILENAME_LIST_EXERCISE = "exercise_list.txt";
    String FILEPATH_LIST_EXERCISE = FILEPATH + FILENAME_LIST_EXERCISE;
    String FILENAME_LIST_FUTURE = "future_list.txt";
    String FILEPATH_LIST_FUTURE = FILEPATH + FILENAME_LIST_FUTURE;

    /**
     * Loads all the data into the DataManager object.
     *
     * @return DataManager containing data loaded from storage
     */
    DataManager loadAll();

    /**
     * Saves all the data in the DataManager object.
     * Usually used when exiting the program.
     *
     * @param dataManager DataManager containing the data to be saved
     */
    void saveAll(DataManager dataManager);
}
