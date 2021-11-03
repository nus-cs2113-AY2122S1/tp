package seedu.foodorama.command;

import seedu.foodorama.Ui;

import java.util.ArrayList;
import java.util.Random;

public class RandomDishCommand extends Command {

    private String randomDishName;

    public String getRandomDishName() {
        return this.randomDishName;
    }

    ArrayList<String> carbohydrates = new ArrayList<>() {
        {
            add("rice");
            add("noodles");
            add("beans");
            add("potatoes");
            add("pizza");
            add("burger");
            add("pasta");
        }
    };

    ArrayList<String> proteins = new ArrayList<>() {
        {
            add("chicken");
            add("turkey");
            add("beef");
            add("pork");
            add("duck");
            add("lamb");
            add("fish");
            add("lobster");
        }
    };

    ArrayList<String> sauces = new ArrayList<>() {
        {
            add("chili");
            add("tomato");
            add("mustard");
            add("cheesy");
            add("soy sauce");
            add("mayonnaise");
            add("sweet");
        }
    };

    ArrayList<String> cookingMethods = new ArrayList<>() {
        {
            add("steamed");
            add("grilled");
            add("fried");
            add("baked");
            add("smoked");
            add("roasted");
        }
    };

    public ArrayList<String> getCarbohydrates() {
        return carbohydrates;
    }

    public ArrayList<String> getProteins() {
        return proteins;
    }

    public ArrayList<String> getSauces() {
        return sauces;
    }

    public ArrayList<String> getCookingMethods() {
        return cookingMethods;
    }


    @Override
    public void execute(ArrayList<String> parameters) {

        Ui ui = new Ui();

        Random randomMethod = new Random();

        int carbohydratesIndex = randomMethod.nextInt(carbohydrates.size());
        int proteinsIndex = randomMethod.nextInt(proteins.size());
        int saucesIndex = randomMethod.nextInt(sauces.size());
        int cookingMethodsIndex = randomMethod.nextInt(cookingMethods.size());

        String generatedDishName = cookingMethods.get(cookingMethodsIndex) + " "
                + sauces.get(saucesIndex) + " "
                + proteins.get(proteinsIndex) + " "
                + carbohydrates.get(carbohydratesIndex) + " ";

        this.randomDishName = generatedDishName;

        ui.printRandommDishName(generatedDishName);

    }


}
