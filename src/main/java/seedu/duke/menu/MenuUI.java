package seedu.duke.menu;

import seedu.duke.main.MainUI;

public class MenuUI {

    public static void printAddMenuMessage(Menu newMenu) {
        MainUI.printSingleLine();
        System.out.println("I have added: ");
        System.out.println(newMenu);
        MainUI.printSingleLine();
    }

    public static void printInvalidIndexMessage() {
        MainUI.printSingleLine();
        System.out.println("You have entered an invalid index number! Please try again :)");
        MainUI.printSingleLine();
    }

    public static void printRemoveMenuMessage(Menu oldMenu) {
        MainUI.printSingleLine();
        System.out.println("I have removed: ");
        System.out.println(oldMenu);
        MainUI.printSingleLine();
    }

    public static void printEmptyMenuList() {
        MainUI.printSingleLine();
        System.out.println("No items in menu yet :(");
        MainUI.printSingleLine();
    }

    public static void printMenuList(MenuList masterList) {
        MainUI.printSingleLine();
        System.out.println("Here are the items in your menu:");
        for (int i = 0; i < masterList.menuList.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + masterList.menuList.get(i));
        }
        MainUI.printSingleLine();
    }

}
