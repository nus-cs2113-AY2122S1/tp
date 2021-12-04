package seedu.duke.storage.data;

import seedu.duke.data.item.Item;
import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exceptions.DuplicateItemInBankException;
import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.food.Food;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.data.exercise.exercisebank.ExerciseBankStorageUtils;
import seedu.duke.storage.data.food.foodbank.FoodBankStorageUtils;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Decodes the ItemBanks from their respective data storage files.
 */
public class ItemBankDecoder {

    /**
     * Retrieves data from ItemBank storage.
     *
     * @param filePath path of item bank to be retrieved
     * @param type     type of ItemBank to retrieve to
     * @return the items that have been successfully loaded from the filepath
     * @throws FileNotFoundException If the filepath provided is invalid
     */
    public static ItemBank retrieveDataFromItemBank(String filePath,
                                                    String type) throws FileNotFoundException {
        ItemBank items = new ItemBank();
        File file = new File(filePath);
        Scanner in = new Scanner(file);
        decodeItems(type, items, in);
        return items;
    }

    private static void decodeItems(String type, ItemBank items, Scanner in) {
        while (in.hasNext()) {
            try {
                decodeFoodBankDataFromString(items, in.nextLine(), type);
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void decodeFoodBankDataFromString(ItemBank items, String line,
                                                     String type) throws InvalidDataException {
        try {
            final String[] itemDetails = line.split(Storage.FILE_TEXT_DELIMITER);
            final String name = itemDetails[1];
            final int calories = Integer.parseInt(itemDetails[2]);
            addToRespectiveBank(items, type, name, calories);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException
                | DuplicateItemInBankException | InvalidCharacteristicException e) {
            throw new InvalidDataException(Storage.FILENAME_BANK_FOOD, line);
        }
    }

    private static void addToRespectiveBank(ItemBank items, String type, String name, int calories)
            throws DuplicateItemInBankException, InvalidCharacteristicException {
        if (isFoodType(type)) {
            addFood(items, name, calories);
        } else if (isExerciseType(type)) {
            addExercise(items, name, calories);
        }
    }

    private static void addExercise(ItemBank items, String name, int calories)
            throws InvalidCharacteristicException, DuplicateItemInBankException {
        final Exercise exercise = new Exercise(name, calories);
        checkItemValidity(exercise);
        items.addItem(exercise);
    }

    private static void checkItemValidity(Item item) throws InvalidCharacteristicException {
        if (!item.isValid()) {
            throw new InvalidCharacteristicException(item.toString());
        }
    }

    private static void addFood(ItemBank items, String name, int calories)
            throws InvalidCharacteristicException, DuplicateItemInBankException {
        Food food = new Food(name, calories);
        checkItemValidity(food);
        items.addItem(food);
    }

    private static boolean isExerciseType(String type) {
        return type.equals(ExerciseBankStorageUtils.TYPE);
    }

    private static boolean isFoodType(String type) {
        return type.equals(FoodBankStorageUtils.TYPE);
    }
}
