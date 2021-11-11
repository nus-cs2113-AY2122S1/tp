package gordon.kitchen;

import gordon.exception.GordonException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CookbookTest {
    @Test
    public void testAdd() {
        try {
            Recipe r = new Recipe("Coffee");
            r.addIngredient("Coffee beans");
            r.addIngredient("Water");
            r.addIngredient("Sugar");
            r.addStep("Boil water");
            r.addStep("Grind beans");
            r.addStep("Pour water over grounds");
            r.addStep("Serve in cup");
            r.setCalories(10);
            Cookbook c = new Cookbook();
            c.addRecipe(r);
            Recipe r3 = new Recipe("Milo");
            r3.addIngredient("Milo powder");
            r3.addIngredient("Hot water");
            r3.addStep("Put milo powder into cup");
            r3.addStep("Boil Water");
            r3.addStep("Add hot water to cup");
            r3.addStep("Stir well");
            r3.addStep("Drink");
            r3.setCalories(200);
            c.addRecipe(r3);
            assertEquals("1. Coffee" + System.lineSeparator() + "2. Milo" + System.lineSeparator(), c.toString());
        } catch (GordonException g) {
            g.printStackTrace();
        }
    }

    @Test
    public void testRemove() {
        try {
            Cookbook c = new Cookbook();
            c.removeRecipe(12);
            fail();
        } catch (GordonException g) {
            System.out.println(g.getMessage());
        }

        try {
            Recipe r1 = new Recipe("Coffee");
            r1.addIngredient("Coffee beans");
            r1.addIngredient("Water");
            r1.addStep("Boil water");
            r1.addStep("Grind beans");
            r1.addStep("Pour water over grounds");
            r1.setCalories(10);
            Cookbook c = new Cookbook();
            c.addRecipe(r1);
            Recipe r2 = new Recipe("Tea");
            r2.addIngredient("Tea leaves");
            r2.addIngredient("Water");
            r2.addStep("Boil water");
            r2.addStep("Pour water over leaves");
            r2.setCalories(10);
            c.addRecipe(r2);
            c.removeRecipe(1);
            assertEquals("1. Coffee" + System.lineSeparator(), c.toString());
        } catch (GordonException g) {
            g.printStackTrace();
        }
    }
}