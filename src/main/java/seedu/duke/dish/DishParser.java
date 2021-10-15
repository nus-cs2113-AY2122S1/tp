package seedu.duke.dish;

import seedu.duke.main.MainUI;

public class DishParser {

    public boolean addDishCommandChecker(String[] command) {
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
            DishUI.printInvalidPriceMessage();
            return false;
        }
        return true;
    }

    public void addDish(String[] command, Menu menu) {
        boolean isValidAddDishCommand = addDishCommandChecker(command);
        if (isValidAddDishCommand) {
            double dishPrice = Double.valueOf(command[2]);
            Dish newDish = new Dish(command[1], dishPrice);
            menu.menu.add(newDish);
            DishUI.printAddDishMessage(newDish, menu.menu.size());
        }
    }

    public void loadDishFromStorage(Menu menu, Dish dish) {
        menu.menu.add(dish);
    }

    public boolean removeDishCommandChecker (String[] command, int menuSize) {
        try {
            int dishIndex = Integer.valueOf(command[1]);
            boolean validDishIndex = (0 < dishIndex) && (dishIndex < menuSize + 1);
            if (!validDishIndex) {
                DishUI.printInvalidIndexMessage();
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
            return false;
        } catch (NumberFormatException e) {
            DishUI.printInvalidIndexMessage();
            return false;
        }
        return true;
    }

    public void removeDish(String[] command, Menu menu) {
        boolean isValidRemoveDishCommand = removeDishCommandChecker(command, menu.menu.size());
        if (isValidRemoveDishCommand) {
            int dishIndex = Integer.valueOf(command[1]);
            assert 0 < dishIndex : "Index should be more than 0";
            assert dishIndex < menu.menu.size() + 1 : "Index should not be bigger than the menu size";
            Dish oldDish = menu.menu.get(dishIndex - 1);
            menu.menu.remove(dishIndex - 1);
            DishUI.printRemoveDishMessage(oldDish, dishIndex);
        }
    }

    public boolean editDishCommandChecker(String[] command, int menuSize) {
        try {
            Double.valueOf(command[2]);
            int dishIndex = Integer.valueOf(command[1]);
            boolean validDishIndex = (0 < dishIndex) && (dishIndex < menuSize + 1);
            if (!validDishIndex) {
                DishUI.printInvalidIndexMessage();
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
            return false;
        } catch (NumberFormatException e) {
            DishUI.printInvalidPriceMessage();
            return false;
        }
        return true;
    }

    public void editDish(String[] command, Menu menu) {
        boolean isValidEditDishCommand = editDishCommandChecker(command, menu.menu.size());
        if (isValidEditDishCommand) {
            int dishIndex = Integer.valueOf(command[1]);
            assert 0 < dishIndex : "Index should be more than 0";
            assert dishIndex < menu.menu.size() + 1 : "Index should not be bigger than the menu size";
            double newPrice = Double.valueOf(command[2]);
            menu.menu.get(dishIndex - 1).setPrice(newPrice);
            DishUI.printEditDishMessage(menu.menu.get(dishIndex - 1), dishIndex);
        }
    }

    public void listMenu(Menu menu) {
        if (menu.menu.size() < 1) {
            DishUI.printEmptyDishList();
        } else {
            DishUI.printDishList(menu);
        }
    }

}