package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.Scanner;

public class Ingredient implements  Comparable<Ingredient> {

    private static final Ui ui = new Ui();
    private String ingredientName;
    private double ingredientWeight;
    private double ingredientWasteIngr;
    public double ingredientWasteDish;
    private double limit;

    public Ingredient(String ingredientName, double ingredientWeight) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWasteIngr = 0;
        this.limit = -1;
    }

    public Ingredient(String ingredientName, double ingredientWeight, double ingredientWaste) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWasteIngr = ingredientWaste;
        this.limit = -1;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public double getIngredientWeight() {
        return ingredientWeight;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void updateIngredientWeight() throws FoodoramaException {
        ui.printEnterWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String inputIngredientWeight = in.nextLine();
        double ingredientWeightValue;
        try {
            ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
        } catch (NumberFormatException e) {
            throw new FoodoramaException(ui.getInvalidNumberMsg());
        }
        ingredientWeight += ingredientWeightValue;
        ui.printStorage(ingredientName, ingredientWeight);
    }

    public void setLimitValue() throws FoodoramaException {
        ui.printEnterLimitFor(ingredientName);
        Scanner in = new Scanner(System.in);
        String inputLimit = in.nextLine();
        double userLimit;
        try {
            userLimit = Double.parseDouble(inputLimit);
        } catch (NumberFormatException e) {
            throw new FoodoramaException(ui.getInvalidNumberMsg());
        }
        limit = userLimit;
        ui.printLimitSet(ingredientName, limit);
    }

    public void addWaste() throws FoodoramaException {
        ui.printEnterWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String ingredientWeight = in.nextLine();
        double ingredientWeightValue;
        try {
            ingredientWeightValue = Double.parseDouble(ingredientWeight);
        } catch (NumberFormatException e) {
            throw new FoodoramaException(ui.getInvalidNumberMsg());
        }
        ingredientWasteIngr += ingredientWeightValue;
        double totalWaste = ingredientWasteIngr + ingredientWasteDish;
        ui.printWastage(ingredientName, totalWaste);
        if (totalWaste >= limit && limit != -1) {
            ui.printLimitExceeded(ingredientName);
        }
    }

    public double getWastage() {
        return ingredientWasteIngr + ingredientWasteDish;
    }

    @Override
    public String toString() {
        double totalWaste = ingredientWasteIngr + ingredientWasteDish;
        String limitString;
        if (limit == -1) {
            limitString = "No limit has been set";
        } else {
            limitString = String.valueOf(limit);
            if (limit < totalWaste) {
                limitString  = limitString + " (exceeded)";
            }
        }
        //Todo add parts
        return ingredientName + '\n'
                + "   Storage: " + ingredientWeight + " kg" +  System.lineSeparator()
                + "   Wastage: " + totalWaste + " kg" + System.lineSeparator()
                + "   Limit: " + limitString;
    }

    public String formatData() {
        return ingredientName + "|"  + ingredientWeight + "|" + ingredientWasteIngr + "|" + limit;
    }


    public void addDishWaste(Double value) {
        ingredientWasteDish += value;
    }

    public int getGraphHeight(double max, int resolution) {
        double wastage = ingredientWasteDish + ingredientWasteIngr;
        int num = (int)(resolution * wastage / max);
        return num;
    }

    @Override
    public int compareTo(Ingredient o) {
        double wastage = ingredientWasteDish + ingredientWasteIngr;
        return (int) (o.getWastage() - wastage);
    }
}
