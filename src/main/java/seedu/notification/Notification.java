package seedu.notification;

import seedu.data.item.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//@@author zonglun99
public class Notification {
    private static final String HEALTH_NOTIFICATION = " ___________________________________________________\n"
            + "|                      HEALTH!                      |\n"
            + "|===================================================|\n"
            + "|      ___            _         __        ______    |\n"
            + "|     / _ \\___ __ _  (_)__  ___/ /__ ____/ / / /    |\n"
            + "|    / , _/ -_)  ' \\/ / _ \\/ _  / -_) __/_/_/_/     |\n"
            + "|   /_/|_|\\__/_/_/_/_/_//_/\\_,_/\\__/_/ (_|_|_)      |\n"
            + "|   ---------------------------------------------   |\n"
            + "|                                                   |\n"
            + "|    \"If you don't take time to take care of your   |\n"
            + "|    health now, you're gonna have to make time     |\n"
            + "|    for feeling sick and tired later.\"             |\n"
            + "|                                                   |\n"
            + "|            Always remember to eat more            |\n"
            + "|              Fruits and Vegetables!               |\n"
            + "|___________________________________________________|";
    private static final String EXPIRING_NOTIFICATION = " ___________________________________________________\n"
            + "|                      EXPIRY!                      |\n"
            + "|===================================================|\n"
            + "|      ___            _         __        ______    |\n"
            + "|     / _ \\___ __ _  (_)__  ___/ /__ ____/ / / /    |\n"
            + "|    / , _/ -_)  ' \\/ / _ \\/ _  / -_) __/_/_/_/     |\n"
            + "|   /_/|_|\\__/_/_/_/_/_//_/\\_,_/\\__/_/ (_|_|_)      |\n"
            + "|___________________________________________________|\n";
    private static final String FIVE_SPACE_INDENTATION = "     ";
    private static final String SEPARATOR_LINE = "__________________________________________";
    private static final String NOTIFICATION_SEPARATOR = " --------------------------------------------------- \n";
    private static final String NOTIFICATION_CLOSING_LINE = "|___________________________________________________|\n";
    private static final String EXPIRED_MESSAGE = " >> Items expired! Please discard ASAP:";
    private static final String EXPIRING_MESSAGE = " >> Please use them before they expire:";
    private LocalDateTime dateAndTime = LocalDateTime.now().minusHours(4);
    private static boolean isNotificationOn = true;

    /**
     * Constructor for notification.
     */
    public Notification() {
    }

    private void printMessage(String content) {
        System.out.println(content);
    }

    /**
     * Prints out all notifications.
     *
     * @param listOfItems list of items stored in itemList.
     */
    public void printNotification(ArrayList<Item> listOfItems) {
        if (isNotificationOn() && isMoreThan4Hours()) {
            printHealthNotification();
            printExpiringNotification(listOfItems);
        }
    }

    /**
     * Prints health notification when it is more than 4 hours since last notification
     * and notification is set to on.
     */
    private void printHealthNotification() {
        printMessage(HEALTH_NOTIFICATION + "\n" + SEPARATOR_LINE);
        setDateAndTime(LocalDateTime.now());
    }

    /**
     * Print out expiring notification for items expiring and/or expired if any.
     *
     * @param listOfItems list of items stored in itemList.
     */
    private void printExpiringNotification(ArrayList<Item> listOfItems) {
        listOfItems.sort(Item.ItemExpiryComparator);
        ArrayList<Item> expiringList = new ArrayList<Item>();
        for (Item item : listOfItems) {
            if (item.isNearExpiry()) {
                expiringList.add(item);
            }
        }
        if (expiringList.size() > 0) {
            printMessage(EXPIRING_NOTIFICATION);
            int i = 0;
            i = printExpiredItemsAndGetLastIndex(expiringList, i);
            printExpiringItems(expiringList, i);
            printMessage(NOTIFICATION_CLOSING_LINE + SEPARATOR_LINE);
        }
    }

    /**
     * Print out items that are expiring.
     *
     * @param expiringList an ArrayList of items that are expiring within a week or expired.
     * @param i            variable to traverse the list.
     */
    private void printExpiringItems(ArrayList<Item> expiringList, int i) {
        if (expiringList.size() - i > 0) {
            int index = 1;
            printMessage(NOTIFICATION_SEPARATOR + EXPIRING_MESSAGE);
            while (i < expiringList.size()) {
                String beforeItem = FIVE_SPACE_INDENTATION + index + ". ";
                printMessage(beforeItem + expiringList.get(i));
                index++;
                i++;
            }
        }
    }

    /**
     * Print out items that are expired from a list of expiring items and return the last index it traverse.
     *
     * @param expiringList an ArrayList of items that are expiring within a week or expired.
     * @param i            variable to traverse the list.
     * @return int value of i of the last item traversed.
     */
    private int printExpiredItemsAndGetLastIndex(ArrayList<Item> expiringList, int i) {
        if (expiringList.get(i).isExpired()) {
            printMessage(EXPIRED_MESSAGE);
            int index = 1;
            while ((expiringList.size() - i > 0) && expiringList.get(i).isExpired()) {
                String beforeItem = FIVE_SPACE_INDENTATION + index + ". ";
                printMessage(beforeItem + expiringList.get(i));
                index++;
                i++;
            }
        }
        return i;
    }

    /**
     * Return true if notification is printed more than 4 hours ago.
     *
     * @return Return true if time difference is more than 4 hours.
     */
    private boolean isMoreThan4Hours() {
        return dateAndTime.compareTo(LocalDateTime.now().minusHours(4)) <= 0;
    }

    /**
     * Indicate whether notification is on or off.
     *
     * @return Return true if notification is on.
     */
    public static boolean isNotificationOn() {
        return isNotificationOn;
    }

    /**
     * Sets the date and time when the notification is printed.
     *
     * @param dateAndTime date and time since notification is printed.
     */
    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    /**
     * Set to toggle notification on or off.
     *
     * @param notificationOn A boolean value passed to turn on or off notification.
     */
    public static void setNotificationStatus(boolean notificationOn) {
        isNotificationOn = notificationOn;
    }

    /**
     * Converted string format for saving into log text file.
     *
     * @return String containing date, time and status.
     */
    public String toString() {
        String dateTimeAndStatus = dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " | " + (isNotificationOn ? "yes" : "no");
        assert dateTimeAndStatus.contains(" | ");
        return dateTimeAndStatus;
    }
}
