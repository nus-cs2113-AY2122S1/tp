package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu {

    private static String[] FOODS = {"Plain Prata", 
                                     "Egg Prata",
                                     "Cheese Prata",
                                     "Chili Prata",
                                     "Garlic Prata",
                                     "Paper Prata",
                                     "Special Paper Prata",
                                     "Special Paper Prata (Milk)",
                                     "Special Paper Prata (Sugar)",
                                     "Special Paper Prata (Milk & Sugar)",
                                     "Garlic Cheese Prata",
                                     "Mozzarella Cheese Prata",
                                     "Egg and Onion Prata",
                                     "Egg and Garlic Prata",
                                     "Egg and Cheese Prata",
                                     "Egg and Chili Prata",
                                     "Egg and Onion Cheese Prata",
                                     "Egg and Onion Chili Prata",
                                     "Egg and Onion Mushroom Prata",
                                     "Double Egg Prata",
                                     "Double Egg Onion Prata",
                                     "Double Egg Cheese Prata",
                                     "Double Egg Chili Prata",
                                     "Onion Prata",
                                     "Onion Cheese Prata",
                                     "Onion Chili Prata",
                                     "Onion Garlic Prata",
                                     "Plaster Prata",
                                     "Plaster Chili Prata",
                                     "Plaster Onion Prata",
                                     "Plaster Cheese Prata",
                                     "Banana Prata",
                                     "Chocolate Prata",
                                     "Pineapple Prata",
                                     "Red Bean Prata",
                                     "Roti Bom Prata",
                                     "Strawberry Prata",
                                     "Milky Prata",
                                     "Banana Chocolate Prata",
                                     "Egg Floss Prata",
                                     "Egg Cheese Floss Prata",
                                     "Tomato Prata",
                                     "Mushroom Egg Tomato Prata",
                                     "Hotdog Prata",
                                     "Mushroom Egg Prata",
                                     "Hotdog Cheese Prata",
                                     "Mushroom Cheese Egg Prata",
                                     "Portobello Mozzarella Prata"};
    private static final Double[] PRICES = {1.1, 1.9, 2.5, 1.4, 1.7, 1.2, 1.4, 1.6, 1.6, 1.9,
                                            3.0, 3.0, 2.2, 2.4, 3.2, 2.2, 3.7, 2.5, 3.5, 2.8,
                                            3.0, 4.0, 3.0, 1.5, 2.5, 2.1, 2.1, 1.9, 2.2, 2.2,
                                            3.2, 3.0, 2.5, 2.5, 3.0, 2.0, 2.5, 3.0, 3.5, 3.5,
                                            4.5, 1.7, 3.5, 3.0, 3.0, 4.0, 4.0, 5.0};

    public static final int TOTAL_MENU_ITEMS = PRICES.length;
    public static final ArrayList<String> FOODLIST = new ArrayList<>(Arrays.asList(FOODS));
    public static final ArrayList<Double> PRICELIST = new ArrayList<>(Arrays.asList(PRICES));

}
