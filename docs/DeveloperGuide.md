# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Ui Component

![](images/UiClass.png)

The `Ui` Class is responsible for the printing of interactive messages whenever a user types an input. It handles print messages to the Command Line Interface from when the program loads, to after every input by the user and finally when the user exits the program.

The interface of the program utilizes the ClearScreen class to clear the terminal after every user input through the built-in `ProcessBuilder` Java class. Such a feature allows greater readability and focus for the user as the terminal will not be cluttered with past commands.
`Ui` will call `ClearScreen.clearConsole()` method to clear the terminal.

The ProcessBuilder class will send a respective command to the terminal depending on the Operating System of the user.
The command it sends to the terminal is as follows:
* `cls` for Windows CMD Terminals
* `clear` for Linux/MacOS Terminals

`ClearConsole()` Code Snippet:
```
 public static void clearConsole() {
        try {
            // Get current operating system
            String operatingSystem = System.getProperty("os.name");

            if (operatingSystem.contains("Windows")) {
                // Try clear for Windows
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                // Try clear for MacOS/Linux
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
```
## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|Restaurant owner|I can delete an entry for a particular dish|Change the tracking to adapt to a change in my menu|
|v1.0|Restaurant owner| I can use a help function|help me get familiar with the application|
|v1.0|Restaurant owner|I can add a ingredient to be tracked|keep track of the ingredient storage|
|v1.0|Restaurant owner|I can add a dish to be tracked|So I can track its wastage and its ingredients’ wastage|
|v1.0|Restaurant owner|I can add the weight of wastage of a dish|Know how much of a certain dish is being wasted|
|v1.0|Restaurant owner|I can calculate the Ingredients and Dishes wasted|Plan for future meal services to reduce food wastage|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
