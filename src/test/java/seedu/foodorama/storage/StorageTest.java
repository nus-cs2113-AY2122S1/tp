package seedu.foodorama.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.foodorama.DishList;
import seedu.foodorama.Ingredient;
import seedu.foodorama.IngredientList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    @Test
    void write() {
        DishList.add("Nasi Lemak");
        DishList.add("Nasi Goreng");
        DishList.add("Mee Goreng");
        Storage.write("dish");
        IngredientList.ingredientList.add(new Ingredient("Noodles", 3.44));
        IngredientList.ingredientList.add(new Ingredient("Egg", 1.21));
        Storage.write("ingredient");
    }

    @Test
    void load() {
        write();
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
        assertEquals(0, DishList.dishList.size());
        assertEquals(0, IngredientList.ingredientList.size());
        Storage.load();
        DishList.list();
        IngredientList.list();
        assertEquals(3, DishList.dishList.size());
        assertEquals(2, IngredientList.ingredientList.size());
    }

    @AfterEach
    void resetLists() {
        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
        Storage.write("ingredient");
        Storage.write("dish");
    }
}