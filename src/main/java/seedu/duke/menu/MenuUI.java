package seedu.duke.menu;

import seedu.duke.main.MainUI;

public class MenuUI {

    public static void printAddMenuMessage(Menu newMenu, int menuIndex) {
        MainUI.printSingleLine();
        System.out.println(" I have added the following dish to the menu:");
        System.out.println("   " + menuIndex + ". " + newMenu);
        MainUI.printSingleLine();
    }

    public static void printRemoveMenuMessage(Menu oldMenu, int menuIndex) {
        MainUI.printSingleLine();
        System.out.println(" I have removed the following dish from the menu:");
        System.out.println("   " + menuIndex + ". " + oldMenu);
        MainUI.printSingleLine();
    }

    public static void printEmptyMenuList() {
        MainUI.printSingleLine();
        System.out.println(" Sorry, there are no dishes in the menu yet :(");
        MainUI.printSingleLine();
    }

    public static void printMenuList(MenuList masterList) {
        MainUI.printSingleLine();
        System.out.println(" Here are the dishes in your menu:");
        for (int i = 0; i < masterList.menuList.size(); i++) {
            int index = i + 1;
            System.out.println("   " + index + ". " + masterList.menuList.get(i));
        }
        MainUI.printSingleLine();
    }

    public static void printEditMenuMessage(Menu newMenu, int menuIndex) {
        MainUI.printSingleLine();
        System.out.println(" Got it! I have updated the price of the dish as follows:");
        System.out.println("   " + menuIndex + ". " + newMenu);
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
