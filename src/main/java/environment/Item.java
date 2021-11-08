package environment;

public class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void useAbility() {
        System.out.println("Nothing Happened");
    }
}
