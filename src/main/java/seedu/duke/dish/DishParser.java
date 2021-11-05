//@@author jerrelllzw

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
            double dishPrice = Double.parseDouble(command[2]);
            boolean negativePrice = dishPrice < 1;
            if (negativePrice) {
                DishUI.printInvalidPriceMessage();
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

    public boolean duplicateDishChecker(String dishName, Menu menu){
        for (int i = 0; i < menu.menu.size(); i++) {
            if (menu.menu.get(i).getName().equals(dishName)) {
                DishUI.printDuplicateDishMessage();
                return true;
            }
        }
        return false;
    }

    public void addDish(String[] command, Menu menu) {
        boolean isValidAddDishCommand = addDishCommandChecker(command);
        boolean noDuplicateDishes = !duplicateDishChecker(command[1], menu);
        if (isValidAddDishCommand && noDuplicateDishes) {
            double dishPrice = Double.parseDouble(command[2]);
            Dish newDish = new Dish(command[1], dishPrice);
            menu.menu.add(newDish);
            DishUI.printAddDishMessage(newDish, menu.menu.size());
        }
    }

    public void loadDishFromStorage(Menu menu, Dish dish) {
        menu.menu.add(dish);
    }

    public boolean removeDishCommandChecker(String[] command, int menuSize) {
        try {
            int dishIndex = Integer.parseInt(command[1]);
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
            int dishIndex = Integer.parseInt(command[1]);
            assert 0 < dishIndex : "Index should be more than 0";
            assert dishIndex < menu.menu.size() + 1 : "Index should not be bigger than the menu size";
            Dish oldDish = menu.menu.get(dishIndex - 1);
            menu.menu.remove(dishIndex - 1);
            DishUI.printRemoveDishMessage(oldDish, dishIndex);
        }
    }

    public boolean editDishCommandChecker(String[] command, int menuSize) {
        try {
            int dishIndex = Integer.parseInt(command[1]);
            boolean validDishIndex = (0 < dishIndex) && (dishIndex < menuSize + 1);
            if (!validDishIndex) {
                DishUI.printInvalidIndexMessage();
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
            return false;
        } catch (NumberFormatException e) {
            DishUI.printInvalidIndexMessage();
            return false;
        }
        try {
            Double.parseDouble(command[2]);
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
            int dishIndex = Integer.parseInt(command[1]);
            assert 0 < dishIndex : "Index should be more than 0";
            assert dishIndex < menu.menu.size() + 1 : "Index should not be bigger than the menu size";
            double newPrice = Double.parseDouble(command[2]);
            menu.menu.get(dishIndex - 1).setPrice(newPrice);
            DishUI.printEditDishMessage(menu.menu.get(dishIndex - 1), dishIndex);
        }
    }

    public boolean discountDishCommandChecker(String[] command, int menuSize) {
        try {
            int dishIndex = Integer.parseInt(command[1]);
            boolean validDishIndex = (0 < dishIndex) && (dishIndex < menuSize + 1);
            if (!validDishIndex) {
                DishUI.printInvalidIndexMessage();
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
            return false;
        } catch (NumberFormatException e) {
            DishUI.printInvalidIndexMessage();
            return false;
        }
        try {
            Double.parseDouble(command[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
            return false;
        } catch (NumberFormatException e) {
            DishUI.printInvalidDiscountMessage();
            return false;
        }
        return true;
    }

    public void discountDish(String[] command, Menu menu) {
        boolean isValidDiscountDishCommand = discountDishCommandChecker(command, menu.menu.size());
        if (isValidDiscountDishCommand) {
            int dishIndex = Integer.parseInt(command[1]);
            assert 0 < dishIndex : "Index should be more than 0";
            assert dishIndex < menu.menu.size() + 1 : "Index should not be bigger than the menu size";
            double discount = Double.parseDouble(command[2]);
            menu.menu.get(dishIndex - 1).setDiscount(discount);
            DishUI.printDiscountDishMessage(menu.menu.get(dishIndex - 1), dishIndex);
        }
    }

    public void listMenu(Menu menu) {
        if (menu.menu.size() < 1) {
            DishUI.printEmptyMenu();
        } else {
            DishUI.printMenu(menu);
        }
    }

}