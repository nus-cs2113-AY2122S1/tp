# Tran Nhan Duc Anh - Product Portfolio Page

## Project: SITUS (Smart Inventory Tracking and Updating System)

The Smart Inventory Tracking and Updating System (SITUS) is a desktop app
for tracking ingredient inventory designed for restaurant/ F&B inventory managers.
Users interact with in through a CLI, and is written in Java.

## Summary of contributions
- **Code contributed**: The complete list of code contributed can be seen [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=datn02&tabRepo=AY2122S1-CS2113T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
- **Enhancements implemented**:
  1. `Parser` class
     - **What it does**: the `Parser` class is responsible for parsing and receiving of commands.
     - **Justification**: creating a seperate class to handle parsing and checking commands of user input is one of the recommended approach to OOP code.
  2. `Command` abstract class
     - **What it does**: the `Command` class provides a template for *all* commands type in the code to inherit. 
     - **Justification**: Since command are to be run based on user's input, the idea of having the `run` method to be returned the same (`String` type) by all other command class is useful. Therefore, the `Command` class is implemented.
  3. `DateCommand` command class
     - **What it does**: the `DateCommand` class provides a way to modify/check if the current date/time in the system is correct.
     - **Justification**: this command provides a way to fix the wrong date in the system to ensure correct expiry calculation is peformed. 
  4. `CurrentDate` class
     - **What it does**: the `CurrentDate` class provides a way to retrieve/display the current date of the system in the `LocalDate` format.
     - **Justification**: This class is responsible for retrieving current date in the system for expiry calculation. 
  5. `Storage` class
     - **What it does**: the `Storage` class is responsible for writing/reading ingredients data from the memory.  
     - **Justification**: the ability to read and write ingredient data to a memory file is helpful for long-term ingredient tracking. 
     - **Difficulties encountered**: in the PE-d, testers have reported that any invalid lines in the memory file would skip the rest of the ingredient information. 
     - **Solution**: split the ingredient and threshold data into separate memory files. In addition, a check method was added in the `loadIngredientsFromMemory()` method to skip invalid ingredients data lines, while retaining the integrity of the rest of the data file to add into the current ingredient database.
  6. `DeleteCommand` class
     - **What it does**: this class is responsible for removing ingredients from the database based on the ingredient group number and ingredient number.
     - **Justification**: deleting by their numbers in lists and groups provides a faster way to operate this command.
     - **Further details**: previously, this command is operated by supplying the ingredient name and expiry date instead of ingredient numbers in the list. This involved checking of the ingredient name, and then checking the matching expiry date in each individual ingredient groups. This method was lengthy and error-prone, and since the ingredients are already sorted accordingly to their name and expiry, the current method of this command is carried out.
  7. Others
     - Participated in early stages of `AlertExpiringSoon` command, which later completed by @mudkip8.
     - Added sorting methods in the `IngredientGroup` class.
     - Added `add()` and `deleteIngredientFromGroup()` methods in `IngredientList` class for adding and removing ingredients.
     - Debugged and fixed `SubtractCommand` and `UpdateCommand` with their respecting methods `subtractIngredientFromGroup()` and `update()` in `IngredientList` class.
- **User Guide**: The user guide can be found [here](https://ay2122s1-cs2113t-t09-3.github.io/tp/UserGuide.html)
  * Added table of contents.
  * Added quick start section.
  * Modified `Introduction` and added `Format notes` part.
- **Developer Guide**: The developer guide can be found [here](https://ay2122s1-cs2113t-t09-3.github.io/tp/DeveloperGuide.html)
  * Added `First-time setup` section.
  * Added `Storage component` section.
  * Added `Deleting ingredients` section.
  * Added puml diagram for deleting procedure.
- **Team tasks**
  * Wrote documentation for methods in Java code.
  * Making sure weekly tp tasks is completed in time.
  * Debugged and fixed issues raised by testers in PE-d.
- **Community**
  * Reported 9 issues in peers' tp project in PE-d. See [here](https://github.com/datn02/ped/issues)
