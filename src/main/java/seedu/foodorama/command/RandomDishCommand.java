package seedu.foodorama.command;

import java.util.ArrayList;
import java.util.Random;

public class RandomDishCommand extends Command {

    String randomDishName;

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


    @Override
    public void execute(ArrayList<String> parameters) {

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

        System.out.println("Here's an idea for a new Dish!"
                + System.lineSeparator()
                + generatedDishName
        );

    }


}
