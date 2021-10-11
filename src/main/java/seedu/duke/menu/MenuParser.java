package seedu.duke.menu;

public class MenuParser {

    public void addMenu(String[] command, MenuList masterList) {
        Menu newMenu = new Menu(command[1], command[2]);
        masterList.menuList.add(newMenu);
        MenuUI.printAddMenuMessage(newMenu);
    }

    public void loadMenuFromStorage(MenuList masterList, Menu menu) {
        masterList.menuList.add(menu);
    }

    public void deleteMenu(String[] command, MenuList masterList) {
        int targetIndex = Integer.valueOf(command[1]) - 1;
        Menu oldMenu = masterList.menuList.get(targetIndex);

        if (0 < targetIndex || targetIndex < masterList.menuListSize + 1) {
            MenuUI.printInvalidIndexMessage();
        } else {
            assert 0 < targetIndex : "Index of menu item to be removed should be more than 0";
            assert targetIndex < masterList.menuListSize : "Index of menu item to be removed should be less than the menu size";
            masterList.menuList.remove(targetIndex);
            MenuUI.printRemoveMenuMessage(oldMenu);
        }
    }

    public void listMenu(MenuList masterList) {
        if (masterList.menuList.size() < 1) {
            MenuUI.printEmptyMenuList();
        } else {
            MenuUI.printMenuList(masterList);
        }
    }

}