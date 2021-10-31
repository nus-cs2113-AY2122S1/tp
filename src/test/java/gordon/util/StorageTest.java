package gordon.util;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

public class StorageTest {
    @TempDir
    static Path tempDir;

    @Test
    public void fileWriteTest() {
        Cookbook saveCookbook = new Cookbook();

        Recipe r = new Recipe("Coffee");
        r.addIngredient("Coffee beans");
        r.addIngredient("Water");
        r.addIngredient("Sugar");
        r.addStep("Boil water");
        r.addStep("Grind beans");
        r.addStep("Pour water over grounds");
        r.setCalories(10);

        try {
            saveCookbook.addRecipe(r);
        } catch (GordonException e) {
            e.printStackTrace();
        }

        Storage storage = new Storage(tempDir.toString(), saveCookbook);
        storage.saveCookbook(saveCookbook);
        storage.deleteSaveFile(tempDir.toString());
    }
}
