package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    @Test
    void mealSummary() {
        ArrayList<String> mealTest = new ArrayList<String>();
        mealTest.add("pasta /c 100 /d 06/11/2021 /t 23:59");
        mealTest.add("risotto /c 200 /d 06/11/2021 /t 23:59");
        mealTest.add("linguini /c 300 /d 06/11/2021 /t 23:59");

        int totalCalories = 0;
        int i = 1;
        for (String m : mealTest) {
            if (m.contains("[")) {
                String[] descriptor = m.substring(1).split(" /c ");
                String[] calorie = descriptor[1].split(" /d ");
                String description = descriptor[0];
                System.out.println(i + ". " + description);
                String calorieIndiv = calorie[0];
                totalCalories += Integer.parseInt(calorieIndiv);
                i++;
            } else {
                String[] descriptor = m.split(" /c ");
                String[] calorie = descriptor[1].split(" /d ");
                String description = descriptor[0];
                System.out.println(i + ". " + description);
                String calorieIndiv = calorie[0];
                totalCalories += Integer.parseInt(calorieIndiv);
                i++;
            }
        }
        System.out.println(System.lineSeparator() + "Total number of meals = " + (i - 1));
        System.out.println("Total calories = " + totalCalories);
        assertEquals(600, totalCalories);
        assertEquals(3, mealTest.size());
    }



    @Test
    void fluidSummary() {
        ArrayList<String> fluidTest = new ArrayList<String>();
        fluidTest.add("cola /c 100 /v 100 /d 06/11/2021 /t 23:59");
        fluidTest.add("water /c 200 /v 200 /d 06/11/2021 /t 23:59");
        fluidTest.add("sprite /c 300 /v 300 /d 06/11/2021 /t 23:59");

        int totalCalories = 0;
        int totalVolume = 0;
        int i = 1;

        for (String f : fluidTest) {
            if (f.contains("[")) {
                String[] descriptor = f.substring(1).split(" /c ");
                String[] calorie = descriptor[1].split(" /v ");
                String[] volumeSplitter = calorie[1].split(" /d ");
                String description = descriptor[0];
                System.out.println(i + ". " + description);
                String calorieIndiv = calorie[0];
                String volumeIndiv = volumeSplitter[0];
                totalCalories += Integer.parseInt(calorieIndiv);
                totalVolume += Integer.parseInt((volumeIndiv));
                i++;
            } else {
                String[] descriptor = f.split(" /c ");
                String[] calorie = descriptor[1].split(" /v ");
                String[] volumeSplitter = calorie[1].split(" /d ");
                String description = descriptor[0];
                System.out.println(i + ". " + description);
                String calorieIndiv = calorie[0];
                String volumeIndiv = volumeSplitter[0];
                totalCalories += Integer.parseInt(calorieIndiv);
                totalVolume += Integer.parseInt((volumeIndiv));
                i++;
            }
        }
        if (totalVolume > 0) {
            System.out.println("Total volume consumed = " + totalVolume);
        } else {
            System.out.println(System.lineSeparator() + "Total variety of drinks = " + (i - 1));
        }
        System.out.println("Total calories = " + totalCalories);
        assertEquals(600, totalCalories);
        assertEquals(3,fluidTest.size());
        assertEquals(600,totalVolume);
    }

    @Test
    void workoutSummary() {
        ArrayList<String> workoutTest = new ArrayList<String>();
        workoutTest.add("pull ups /c 100 /d 06/11/2021 /t 23:59");
        workoutTest.add("run /c 200 /d 06/11/2021 /t 23:59");
        workoutTest.add("fight /c 300 /d 06/11/2021 /t 23:59");

        int totalCalories = 0;
        int i = 1;
        for (String w : workoutTest) {
            if (w.contains("[")) {
                String[] descriptor = w.substring(1).split(" /c ");
                String[] calorie = descriptor[1].split(" /d ");
                String description = descriptor[0];
                System.out.println(i + ". " + description);
                String calorieIndiv = calorie[0];
                totalCalories += Integer.parseInt(calorieIndiv);
                i++;
            } else {
                String[] descriptor = w.split(" /c ");
                String[] calorie = descriptor[1].split(" /d ");
                String description = descriptor[0];
                System.out.println(i + ". " + description);
                String calorieIndiv = calorie[0];
                totalCalories += Integer.parseInt(calorieIndiv);
                i++;
            }
        }
        System.out.println(System.lineSeparator() + "Completed Workouts = " + (i - 1));
        System.out.println("Total calories burned = " + totalCalories);
        assertEquals(600, totalCalories);
        assertEquals(3, workoutTest.size());

    }
}