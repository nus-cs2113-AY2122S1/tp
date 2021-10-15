package seedu.duke.dish;

import seedu.duke.main.MainUI;

public class DishUI {

    public static void printAddDishMessage(Dish newDish, int dishIndex) {
        MainUI.printSingleLine();
        System.out.println(" I have added the following dish to the menu:");
        System.out.println("   " + dishIndex + ". " + newDish);
        MainUI.printSingleLine();
    }

    public static void printRemoveDishMessage(Dish oldDish, int dishIndex) {
        MainUI.printSingleLine();
        System.out.println(" I have removed the following dish from the menu:");
        System.out.println("   " + dishIndex + ". " + oldDish);
        MainUI.printSingleLine();
    }

    public static void printEmptyDishList() {
        MainUI.printSingleLine();
        System.out.println(" Sorry, there are no dishes in the menu yet :(");
        MainUI.printSingleLine();
    }

    public static void printDishList(Menu menu) {
        MainUI.printSingleLine();
        System.out.println(" Here are the dishes in your menu:");
        for (int i = 0; i < menu.menu.size(); i++) {
            int index = i + 1;
            System.out.println("   " + index + ". " + menu.menu.get(i));
        }
        MainUI.printSingleLine();
    }

    public static void printEditDishMessage(Dish newDish, int dishIndex) {
        MainUI.printSingleLine();
        System.out.println(" Got it! I have updated the price of the dish as follows:");
        System.out.println("   " + dishIndex + ". " + newDish);
        MainUI.printSingleLine();
    }

    public static void printInvalidIndexMessage() {
        MainUI.printSingleLine();
        System.out.println(" You have entered an invalid index number!");
        System.out.println(" Please try again :)");
        MainUI.printSingleLine();
    }

    public static void printInvalidPriceMessage() {
        MainUI.printSingleLine();
        System.out.println(" You have entered an invalid price!");
        System.out.println(" Please try again :)");
        MainUI.printSingleLine();
    }

}
