package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class IngredientList {
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
            if (ingredientWeightValue < 0) {
                throw new FoodoramaException("");
            }
        } catch (NumberFormatException | FoodoramaException e) {
            throw new FoodoramaException(UI.getInvalidNumberMsg());
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

    public static void delete(String ingredientName) {
        Scanner input = new Scanner(System.in);
        int listSize = ingredientList.size(); //listSize = N
        int ingredientIndex = IngredientList.find(ingredientName);
        if (ingredientIndex == -1) {
            UI.printIngrNotExistMsg();
            assert ingredientList.size() == listSize : "ingredientList should be of size N";
        } else {
            UI.printConfirmDelIngr();
            String confirmDel = input.nextLine().toLowerCase();
            while (!(confirmDel.equals(YES) | confirmDel.equals(NO))) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmDel = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmDel.equals(YES)) {
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

        while (!(confirmClear.equals(YES) | confirmClear.equals(NO))) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmClear.equals(YES)) {
            ingredientList.clear();
            assert ingredientList.size() == 0 : "ingredientList should be of size 0";
            UI.printIngrListCleared();
        } else {
            UI.printDisregardMsg();
        }
    }

    public static void editName(int ingredientIndex) throws FoodoramaException {
        if (ingredientIndex == -1) {
            throw new FoodoramaException(UI.getIngrNotExistEdit());
        } else if (ingredientIndex >= ingredientList.size()) {
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            String ingrName = ingredientList.get(ingredientIndex).getIngredientName();
            UI.printAskNewNameIngr(ingrName);

            Scanner input = new Scanner(System.in);
            String newName = input.nextLine();

            UI.clearTerminalAndPrintNewPage();
            UI.printConfirmIngrEditMsg(ingrName, newName);
            String confirmChange = input.nextLine().toLowerCase();
            while (!(confirmChange.equals(YES) | confirmChange.equals(NO))) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmChange = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmChange.equals(YES)) {
                ingredientList.get(ingredientIndex).setIngredientName(newName);
                UI.printIngrNameChanged(newName);
            } else {
                UI.printDisregardMsg();
            }
        }
    }

    private static boolean isValidDate(String expiryDateString) {
        try {
            LocalDate.parse(expiryDateString, dtf);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static void addExpiry(int ingredientIndex) throws FoodoramaException {
        if (ingredientIndex == -1) {
            throw new FoodoramaException(UI.getIngrNotExistEdit());
        } else if (ingredientIndex >= ingredientList.size()) {
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            String ingrName = ingredientList.get(ingredientIndex).getIngredientName();
            UI.printAskIngrExpiryDate(ingrName);

            Scanner input = new Scanner(System.in);
            String expiryDateString = input.nextLine();
            LocalDate expiryDate = null;

            if (isValidDate(expiryDateString)) {
                expiryDate = LocalDate.parse(expiryDateString, dtf);
            } else {
                UI.clearTerminalAndPrintNewPage();
                throw new FoodoramaException(UI.getIncorrectExpiryDateFormatMsg());
            }

            long daysBetweenExpiryToday = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
            if (daysBetweenExpiryToday > TEN_YEARS_IN_DAYS) {
                UI.clearTerminalAndPrintNewPage();
                throw new FoodoramaException(UI.getLongExpiryDateMsg());
            } else if (daysBetweenExpiryToday < ZERO_DAYS) {
                UI.clearTerminalAndPrintNewPage();
                throw new FoodoramaException(UI.getPassedExpiryDateMsg());
            } else {
                ingredientList.get(ingredientIndex).setExpiryDate(expiryDate);
                UI.clearTerminalAndPrintNewPage();
                UI.printSetIngrExpiryDate(ingrName, expiryDate, daysBetweenExpiryToday);
            }
        }
    }

    public static void editWastage(int ingrIndex) throws FoodoramaException {
        if (ingrIndex == -1) {
            throw new FoodoramaException(UI.getIngrNotExistEdit());
        } else if (ingrIndex >= ingredientList.size()) {
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
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
            Double ingrWeight = ingredientList.get(ingrIndex).getWastage();

            UI.clearTerminalAndPrintNewPage();
            UI.printConfirmDishWastageEditMsg(ingrWeight, newWeight);
            String confirmChange = input.nextLine().toLowerCase();
            while (!(confirmChange.equals(YES) | confirmChange.equals(NO))) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmChange = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmChange.equals(YES)) {
                ingredientList.get(ingrIndex).setIngredientWaste(newWeight);
                UI.printDishWastageChanged(ingrName, newWeight);
            } else {
                UI.printDisregardMsg();
            }
        }
    }

    public static void editStorage(int ingrIndex) throws FoodoramaException {
        if (ingrIndex == -1) {
            throw new FoodoramaException(UI.getIngrNotExistEdit());
        } else if (ingrIndex >= ingredientList.size()) {
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
            Double ingrWeight = ingredientList.get(ingrIndex).getIngredientWeight();

            UI.clearTerminalAndPrintNewPage();
            UI.printConfirmIngrStorageEditMsg(ingrWeight, newWeight);
            String confirmChange = input.nextLine().toLowerCase();
            while (!(confirmChange.equals(YES) | confirmChange.equals(NO))) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidConfirmation();
                confirmChange = input.nextLine().toLowerCase();
            }
            UI.clearTerminalAndPrintNewPage();
            if (confirmChange.equals(YES)) {
                ingredientList.get(ingrIndex).setIngredientWaste(newWeight);
                UI.printIngrStorageChanged(ingrName, newWeight);
            } else {
                UI.printDisregardMsg();
            }
        }
    }
}
