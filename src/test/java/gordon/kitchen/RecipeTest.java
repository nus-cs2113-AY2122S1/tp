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
                + "Calories (kcal): " + System.lineSeparator()
                + "10" + System.lineSeparator()
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

    @Test
    public void addIngredient_newIngredient_success() {
        Recipe r = new Recipe("Chicken rice");
        r.addIngredient("Chicken");
        r.addIngredient("Rice");
        r.addStep("Cook rice");
        r.addStep("Cook chicken");
        r.addStep("Mix");
        r.addIngredient("Salt");
        String expected = "Chicken rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "3. Salt" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook rice" + System.lineSeparator()
                + "2. Cook chicken" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator();
        assertEquals(expected, r.toString());
    }
}
