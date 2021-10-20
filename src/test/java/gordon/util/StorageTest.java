package gordon.util;

import gordon.exception.GordonException;
import gordon.kitchen.Cookbook;
import gordon.kitchen.Recipe;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @TempDir
    static Path tempDir;

    @Test
    public void fileReadWriteTest1() {
        Cookbook saveCookbook = new Cookbook();
        Recipe r = new Recipe("Coffee");
        r.addIngredient("Coffee beans");
        r.addIngredient("Water");
        r.addIngredient("Sugar");
        r.addStep("Boil water");
        r.addStep("Grind beans");
        r.addStep("Pour water over grounds");
        r.setCalories(10);
    }
}
