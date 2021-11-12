# Developer Guide

## Introduction
CLI.ckFit is a desktop-based fitness app which can be accessed easily via Command Line Interface (CLI). CLI.ckFit allows you to input and track
your calories, weight, foods, and workouts throughout the day. It also allows you to save your data and view it whenever
you wish to. It comes with a BMI and recommended caloric intake calculator which can give you an idea of your current fitness
level.

## Acknowledgements
The UML Diagrams were generated with the help of: [PlantUML](https://plantuml.com/)

Written with reference to:
* [https://se-education.org/addressbook-level2/DeveloperGuide.html](https://se-education.org/addressbook-level2/DeveloperGuide.html)
* [https://se-education.org/addressbook-level3/DeveloperGuide.html](https://se-education.org/addressbook-level3/DeveloperGuide.html)

## Table of Contents
- [**Design & Implementation**](#design--implementation)
  - [**User Interface**](#user-interface-ui)
  - [**Meal Tracker**](#mealtracker)
  - [**Fluid Tracker**](#fluidtracker)
  - [**Weight Tracker**](#weighttracker)
  - [**Schedule Tracker**](#scheduletracker)
- [**Appendix A: Product Scope**](#appendix-a-product-scope)
- [**Appendix B: User Stories**](#appendix-b-user-stories)
- [**Appendix C: Non-Functional Requirements**](#appendix-c-non-functional-requirements)
- [**Appendix D: Glossary**](#appendix-d-glossary)
- [**Appendix E: Instructions for manual testing**](#appendix-e-instructions-for-manual-testing)

## Design & implementation

### User Interface (Ui)

The proposed Ui class has the class level attributes of sex, weight, height, age and activityLevel. It consists of 3 
methods that in turn reference from different classes.

It implements the following operations:

* welcomeMessage()
* getInfo()
* memoryStartup()

These operations will be illustrated through UML diagrams.

#### Printing the welcome message

The user launches the CLI for the first time. The welcomeMessage() is called first and prints out the messages imported from Clickfitmessages class.

#### Getting BMI and recommended daily caloric intake
![Imgur](https://i.imgur.com/Pr4fVXf.png)

The user is then greeted with a prompt that asks whether he or she wished to enter the calculator function of CLI.ckFit. 
The calculator takes in the following inputs as shown in the UML diagram through the instantiating of a new calculator
object that takes in the class-level attributes of Ui to calculate the user's BMI in getBmi and the user's recommended 
daily caloric intake through getIdealCalories().

#### Memory startup method
![Imgur](https://i.imgur.com/cRw6Bp3.png)

The user is greeted with a prompt that asks whether he wishes to load his saved date. Pressing the "enter" keystroke will 
load up the previous session's data. Typing in "y" will cause the previous session's data to be wiped, meaning the
contents of the storage text files will all be emptied, so the user can start afresh.

#### Get summary of all info stored in text files

The user is next greeted with a second prompt that asks whether he or she wishes to get a summary of all meals, fluids 
and workouts he has eaten or completed.
memoryStartup() is used to return a Boolean value. If it returns ture, it enters an if-loop in Duke class which calls 
the method printLoadedLists() in Storage class. In storage class, the text files(storage) of Meal, Fluid and Workout 
classes are first converted
to arrayLists, which are converted to arrayLists, which are then referenced by PrintLoadedLists() to be formatted and
printed as a summary of all stored information iu the text files.

### MealTracker

#### Meal: Listing Meals
![](https://user-images.githubusercontent.com/69350459/138880611-c82f4574-037f-4b64-9631-90d914f71701.png)

The UML sequence diagram above shows what happens when the user attempts to list their meals. If the user inputs
"list meals all", the if block's condition will be satisfied, and the method "listAllMeals()" will be called. If 
the user inputs "list meals DATE" where date is an arbitrary date, the method "listMealsByDate(userDate)" will be 
called, where userDate is the date specified by the user. Then, the static method 
"printMealListTotals(mealNumber, totalCalories)", from the ClickfitMessages class will be called, 
printing out a message to tell the user the total meal calories and number of meals.

#### Class Diagram
![image](https://user-images.githubusercontent.com/69350459/140748412-948edbb6-313f-4658-a373-25051414d9ae.png)

Above are the UML class level diagrams of `Meal`, and `Tracker`. As seen in
the diagram, the `Meal` class inherits from the `Tracker` class. This class diagram has been simplified for better readability.

### FoodBank
#### FoodBank: Adding custom meal
![image](https://user-images.githubusercontent.com/69350459/140748085-b258f953-5d76-4ce4-9a4f-974d410ff22b.png)

The UML sequence diagram above shows what happens when the user attempts to add a meal to their library. 
If the user inputs "library addmeal", the first if block's condition will be satisfied, and the exception 
"EmptyLibraryDescription()" will be thrown. Then the method "generateParameters(inputArguments)" is called, which sets 
the class level attributes. Then, a for loop will be entered until the entire meal arraylist has been iterated through. 
If the user has input a meal that already exists within the meal arraylist, the exception "DuplicateFood()" will be 
thrown. Lastly, after exiting the for loop, the static method "printAddedLibraryMeal(description,calories,totalMeals)", 
from the ClickfitMessages class will be called, printing out a message to tell the user that their meal has been added 
to the library.

### FluidTracker
#### Class diagram
![](https://user-images.githubusercontent.com/69461398/140756868-222576b6-87b1-4c10-9b7a-deac1e5c5643.png)

Above are the UML class level diagrams of `Fluid`, `FluidExceptions` and `Tracker`. As seen in the diagram, the `Fluid` class is dependent on the `FluidExceptions` and the `Fluid`class inherits from the `Tracker` class. This class diagram has been simplified for better readability.

#### Adding fluid sequence diagram
![](https://user-images.githubusercontent.com/69446495/140606905-45c62251-ae7e-43f5-a5aa-b37d7a12ec1a.png)

The UML sequence diagram above shows what happens when the input command is recognised as `add fluid`.
`generateFluidParameters` method in the `Fluid class` is called upon which updates variables relevent to a fluid, such its `description`, `calories`, `volume`, `date` and `time`. An `if` block checks for possible errors in user input, which are caught by their respective exceptions. Otherwise, variables are then concatenated together as a string called `inputArguments` and added to the `fluidArray` list to be saved.

#### Deleting fluid sequence diagram
![](https://user-images.githubusercontent.com/69446495/140607494-11daab2e-4c64-482c-80e9-4bf435ef554d.png)

The UML sequence diagram above shows what happens when the input command is recognised as `delete fluid`.
`generateFluidParameters` method in the `Fluid class` is called upon which updates variables relevent to a fluid, such its `description`, `calories`, `volume`, `date` and `time`. `taskNumber`, which refers to the respective fluid's entry index is parsed from user input. An `if` block checks for possible errors in user input, which are caught by their respective exceptions. Otherwise, `fluidArray.remove(taskNumber)` is called, which deletes the relevant entry from the `fluidArray` list.

#### Get total calories for specific date fluid sequence diagram
![](https://user-images.githubusercontent.com/69446495/140621687-7f221499-f29b-4003-a0f5-90d26ecf7f16.png)

The UML sequence diagram above shows what happens when the input command is recognised as `list calories`. For all fluid entries stored in `fluidArray`, if the entries contain the date as provided, `generateFluidParameters` method in the `Fluid class` is called upon which updates variables relevent to a fluid, such its `description`, `calories`, `volume`, `date` and `time`. The `calorie` parameter for each fluid entry in the `fluidArray` is added up, which returns `calorieTotal` at the end of the method's lifeline.


### WeightTracker

#### Class diagram
![image](https://user-images.githubusercontent.com/69350459/140748525-1ca74ba6-f530-4fd1-a6fe-c2952e184e78.png)

Above are the UML class level diagrams of `WeightTracker`, `ClickfitMessages`, `Tracker` and relevant exception classes. 
As seen in the diagram, the `WeightTracker` class is dependent on the `ClickfitMessages` class and inherits from the 
`Tracker` class. The `WeightTracker` class also throws 4 exceptions which inherit from the `WeightException` class to
print the corresponding error message for each exception.
This class diagram has been simplified for better readability.

#### Adding weight
![WeightTracker_add_sequence](https://user-images.githubusercontent.com/69446729/140641320-d243b7a8-a75c-4960-a731-6aeed02fd7ea.png)

The UML sequence diagram above shows what happens when the input command is recognised as `add weight` which
calls `AddWeights(...)` method in the `WeightTracker` class. The `WeightTracker` class calls the 
`generateWeightParameters` function which updates the `weight` and `date` variables. Then the `weight` and 
`date` variables are added to weight array list and `printAddWeightResponse` is called from the `ClickfitMessages` 
class for both the typical input and missing date cases. However, when an exception is encountered, the 
`WeightTracker` class will throw `AddWeightException()` instead.

### ScheduleTracker

#### Class diagram

![image](https://user-images.githubusercontent.com/69350459/140748620-e191dccb-d8ef-43fa-8b4f-48def5b0d84a.png)

Above are the UML class level diagrams of `ScheduleTracker` and `ScheduledWorkout`. As seen in the diagram, one 
`ScheduleTracker` object keeps track/is linked to **any** number of `ScheduledWorkout` objects, thus have a 
multiplicity of `0..*`. This association forms through a private attribute `scheduledWorkouts` which is of type 
`ArrayList<ScheduledWorkout>`. 

Each `ScheduledWorkout` object also has a private attribute called `activities` which is 
of type `ArrayList<WorkoutActivity>` where `activities` keeps track of **any** number of `WorkoutActivity` objects.
Do note that **not all** class attributes and methods are present in the diagram for 
the sake of better comprehensibility.

#### Adding scheduled workout
![ScheduleTracker_add_diag](https://user-images.githubusercontent.com/69461398/140618156-1622efcb-7c48-48a6-b611-788138ec955f.png)

The UML sequence diagram above shows what happens when the method `addScheduledWorkout(...)` is called. 
Parameters are generated and a `ScheduledWorkout` object is added into the `scheduledWorkouts` ArrayList.
There are a few method calls which are omitted from this diagram. The main focus is on `cleanUpScheduleList()` where
the rescheduling or deleting of overdue workouts take place. This process as represented by the ref frame 
`updateOrDeleteScheduledWorkout`, will be elaborated on in the next section.

#### Updating and deleting of overdue workouts
![ScheduleTracker_update_diag](https://user-images.githubusercontent.com/69461398/140618246-1fea0a8f-46b2-499a-a05f-cf85f38133d5.png)


If there are any overdue workouts, a `loop` continues until there are no more overdue workouts in the schedule list.
Essentially, depending on whether the overdue workout detected is recurring, the workout is deleted or rescheduled 
appropriately.

This process is done to ensure a correctly sorted and cleaned up list is always output to the user. 
This also ensures the `scheduledWorkouts` ArrayList remains free of overdue workouts.

## Appendix A: Product scope
### Target user profile
Specifically made for people getting into serious fitness routines such as athletes but helpful for general populace.

### Value proposition

CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input and track
your calories, weight, foods, and workouts throughout the day. It also allows you to save your data and view it whenever
you wish to. It also comes with a BMI and recommended caloric intake calculator which can give you an idea of 
your current fitness level. All these features come together and complement each other but can also be used 
independently of one another. For instance, CLI.ckFit can simply be used as a stand alone Weight tracker if the user
sees fit.

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|fitness enthusiast|record my fitness activities|plan my extensive workout schedule|
|v1.0|user|update how many calories I have burned through my workouts|keep track of my daily calories|
|v1.0|athlete|record my weight|keep track of and maintain a competitive weight|
|v1.0|forgetful user|input a "help" command|know the standard ways of inputting my commands|
|v2.0|user|have the app remember my user data|access my data anytime| 
|v2.0|user|have scheduled workouts in the list to be sorted by the nearest dates|easily keep track of upcoming workouts|
|v2.0|frequent gym goer| be able to schedule recurring weekly workouts| have a routine schedule without having to reschedule the same workout every week|
|v2.0|serious athlete|breakdown my workout into smaller activities|track things like sets, reps and distance|
|v2.0|beginner|check my fluid summary| budget my remaining calories|
|v2.0|beginner|check my meal summary| budget my remaining calories|
|v2.0|beginner|check my workout summary| know whether i have burned enough calories|
|v2.0|beginner|check my weight summary| check my weight loss or gain progress|
|v2.0|health conscious individual|know my recommended caloric intake|know how much food to consume in one day|
|v2.0|health conscious individual| know my BMI indicator|know whether i need to lose weight|
|v2.0| lazy user| have date/time automatically input corresponding to system date/time if i leave it empty| save time and effort for other stuff after my meal or drinks|

## Appendix C: Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed (Java has backward compatibility).
2. A user with above average typing speed in English text (i.e. not coding/system admin commands) should 
   be able to accomplish most of the tasks faster using commands than using the mouse.

## Appendix D: Glossary

* *recurring workouts* - workouts that are scheduled ***weekly***
* *overdue workouts* - workouts whose dates are before the current date

## Appendix E: Instructions for manual testing

### Launching CLI.ckFit
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `CLI.ckFit` from [here](https://github.com/AY2122S1-CS2113T-F14-3/tp/releases/tag/v2.1).
3. Go to the folder you saved the `CLIckFit.jar` file and note the absolute file path.
4. If you are using Windows, open up a Command prompt terminal cmd.exe or powershell.exe and for
   Mac and Linux users, do the same with the terminal of your respective systems.
5. Navigate to the folder where the `CLIckFit.jar` file is stored.
6. Execute `java -jar CLIckFit.jar` in the terminal, and the application will start running.

**Expected**: CLI.ckFit will launch and a welcome message will be displayed.

### Saving Data
Data is saved in "Food.txt", "FoodBank.txt", "Weight.txt", "Workout.txt" and "Schedule.txt" in the same folder
as your `CLIckFit.jar`.
   * **Test Case**:   
      1. Run `CLIckFit.jar`.
         * **Note**: Ensure this is done in a new isolated folder or all data is already wiped prior.
      3. Add one valid meal, fluid, library, weight, workout and schedule entry each.
      4. Verify that there is a new entry in all the respective data files.
         * **Note**: Closing and reopening the data files might be required to see the changes.
      5. Exit the application.
      6. Verify that data is still saved in their respective data files.
      7. Run `CLIckFit.jar` again and load the saved data.
      8. Verify that data is loaded correctly by listing the respective entries.
      9. Delete all entries added in *step 3*.
      9. Exit the application.
   * **Expected**: "Food.txt", "FoodBank.txt", "Weight.txt", "Workout.txt" and "Schedule.txt" will be empty.

### Command testing
Please check out CLI.ckFit's [User Guide](https://ay2122s1-cs2113t-f14-3.github.io/tp/UserGuide.html) for more detailed
walk through of all commands and their formats.

Take note that the following sections will focus more on command behavior not specified in the User Guide, such as 
expected output for incorrect or invalid inputs. The test cases stated in the following sections are not exhaustive and 
testers are expected to do more exploratory work for more comprehensive testing. 

### BMI calculator and Recommended caloric intake calculator

Description: It is a calculator that allows the user to check his current BMI and daily recommended caloric intake.
The user will be asked to enter his weight, height, age, activity level and gender.

#### Gender:
Possible inputs: M , F

Wrong inputs: negative integers and doubles, symbols, combination of integers and doubles with symbols
and strings, m, f

Testcase 1 : m (small m or f are rejected)

Testcase 2 : M (only M or F are accepted)

Expected:

`what is your SEX : M / F ?`

`m`

`what is your SEX : M / F ?`

`M`

*input accepted, proceeding to next question*

#### Weight:
Possible inputs: 60, 60.5 (integers or doubles are accepted)

Wrong inputs: negative integers and doubles, symbols, strings, combination of integers and doubles with symbols
and strings

Testcase 1 : -34 (negative integers or doubles are not accepted)

Testcase 2 : 60.5 (integers or doubles are accepted)

Expected:

`what is your weight in kg? Enter a valid number!`

`-34`

`what is your weight in kg? Enter a valid number!`

`60.5`

*input accepted, proceeding to next question*

#### Height:
Possible inputs: 181, 181.5 (integers or doubles are accepted)

Wrong inputs: negative integers and doubles, symbols, strings, combination of integers and doubles with symbols
and strings

Testcase 1 : -34.7 (negative integers or doubles are not accepted)

Testcase 2 : 132! (combination of integers or doubles and symbols are not accepted)

Testcase 3 : 181.5 (integers or doubles are accepted)

Expected:

`what is your height in cm? Enter a valid number!`

`-34.7`

`what is your height in cm? Enter a valid number!`

`132!`

`what is your height in cm? Enter a valid number!`

`181.5`

*input accepted, proceeding to next question*

#### Age:
Possible inputs: 23 (only integers are accepted)

Wrong inputs: negative integers and doubles, positive doubles, symbols, strings, combination of integers and doubles with symbols
and strings

Testcase 1 : 23.5 (doubles are not accepted)

Testcase 2 : twenty three (strings are not accepted)

Testcase 3 : 23 (only integers are accepted)

Expected:

`what is your age in years? Enter an integer!`

`23.5`

`Please enter a valid input!`

`what is your age in years? Enter an integer!`

`twenty three`

`what is your age in years? Enter an integer!`

`23`

*input accepted, proceeding to next question*

#### Activity level:
Possible inputs: 1, 2, 3, 4, 5 (integers 1 to 5)

Wrong inputs: negative integers and doubles, positive doubles, symbols, strings, combination of integers and
doubles with symbols and strings

Testcase 1 : 1.0 (doubles are not accepted even though it is within the range of 1 to 5)

Testcase 2 : 6 (integers outside the range of 1 to 5 are not accepted)

Testcase 3 : 4 (only integers within the range of 1 to 5 are accepted)

Expected:

`what is your activity level from a scale of 1 - 5? Enter an integer from 1 to 5!`

`1.0`

`Please enter a valid input!`

`what is your activity level from a scale of 1 - 5? Enter an integer from 1 to 5!`

`6`

`what is your activity level from a scale of 1 - 5? Enter an integer from 1 to 5!`

`4`

*input accepted, proceeding to result*
#### Result
If all the same inputs are used and accepted, the user will be greeted with this printout:

`Your BMI outcome is`

`You are underweight`

`Your ideal number of calories to maintain your weight is`

`2847 kcal`

`Would you like to clear all records of your fitness journey?
Key in "y" to clear your records, or press enter keystroke to load in data from your previous session(s)
Note: Keying in "y" will result in the previous session's data being deleted!`

### Workout Commands

#### Adding a workout
1. Adding a workout omitting date and time
    * **Test Case**: `add workout test /c 123`
    * **Expected**: workout with description "test" and "123" calories burned will be recorded with the current date and time.
    
2. Adding a workout with specified date and time
    * **Test Case**: `add workout test /c 123 /d 08/11/2021 /t 23:59`
    * **Expected**: workout with description "test" and "123" calories burned will be recorded on "08/11/2021" at "23:59".
    
3. Adding a workout with missing description
    * **Test Case**: `add workout /c 123`
    * **Expected**:
      ```
      I am sorry... it appears the description is missing.
      Please enter a workout description!
      ```
   
4. Adding a workout with missing calorie separator "/c"
    * **Test Case**: `add workout test 123`
    * **Expected**:
      ```
      CLI.ckFit is having difficulties finding the calorie separator /c
      Please minimally have the format: add workout [workout_description] /c [calories]
      Do remember to put spaces between your separators!
      ```
      
5. Adding a workout with invalid date or time format
    * **Test Case**: `add workout test /c 123 /d 08-11-21 /t 5:00pm`
    * **Expected**: 
      ```
      Please enter your date and time in the right format. 
      It should be "DD/MM/YYYY" and "HH:MM" respectively.
      ```
      
#### Deleting a workout
1. Deleting a workout with valid index
    * **Test Case**: `delete workout 1`
    * **Expected**: deletes the first workout in the workout list.
      
2. Deleting a workout with invalid index
    * **Test Case**: `delete workout -1`
    * **Expected**: 
      ```
      Failed to delete that workout! Please enter an Integer within range.
      ```
      
3. Deleting a workout without specifying index
    * **Test Case**: `delete workout`
    * **Expected**:
      ```
      Please enter the workout index in the format: delete workout [index]
      ```
      
#### Listing workouts
1. Listing workouts on date without any recorded workouts
    * **Test Case**: `list workouts 23/12/2021`
    * **Expected**: 
      ```
      No workouts recorded on the date: 23/12/2021
      ```
    
### Schedule Commands

#### Adding a scheduled workout
1. Adding a scheduled workout with missing date or time separators, "/d" and "/t"
    * **Test Case**: `add schedule 12/12/2021 23:59`
    * **Expected**: 
      ```
      CLI.ckFit is having difficulties finding the separators...
      Please enter in the format: add schedule [workout_description] /d [dd/mm/yyyy] /t [hh:mm]
      Do remember to put spaces between your separators.
      ```
      
2. Adding a scheduled workout with missing description
    * **Test Case**: `add schedule /d 12/12/2021 /t 23:59`
    * **Expected**:
      ```
      I am sorry... it appears the description is missing.
      Please enter a description for your scheduled workout!
      ```
      
4. Adding a scheduled workout with invalid date or time format
    * **Test Case**: `add schedule test /d 12-12-21 /t 11:59pm`
    * **Expected**:
      ```
      Please enter your date and time in the right format. 
      It should be "DD/MM/YYYY" and "HH:MM" respectively.
      ```
      
5. Adding a scheduled workout with a date in the past
    * **Test Case**: `add schedule test /d 07/11/2021 /t 13:59`
    * **Expected**:
      ```
      Noted! CLI.ckFit has scheduled your workout of description "test" on 07/11/2021 at 13:59.
      CLI.ckFit has detected some overdue scheduled workouts and has deleted/rescheduled them!
      ```
      
5. Adding a scheduled workout with activity breakdown, missing activity splitter ":"
    * **Test Case**: `add schedule test /d 12/12/2021 /t 13:59 /a chest8x10, squats:3x10`
    * **Expected**:
      ```
      Missing activity splitter ":" detected.
      Please enter [activity name]:[sets]x[reps] or [activity name]:[distance in metres] 
      for your workout activities
      ```
      
6. Adding a scheduled workout with activity breakdown, unnecessary quantifier "x"
    * **Test Case**: `add schedule test /d 12/12/2021 /t 13:59 /a swimming:1000x2`
    * **Expected**:
      ```
      Unnecessary activity quantifier splitter "x" detected.
      Please enter [activity name]:[distance in metres] for distance based workout activities 
      if your activity name is either running/swimming/cycling.
      E.g. running:8000
      ```
      
7. Adding a scheduled workout with activity breakdown, missing quantifier "x"
    * **Test Case**: `add schedule test /d 07/11/2021 /t 13:59 /a chest:8x10, squats:3 10`
    * **Expected**:
      ```
      Missing activity quantifier "x" detected.
      Please enter your [sets]x[reps] for your non-distance based workout activities.
      ```
      
8. Adding a scheduled workout with activity breakdown with non-positive integers for distance or sets and reps
    * **Test Case**: `add schedule test /d 07/11/2021 /t 13:59 /a chest:8x10, squats:3x-10`
    * **Expected**: 
      ```
      There was an issue getting your activity breakdown.
      Please enter a positive integer [distance in metres] for distance based 
      activities, namely swimming/running/cycling.
      E.g. running:8000
      Enter two positive integers in the format [set]x[reps] for everything else.
      E.g. bench press:3x12
      For multiple activities please separate them by ","
      ```
      
#### Deleting a scheduled workout
1. Deleting a scheduled workout with valid index
    * **Test Case**: `delete schedule 1`
    * **Expected**: deletes the first workout in the workout list.

2. Deleting a scheduled workout with invalid index
    * **Test Case**: `delete schedule -1`
    * **Expected**:
      ```
      Failed to delete that scheduled workout! Please enter an Integer within range.
      ```
      
3. Deleting a scheduled workout without specifying index
    * **Test Case**: `delete schedule`
    * **Expected**:
      ```
      Please enter the schedule index in the format: delete schedule [index]
      ```
      
#### Listing scheduled workouts
1. Listing workout schedule on date without any scheduled workouts
    * **Test Case**: `list schedule 23/12/2021`
    * **Expected**:
      ```
      Workout schedule is empty on the date: 23/12/2021
      ```

### Meal commands

#### Adding a meal

1. Adding a meal omitting date and time
    * **Test Case**: `add meal pasta /c 190`
    * **Expected**: 
      ```
      Noted! CLI.ckFit has recorded your meal of pasta on 08/11/2021 and at 20:46. 
      190 calories has been added to the calorie count!
      ```
    
2. Adding a meal with specified date and time
    * **Test Case**: `add meal pasta /c 190 /d 08/11/2021 /t 23:59`
    * **Expected**:
      ```
      Noted! CLI.ckFit has recorded your meal of pasta on 08/11/2021 and at 23:59. 
      190 calories has been added to the calorie count!
      ``` 
   
3. Adding a meal with missing description
    * **Test Case**: `add meal /c 190`
    * **Expected**: 
      ```
      Please enter a meal/fluid description, volume for fluids, and optionally,
      calories if not previously added to the library!
      e.g "add meal {DESCRIPTION} /c {CALORIES}" OR "add meal {DESCRIPTION}"
      e.g "add fluid {DESCRIPTION} /c {CALORIES} /v {volume}" OR "add fluid {DESCRIPTION}"
      ```
   
4. Adding a meal with missing calories
    * **Test Case**: `add meal pasta /c`
    * **Expected**:
      ```
      Please enter a meal/fluid description, volume for fluids, and optionally, 
      calories if not previously added to the library! 
      e.g "add meal {DESCRIPTION} /c {CALORIES}" OR "add meal {DESCRIPTION}"
      e.g "add fluid {DESCRIPTION} /c {CALORIES} /v {volume}" OR "add fluid {DESCRIPTION}"
      ```

5. Adding a meal with invalid date or time format
    * **Test Case**: `add meal pasta /c 123 /d 08-11-21 /t 5:00pm`
    * **Expected**:
      ```
      Please enter your date and time in the right format. 
      It should be "DD/MM/YYYY" and "HH:MM" respectively.
      ```
      
6. Adding a meal with negative calories
   * **Test Case**: `add meal pasta /c -123`
   * **Expected**:
     ```
     Please input a positive integer value for your calories!
     ```

#### Deleting a meal
1. Deleting a meal with valid index
    * **Test Case**: `delete meal 1`
    * **Expected**:
      ```
      pasta will be removed from your list of meals consumed!
      ```

2. Deleting a meal with invalid index
    * **Test Case**: `delete meal -1`
    * **Expected**:
      ```
      Please enter a proper meal index. Use "list meals all" to view each meal's index
      ```

3. Deleting a meal without specifying index
    * **Test Case**: `delete meal`
    * **Expected**:
      ```
      Please enter the index of the meal you wish to delete!
      ```

### Fluid commands

#### Adding a fluid

1. Adding a fluid omitting date and time
   * **Test Case**: `add fluid cola /c 100 /v 100`
   * **Expected**:
     ```
     Noted! CLI.ckFit has recorded your drink of cola of 100 calories and 100 ml 
     on 08/11/2021 19:47.
     ```

2. Adding a fluid with specified date and time
   * **Test Case**: `add fluid cola /c 190 /v 100 /d 01/02/2021 /t 23:59`
   * **Expected**:
     ```
     Noted! CLI.ckFit has recorded your drink of cola of 190 calories and 100 ml 
     on 01/02/2021 23:59.
     ``` 

3. Adding a fluid with missing description
   * **Test Case**: `add fluid /c 190`
   * **Expected**:
     ```
     Please enter a meal/fluid description, volume for fluids, and optionally, 
     calories if not previously added to the library! 
     e.g "add meal {DESCRIPTION} /c {CALORIES}" OR "add meal {DESCRIPTION}"
     e.g "add fluid {DESCRIPTION} /c {CALORIES} /v {volume}" OR "add fluid {DESCRIPTION}"
     ```
     
4. Adding a fluid with missing calories
   * **Test Case**: `add fluid water /c`
   * **Expected**:
     ```
     Please enter a meal/fluid description, volume for fluids, and optionally, 
     calories if not previously added to the library!
     e.g "add meal {DESCRIPTION} /c {CALORIES}" OR "add meal {DESCRIPTION}"
     e.g "add fluid {DESCRIPTION} /c {CALORIES} /v {volume}" OR "add fluid {DESCRIPTION}"
     ```

5. Adding a fluid with invalid date or time format
   * **Test Case**: `add fluid cola /c 123 /d 08-11-21 /t 5:00pm`
   * **Expected**:
     ```
     Please enter your date and time in the right format. 
     It should be "DD/MM/YYYY" and "HH:MM" respectively.
     ```

6. Adding a fluid with negative calories
   * **Test Case**: `add fluid pasta /c -123`
   * **Expected**:
     ```
     Please input a positive integer value for your calories!
     ```

#### Deleting a fluid
1. Deleting a fluid with valid index
   * **Test Case**: `delete fluid 1`
   * **Expected**:
     ```
     Noted! CLI.ckFit has deleted your drink of cola of 60 calories and 80 ml 
     on 08/11/2021 20:24.
     ```

2. Deleting a fluid with invalid index
   * **Test Case**: `delete fluid -1`
   * **Expected**:
     ```
     This fluid entry does not exist. 
     You may try again or wish to add a fluid entry first.
     ```

3. Deleting a fluid without specifying index
   * **Test Case**: `delete fluid`
   * **Expected**:
     ```
     Please enter a valid fluid index. 
     You may wish to list to check the index numbers.
     ```

### FoodBank commands

#### Adding a custom meal

1. Adding a custom meal 
   * **Test Case**: `library addmeal pasta /c 190`
   * **Expected**:
     ```
     pasta, which has 190 calories, will be added to your library of meals. 
     You now have 1 meals!
     ```

2. Adding a custom meal without its calories
   * **Test Case**: `library addmeal pasta`
   * **Expected**:
     ```
     Please enter a food description, and its associated calories
     e.g "library addmeal {DESCRIPTION} /c {CALORIES}"
     e.g "library addfluid {DESCRIPTION} /c {CALORIES}"   
     ``` 

3. Adding a custom meal with missing description
   * **Test Case**: `library addmeal /c 190`
   * **Expected**:
     ```
     Please enter a food description, and its associated calories
     e.g "library addmeal {DESCRIPTION} /c {CALORIES}"
     e.g "library addfluid {DESCRIPTION} /c {CALORIES}"
     ```
   
6. Adding a meal with negative calories
   * **Test Case**: `library addmeal pasta /c -123`
   * **Expected**:
     ```
     Please input a positive integer value for your calories!
     ```

#### Deleting a custom meal

1. Deleting a meal with valid index
   * **Test Case**: `library deletemeal 1`
   * **Expected**:
     ```
     pasta will be removed from your meal library.
     You now have 0 meals left!
     ```

2. Deleting a meal with invalid index
   * **Test Case**: `library deletemeal -1`
   * **Expected**:
     ```
     Please enter a valid meal index! 
     Use "library listmeals" to see the meal indexes
     ```

3. Deleting a meal without specifying index
   * **Test Case**: `library deletemeal`
   * **Expected**:
     ```
     Please enter a food index!
     ```

#### Adding a custom fluid

1. Adding a custom fluid
   * **Test Case**: `library addfluid water /c 190`
   * **Expected**:
     ```
     water, which has 190 calories, will be added to your library of fluids. 
     You now have 1 fluids!
     ```

2. Adding a custom meal without its calories
   * **Test Case**: `library addfluid cola`
   * **Expected**:
     ```
     Please enter a food description, and its associated calories
     e.g "library addmeal {DESCRIPTION} /c {CALORIES}"
     e.g "library addfluid {DESCRIPTION} /c {CALORIES}"
     ``` 

3. Adding a custom fluid with missing description
   * **Test Case**: `library addfluid /c 190`
   * **Expected**:
     ```
     Please enter a food description, and its associated calories
     e.g "library addmeal {DESCRIPTION} /c {CALORIES}"
     e.g "library addfluid {DESCRIPTION} /c {CALORIES}"
     ```

6. Adding a fluid with negative calories
   * **Test Case**: `library addfluid juice /c -123`
   * **Expected**:
     ```
     Please input a positive integer value for your calories!
     ```

#### Deleting a custom fluid

1. Deleting a fluid with valid index
   * **Test Case**: `library deletefluid 1`
   * **Expected**:
     ```
     water will be removed from your list of fluids consumed. 
     You now have 0 fluids left!
     ```

2. Deleting a fluid with invalid index
   * **Test Case**: `library deletemeal -1`
   * **Expected**:
     ```
     Please enter a valid fluid index! 
     Use "library listfluids" to see the fluid indexes
     ```

3. Deleting a fluid without specifying index
   * **Test Case**: `library deletefluid`
   * **Expected**:
     ```
     Please enter a food index!
     ```

#### Listing calories

1. Listing meals on a specific date
   * **Test Case**: `list calories`
   * **Expected**:
     ```
     Your total calorie consumption for 08/11/2021 is: 60 calories.
     Your total calories burned for 08/11/2021 is: 0 calories.
     Your NET calories for 08/11/2021 is: 60 calories.
     ```

#### Listing volumes

1. Listing meals on a specific date
   * **Test Case**: `list volumes`
   * **Expected**:
     ```
     Your total volume consumption for 08/11/2021 is: 100 ml.
     ```
     
### Weight Commands

#### Adding a weight
1. Adding a weight for current date
   * **Test Case**: `add weight 50`
   * **Expected**: `Noted! CLI.ckFit has recorded your weight as 50.0 kg on 08/11/2021. Check back for your progress!`
2. Adding a weight for specific date
   * **Test Case**: `add weight 50 08/11/2021`
   * **Expected**: `Noted! CLI.ckFit has recorded your weight as 50.0 kg on 08/11/2021. Check back for your progress!`
3. Adding a weight for unaccepted weight
(only numbers to 1 decimal point from 0 <= weight <= 1000 allowed)
   * **Test Case**: `add weight 99999`
   * **Expected**:
   ```
   Exceeded maximum weight 
   An unknown error has occurred in Weight Tracker
   ```
4. Adding a weight with omission of weight
   * **Test Case**: `add weight`
   * **Expected**: `CLI.ckFit has detected a wrong input, kindly check your inputs or type "help commands" for input examples.`

#### Deleting a weight
1. Deleting a weight with valid index
   * **Test Case**: `delete weight 1`
   * **Expected**: `Noted! CLI.ckFit has successfully deleted your weight of 50.0 kg on 08/11/2021.`
2. Deleting a weight with invalid index
   * **Test Case**: `delete weight -1`
   * **Expected**: 
   ```
   CLI.ckFit encountered a problem deleting your weight.
   Please ensure the index is within the list.
   ```
3. Deleting a weight with omission of index
   * **Test Case**: `delete weight`
   * **Expected**:
   ```
   CLI.ckFit encountered a problem deleting your weight.
   Please follow the format: delete weight INDEX
   ```
   
#### Listing weights
1. Listing weights for current date
   * **Test Case**: `list weights`
   * **Expected**:
   ```
   1. Weight: 50.0 kg
   Total number of weights: 1
   ```
2. Listing weights for specific date
   * **Test Case**: `list weights 08/11/2021`
   * **Expected**:
   ```
   1. Weight: 50.0 kg
   Total number of weights: 1
   ```

