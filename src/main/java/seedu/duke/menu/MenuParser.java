package seedu.duke.menu;

public class MenuParser {

    public void addMenu(String[] command, MenuList masterList) {
        MenuItem newMenuItem = new MenuItem(command[1], command[2]);
        masterList.menuList.add(newMenuItem);

        System.out.println("I have added: ");
        System.out.println(newMenuItem);
    }

    public void loadMenuFromStorage(MenuList masterList, MenuItem menuItem) {
        masterList.menuList.add(menuItem);
    }

    public void deleteMenu(String[] command, MenuList masterList) {
        int targetIndex = Integer.valueOf(command[1]) - 1;
        MenuItem removedMenuItem = masterList.menuList.get(targetIndex);

        if (masterList.menuList.size() < 1) {
            System.out.println("No items in list :(");
        } else {
            masterList.menuList.remove(targetIndex);
        }

        System.out.println("I have removed: ");
        System.out.println(removedMenuItem);
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