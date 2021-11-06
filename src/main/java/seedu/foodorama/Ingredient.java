package seedu.foodorama;

import seedu.foodorama.exceptions.FoodoramaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Ingredient implements Comparable<Ingredient> {

    public static final String YES_NO_REGEX = "^(y|yes|n|no)$";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final int LOOP = 0;
    private static final int EXIT = 1;
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
        UI.printEnterStoredWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String inputIngredientWeight = in.nextLine();

        int loop = LOOP;
        double ingredientWeightValue;
        while (loop == LOOP) {
            String confirmAdd = "e";
            if (!isNumber(inputIngredientWeight)) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }

            ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
            while (ingredientWeightValue < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrWeight(inputIngredientWeight);
                inputIngredientWeight = in.nextLine();
                ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
            }
            if (Double.isInfinite(ingredientWeightValue) | Double.isNaN(ingredientWeightValue)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("dish waste"));
            } else if (ingredientWeightValue > 10000) {
                UI.clearTerminalAndPrintNewPage();
                UI.printIngrValueHigh(ingredientName);
                confirmAdd = in.nextLine();

                confirmAdd = getConfirmation(confirmAdd);
                if (confirmAdd.startsWith(NO)) {
                    UI.clearTerminalAndPrintNewPage();
                    UI.printEnterWeightOf(ingredientName);
                    inputIngredientWeight = in.nextLine();
                    ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
                }
            }
            if ((isNumber(inputIngredientWeight) && (ingredientWeightValue >= 0)
                    && (ingredientWeightValue <= 10000)) | confirmAdd.startsWith(YES)) {
                loop = EXIT;
            }
        }
        ingredientWeightValue = Double.parseDouble(inputIngredientWeight);
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
            while (userLimit < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrLimitValue(ingredientName);
                inputLimit = in.nextLine();
                userLimit = Double.parseDouble(inputLimit);
            }
        } catch (NumberFormatException e) {
            throw new FoodoramaException(UI.getInvalidNumberMsg());
        }
        if (Double.isInfinite(userLimit) | Double.isNaN(userLimit)) {
            throw new FoodoramaException(UI.printNumericalInputInvalid("ingredient limit"));
        }
        limit = userLimit;
        UI.printLimitSet(ingredientName, limit);
    }

    public void addWaste() throws FoodoramaException {
        UI.printEnterWasteWeightOf(ingredientName);
        Scanner in = new Scanner(System.in);
        String ingredientWeight = in.nextLine();

        int exitloop = 0;
        double ingredientWeightValue;
        while (exitloop == 0) {
            String confirmAdd = "e";
            if (!isNumber(ingredientWeight)) {
                throw new FoodoramaException(UI.getInvalidNumberMsg());
            }

            ingredientWeightValue = Double.parseDouble(ingredientWeight);
            while (ingredientWeightValue < 0) {
                UI.clearTerminalAndPrintNewPage();
                UI.printInvalidIngrWasteValue(ingredientName);
                ingredientWeight = in.nextLine();
                ingredientWeightValue = Double.parseDouble(ingredientWeight);
            }
            if (Double.isInfinite(ingredientWeightValue) | Double.isNaN(ingredientWeightValue)) {
                throw new FoodoramaException(UI.printNumericalInputInvalid("dish waste"));
            } else if (ingredientWeightValue > 10000) {
                UI.clearTerminalAndPrintNewPage();
                UI.printIngrWasteValueHigh(ingredientName);
                confirmAdd = in.nextLine();

                confirmAdd = getConfirmation(confirmAdd);
                if (confirmAdd.startsWith(NO)) {
                    UI.clearTerminalAndPrintNewPage();
                    UI.printEnterWeightOf(ingredientName);
                    ingredientWeight = in.nextLine();
                    ingredientWeightValue = Double.parseDouble(ingredientWeight);
                }
            }
            if ((isNumber(ingredientWeight) && (ingredientWeightValue >= 0)
                    && (ingredientWeightValue <= 10000)) | confirmAdd.startsWith(YES)) {
                exitloop = 1;
            }
        }

        ingredientWeightValue = Double.parseDouble(ingredientWeight);

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
                limitString = limitString + " (exceeded)";
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
                + "   Storage: " + ingredientWeight + " kg" + System.lineSeparator()
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
        return ingredientName + "|" + ingredientWeight + "|" + ingredientWasteIngr + "|" + limit + "|"
                + expiryDateString;
    }


    public void addDishWaste(Double value) {
        ingredientWasteDish += value;
    }

    public double getGraphHeight(double max, int resolution) {
        double wastage = ingredientWasteDish + ingredientWasteIngr;
        double num = resolution * wastage / max;
        return num;
    }

    @Override
    public int compareTo(Ingredient o) {
        double wastage = ingredientWasteDish + ingredientWasteIngr;
        double diff = (o.getWastage() - wastage);
        return (diff >= 0) ? (diff == 0) ? 0 : 1 : -1;
    }

    public static boolean isNumber(String numberString) {
        try {
            int numberInteger = Integer.parseInt(numberString) - 1;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getConfirmation(String confirmAdd) {
        Scanner input = new Scanner(System.in);
        while (!confirmAdd.matches(YES_NO_REGEX)) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmationSoftLimit();
            confirmAdd = input.nextLine().toLowerCase();
        }
        return confirmAdd;
    }
}
