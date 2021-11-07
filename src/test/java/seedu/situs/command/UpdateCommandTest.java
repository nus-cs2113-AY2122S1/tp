package seedu.situs.command;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

//class UpdateCommandTest {

//    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//    @Test
//    public void updateCommandTest_dukeExceptionThrown() throws DukeException, IOException {
//        LocalDate expiryDate1 = LocalDate.parse("21/11/2021", DATE_FORMATTER);
//        LocalDate expiryDate2 = LocalDate.parse("01/12/2021", DATE_FORMATTER);
//        LocalDate expiryDate3 = LocalDate.parse("02/01/2022", DATE_FORMATTER);
//        LocalDate expiryDate4 = LocalDate.parse("02/03/2022", DATE_FORMATTER);
//
//        Ingredient ingredient1 = new Ingredient("Carrot", 300, expiryDate1);
//        Ingredient ingredient2 = new Ingredient("Tomato", 200, expiryDate2);
//        Ingredient ingredient3 = new Ingredient("Carrot", 1.5, expiryDate3);
//
//        IngredientGroup.getInstance().add(ingredient1);
//        IngredientGroup.getInstance().add(ingredient2);
//        IngredientGroup.getInstance().add(ingredient3);
//
//        Ingredient updatedIngredient = new Ingredient("Carrot", 450.0, expiryDate4);
//        String resultMsg = new UpdateCommand(updatedIngredient).run();
//        String expected = "Got it. This ingredient has been updated:\n" + "\t" + updatedIngredient.toString();
//
//        assertEquals(expected, resultMsg);
//    }
//}