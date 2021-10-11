package seedu.duke.menu;

public class MenuParser {

    public void addMenu(MenuList masterList, MenuItem menuItem) {
        masterList.menuList.add(menuItem);
    }

    public void deleteMenu(MenuList masterList, int menuItemIndex) {
        if (masterList.menuList.size() < 1) {
            System.out.println("No items in list :(");
        } else {
            masterList.menuList.remove(menuItemIndex);
        }
    }

    public void listMenu(MenuList masterList) {
        if (masterList.menuList.size() < 1) {
            System.out.println("No items in list :(");
        } else {
            System.out.println("--------------------");
            System.out.println("Here are the items in your menu:");
            for (int i = 0; i < masterList.menuList.size(); i++) {
                int index = i + 1;
                System.out.println(index + ". " + masterList.menuList.get(i));
            }
            System.out.println("--------------------");
        }
    }

}