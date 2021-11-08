# Code Hunt

Code Hunt is a text-based adventure game using command lines for children aged 4 to 9 who have little prior coding 
experience but have an interest in coding to learn basic coding knowledge through playing games and collecting knowledge cards.

## Setting up in Intellij

Prerequisites: JDK 11 (use the exact version), update Intellij to the most recent version.

1. **Ensure Intellij JDK 11 is defined as an SDK**, as described [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) -- this step is not needed if you have used JDK 11 in a previous Intellij project.
   * In the same dialog, you _may_ have to set the Project language level field to the SDK default option.
1. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
1. **Verify the set up**: After the importing is complete, locate the `src/main/java/game/CodeHunt.java` file, right-click it, and choose `Run CodeHunt.main()`. If the setup is correct, you should see something like the below:
   ```
   > Task :compileJava
   > Task :processResources NO-SOURCE
   > Task :classes

   > Task :CodeHunt.main()
     ____          _        _   _             _
   / ___|___   __| | ___  | | | |_   _ _ __ | |_
   | |   / _ \ / _` |/ _ \ | |_| | | | | '_ \| __|
   | |__| (_) | (_| |  __/ |  _  | |_| | | | | |_
   \____\___/ \__,_|\___| |_| |_|\__,_|_| |_|\__|
   Welcome to A text-based code game for children ages 6 and up(Code Hunt).Are you ready to play?
   Remember, you can't play the next game until you pass through the processing one. Good luck, young knight! Type "help" to see a list of available commands.

   newgame     Start a new game.
   continue    Continue the game stored previously.
   help        Display a list of available commands.
   about       Display information about Code Hunt.
   exit        Exit Code Hunt.

   Code Hunt [MAIN MENU] >
   ```
   Type some word and press enter to let the execution proceed.

## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text ui tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests

* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains the project documentation.

Useful links:
* [User Guide](docs/UserGuide.md)
* [Developer Guide](docs/DeveloperGuide.md)
* [About Us](docs/AboutUs.md)
