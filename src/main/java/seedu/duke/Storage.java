package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.schedule.ScheduleTracker;
import seedu.duke.schedule.ScheduledWorkout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.ClickfitMessages.MEAL_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.FLUID_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.WORKOUT_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.WEIGHT_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.ENDLINE_PRINT_FORMAT;

public class Storage {

    public static final String SCHEDULE_FILE_PATH = "Schedule.txt";
    public static final String WORKOUT_FILE_PATH = "Workout.txt";
    public static final String foodFile = "Food.txt";
    public static final String libraryFile = "FoodBank.txt";
    public static final String weightFile = "Weight.txt";

    public Storage() {
        initializeFoodFile();
        initializeFoodBankFile();
        initializeWeightFile();
        initializeScheduleFile();
        initializeWorkoutFile();
    }

    //@@author VishalJeyaram
    /**
     * Saves meal and fluid headers and date headers to text file.
     *
     * @param fluid Fluid object.
     * @param meal Meal object.
     * @throws IOException If there is a problem with the text file.
     */
    public void saveFood(Fluid fluid, Meal meal) throws IOException {
        String header;
        String filePath = new File(foodFile).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        header = "Meals" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String date : DateTracker.dates) {
            saveFoodLists(filePath, fw, date, meal.meals);
        }
        header = "Fluids" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String date : DateTracker.dates) {
            saveFoodLists(filePath, fw, date, fluid.fluidArray);
        }
    }

    //@@author VishalJeyaram
    /**
     * Saves meal and fluid lists to text file.
     *
     * @param filePath Name of textfile.
     * @param fw FileWriter variable.
     * @param date Date of food consumption.
     * @param foods List of foods.
     * @throws IOException If there is a problem with the text file.
     */
    private void saveFoodLists(String filePath, FileWriter fw,
                               String date, ArrayList<String> foods) throws IOException {
        int headerFlag;
        String currentDate;
        String currentMeal;
        headerFlag = 0;
        for (String food : foods) {
            if (food.contains(date) && (headerFlag == 0)) {
                currentDate = "Date: " + date + "\n";
                Files.write(Paths.get(filePath), currentDate.getBytes(), StandardOpenOption.APPEND);
                fw.close();
                headerFlag = 1;
            }
            if (food.contains(date)) {
                currentMeal = food + "\n";
                Files.write(Paths.get(filePath), currentMeal.getBytes(), StandardOpenOption.APPEND);
                fw.close();
            }
        }
    }

    //@@author VishalJeyaram
    /**
     * Saves library meals and fluids to text file.
     *
     * @throws IOException If there is a problem with the text file.
     */
    public void saveLibrary() throws IOException {
        String customMeal;
        String customFluid;
        String header;
        String filePath = new File(libraryFile).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        header = "Meals" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String m : FoodBank.meals) {
            customMeal = m + "\n";
            Files.write(Paths.get(filePath), customMeal.getBytes(), StandardOpenOption.APPEND);
            fw.close();

        }
        header = "Fluids" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String f : FoodBank.fluids) {
            customFluid = f + "\n";
            Files.write(Paths.get(filePath), customFluid.getBytes(), StandardOpenOption.APPEND);
            fw.close();
        }
    }

    //@@author teoziyiivy
    /**
     * Saves weight to Weight.txt file.
     *
     * @throws IOException If there is a problem with the text file.
     */
    public void saveWeight(WeightTracker weight) throws IOException {
        String currentWeight;
        String header;
        String filePath = new File(weightFile).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        header = "Weights" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String w : weight.weightsArray) {
            currentWeight = w + "\n";
            Files.write(Paths.get(filePath), currentWeight.getBytes(), StandardOpenOption.APPEND);
            fw.close();
        }
    }

    //@@author EdwardZYWang
    /**
     * saves the user's workouts to the text file so that it can be accessed again in future sessions.
     *
     * @param workoutTracker workout of the user.
     * @throws IOException if there is an incorrect input.
     */
    public void saveWorkout(WorkoutTracker workoutTracker) throws IOException {
        FileWriter fileWriter = new FileWriter(WORKOUT_FILE_PATH, true);
        FileWriter fileCleaner = new FileWriter(WORKOUT_FILE_PATH, false);
        fileCleaner.write(Parser.EMPTY_STRING);
        DateTracker.sortDateAndTime(workoutTracker.workouts);
        fileCleaner.close();
        for (String w : workoutTracker.workouts) {
            fileWriter.write(w + System.lineSeparator());
        }
        fileWriter.close();
    }

    //@@author EdwardZYWang
    /**
     * saves the user's future workout schedule to the text file so that it can be accessed again in future sessions.
     *
     * @param scheduleTracker workout of the user.
     * @throws IOException if there is an incorrect input.
     */
    public void saveSchedule(ScheduleTracker scheduleTracker) throws IOException {
        FileWriter fileWriter = new FileWriter(SCHEDULE_FILE_PATH, true);
        FileWriter fileCleaner = new FileWriter(SCHEDULE_FILE_PATH, false);
        ArrayList<String> currentScheduleStringList = new ArrayList<>();
        for (ScheduledWorkout w : scheduleTracker.getScheduledWorkouts()) {
            currentScheduleStringList.add(w.getScheduledWorkoutAsDataString());
        }
        DateTracker.sortDateAndTime(currentScheduleStringList);
        fileCleaner.write(Parser.EMPTY_STRING);
        fileCleaner.close();
        for (String s : currentScheduleStringList) {
            if (Parser.isRecurringWorkout(s)) {
                fileWriter.write(s + System.lineSeparator());
                continue;
            }
            if (LocalDate.parse(
                    Parser.getDateNoDateTracker(s),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    .isBefore(LocalDate.now())) {
                continue;
            }
            fileWriter.write(s + System.lineSeparator());
        }
        fileWriter.close();
    }

    //@@author pragyan01
    /**
     * This method loads all meals saved in .txt file to meal array list.
     *
     * @return meal array list
     * @throws IOException if I/O error occurs
     */
    public ArrayList<String> loadMeals() throws IOException {
        ArrayList<String> meals = new ArrayList<>();
        String newFilePath = new File(foodFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while ((s.hasNext()) && (flag == 0)) {
            textFromFile = s.nextLine();
            if (textFromFile.equals("Fluids")) {
                flag = 1;
            } else if (textFromFile.contains(Parser.CALORIE_SEPARATOR)) {
                meals.add(textFromFile);
            } else if (textFromFile.contains("Date")) {
                String[] date = textFromFile.split(" ");
                DateTracker.checkIfDateExists(date[1]);
            }
        }
        return meals;
    }

    //@@author pragyan01
    /**
     * This method loads all fluids saved in .txt file to fluid array list.
     *
     * @return fluid array list
     * @throws IOException if I/O error occurs
     */
    public ArrayList<String> loadFluids() throws IOException {
        ArrayList<String> fluids = new ArrayList<>();
        String newFilePath = new File(foodFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            if ((flag == 1) && (textFromFile.contains(Parser.CALORIE_SEPARATOR))) {
                fluids.add(textFromFile);
            } else if (textFromFile.equals("Fluids")) {
                flag = 1;
            } else if (textFromFile.contains("Date")) {
                String[] date = textFromFile.split(" ");
                DateTracker.checkIfDateExists(date[1]);
            }
        }
        return fluids;
    }

    //@@author teoziyiivy
    /**
     * This method loads all weights saved in Weight.txt file to the weights array list.
     *
     * @return weights arrayList
     * @throws IOException If there is a problem with the text file.
     */
    public ArrayList<String> loadWeights() throws IOException {
        ArrayList<String> weights = new ArrayList<>();
        String newFilePath = new File(weightFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            if (flag == 1) {
                weights.add(textFromFile);
            } else if (textFromFile.equals("Weights")) {
                flag = 1;
            }
        }
        return weights;
    }

    //@@author VishalJeyaram
    /**
     * Loads meal library from text file to arraylist.
     *
     * @return Meal library arraylist.
     * @throws IOException If there is a problem with the text file.
     */
    public ArrayList<String> loadMealLibrary() throws IOException {
        ArrayList<String> meals = new ArrayList<>();
        String newFilePath = new File(libraryFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while ((s.hasNext()) && (flag == 0)) {
            textFromFile = s.nextLine();
            if (textFromFile.equals("Fluids")) {
                flag = 1;
            } else if (textFromFile.contains(Parser.CALORIE_SEPARATOR)) {
                meals.add(textFromFile);
            } else if (textFromFile.contains("Date")) {
                String[] date = textFromFile.split(" ");
                DateTracker.checkIfDateExists(date[1]);
            }
        }
        return meals;
    }

    //@@author pragyan01
    /**
     * This method loads all fluid entries for foodbank saved in .txt file to fluid array list.
     *
     * @return fluid array lists
     * @throws IOException if I/O error occurs
     */
    public ArrayList<String> loadFluidLibrary() throws IOException {
        ArrayList<String> fluids = new ArrayList<>();
        String newFilePath = new File(libraryFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            if ((flag == 1) && (textFromFile.contains(Parser.CALORIE_SEPARATOR))) {
                fluids.add(textFromFile);
            } else if (textFromFile.equals("Fluids")) {
                flag = 1;
            } else if (textFromFile.contains("Date")) {
                String[] date = textFromFile.split(" ");
                DateTracker.checkIfDateExists(date[1]);
            }
        }
        return fluids;
    }

    //@@author EdwardZYWang
    /**
     * Loads all the workouts in the text file and stores them as an array list of strings for workout summary.
     *
     * @return workout array list.
     * @throws IOException if there is an incorrect input.
     */
    public ArrayList<String> loadWorkouts() throws IOException {
        ArrayList<String> workout = new ArrayList<>();
        File dataFile = new File(WORKOUT_FILE_PATH);
        Scanner fileScanner = new Scanner(dataFile);
        String textFromFile;
        while (fileScanner.hasNext()) {
            textFromFile = fileScanner.nextLine();
            if (Parser.containsCalorieSeparator(textFromFile) && Parser.containsDateSeparator(textFromFile)
                    && Parser.containsTimeSeparator(textFromFile)) {
                workout.add(textFromFile);
            }
        }
        return workout;
    }

    //@@author EdwardZYWang
    /**
     * Creates workout schedule file if it hasn't been created already.
     *
     */
    public static void initializeScheduleFile() {
        File dataFile = new File(SCHEDULE_FILE_PATH);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for ScheduleTracker.");
            }
        }
    }

    //@@author VishalJeyaram
    /**
     * Creates food file, "Food.txt" if it hasn't been created already.
     */
    public static void initializeFoodFile() {
        File dataFile = new File(foodFile);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for meals and fluids.");
            }
        }
    }

    //@@author teoziyiivy
    /**
     * Creates food bank file, "Foodbank.txt" if it hasn't already been created.
     */
    public static void initializeFoodBankFile() {
        File dataFile = new File(libraryFile);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for meals and fluids.");
            }
        }
    }

    //@@author teoziyiivy
    /**
     * Creates weight file, "Weight.txt" if it hasn't already been created.
     */
    public static void initializeWeightFile() {
        File dataFile = new File(weightFile);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for meals and fluids.");
            }
        }
    }

    //@@author EdwardZYWang
    /**
     * Creates workout file if it hasn't been created already.
     *
     */
    public static void initializeWorkoutFile() {
        File dataFile = new File(WORKOUT_FILE_PATH);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for WorkoutTracker.");
            }
        }
    }

    //@@author EdwardZYWang
    /**
     * A method that take in the array list, meals, and formats it into the appropriate output
     * form to be a useful summary to the user. if loop accounts for the "[" in the first element
     * during parsing, else loop accounts for the case when there is no "[" in the parsed string m.
     *
     */
    public void mealSummary() {
        int totalCalories = 0;
        int i = 1;
        try {
            for (String m : loadMeals()) {
                if (m.contains("[")) {
                    String[] descriptor = m.substring(1).split(" /c ");
                    String[] calorie = descriptor[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    i++;
                } else {
                    String[] descriptor = m.split(" /c ");
                    String[] calorie = descriptor[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    i++;
                }
            }
            System.out.println(System.lineSeparator() + "Total number of meals = " + (i - 1));
            System.out.println("Total calories = " + totalCalories);

        } catch (IOException e) {
            System.out.println("Error during printing arrayList");
        }
    }

    //@@author EdwardZYWang
    /**
     * A method that take in the array list, fluids, and formats it into the appropriate output
     * form to be a useful summary to the user. if loop accounts for the "[" in the first element
     * during parsing, else loop accounts for the case when there is no "[" in the parsed string m.
     *
     */
    public void fluidSummary() {
        int totalCalories = 0;
        int totalVolume = 0;
        int i = 1;
        try {
            for (String f : loadFluids()) {
                if (f.contains("[")) {
                    String[] descriptor = f.substring(1).split(" /c ");
                    String[] calorie = descriptor[1].split(" /v ");
                    String[] volumeSplitter = calorie[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    String volumeIndiv = volumeSplitter[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    totalVolume += Integer.parseInt((volumeIndiv));
                    i++;
                } else {
                    String[] descriptor = f.split(" /c ");
                    String[] calorie = descriptor[1].split(" /v ");
                    String[] volumeSplitter = calorie[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    String volumeIndiv = volumeSplitter[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    totalVolume += Integer.parseInt((volumeIndiv));
                    i++;
                }
            }
            if (totalVolume > 0) {
                System.out.println("Total volume consumed = " + totalVolume);
            } else {
                System.out.println(System.lineSeparator() + "Total variety of drinks = " + (i - 1));
            }
            System.out.println("Total calories = " + totalCalories);
        } catch (IOException e) {
            System.out.println("Error during printing arrayList");
        }
    }

    //@@author teoziyiivy
    /**
     * A method that takes in the weight array list and formats it into the appropriate output
     * form to provide a useful weight summary to the user. It will print out the summary for
     * all the weights in the weights array list printing the index in the list, weight, date,
     * and total number of weights.
     *
     * @throws DukeException If encountered error when generating weight parameters
     */
    public void weightSummary() throws DukeException {
        int i = 1;
        double weight;
        String date;
        try {
            for (String w : loadWeights()) {
                weight = Parser.getWeight(w);
                date = Parser.getDate(w);
                System.out.println(i + ". " + weight + " kg " + date);
                i++;
            }
            System.out.println(System.lineSeparator() + "Total number of weights = " + (i - 1));
        } catch (IOException e) {
            System.out.println("Error during printing arrayList");
        }
    }

    //@@author EdwardZYWang
    /**
     * A method that take in the array list,workout, and formats it into the appropriate output
     * form to be a useful summary to the user. if loop accounts for the "[" in the first element
     * during parsing, else loop accounts for the case when there is no "[" in the parsed string m.
     *
     */
    public void workoutSummary() {
        int totalCalories = 0;
        int i = 1;
        try {
            for (String w : loadWorkouts()) {
                if (w.contains("[")) {
                    String[] descriptor = w.substring(1).split(" /c ");
                    String[] calorie = descriptor[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    i++;
                } else {
                    String[] descriptor = w.split(" /c ");
                    String[] calorie = descriptor[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    i++;
                }
            }
            System.out.println(System.lineSeparator() + "Completed Workouts = " + (i - 1));
            System.out.println("Total calories burned = " + totalCalories);

        } catch (IOException e) {
            System.out.println("Error during printing arrayList");
        }
    }

    //@@author EdwardZYWang
    /**
     * A method that take calls the class summaries above and prints them out in the correct format.
     * it also prints out the date and time of the summary so that the user knows that he or she is receiving the
     * most updated information.
     *
     * @throws DukeException when there is an error
     */
    public void printLoadedLists() throws DukeException {
        System.out.println(MEAL_PRINT_FORMAT);
        mealSummary();
        System.out.println(ENDLINE_PRINT_FORMAT);
        System.out.println(FLUID_PRINT_FORMAT);
        fluidSummary();
        System.out.println(ENDLINE_PRINT_FORMAT);
        System.out.println(WORKOUT_PRINT_FORMAT);
        workoutSummary();
        System.out.println(ENDLINE_PRINT_FORMAT);
        System.out.println(WEIGHT_PRINT_FORMAT);
        weightSummary();
        System.out.println(ENDLINE_PRINT_FORMAT);
        System.out.println(System.lineSeparator() + "Updated as of: "
                + Parser.getSystemDate() + " " + Parser.getSystemTime());
    }
}