package seedu.duke.food;

import seedu.duke.exceptions.food.NoFoodFoundOnDateException;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

/**
 * Represents a list of what the user has eaten.
 *  This would include the date of the record and
 *      the food items consumed.
 *
 * @author ngnigel99
 */
public class WhatIAteList  {

    private String stallName;
    private ArrayList<FoodRecord> whatIAteList = new ArrayList<>();


    public WhatIAteList() {

    }

    public ArrayList<FoodRecord> getList() {
        return whatIAteList;
    }

    /**
     * Adds a food Record to a list, then notifies the user of what  item
     *  they  have entered.
     *
     * @author  ngnigel99
     * @param recordToAdd record to add to list
     * @param isSilent checks whether to print Ui message
     */
    public void addToList(FoodRecord recordToAdd, boolean isSilent) {
        whatIAteList.add(recordToAdd);
        if (!isSilent) {
            Ui.printAddRecord(recordToAdd);
        }
    }

    /**
     * Prints  index with suffix for visuals.
     *
     * @author ngnigel99
     * @param index index of list
     */
    public void printIndexWithSuffix(int index) {
        String[] suffixList = {"st", "nd", "rd", "th"};
        String suffixToPrint;
        int lastDigit = index % 10;
        int exceptionTeen = index % 100; //11,12,13th
        if (lastDigit == 1 && exceptionTeen != 11) {
            suffixToPrint = suffixList[0];
        } else if (lastDigit == 2 && exceptionTeen != 12) {
            suffixToPrint = suffixList[1];
        } else if (lastDigit == 3 && exceptionTeen != 13) {
            suffixToPrint = suffixList[2];
        } else {
            suffixToPrint = suffixList[3];
        }
        Ui.printMessageSameLine(index + suffixToPrint  +  ",");
    }

    /**
     * Prints food records collated in list.
     * This list could belong either to the user or to a reference list.
     * For reference list, we do not need messages.
     * For the user, messages would be included.
     * @param withMessages if messages are to be included.
     */
    public void printList(boolean withMessages) {
        int index = 1;  //TODO integrate this with storage so it's not a magic number
        int calorieSum =  0;
        Ui.printLine();
        for (FoodRecord listRecord : whatIAteList) {
            printIndexWithSuffix(index);
            printFoodNameAndCalories(withMessages, listRecord);
            index++;
            calorieSum += listRecord.getCalorieCount();
        }
        printFinishReadingList(calorieSum);
        if (withMessages) {
            Ui.printMessage("You consumed " + calorieSum + " calories in total!");
        }
        Ui.printLine();
    }

    private void printFinishReadingList(int calorieSum) {
        if (calorieSum == 0) {
            Ui.printMessage("Wow, 0 calories, please add something to the list!");
        } else {
            Ui.printMessage("Wow, that's a lot of food! Finished reading the list");
        }
    }

    private void printFoodNameAndCalories(boolean withMessages, FoodRecord listRecord) {
        if (withMessages) {
            System.out.println("You consumed  " + listRecord.getFoodName()
                    + " , which has a calorie count of : "
                    + listRecord.getCalorieCount()
                    + ((listRecord.getDateIAte() != null)
                        ? " on " + listRecord.stringLocalDateIfExistsFull() + "!"
                        : "!"));
        } else {
            System.out.println(listRecord.getFoodName() + " : " + listRecord.getCalorieCount() + "Kcal");
        }
    }

    public void clearList() {
        whatIAteList.clear();
        Ui.printDoneClearList();
    }

    public void printFoodWithFoundDate(LocalDate date) throws
            NoFoodFoundOnDateException {
        WhatIAteList whatIAteThatDay = new WhatIAteList();
        String formattedDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        addRecordIfFound(date, whatIAteThatDay);
        if (whatIAteThatDay.getList().isEmpty()) {
            throw new NoFoodFoundOnDateException();
        }
        Ui.printMessage("Nice, I found the items you ate on " + formattedDate);
        whatIAteThatDay.printList(true);
    }

    /**
     * Adds a record to a list if the date recorded matches a given date.
     * @param date date checked.
     * @param whatIAteList list of what the user ate.
     */
    private void addRecordIfFound(LocalDate date, WhatIAteList whatIAteList) {
        for (FoodRecord foodRecord : this.whatIAteList) {
            if (foodRecord.getDateIAte().equals(date)) {
                whatIAteList.getList().add(foodRecord);
            }
        }
    }
}
