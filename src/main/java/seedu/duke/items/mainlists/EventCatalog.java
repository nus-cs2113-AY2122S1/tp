package seedu.duke.items.mainlists;

import seedu.duke.items.Event;
import seedu.duke.items.Item;

import java.util.ArrayList;

public class EventCatalog extends ArrayList<Event> {

    private static EventCatalog eventCatalog = null;

    private EventCatalog() {
    }

    public static EventCatalog getInstance() {
        if (eventCatalog == null) {
            eventCatalog = new EventCatalog();
        }
        return eventCatalog;
    }

    private static <T> void swap(int i, ArrayList<T> list) {
        T t;
        t = list.get(i);
        list.set(i, list.get(i + 1));
        list.set(i + 1, t);
    }

    public static <T extends Item> void bubbleSortItems(ArrayList<T> list) {
        for (int j = 0; j < list.size() - 1; j++) {
            for (int i = 0; i < list.size() - j - 1; i++) {
                if (list.get(i + 1).getDateTime().isBefore(list.get(i).getDateTime())) {
                    swap(i, list);
                }
            }
        }
    }

    public void sortCatalog() {
        bubbleSortItems(eventCatalog);
        for (Event event : eventCatalog) {
            bubbleSortItems(event.getTaskList());
        }
    }
}
