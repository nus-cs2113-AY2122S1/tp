package gordon.kitchen;

import gordon.exception.GordonException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class RecipeTest {
    @Test
    public void testToString() {
        Recipe r = new Recipe("Coffee");
        r.addIngredient("Coffee beans");
        r.addIngredient("Water");
        r.addStep("Boil water");
        r.addStep("Grind beans");
        r.addStep("Pour water over grounds");
        r.setCalories(10);
        String expected = "Coffee" + System.lineSeparator()
                + "Number of calories: 10" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Coffee beans" + System.lineSeparator()
                + "2. Water" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Boil water" + System.lineSeparator()
                + "2. Grind beans" + System.lineSeparator()
                + "3. Pour water over grounds" + System.lineSeparator();
        assertEquals(expected, r.toString());
    }

    @Test
    public void testGetName() {
        Recipe r = new Recipe("Coffee");
        assertEquals("Coffee", r.name);
    }

    @Test
    public void testAdd() {
        try {
            Recipe r = new Recipe("Coffee");
            r.addIngredient("Coffee beans");
            r.addIngredient("Water");
            r.addStep("Boil water");
            r.addStep("Grind beans");
            r.addStep("Pour water over grounds");
            r.addIngredient("Test", 12);
            fail();
        } catch (GordonException g) {
            System.out.println(g.getMessage());
        }

        try {
            Recipe r = new Recipe("Coffee");
            r.addIngredient("Coffee beans");
            r.addIngredient("Water");
            r.addStep("Boil water");
            r.addStep("Grind beans");
            r.addStep("Pour water over grounds");
            r.addStep("Test", 12);
            fail();
        } catch (GordonException g) {
            System.out.println(g.getMessage());
        }
    }

    @Test
    public void testRemove() {
        try {
            Recipe r = new Recipe("Coffee");
            r.addIngredient("Coffee beans");
            r.addIngredient("Water");
            r.addStep("Boil water");
            r.addStep("Grind beans");
            r.addStep("Pour water over grounds");
            r.removeStep(1);
            String expected = "Coffee" + System.lineSeparator()
                    + "Number of calories: -1" + System.lineSeparator()
                    + "Ingredients needed: " + System.lineSeparator()
                    + "1. Coffee beans" + System.lineSeparator()
                    + "2. Water" + System.lineSeparator()
                    + "Method: " + System.lineSeparator()
                    + "1. Boil water" + System.lineSeparator()
                    + "2. Pour water over grounds" + System.lineSeparator();
            assertEquals(expected, r.toString());
            r.removeStep(3098);
            fail();
        } catch (GordonException g) {
            System.out.println(g.getMessage());
        }
    }
}
