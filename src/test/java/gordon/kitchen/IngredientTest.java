package gordon.kitchen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IngredientTest {
    @Test
    public void getDescriptionTest() {
        Ingredient i = new Ingredient("Salt");
        assertEquals("Salt", i.getDescription());
    }

    @Test
    public void getDescriptionTest_singleStringInput_noOutput() {
        Ingredient i = new Ingredient("Water");
        assertEquals("Water", i.getDescription());
    }
}