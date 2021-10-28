package storage;
import java.util.Map;

import static java.util.Map.entry;

public class DataLocation {
    public static final String rootDirectory = System.getProperty("user.dir");
    public static final String dataLocation = "data";
    public static final Map<String, String> dataFilenames = Map.ofEntries(
            entry("income", "income.txt"),
            entry("budget", "budget.txt"),
            entry("expense", "expense.txt")
    );
}
