package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class IngredientList {
    public static final String YES_NO_REGEX = "^(y|yes|n|no)$";
    public static ArrayList<Ingredient> ingredientList = new ArrayList<>();
    private static final Ui UI = new Ui();
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final int TEN_YEARS_IN_DAYS = 3650;
    private static final int ZERO_DAYS = 0;

    public static void add(String ingredientName) throws FoodoramaException {
        UI.printEnterWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String ingredientWeight = in.nextLine();
        double ingredientWeightValue;

        try {
            ingredientWeightValue = Double.parseDouble(ingredientWeight);
            while (ingredientWeightValue < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrWeight(ingredientName);
                ingredientWeight = in.nextLine();
                ingredientWeightValue = Double.parseDouble(ingredientWeight);
            }
        } catch (NumberFormatException e) {
            throw new FoodoramaException(UI.getInvalidNumberMsg());
        }

        if (Double.isInfinite(ingredientWeightValue) | Double.isNaN(ingredientWeightValue)) {
            throw new FoodoramaException(UI.printNumericalInputInvalid("ingredient storage"));
        }

        Ingredient ingredientToAdd = new Ingredient(ingredientName, ingredientWeightValue);
        ingredientList.add(ingredientToAdd);
        UI.printAddedIngredient(ingredientToAdd, ingredientWeightValue);

    }

    //Returns -1 if not present, index if present
    public static int find(String ingredientName) {
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getIngredientName().equals(ingredientName)) {
                return ingredientList.indexOf(ingredient);
            }
        }
        return -1;
    }

    public static double getGreatestWaste() {
        double greatest = 0;
        for (Ingredient ingr : ingredientList) {
            double currWaste = ingr.getWastage();
            if (currWaste > greatest) {
                greatest = currWaste;
            }
        }
        assert greatest != 0 : "Exception should have been thrown earlier if list is empty";
        return greatest;
    }

    public static void graph() {
        UI.printIngrListGraph(ingredientList);
    }

    public static void list() {
        UI.printIngrList(ingredientList);
    }

    public static void delete(int ingredientIndex) {
        Scanner input = new Scanner(System.in);
        int listSize = ingredientList.size(); //listSize = N
        String ingredientName = ingredientList.get(ingredientIndex).getIngredientName();
        if (ingredientIndex == -1) {
            UI.printIngrNotExistMsg();
            assert ingredientList.size() == listSize : "ingredientList should be of size N";
        } else {
            UI.printConfirmDelIngr(ingredientName);
            String confirmDel = input.nextLine().toLowerCase();
            while (!confirmDel.matches(YES_NO_REGEX)) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmDel = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmDel.startsWith(YES)) {
                //Get all dishes
                for (Dish dish : DishList.dishList) {
                    //Find if they contain ingr in parts
                    ArrayList<Ingredient> parts = dish.getParts();
                    for (int i = 0; i < parts.size(); i++) {
                        if (parts.get(i).getIngredientName().equals(ingredientName)) {
                            parts.remove(i);
                        }
                    }
                }
                ingredientList.remove(ingredientIndex);
                UI.printIngrNameRemoved(ingredientName);
                assert ingredientList.size() == (listSize - 1) : "ingredientList should be of size N-1";
            } else {
                UI.printDisregardMsg();
            }


        }
    }

    public static void clearList() {
        Scanner input = new Scanner(System.in);
        UI.printConfirmClearIngr();
        String confirmClear = input.nextLine().toLowerCase();

        while (!confirmClear.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmClear.startsWith(YES)) {
            ingredientList.clear();
            assert ingredientList.size() == 0 : "ingredientList should be of size 0";
            UI.printIngrListCleared();
        } else {
            UI.printDisregardMsg();
        }
    }

    public static void editName(int ingredientIndex) throws FoodoramaException {
        String ingrName = ingredientList.get(ingredientIndex).getIngredientName();
        UI.printAskNewNameIngr(ingrName);

        Scanner input = new Scanner(System.in);
        String newName = input.nextLine().toLowerCase();
        while (isNumber(newName)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidIngredientName();
            newName = input.nextLine().toLowerCase();
        }

        if (newName.isBlank()) {
            throw new FoodoramaException(UI.getBlankName("ingredient"));
        }

        UI.clearTerminalAndPrintNewPage();
        UI.printConfirmIngrEditMsg(ingrName, newName);
        String confirmChange = input.nextLine().toLowerCase();
        while (!confirmChange.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmChange = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmChange.startsWith(YES)) {
            ingredientList.get(ingredientIndex).setIngredientName(newName);
            UI.printIngrNameChanged(ingrName, newName);
        } else {
            UI.printDisregardMsg();
        }
    }

    private static boolean isValidDateFormat(String expiryDateString) {
        try {
            LocalDate.parse(expiryDateString, dtf);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidExpiryLength(long daysBetweenExpiryToday, String ingrName) {
        if (daysBetweenExpiryToday > TEN_YEARS_IN_DAYS) {
            UI.printLongExpiryDateMsg();
            Scanner input = new Scanner(System.in);
            String confirmDate = input.nextLine().toLowerCase();
            while (!confirmDate.matches(YES_NO_REGEX)) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmDate = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmDate.startsWith("n")) {
                UI.printAskIngrExpiryDate(ingrName);
                return false;
            }
        } else if (daysBetweenExpiryToday < ZERO_DAYS) {
            UI.printPassedExpiryDateMsg();
            return false;
        }
        return true;
    }

    public static void addExpiry(int ingredientIndex) throws FoodoramaException {
        String ingrName = ingredientList.get(ingredientIndex).getIngredientName();
        UI.printAskIngrExpiryDate(ingrName);

        Scanner input = new Scanner(System.in);
        String expiryDateString = input.nextLine();
        LocalDate expiryDate = null;

        int exitLoop = 0;
        long daysBetweenExpiryToday = Long.MIN_VALUE;
        while (exitLoop == 0) {
            UI.clearTerminalAndPrintNewPage();
            if (!isValidDateFormat(expiryDateString)) {
                UI.printIncorrectExpiryDateFormatMsg();
            } else if (isValidDateFormat(expiryDateString)) {
                expiryDate = LocalDate.parse(expiryDateString, dtf);
                daysBetweenExpiryToday = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
            }
            if (isValidDateFormat(expiryDateString) && daysBetweenExpiryToday != Long.MIN_VALUE
                    && isValidExpiryLength(daysBetweenExpiryToday, ingrName)) {
                exitLoop = 1;
            } else {
                expiryDateString = input.nextLine();
            }
        }
        ingredientList.get(ingredientIndex).setExpiryDate(expiryDate);
        UI.clearTerminalAndPrintNewPage();
        UI.printSetIngrExpiryDate(ingrName, expiryDate, daysBetweenExpiryToday);
    }

    public static void editWastage(int ingrIndex) throws FoodoramaException {

        String ingrName = ingredientList.get(ingrIndex).getIngredientName();
        UI.printAskNewWastageDish(ingrName);

        Scanner input = new Scanner(System.in);
        double newWeight;

        try {
            newWeight = Double.parseDouble(input.nextLine());
            if (newWeight < 0) {
                throw new FoodoramaException("");
            }
        } catch (NumberFormatException | FoodoramaException e) {
            throw new FoodoramaException(UI.getInvalidNumberMsg());
        }
        if (Double.isInfinite(newWeight) | Double.isNaN(newWeight)) {
            throw new FoodoramaException(UI.printNumericalInputInvalid("ingredient waste"));
        }
        Double ingrWeight = ingredientList.get(ingrIndex).getWastage();

        UI.clearTerminalAndPrintNewPage();
        UI.printConfirmDishWastageEditMsg(ingrWeight, newWeight);
        String confirmChange = input.nextLine().toLowerCase();
        while (!confirmChange.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmChange = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmChange.startsWith(YES)) {
            ingredientList.get(ingrIndex).setIngredientWaste(newWeight);
            UI.printDishWastageChanged(ingrName, newWeight);
        } else {
            UI.printDisregardMsg();
        }
    }

    public static void editStorage(int ingrIndex) throws FoodoramaException {
        if (ingrIndex == -1) {
            throw new FoodoramaException(UI.getIngrNotExistEdit());
        } else if (ingrIndex < 0 || ingrIndex >= IngredientList.ingredientList.size()) {
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            String ingrName = ingredientList.get(ingrIndex).getIngredientName();
            UI.printAskNewStorageIngr(ingrName);

            Scanner input = new Scanner(System.in);
            double newWeight;

            try {
                newWeight = Double.parseDouble(input.nextLine());
                if (newWeight < 0) {
                    throw new FoodoramaException("");
                }
            } catch (NumberFormatException | FoodoramaException e) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }
            if (Double.isInfinite(newWeight) | Double.isNaN(newWeight)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("ingredient storage"));
            }
            Double ingrWeight = ingredientList.get(ingrIndex).getIngredientWeight();

            UI.clearTerminalAndPrintNewPage();
            UI.printConfirmIngrStorageEditMsg(ingrWeight, newWeight);
            String confirmChange = input.nextLine().toLowerCase();
            while (!confirmChange.matches(YES_NO_REGEX)) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmChange = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmChange.startsWith(YES)) {
                ingredientList.get(ingrIndex).setIngredientWeight(newWeight);
                UI.printIngrStorageChanged(ingrName, newWeight);
            } else {
                UI.printDisregardMsg();
            }
        }
    }

    public static boolean isNumber(String numberString) {
        try {
            int numberInteger = Integer.parseInt(numberString) - 1;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
