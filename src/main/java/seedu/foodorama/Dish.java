package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dish implements Comparable<Dish> {
    private static final Logger LOGGER = Logger.getLogger("Dish class");
    private ArrayList<Ingredient> parts = new ArrayList<>();
    private static final Ui UI = new Ui();
    private static final String YES = "y";
    private static final String NO = "n";
    private String dishName;
    private double wastage;
    private double limit;
    //Each dish contributes a portion of its wastage to part ingredients
    private double ingredientContribution;

    public Dish(String dishName) {
        LoggerManager.setupLogger(LOGGER);
        LOGGER.log(Level.INFO, "Calling default constructor");
        this.dishName = dishName;
        this.wastage = 0.0;
        this.ingredientContribution = 0.0;
        this.limit = -1;
    }

    public Dish(String dishName, double wastage, double ingredientContribution) {
        LoggerManager.setupLogger(LOGGER);
        LOGGER.log(Level.INFO, "Calling loading constructor");
        this.dishName = dishName;
        this.wastage = wastage;
        this.ingredientContribution = ingredientContribution;
        this.limit = -1;
    }

    public String getDishName() {
        return dishName;
    }

    public double getLimit() {
        return limit;
    }

    public double getWastage() {
        return wastage;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setDishWastage(Double wastage) {
        this.wastage = wastage;
    }

    public void setLimitValue() throws FoodoramaException {
        UI.printEnterLimitFor(dishName);
        Scanner in = new Scanner(System.in);
        String inputLimit = in.nextLine();
        double userLimit;
        try {
            userLimit = Double.parseDouble(inputLimit);
            if (userLimit < 0) {
                throw new FoodoramaException("");
            }
        } catch (NumberFormatException | FoodoramaException e) {
            throw new FoodoramaException(UI.getInvalidNumberMsg());
        }
        limit = userLimit;
        UI.printLimitSet(dishName, limit);
    }

    public double getIngredientContribution() {
        return ingredientContribution;
    }

    public ArrayList<Ingredient> getParts() {
        return parts;
    }

    public void addPart(String ingredientName) {
        int ingredientIndex = IngredientList.find(ingredientName);
        if (ingredientIndex == -1) {
            UI.printIngrNotExistMsg();
        } else {
            //Subtract the old contribution if it exists
            for (Ingredient ingredient : parts) {
                ingredient.addDishWaste(-ingredientContribution);
            }
            parts.add(IngredientList.ingredientList.get(ingredientIndex));

            UI.printAddedPartOf(ingredientName, dishName);

            //Modify the ingredient contribution to reflect the change
            ingredientContribution = wastage / parts.size();

            //Add new contribution
            for (Ingredient ingredient : parts) {
                ingredient.addDishWaste(ingredientContribution);
            }
        }
    }

    public void addWaste() throws FoodoramaException {
        UI.printEnterWeightOf(dishName);
        Scanner in = new Scanner(System.in);
        String dishWaste = in.nextLine();
        while (!isNumber(dishWaste)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidDishName();
            dishWaste = in.nextLine();
        }
        double inputWastage;
        try {
            inputWastage = Double.parseDouble(dishWaste);
            while (inputWastage < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidDishWasteValue(dishName);
                dishWaste = in.nextLine();
                inputWastage = Double.parseDouble(dishWaste);
            }
        } catch (NumberFormatException e) {
            throw new FoodoramaException(UI.getInvalidNumberMsg());
        }

        assert inputWastage > 0 : "Adding negative waste is impossible";
        wastage += inputWastage;
        UI.printWastage(dishName, wastage);
        if (wastage >= limit && limit != -1) {
            UI.printLimitExceeded(dishName);
        }
        if (!parts.isEmpty()) {
            //Todo proportion stuff and prevent feedback loop
            ingredientContribution = wastage / parts.size();
            for (Ingredient ingredient : parts) {
                //Change in contribution is change in wastage / num of partss
                ingredient.addDishWaste(inputWastage / parts.size());
            }
        }

    }

    private boolean isNumber(String numberString) {
        try {
            double number = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        String limitString;
        if (limit == -1) {
            limitString = "No limit has been set";
        } else {
            limitString = String.valueOf(limit);
            if (limit < wastage) {
                limitString = limitString + " (exceeded)";
            }
        }
        String partList = "";
        if (!parts.isEmpty()) {
            for (Ingredient ingredient : parts) {
                partList = partList + "," + ingredient.getIngredientName();
            }
            partList = partList.replaceFirst(",", "");
        } else {
            LOGGER.log(Level.INFO, "No parts present for dish " + dishName);
            partList = "None";
        }
        return dishName + System.lineSeparator()
                + "   Wastage: " + wastage + " kg" + System.lineSeparator()
                + "   Ingredients Linked: " + partList + System.lineSeparator()
                + "   Limit: " + limitString;
    }

    public double getGraphHeight(double max, int resolution) {
        double num = (resolution * wastage / max);
        return num;
    }

    public String formatData() {
        String output = "";
        output = output + dishName + "|" + wastage + "|" + ingredientContribution + "|" + limit;
        for (Ingredient ingredient : parts) {
            output = output + "|" + ingredient.getIngredientName();
        }
        return output;
    }

    @Override
    public int compareTo(Dish o) {
        double diff = (o.wastage - wastage);
        return (diff >= 0) ? (diff == 0) ? 0 : 1 : -1;
    }
}
