package seedu.duke;

import seedu.duke.exceptions.FoodoramaException;
import seedu.duke.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dish implements Comparable<Dish> {
    private static final Logger logger = Logger.getLogger("Dish class");
    private ArrayList<Ingredient> constituents = new ArrayList<>();
    private static final Ui ui = new Ui();
    private String dishName;
    private Double wastage;
    //Each dish contributes a portion of its wastage to constituent ingredients
    private Double ingredientContribution;

    public Dish(String dishName) {
        LoggerManager.setupLogger(logger);
        logger.log(Level.INFO, "Calling default constructor");
        this.dishName = dishName;
        this.wastage = 0.0;
        this.ingredientContribution = 0.0;
    }

    public Dish(String dishName, double wastage, double ingredientContribution) {
        LoggerManager.setupLogger(logger);
        logger.log(Level.INFO, "Calling loading constructor");
        this.dishName = dishName;
        this.wastage = wastage;
        this.ingredientContribution = ingredientContribution;
    }

    public String getDishName() {
        return dishName;
    }

    public Double getWastage() {
        return wastage;
    }

    public Double getIngredientContribution() {
        return ingredientContribution;
    }

    public ArrayList<Ingredient> getConstituents() {
        return constituents;
    }

    public void addConstituent(String ingredientName) {
        int ingredientIndex = IngredientList.find(ingredientName);
        if (ingredientIndex == -1) {
            ui.printIngrNotExistMsg();
        } else {
            //Subtract the old contribution if it exists
            for (Ingredient ingredient : constituents) {
                ingredient.addDishWaste(-ingredientContribution);
            }
            constituents.add(IngredientList.ingredientList.get(ingredientIndex));

            ui.printAddedConstituentOf(ingredientName, dishName);

            //Modify the ingredient contribution to reflect the change
            ingredientContribution = wastage / constituents.size();

            //Add new contribution
            for (Ingredient ingredient : constituents) {
                ingredient.addDishWaste(ingredientContribution);
            }
        }
    }

    public void addWaste() throws FoodoramaException {
        ui.printEnterWeightOf(dishName);
        Scanner in = new Scanner(System.in);
        String dishWaste = in.nextLine();
        double inputWastage;
        try {
            inputWastage = Double.parseDouble(dishWaste);
        } catch (NumberFormatException e) {
            throw new FoodoramaException(ui.getInvalidNumberMsg());
        }
        assert inputWastage > 0 : "Adding negative waste is impossible";
        wastage += inputWastage;
        ui.printWastage(dishName, wastage);
        if (!constituents.isEmpty()) {
            //Todo proportion stuff and prevent feedback loop
            ingredientContribution = wastage / constituents.size();
            for (Ingredient ingredient : constituents) {
                //Change in contribution is change in wastage / num of constituents
                ingredient.addDishWaste(inputWastage / constituents.size());
            }
        }
    }


    @Override
    public String toString() {
        String constituentList = "";
        if (!constituents.isEmpty()) {
            for (Ingredient ingredient : constituents) {
                constituentList = constituentList + "," + ingredient.getIngredientName();
            }
            constituentList = constituentList.replaceFirst(",", "");
        } else {
            logger.log(Level.INFO, "No constituents present for dish " + dishName);
            constituentList = "None";
        }
        return dishName + System.lineSeparator()
                + "   Wastage: " + wastage + " kg" + System.lineSeparator()
                + "   Constituents: " + constituentList;
    }

    public String toGraph(double max) {
        String bar = "[";
        int num = (int)(10 * wastage / max);
        for (int i = 0; i < 10; i++) {
            if (i < num) {
                bar = bar + "█";
            } else  {
                bar = bar + " ";
            }
        }
        bar = bar + "]";
        return dishName + System.lineSeparator()
                + "   Wastage: " + bar + " " + wastage + " kg";
    }

    public String formatData() {
        String output = "";
        output = output + dishName + "|" + wastage + "|" + ingredientContribution;
        for (Ingredient ingredient : constituents) {
            output = output + "|" + ingredient.getIngredientName();
        }
        return output;
    }

    @Override
    public int compareTo(Dish o) {
        return (int) (o.wastage - wastage);
    }
}
