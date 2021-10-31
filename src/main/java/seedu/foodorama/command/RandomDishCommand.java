package seedu.foodorama.command;

import seedu.foodorama.exceptions.FoodoramaException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class RandomDishCommand extends Command {

    String randomDishName;

    ArrayList<String> Carbohydrates = new ArrayList<>() {
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

    ArrayList<String> Proteins = new ArrayList<>() {
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

    ArrayList<String> Sauces = new ArrayList<>() {
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

    ArrayList<String> CookingMethods = new ArrayList<>() {
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

        Random random_method = new Random();

        int carbohydratesIndex = random_method.nextInt(Carbohydrates.size());
        int proteinsIndex = random_method.nextInt(Proteins.size());
        int saucesIndex = random_method.nextInt(Sauces.size());
        int cookingMethodsIndex = random_method.nextInt(CookingMethods.size());

        String generatedDishName = CookingMethods.get(cookingMethodsIndex) + " "
                + Sauces.get(saucesIndex) + " "
                + Proteins.get(proteinsIndex) + " "
                + Carbohydrates.get(carbohydratesIndex) + " ";

        this.randomDishName = generatedDishName;

        System.out.println("Here's an idea for a new Dish!"
                + System.lineSeparator()
                + generatedDishName
        );

    }


}
