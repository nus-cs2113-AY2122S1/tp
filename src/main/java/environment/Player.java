package environment;

import java.util.HashMap;

public class Player {
    private final String name;
    private final HashMap<String, Item> items;

    public Player(String name) {
        this.name = name;
        this.items = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public String getItems() {
        StringBuilder toReturn = new StringBuilder("Items in backpack");
        int count = 1;
        for (String itemName : this.items.keySet()) {
            toReturn.append("\n")
                    .append(count)
                    .append(". ")
                    .append(itemName);
            count += 1;
        }
        return toReturn.toString();
    }

    public void pickUp(Item item) {
        this.items.put(item.getName(), item);
    }

    public void use(String itemName) {
        Item toUse = this.items.get(itemName);
        this.items.remove(itemName);
        toUse.useAbility();
    }

    public void drop(String itemName) {
        this.items.remove(itemName);
    }
}
