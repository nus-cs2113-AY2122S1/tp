package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;
import java.util.Scanner;

public class IngredientList {
    public static ArrayList<Ingredient> ingredientList = new ArrayList<>();
    private static final Ui ui = new Ui();

    public static void add(String ingredientName) throws FoodoramaException {
        ui.printEnterWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String ingredientWeight = in.nextLine();
        double ingredientWeightValue;
        try {
            ingredientWeightValue = Double.parseDouble(ingredientWeight);
            if(ingredientWeightValue < 0) {
                throw new FoodoramaException("");
            }
        } catch (NumberFormatException | FoodoramaException e) {
            throw new FoodoramaException(ui.getInvalidNumberMsg());
        }
        Ingredient ingredientToAdd = new Ingredient(ingredientName, ingredientWeightValue);
        ingredientList.add(ingredientToAdd);
        ui.printAddedIngredient(ingredientToAdd, ingredientWeightValue);
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
        ui.printIngrListGraph(ingredientList);
    }

    public static void list() {
        ui.printIngrList(ingredientList);
    }

    public static void delete(String ingredientName) {
        Scanner input = new Scanner(System.in);
        int listSize = ingredientList.size(); //listSize = N
        int ingredientIndex = IngredientList.find(ingredientName);
        if (ingredientIndex == -1) {
            ui.printIngrNotExistMsg();
            assert ingredientList.size() == listSize : "ingredientList should be of size N";
        } else {
            ui.printConfirmDelIngr();
            String confirmDel = input.nextLine().toLowerCase();
            while (!(confirmDel.equals("y") | confirmDel.equals("n"))) {
                ui.clearTerminalAndPrintNewPage();
                ui.printInvalidConfirmation();
                confirmDel = input.nextLine().toLowerCase();
            }
            ui.clearTerminalAndPrintNewPage();
            if (confirmDel.equals("y")) {
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
                ui.printIngrNameRemoved(ingredientName);
                assert ingredientList.size() == (listSize - 1) : "ingredientList should be of size N-1";
            } else {
                ui.printDisregardMsg();
            }


        }
    }

    public static void clearList() {
        Scanner input = new Scanner(System.in);
        ui.printConfirmClearIngr();
        String confirmClear = input.nextLine().toLowerCase();

        while (!(confirmClear.equals("y") | confirmClear.equals("n"))) {
            ui.clearTerminalAndPrintNewPage();
            ui.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        ui.clearTerminalAndPrintNewPage();
        if (confirmClear.equals("y")) {
            ingredientList.clear();
            assert ingredientList.size() == 0 : "ingredientList should be of size 0";
            ui.printIngrListCleared();
        } else {
            ui.printDisregardMsg();
        }
    }
}
