package seedu.duke.storage;

import seedu.duke.data.DataManager;
import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.storage.data.exercise.exercisebank.ExerciseBankStorage;
import seedu.duke.storage.data.exercise.exercisebank.ExerciseBankStorageUtils;
import seedu.duke.storage.data.exercise.exerciselist.ExerciseListStorageUtils;
import seedu.duke.storage.data.exercise.exerciselist.ExerciseListStorage;
import seedu.duke.storage.data.exercise.futurelist.FutureExerciseListStorageUtils;
import seedu.duke.storage.data.exercise.futurelist.UpcomingStorage;
import seedu.duke.storage.data.food.foodbank.FoodBankStorage;
import seedu.duke.storage.data.food.foodbank.FoodBankStorageUtils;
import seedu.duke.storage.data.food.foodlist.FoodListStorage;
import seedu.duke.storage.data.food.foodlist.FoodListStorageUtils;
import seedu.duke.storage.data.profile.ProfileStorage;
import seedu.duke.storage.data.profile.ProfileStorageUtils;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

/**
 * Manages the loading and saving from various storage subclasses.
 */
public class StorageManager implements Storage {

    private final ProfileStorage profileStorage;
    private final ExerciseListStorage exerciseListStorage;
    private final FoodListStorage foodListStorage;
    private final UpcomingStorage futureExerciseListStorage;
    private final FoodBankStorage foodBankStorage;
    private final ExerciseBankStorage exerciseBankStorage;

    /**
     * Constructor for the StorageManager object.
     */
    public StorageManager() {
        this.profileStorage = new ProfileStorageUtils(Storage.FILEPATH_PROFILE);
        this.exerciseListStorage = new ExerciseListStorageUtils(Storage.FILEPATH_LIST_EXERCISE);
        this.foodListStorage = new FoodListStorageUtils(Storage.FILEPATH_LIST_FOOD);
        this.futureExerciseListStorage = new FutureExerciseListStorageUtils(Storage.FILEPATH_LIST_FUTURE);
        this.foodBankStorage = new FoodBankStorageUtils(Storage.FILEPATH_BANK_FOOD);
        this.exerciseBankStorage = new ExerciseBankStorageUtils(Storage.FILEPATH_BANK_EXERCISE);
    }

    @Override
    public DataManager loadAll() {
        return new DataManager(
                loadExerciseList(),
                loadFutureExerciseList(),
                loadFoodList(),
                loadExerciseBank(),
                loadFoodBank(),
                loadProfile());
    }

    @Override
    public void saveAll(DataManager dataManager) {
        try {
            saveProfile(dataManager.getProfile());
            saveExerciseList(dataManager.getExerciseItems());
            saveFoodList(dataManager.getFoodItems());
            saveFutureExerciseList(dataManager.getFutureExerciseItems());
            saveFoodBank(dataManager.getFoodBank());
            saveExerciseBank(dataManager.getExerciseBank());
        } catch (UnableToWriteFileException e) {
            System.out.println("Fitbot was unable to save all your session data. :(");
        }
    }


    //=================== Profile Methods =======================

    @Override
    public Profile loadProfile() {
        try {
            return profileStorage.loadProfile();
        } catch (UnableToReadFileException e) {
            return new Profile();
        }
    }

    @Override
    public void saveProfile(Profile profile) throws UnableToWriteFileException {
        profileStorage.saveProfile(profile);
    }

    //=================== ExerciseList Methods ==================

    @Override
    public ExerciseList loadExerciseList() {
        try {
            return exerciseListStorage.loadExerciseList();
        } catch (UnableToReadFileException e) {
            return new ExerciseList();
        }
    }

    @Override
    public void saveExerciseList(ExerciseList exerciseList) throws UnableToWriteFileException {
        exerciseListStorage.saveExerciseList(exerciseList);
    }

    //================= FutureExerciseList Methods ==============

    @Override
    public FutureExerciseList loadFutureExerciseList() {
        try {
            return futureExerciseListStorage.loadFutureExerciseList();
        } catch (UnableToReadFileException e) {
            return new FutureExerciseList();
        }
    }

    @Override
    public void saveFutureExerciseList(FutureExerciseList futureExerciseList) throws UnableToWriteFileException {
        futureExerciseListStorage.saveFutureExerciseList(futureExerciseList);
    }

    //===================== FoodList Methods ====================

    @Override
    public FoodList loadFoodList() {
        try {
            return foodListStorage.loadFoodList();
        } catch (UnableToReadFileException e) {
            return new FoodList();
        }
    }

    @Override
    public void saveFoodList(FoodList foodList) throws UnableToWriteFileException {
        foodListStorage.saveFoodList(foodList);
    }

    //================= ExerciseBank Methods ====================

    @Override
    public ItemBank loadExerciseBank() {
        try {
            return exerciseBankStorage.loadExerciseBank();
        } catch (UnableToReadFileException e) {
            return new ItemBank();
        }
    }

    @Override
    public void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException {
        exerciseBankStorage.saveExerciseBank(exerciseBank);
    }

    //===================== FoodBank Methods ====================

    @Override
    public ItemBank loadFoodBank() {
        try {
            return foodBankStorage.loadFoodBank();
        } catch (UnableToReadFileException e) {
            return new ItemBank();
        }
    }

    @Override
    public void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException {
        foodBankStorage.saveFoodBank(foodBank);
    }

}
