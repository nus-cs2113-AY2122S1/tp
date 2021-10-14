package seedu.duke.menu;

import seedu.duke.main.MainUI;

public class MenuParser {

    public boolean addMenuCommandChecker(String[] command) {
        try {
            boolean emptyDescription = command[1].stripLeading().stripTrailing().equals("");
            if (emptyDescription) {
                MainUI.printWrongCommandMessage();
                return false;
            }
            Double.valueOf(command[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
            return false;
        } catch (NumberFormatException e) {
            MenuUI.printInvalidPriceMessage();
            return false;
        }
        return true;
    }

    public void addMenu(String[] command, MenuList masterList) {
        boolean isValidAddMenuCommand = addMenuCommandChecker(command);
        if (isValidAddMenuCommand) {
            Menu newMenu = new Menu(command[1], command[2]);
            masterList.menuList.add(newMenu);
            MenuUI.printAddMenuMessage(newMenu, masterList.menuList.size());
        }
    }

    public void loadMenuFromStorage(MenuList masterList, Menu menu) {
        masterList.menuList.add(menu);
    }

    public boolean removeMenuCommandChecker (String[] command, int menuSize) {
        try {
            int menuIndex = Integer.valueOf(command[1]);
            boolean validMenuIndex = (0 < menuIndex) && (menuIndex < menuSize + 1);
            if (!validMenuIndex) {
                MenuUI.printInvalidIndexMessage();
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
            return false;
        } catch (NumberFormatException e) {
            MenuUI.printInvalidIndexMessage();
            return false;
        }
        return true;
    }

    public void removeMenu(String[] command, MenuList masterList) {
        boolean isValidRemoveMenuCommand = removeMenuCommandChecker(command, masterList.menuList.size());
        if (isValidRemoveMenuCommand) {
            int menuIndex = Integer.valueOf(command[1]);
            assert 0 < menuIndex : "Index should be more than 0";
            assert menuIndex < masterList.menuList.size() + 1 : "Index should not be bigger than the menu size";
            Menu oldMenu = masterList.menuList.get(menuIndex - 1);
            masterList.menuList.remove(menuIndex - 1);
            MenuUI.printRemoveMenuMessage(oldMenu, menuIndex);
        }
    }

    public boolean editMenuCommandChecker(String[] command, int menuSize) {
        try {
            Double.valueOf(command[2]);
            int menuIndex = Integer.valueOf(command[1]);
            boolean validMenuIndex = (0 < menuIndex) && (menuIndex < menuSize + 1);
            if (!validMenuIndex) {
                MenuUI.printInvalidIndexMessage();
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
            return false;
        } catch (NumberFormatException e) {
            MenuUI.printInvalidPriceMessage();
            return false;
        }
        return true;
    }

    public void editMenu(String[] command, MenuList masterList) {
        boolean isValidEditMenuCommand = editMenuCommandChecker(command, masterList.menuList.size());
        if (isValidEditMenuCommand) {
            int menuIndex = Integer.valueOf(command[1]);
            assert 0 < menuIndex : "Index should be more than 0";
            assert menuIndex < masterList.menuList.size() + 1 : "Index should not be bigger than the menu size";
            masterList.menuList.get(menuIndex - 1).setPrice(command[2]);
            MenuUI.printEditMenuMessage(masterList.menuList.get(menuIndex - 1), menuIndex);
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