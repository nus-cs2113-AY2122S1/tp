package seedu.duke.menu;

import seedu.duke.main.MainUI;

public class MenuParser {

    public void addMenu(String[] command, MenuList masterList) {
        try {
            Menu newMenu = new Menu(command[1], command[2]);
            masterList.menuList.add(newMenu);
            MenuUI.printAddMenuMessage(newMenu);
        } catch (ArrayIndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
        }
    }

    public void loadMenuFromStorage(MenuList masterList, Menu menu) {
        masterList.menuList.add(menu);
    }

    public void removeMenu(String[] command, MenuList masterList) {
        try {
            int targetIndex = Integer.valueOf(command[1]) - 1;
            boolean validTargetIndex = (-1 < targetIndex) && (targetIndex < masterList.menuListSize + 1);
            if (!validTargetIndex) {
                MenuUI.printInvalidIndexMessage();
            } else {
                assert -1 < targetIndex : "Index should be more than 0";
                assert targetIndex < masterList.menuListSize + 1: "Index should be less than the menu size";
                Menu oldMenu = masterList.menuList.get(targetIndex);
                masterList.menuList.remove(targetIndex);
                MenuUI.printRemoveMenuMessage(oldMenu);
            }
        } catch (IndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
        } catch (NumberFormatException e) {
            MainUI.printWrongCommandMessage();
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