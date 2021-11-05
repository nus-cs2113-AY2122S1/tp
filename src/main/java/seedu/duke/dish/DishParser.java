//@@author jerrelllzw

package seedu.duke.dish;

import seedu.duke.main.MainUI;

/**
 * Contains all Dish related methods.
 * Deals with the user's Dish related input commands.
 */
public class DishParser {

    /**
     * Checks whether an add-dish command is in the correct format.
     *
     * @param command User's command in ArrayList format.
     * @return Boolean representing whether the add-dish command is in the correct format.
     */
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

    /**
     * Checks whether the dish to be added is already in the menu.
     *
     * @param dishName Name of the dish to be added.
     * @param menu Menu of the user.
     * @return Boolean representing whether the dish to be added is already in the menu.
     */
    public boolean duplicateDishChecker(String dishName, Menu menu){
        for (int i = 0; i < menu.menu.size(); i++) {
            if (menu.menu.get(i).getName().equals(dishName)) {
                DishUI.printDuplicateDishMessage();
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new dish to the menu.
     *
     * @param command User's command in ArrayList format.
     * @param menu User's menu.
     */
    public void addDish(String[] command, Menu menu) {
        boolean isValidAddDishCommand = addDishCommandChecker(command);
        boolean noDuplicateDishes = !duplicateDishChecker(command[1], menu);
        if (isValidAddDishCommand && noDuplicateDishes) {
            String dishName = command[1].stripLeading().stripTrailing();
            String dishPriceString = command[2].stripLeading().stripTrailing();
            double dishPrice = Double.parseDouble(dishPriceString);
            Dish newDish = new Dish(dishName, dishPrice);
            menu.menu.add(newDish);
            DishUI.printAddDishMessage(newDish, menu.menu.size());
        }
    }

    /**
     * Adds a new dish to the menu without printing any messages.
     *
     * @param menu User's menu.
     * @param dish Dish to be added.
     */
    public void loadDishFromStorage(Menu menu, Dish dish) {
        menu.menu.add(dish);
    }

    /**
     * Checks whether a remove-dish command is in the correct format.
     *
     * @param command User's command in ArrayList format.
     * @param menuSize Size of user's menu.
     * @return Boolean representing whether the remove-dish command is in the correct format.
     */
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

    /**
     * Removes an existing dish from the menu.
     *
     * @param command User's command in ArrayList format.
     * @param menu User's menu.
     */
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

    /**
     * Checks whether an edit-dish command is in the correct format.
     *
     * @param command User's command in ArrayList format.
     * @param menuSize Size of user's menu.
     * @return Boolean representing whether the edit-dish command is in the correct format.
     */
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

    /**
     * Edits the price of an existing dish in the menu.
     *
     * @param command User's command in ArrayList format.
     * @param menu User's menu.
     */
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

    /**
     * Checks whether a discount-dish command is in the correct format.
     *
     * @param command User's command in ArrayList format.
     * @param menuSize Size of user's menu.
     * @return Boolean representing whether the discount-dish command is in the correct format.
     */
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
            double discount = Double.parseDouble(command[2]);
            boolean validDiscount = (0 <= discount) && (discount <= 100);
            if (!validDiscount) {
                DishUI.printInvalidDiscountMessage();
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            MainUI.printWrongCommandMessage();
            return false;
        } catch (NumberFormatException e) {
            DishUI.printInvalidDiscountMessage();
            return false;
        }
        return true;
    }

    /**
     * Adds a discount to an existing dish in the menu.
     *
     * @param command User's command in ArrayList format.
     * @param menu User's menu.
     */
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

    /**
     * Prints a list of all the dishes in the menu.
     *
     * @param menu Menu to be printed.
     */
    public void listMenu(Menu menu) {
        if (menu.menu.size() < 1) {
            DishUI.printEmptyMenu();
        } else {
            DishUI.printMenu(menu);
        }
    }

}