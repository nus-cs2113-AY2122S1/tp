package seedu.foodorama.command;

import seedu.foodorama.Ui;

import java.util.ArrayList;
import java.util.Random;

/**
 * Allows the user to generate a random dish name
 * from a fixed pool of vocabulary containing
 * Carbohydrates, Proteins, Sauces and Cooking Methods.
 * Format: rdish
 *
 * @author jhsee5
 */
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

    /**
     * Getter for carbohydrates String ArrayList.
     *
     * @return String ArrayList carbohydrates
     * @author jhsee5
     */
    public ArrayList<String> getCarbohydrates() {
        return carbohydrates;
    }

    /**
     * Getter for proteins String ArrayList.
     *
     * @return String ArrayList proteins
     * @author jhsee5
     */
    public ArrayList<String> getProteins() {
        return proteins;
    }

    /**
     * Getter for sauces String ArrayList.
     *
     * @return String ArrayList sauces
     * @author jhsee5
     */
    public ArrayList<String> getSauces() {
        return sauces;
    }

    /**
     * Getter for cookingMethods String ArrayList.
     *
     * @return String ArrayList cookingMethods
     * @author jhsee5
     */
    public ArrayList<String> getCookingMethods() {
        return cookingMethods;
    }


    /**
     * User command to generate a random DISH_NAME.
     *
     * @param parameters contains a blank since rdish does not take in parameters
     * @author jhsee5
     */
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

        ui.printRandomDishName(generatedDishName);

    }


}
