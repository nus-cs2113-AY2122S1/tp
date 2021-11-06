package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Ingredient implements  Comparable<Ingredient> {

    private static final Ui UI = new Ui();
    private String ingredientName;
    private double ingredientWeight;
    private double ingredientWasteIngr;
    private double ingredientWasteDish;
    private double limit;
    private LocalDate expiryDate;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Ingredient(String ingredientName, double ingredientWeight) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWasteIngr = 0;
        this.limit = -1;
        this.expiryDate = null;
    }

    public Ingredient(String ingredientName, double ingredientWeight, double ingredientWaste, String savedDate) {
        this.ingredientName = ingredientName;
        this.ingredientWeight = ingredientWeight;
        this.ingredientWasteIngr = ingredientWaste;
        this.limit = -1;
        if (savedDate.equals("null")) {
            this.expiryDate = null;
        } else {
            this.expiryDate = LocalDate.parse(savedDate, dtf);
        }
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public double getIngredientWeight() {
        return ingredientWeight;
    }

    public void setIngredientWeight(double ingredientWeight) {
        this.ingredientWeight = ingredientWeight;
    }

    public void setIngredientWaste(double newWeight) {
        this.ingredientWasteIngr = newWeight;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }


    public String getExpiryDate() {
        return this.expiryDate.format(dtf);
    }

    public void setExpiryDate(LocalDate date) {
        this.expiryDate = date;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void updateIngredientWeight() throws FoodoramaException {
        UI.printEnterWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String inputIngredientWeight = in.nextLine();
        double ingredientWeightValue;
        try {
            ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
            if (ingredientWeightValue < 0) {
                throw new FoodoramaException("");
            }
        } catch (NumberFormatException | FoodoramaException e) {
            throw new FoodoramaException(UI.getInvalidNumberMsg());
        }
        ingredientWeight += ingredientWeightValue;
        UI.printStorage(ingredientName, ingredientWeight);
    }

    public void setLimitValue() throws FoodoramaException {
        UI.printEnterLimitFor(ingredientName);
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
        UI.printLimitSet(ingredientName, limit);
    }

    public void addWaste() throws FoodoramaException {
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
        ingredientWasteIngr += ingredientWeightValue;
        double totalWaste = ingredientWasteIngr + ingredientWasteDish;
        UI.printWastage(ingredientName, totalWaste);
        if (totalWaste >= limit && limit != -1) {
            UI.printLimitExceeded(ingredientName);
        }
    }

    public double getWastage() {
        return ingredientWasteIngr + ingredientWasteDish;
    }

    @Override
    public String toString() {
        double totalWaste = ingredientWasteIngr + ingredientWasteDish;
        String limitString;
        String expiryDateString;
        if (limit == -1) {
            limitString = "No limit has been set";
        } else {
            limitString = String.valueOf(limit);
            if (limit < totalWaste) {
                limitString  = limitString + " (exceeded)";
            }
        }
        if (this.expiryDate == null) {
            expiryDateString = "No expiry date has been set";
        } else {
            long daysBetweenExpiryToday = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
            if (daysBetweenExpiryToday > 0) {
                expiryDateString = expiryDate.format(dtf) + " (" + daysBetweenExpiryToday + " day(s) from today)";
            } else {
                expiryDateString = expiryDate.format(dtf)
                        + " (EXPIRED " + Math.abs(daysBetweenExpiryToday) + " day(s) ago)";
            }
        }

        return ingredientName + '\n'
                + "   Storage: " + ingredientWeight + " kg" +  System.lineSeparator()
                + "   Wastage: " + totalWaste + " kg" + System.lineSeparator()
                + "   Limit: " + limitString + System.lineSeparator()
                + "   Expiry Date: " + expiryDateString;
    }

    public String formatData() {
        String expiryDateString;
        if (expiryDate == null) {
            expiryDateString = null;
        } else {
            expiryDateString = expiryDate.format(dtf);
        }
        return ingredientName + "|"  + ingredientWeight + "|" + ingredientWasteIngr + "|" + limit + "|"
                + expiryDateString;
    }


    public void addDishWaste(Double value) {
        ingredientWasteDish += value;
    }

    public int getGraphHeight(double max, int resolution) {
        double wastage = ingredientWasteDish + ingredientWasteIngr;
        int num = (int) Math.ceil(resolution * wastage / max);
        return num;
    }

    @Override
    public int compareTo(Ingredient o) {
        double wastage = ingredientWasteDish + ingredientWasteIngr;
        return (int) (o.getWastage() - wastage);
    }
}
