//@@author jerrelllzw

package seedu.duke.dish;

import seedu.duke.main.MainUI;

/**
 * Contains all UI related methods.
 */
public class DishUI {

    /**
     * Prints a message to the user whenever a dish is added.
     *
     * @param newDish Dish added.
     * @param dishIndex Index number of the added dish as shown in the displayed menu.
     */
    public static void printAddDishMessage(Dish newDish, int dishIndex) {
        MainUI.printSingleLine();
        System.out.println(" I have added the following dish to the menu:");
        System.out.println("   " + dishIndex + ". " + newDish);
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever a dish is removed.
     *
     * @param oldDish Dish removed.
     * @param dishIndex Index number of the added dish as shown in the displayed menu.
     */
    public static void printRemoveDishMessage(Dish oldDish, int dishIndex) {
        MainUI.printSingleLine();
        System.out.println(" I have removed the following dish from the menu:");
        System.out.println("   " + dishIndex + ". " + oldDish);
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever there is an attempt to print an empty menu.
     */
    public static void printEmptyMenu() {
        MainUI.printSingleLine();
        System.out.println(" Sorry, there are no dishes in the menu yet :(");
        MainUI.printSingleLine();
    }

    /**
     * Prints a list of all the dishes in the menu.
     *
     * @param menu Menu to be printed.
     */
    public static void printMenu(Menu menu) {
        MainUI.printSingleLine();
        System.out.println(" Here are the dishes in your menu:");
        for (int i = 0; i < menu.menu.size(); i++) {
            int index = i + 1;
            boolean notDiscounted = menu.menu.get(i).getDiscount() == 0;
            if (notDiscounted) {
                System.out.println("   " + index + ". " + menu.menu.get(i));
            } else {
                System.out.println("   " + index + ". " + menu.menu.get(i)
                        + " ---> " + menu.menu.get(i).getDiscountedPriceString());
            }
        }
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever a dish's price is edited.
     *
     * @param newDish Dish which price has been edited.
     * @param dishIndex Index number of the added dish as shown in the displayed menu.
     */
    public static void printEditDishMessage(Dish newDish, int dishIndex) {
        MainUI.printSingleLine();
        System.out.println(" Got it! The updated price of the dish is as follows:");
        System.out.println("   " + dishIndex + ". " + newDish);
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever a discount is added to a dish.
     *
     * @param discountedDish Dish which a discount has been added to.
     * @param dishIndex Index number of the added dish as shown in the displayed menu.
     */
    public static void printDiscountDishMessage(Dish discountedDish, int dishIndex) {
        boolean originalPrice = discountedDish.getDiscount() == 0;
        MainUI.printSingleLine();
        if (originalPrice) {
            System.out.println(" Got it! I have set the dish back to its original price!");
            System.out.println("   " + dishIndex + ". " + discountedDish);
        } else {
            System.out.println(" Got it! I have added the discount to the dish!");
            System.out.println(" The discounted price is as follows:");
            System.out.println("   " + dishIndex + ". " + discountedDish
                    + " ---> " + discountedDish.getDiscountedPriceString());
        }
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever there is an attempt to add an already existing dish to the menu.
     */
    public static void printDuplicateDishMessage() {
        MainUI.printSingleLine();
        System.out.println(" The dish that you entered is already in the menu!");
        System.out.println(" Please try again :)");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever an invalid index number is entered.
     */
    public static void printInvalidIndexMessage() {
        MainUI.printSingleLine();
        System.out.println(" You have entered an invalid index number!");
        System.out.println(" Please try again :)");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever an invalid price is entered.
     */
    public static void printInvalidPriceMessage() {
        MainUI.printSingleLine();
        System.out.println(" You have entered an invalid price!");
        System.out.println(" Please try again :)");
        MainUI.printSingleLine();
    }

    /**
     * Prints a message to the user whenever an invalid discount is entered.
     */
    public static void printInvalidDiscountMessage() {
        MainUI.printSingleLine();
        System.out.println(" You have entered an invalid discount percentage!");
        System.out.println(" Please try again :)");
        MainUI.printSingleLine();
    }

}
