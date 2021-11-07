package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exceptions.LotsException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {

    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(CURRENT_DIRECTORY);
    private static final PeopleManager FILE_PEOPLE_MANAGER = new PeopleManager();
    private static final int MAX_FOOD_QUANTITY = 999;
    private static final int MIN_FOOD_QUANTITY = 0;
    private static final ArrayList<String> LIST_OF_ADD_COMMANDS = new ArrayList<>();

    /**
     * Initialises the PeopleManager according to the contents of the file.
     * Returns the PeopleManager.
     *
     * @return Returns the PeopleManager initialised based on the contents of the file.
     */
    public static PeopleManager initialiseFile() {
        try {
            //get the file, else create file if it does not exist.
            File ordersFile = getOrdersFile();
            Scanner fileList = new Scanner(ordersFile);

            //read and extract the data in the file to store it in listOfPeople in PeopleManager.
            readAndExtractFile(fileList);
        } catch (IOException | LotsException e) {
            e.printStackTrace();
        }
        return FILE_PEOPLE_MANAGER;
    }

    /**
     * Reads the file if it exists or creates the file if it does not.
     * Returns a File type of the file containing the existing list of people and their respective orders.
     *
     * @return Returns a File type of the file containing the list of people and their respective orders.
     * @throws IOException If the file cannot be read or created.
     */
    private static File getOrdersFile() throws IOException {
        File ordersFile = new File(FILE_PATH + "/.orders.txt");
        if (ordersFile.createNewFile()) {
            System.out.println("Creating new file...");
        } else {
            System.out.println("Loading...");
        }
        return ordersFile;
    }

    /**
     * Reads and initialises the PeopleManager based on the contents of the file.
     * If the file is corrupted (any of the variables are invalid), the entire file is wiped clean
     * and the program starts from a clean slate.
     *
     * @param fileList The content of the file.
     * @throws LotsException If there is an error initialising the PeopleManager.
     */
    private static void readAndExtractFile(Scanner fileList) throws LotsException, IOException {
        if (isValidFileInput(fileList)) {
            executeLoad();
        } else {
            updateFileWithEmptyManager();
        }
    }

    /**
     * Updates the file with an empty PeopleManager to clear the file.
     *
     * @throws IOException If there is an error updating the file with an empty PeopleManager.
     */
    private static void updateFileWithEmptyManager() throws IOException {
        PeopleManager.clearListOfPeople();
        updateFile(FILE_PEOPLE_MANAGER);
    }

    /**
     * Checks if the contents (name or quantity) of the files are valid.
     *
     * @param fileList The content of the file.
     * @return Returns false if the variables (name or quantity) are invalid, else returns true.
     */
    private static boolean isValidFileInput(Scanner fileList) {
        while (fileList.hasNextLine()) {
            String data = fileList.nextLine();
            String[] splitData = data.split(",");
            if (!isValidArrayLength(splitData)) {
                return false;
            }
            if (!isValidQuantity(splitData)) {
                return false;
            }
            if (!isValidAddCommand(splitData)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if there is a same amount of quantity as the number of menu items.
     *
     * @param splitData A particular line in the file, in an array of strings, that is getting checked.
     * @return Returns true if there is a same amount of quantity as the number of menu items, else returns false.
     */
    private static boolean isValidArrayLength(String[] splitData) {
        return splitData.length == (Menu.TOTAL_MENU_ITEMS + 1);
    }

    /**
     * Constructs the add command used to load the contents of the file into the program
     * and checks if the add command is valid.
     *
     * @param splitData A particular line in the file, in an array of strings, that is
     *                  getting checked.
     * @return Returns false if the add command used to load the contents of the file into the program is invalid,
     *     else returns true.
     */
    private static boolean isValidAddCommand(String[] splitData) {
        String personName = splitData[0];
        for (int i = 1; i < splitData.length; i++) {
            int quantityToAdd = Integer.parseInt(splitData[i]);
            if (quantityToAdd > MIN_FOOD_QUANTITY && quantityToAdd <= MAX_FOOD_QUANTITY) {
                String loadCommand = "add /n " + personName + " /i " + i + " /q " + quantityToAdd;
                if (!isValidCommand(loadCommand)) {
                    return false;
                } else {
                    LIST_OF_ADD_COMMANDS.add(loadCommand);
                }
            }
        }
        return true;
    }

    /**
     * Checks if the quantity value in a particular line of the file is parsable as an integer
     * and in the range of 0 to 999.
     *
     * @param splitData A particular line in the file, in an array of strings, that is getting checked.
     * @return Returns false if the file is not parsable as an integer or out of the range of 0 to 999,
     *     else returns true.
     */
    private static boolean isValidQuantity(String[] splitData) {
        for (int i = 1; i < splitData.length; i++) {
            if (!isParsable(splitData[i])) {
                return false;
            }
            if (!isInQuantityRange(splitData[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the quantity value in a particular line of the file is in the range of 0 to 999.
     *
     * @param quantityInString The quantity value in a string format.
     * @return Returns false if the quantity value in a particular line of the file is out of the range of 0 to 999,
     *     else returns true.
     */
    private static boolean isInQuantityRange(String quantityInString) {
        int quantityToAdd = Integer.parseInt(quantityInString);
        if (quantityToAdd < MIN_FOOD_QUANTITY || quantityToAdd > MAX_FOOD_QUANTITY) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the quantity value in a particular line of the file is parsable as an integer.
     *
     * @param quantityInString The quantity value in a string format.
     * @return Returns false if the quantity value in a particular line of the file is not parsable as an integer,
     *     else returns true.
     */
    private static boolean isParsable(String quantityInString) {
        try {
            Integer.parseInt(quantityInString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Execute the add command to load the contents of the file into the program.
     *
     * @throws IOException If there is an error updating the file with an empty PeopleManager.
     */
    private static void executeLoad() throws IOException {
        try {
            for (String addCommand : LIST_OF_ADD_COMMANDS) {
                Command command;
                command = Parser.getCommand(addCommand);
                command.setData(FILE_PEOPLE_MANAGER);
                command.executeFromFile();
            }
        } catch (LotsException e) {
            updateFileWithEmptyManager();
        }
    }

    /**
     * Regex to check the if the add command to be called to load the contents of the file is valid.
     *
     * @param input File input.
     * @return Returns true if the add command to be called to load the contents of the file matches the regex,
     *     else returns false.
     * @throws IllegalArgumentException When the pattern for Regex is not able to be interpreted.
     */
    private static boolean isValidCommand(String input) throws IllegalArgumentException {
        try {
            Pattern pattern = Pattern.compile(
                "^add \\/n [a-zA-Z0-9][\\w \\d]{0,50} \\/i \\d{1,2} \\/q \\d{1,3}$",
                Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.find();
            return matchFound;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Updates and overwrites the existing file with an updated listOfPeople with their respective orders.
     *
     * @param manager PeopleManager containing the updated listOfPeople
     * @throws IOException If the write operation to the file fails.
     */
    public static void updateFile(PeopleManager manager) throws IOException {
        FileWriter ordersWriter = new FileWriter(FILE_PATH + "/.orders.txt", false);
        for (Person person : manager.getEntireListOfPeople()) {
            String personName = person.getPersonName();
            int[] quantityPerFood = getQuantityPerFood(person);
            String quantityPerFoodInString = getQuantityPerFoodInString(quantityPerFood);
            String dataWritten = personName + quantityPerFoodInString + System.lineSeparator();
            ordersWriter.write(dataWritten);
        }
        ordersWriter.close();
    }

    /**
     * Returns an array containing the quantity per food.
     * If there is no quantity for that particular food, it will be set as '0'.
     *
     * @param person Person whose list is getting updated in the file.
     * @return Array containing the quantity per food order.
     */
    private static int[] getQuantityPerFood(Person person) {
        int foodIndex = 0;
        int[] quantityArray = new int[Menu.TOTAL_MENU_ITEMS];
        for (Order order : person.getEntireFoodOrdersOfPerson()) {
            quantityArray[foodIndex] = order.getQuantity();
            foodIndex++;
        }
        return quantityArray;
    }

    /**
     * Returns the quantity per food order in a string format.
     * The array would be represented in a string format using ',' as its separator.
     *
     * @param quantityPerFood Array containing the quantity per food.
     * @return A string consisting of all the quantity from each food.
     */
    private static String getQuantityPerFoodInString(int[] quantityPerFood) {
        String quantityPerFoodString = "";
        for (int i = 0; i < quantityPerFood.length; i++) {
            quantityPerFoodString = quantityPerFoodString + "," + quantityPerFood[i];
        }
        return quantityPerFoodString;
    }
}
