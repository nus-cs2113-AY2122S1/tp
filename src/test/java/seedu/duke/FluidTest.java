package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.fluid.DeleteEmptyFluidListException;
import seedu.duke.exceptions.fluid.FluidExceptions;
import seedu.duke.exceptions.fluid.NoDeleteFluidIndexException;
import seedu.duke.exceptions.fluid.NoFluidToDelete;
import seedu.duke.exceptions.fluid.NoVolumeEntered;
import seedu.duke.exceptions.foodbank.EmptyFoodDescription;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.foodbank.NoFoodFoundException;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author pragyan01
class FluidTest {

    @Test
    void generateFluidParameters() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /v 100 /d 12/12/2021 /t 10:30";
        assertDoesNotThrow(() -> fluid.generateFluidParameters(input));
    }

    @Test
    void generateFluidParameters2() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /v 100";
        assertDoesNotThrow(() -> fluid.generateFluidParameters(input));
    }

    @Test
    void generateFluidParameters3() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /v 100";
        assertDoesNotThrow(() -> fluid.generateFluidParameters(input));
    }

    @Test
    void generateFluidParameters4() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40";
        assertDoesNotThrow(() -> fluid.generateFluidParameters(input));
    }

    @Test
    void generateFluidParameters5() {
        Fluid fluid = new Fluid();
        String input = null;
        assertThrows(NullPointerException.class, () -> fluid.generateFluidParameters(input));
    }

    @Test
    void addFluid() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /v 100 /d 12/12/2021 /t 10:30";
        assertDoesNotThrow(() -> fluid.addFluid(input));
    }

    @Test
    void addFluid2() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /v 100 /d 12/12/2021";
        assertDoesNotThrow(() -> fluid.addFluid(input));
    }

    @Test
    void addFluid3() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /v 100 /t 10:30";
        assertDoesNotThrow(() -> fluid.addFluid(input));
    }

    @Test
    void addFluid4() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /v 100 /d 2021-3-9 /t 10:30";
        assertThrows(DateTimeParseException.class, () -> fluid.addFluid(input));
    }


    @Test
    void addFluid5() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /v 100 /d 12/12/2021 /t 2359pm";
        assertThrows(DateTimeParseException.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid6() {
        Fluid fluid = new Fluid();
        String input = "coke /c hello /v 100 /d 12/12/2021 /t 10:30";
        assertThrows(NumberFormatException.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid7() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /v 100ml /d 12/12/2021 /t 10:30";
        assertThrows(NumberFormatException.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid8() {
        Fluid fluid = new Fluid();
        String input = "/c 40 /v 100 /d 12/12/2021 /t 10:30";
        assertThrows(EmptyFoodDescription.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid9() {
        Fluid fluid = new Fluid();
        String input = "coke /c 40 /d 12/12/2021 /t 10:30";
        assertThrows(NoVolumeEntered.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid10() {
        Fluid fluid = new Fluid();
        new FoodBank();
        String input = "coke /v 100 /d 12/12/2021 /t 10:30";
        assertThrows(NoFoodFoundException.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid11() {
        Fluid fluid = new Fluid();
        new FoodBank();
        String input = "";
        assertThrows(NoFoodFoundException.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid12() {
        Fluid fluid = new Fluid();
        new FoodBank();
        String input = " ";
        assertThrows(NoFoodFoundException.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid13() {
        Fluid fluid = new Fluid();
        new FoodBank();
        String input = "coke";
        assertThrows(NoFoodFoundException.class, () -> fluid.addFluid(input));
    }

    @Test
    void deleteFluid() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = "1";
        assertDoesNotThrow(() -> fluid.deleteFluid(input));
    }

    @Test
    void deleteFluid2() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = "one";
        assertThrows(NumberFormatException.class, () -> fluid.deleteFluid(input));
    }

    @Test
    void deleteFluid3() throws FluidExceptions, FoodBankException {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = null;
        assertThrows(NoDeleteFluidIndexException.class, () -> fluid.deleteFluid(input));
    }

    @Test
    void deleteFluid4() throws FluidExceptions, FoodBankException {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = "";
        assertThrows(NumberFormatException.class, () -> fluid.deleteFluid(input));
    }

    @Test
    void deleteFluid5() {
        Fluid fluid = new Fluid();
        String input = "1";
        assertThrows(DeleteEmptyFluidListException.class, () -> fluid.deleteFluid(input));
    }

    @Test
    void deleteFluid6() throws FluidExceptions, FoodBankException {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = "5";
        assertThrows(NoFluidToDelete.class, () -> fluid.deleteFluid(input));
    }

    @Test
    void deleteFluid7() throws FluidExceptions, FoodBankException {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = "-3";
        assertThrows(NoFluidToDelete.class, () -> fluid.deleteFluid(input));
    }

    @Test
    void deleteFluid8() throws FluidExceptions, FoodBankException {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = " ";
        assertThrows(NumberFormatException.class, () -> fluid.deleteFluid(input));
    }

    @Test
    void listFluid() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        assertDoesNotThrow(() -> fluid.listFluids("12/12/2021"));
    }

    @Test
    void listFluid2() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        fluid.addFluid("water /c 50 /v 200 /d 13/12/2021 /t 12:30");
        assertDoesNotThrow(() -> fluid.listFluids("all"));
    }

    @Test
    void listFluid3() {
        Fluid fluid = new Fluid();
        assertDoesNotThrow(() -> fluid.listFluids("all"));
    }

    @Test
    void listFluid4() {
        Fluid fluid = new Fluid();
        assertDoesNotThrow(() -> fluid.listFluids("12/12/2021"));
    }

    @Test
    void listFluid5() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 21/12/2021 /t 10:30");
        assertDoesNotThrow(() -> fluid.listFluids("10/12/2021"));
    }

    @Test
    void listFluid6() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 21/12/2021 /t 10:30");
        assertDoesNotThrow(() -> fluid.listFluids("all"));
    }

    @Test
    void getCalories() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100");
        assertDoesNotThrow(() -> fluid.getCalories("all"));
    }

    @Test
    void getCalories2() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        assertDoesNotThrow(() -> fluid.getCalories("12/12/2021"));
    }

    @Test
    void getCalories3() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        assertDoesNotThrow(() -> fluid.getCalories("23/12/2021"));
    }

    @Test
    void getCalories4() {
        Fluid fluid = new Fluid();
        assertDoesNotThrow(() -> fluid.getCalories("23/12/2021"));
    }

    @Test
    void getVolume() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100");
        assertDoesNotThrow(() -> fluid.getVolume("all"));
    }

    @Test
    void getVolume2() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        assertDoesNotThrow(() -> fluid.getVolume("12/12/2021"));
    }

    @Test
    void getVolume3() throws FoodBankException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        assertDoesNotThrow(() -> fluid.getVolume("23/12/2021"));
    }

    @Test
    void getVolume4() {
        Fluid fluid = new Fluid();
        assertDoesNotThrow(() -> fluid.getVolume("23/12/2021"));
    }
}
