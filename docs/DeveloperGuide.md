# Developer Guide

- [Acknowledgements](#acknowledgements)
- [Architecture](#architecture)
- [Design](#design)
    - [Logical Component](#logical-component)
    - [Manager Component](#manager-component)
- [Implementation](#implementation)
    - [Parser](#parser)
    - [Add, Delete, Edit, Orders & Find Command Classes](#add-delete-edit-orders-and-find-command-classes)
    - [Menu & Order Command Classes](#menu-and-order-command-classes)
    - [Storage](#storage)
- [Product Scope](#product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
- [User Stories](#user-stories)
- [Non-Functional Requirements](#non-functional-requirements)
- [Instructions for manual testing](#instructions-for-manual-testing)
- [Glossary](#glossary)

[Back to main page](README.md)

## Acknowledgements

- Structure of Developer Guide adapted from AB3.

## Architecture

<br>![Architecture Diagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/ArchitectureDiagrams/ArchitectureDiagram.jpg)
<br>The ***Architecture Diagram*** given above explains the high-level design of the LOTS app.

<br>The following section gives a brief overview of the main components in the architecture and how they interact with
each other. Further explanation will be given in depth in the **Design** section of the developer guide.
### Main components of the architecture
1. **Main** consists of the Duke class, which is responsible for initializing the various components of the LOTS program
   at startup, and the handling of the interactions between classes.
2. **UI** handles the UI portion of the LOTS program.
3. **Logic** deals with the parsing and execution of user inputs.
4. **Manager** deals with the various types of data that is stored within the LOTS program.
5. **Storage** deals with the reading and writing of data onto the hard disk.

### Component Interaction

The general flow of the program is as follows:
1. On initial startup, the program will check if the `.orders.txt` exists in the directory. If it does, the data on the file will be loaded into the program. If not, a new file is created.
2. User then inputs data which is read by the `UI` within the `Main`.
3. This data is passed to the `Parser` which will return a `Command`.
4. `Command` will be executed, carrying out whatever task the user has input. `Manager` may be called if data is to
be stored or edited. 
5. Every time a new `Command` is executed, `Storage` will write the updated data to the `.orders.txt` file.
6. `UI` component handles the printing of data if required.  
   <br>Given below is a simplified sequence diagram showing how the components within the LOTS program interact with each other
   when the user inputs the command `delete 1/2`  
   <br>![Delete Sequence Diagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/ArchitectureDiagrams/DeleteSeq.jpg)

## Design

### Logical Component

The logical component of the program consists multiple classes. Namely: `Parser`,`Command` &
the various child class of `Command`.
The class diagram below is a brief overview of how the `Parser`, `Manager` & the various `Command` class
are related to one another.  
<br>![Logical Component Partial Class Diagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/LogicalComponentDiagrams/Logical%20Component%20Diagram.jpg)

:information_source: **Note:** Specific command names are represented using a placeholder `'Abc'`, i.e. AddCommand, FindCommand.

Below is a brief explanation on how the `Logical` component works.
1. Upon receiving the user's input to execute a specific command, it calls the `Parser` to interpret and parse the user's command.
2. A particular `Command` object is then initialised and returned to `Duke`, the main program.
3. `Duke` executes the command, which communicates with the `Manager` to perform its specific function, i.e. add a food order.

The class diagram below is a brief overview of how the `Parser` is used in parsing the user's command.

![Class Diagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/ParserDiagrams/ParserClassDiag-Page-1.jpg)

:information_source: **Note:** Details of each specific command class & Duke have been omitted from this diagram.

Explanation on how the parsing is done:
1. Upon receiving the user's input string from `Duke`, the `Parser` split the user's input into an array of strings.
2. It then interprets the string and tries to match it with one of the known commands.
3. The respective command (i.e. `DeleteCommand`) object will be instantiated and returned to `Duke` as a `Command` object. (`UnknownCommand` object is return
   if there is no match)

The following sequence diagram depicts how the `Logical` components interact with one another upon receiving the user's input of `"delete 1/2"`.  
<br>![Logical Component Partial Sequence Diagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/DeleteCommandDiagram/DeleteCommand%20Sequence%20Diagram.jpg)
   
### Manager Component
The manager component of the program consists multiple classes. Namely: `PeopleManager`,`Person` & `Order`.  
<br>![PeopleManagerDiagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/PeopleManagerDiagram/PeopleManagerDiagram.jpg)

:information_source: **Note:** This diagram shows the components of how the inputs are handled. The dotted boxes are the overall Person Component and Order component.


The `PeopleManager` component,
* Stores a list of people, named: `listOfPeople`. This list stores all `Person` objects.

The `Person` component,
* Store details of the person.
   * It stores the name of the person, using a String Variable named `personName`.
   * It also stores the orders of the person, using a list of Order, named `individualFoodOrders`.
   
The `Order` component,
* Stores details of the order.
   * It stores the name of the order, using a String Variable named `foodName`.
   * It stores the index of the order, using an int Variable named `foodIndex`.
   * It stores the quantity of the order, using a String Variable named `quantity`.
   * It stores the cost of the order, using a double Variable named `costOfOrder`.
   
When the input is passed in through the `logical` component, the `PeopleManager` component will be responsible for managing the input, 
by creating variables needed to store the data, and storing this newly created variables into the `listOfPeople`.

## Implementation

### Parser

Below is a sequence diagram of the `Parser` class. In the event of a valid command, the parser
would call its respective constructor and return the command object created. As for commands that
are not recognized by the `parser`, an `unknownCommand` is returned instead.

![Squence Diagram of Parser](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/ParserDiagrams/Parser%20Sequence%20Diag.jpg)
<br>:information_source: **Note:** All valid commands (E.g. `add`, `find`, etc...) are represented by the `AbcCommand` class.

Below is a step by step example on how the `Parser` interacts when a user keys in an input.

1. Lets assume the user input is `delete 1/2`. `Duke` will then call the method
`Parser.getCommand("delete 1/2")`. The method would split the user's input into an array of
strings along the spaces and store them in the array `listOfInputs`. After which,
it will get the string `"delete"` from the 0th element of `listOfInputs` and store
it in `commandInString`.

2. The method will then parse `commandInString` through the switch cases and try to match it with
one of the known commands words stored in each respective command's class attribute called `COMMAND_WORD`.
In this case, the `"delete"` command will cause the parser to return a `DeleteCommand` object.

3. Next, `Duke` will then use the returned command to call `Command.execute()` which will interact
   with the `Manager Components` of the program who will then remove the 2nd order from the first
   person in the list. The section of code below shows the implementation of the switch statement.

```
switch (commandInString) {
case (AddCommand.COMMAND_WORD):
    return new AddCommand(input);
case (DeleteCommand.COMMAND_WORD):
    return new DeleteCommand(input);
case (EditCommand.COMMAND_WORD):
    return new EditCommand(input);
case (OrdersCommand.COMMAND_WORD):
    return new OrdersCommand(input);
case (MenuCommand.COMMAND_WORD):
    return new MenuCommand(input);
case (FindCommand.COMMAND_WORD):
    return new FindCommand(input);
case (ByeCommand.COMMAND_WORD):
    return new ByeCommand();
default:
    return new UnknownCommand();
}
```

#### Alternate implementation

For the `Parser` class, we initially had thought of having each command's respective
functions to be executed inside the switch statement.
A rough example of the `delete` and `add` function can be seen below.
```
case "delete":
    functionToDeleteTask(input);
    break;
case "add":
    functionToAddTask(input);
    break;
```
The upside of doing would be that there is less code overall.
However, doing so would cause our code to have a higher amount of coupling and would also
cause the code to be messier and therefore harder to read. By having a command class
for each respective command, this allows us to segregate all the necessary functions
for each command in their own respective class, therefore making testing easier too.

### Add, Delete, Edit, Orders and Find Command Classes

This section describes how the commands are implemented.
Explanations and sequence diagrams are used to describe the implementation process.

The commands `add`, `delete`, `edit`, `orders` and `find` have similar implementation, with a few differences in terms of the methods called.
Here is an overview of their class diagram.  
<br>![OverallClassDiagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/LogicalComponentDiagrams/OverallClassDiagram.jpg)

:information_source: **Note:** The diagram is shared between the AddCommand, DeleteCommand, EditCommand, OrdersCommand and FindCommand.


From the above class diagram, we can replace the **AbcCommand** with whichever command we are looking at. For example, if we are 
looking at the `AddCommand` class, we replace the **AbcCommand** with **AddCommand**. Same goes for all the other command classes. 
The overview class diagram is listed here to show how the command classes interact on the logical component to the manager component.

The Sequence Diagram below represents the interactions between components when user inputs command `add /n tom /i 1 /q 2`
<br>![Sequence Diagram](https://raw.githubusercontent.com/thaddyyz/tp/master/UMLdiagrams/EditCommandDiagrams/AddCommandSeqDiagram.png)

The Sequence Diagram below represents the interactions between components when user inputs command `delete 1/2`
<br>![Sequence Diagram 2](https://raw.githubusercontent.com/thaddyyz/tp/master/UMLdiagrams/EditCommandDiagrams/deleteCommandSeqDiagram.png)
This show the interaction between the Logical and Manager components during the add and delete situation.

`Find`command goes through similar sequence as compared to the `delete` command sequence diagram.
The main differences are:
1. deleteOrder() is replaced with checkIfMatchAndPrint().
2. deleteParticularOrder() is replaced with getPersonName().

`Edit`command goes through similar sequence as compared to the `delete` command sequence diagram.
The main differences are:
1. additional getQuantity() method under EditCommand class.
2. deleteOrder() is replaced with editOrder().
3. editParticularOrder() is called directly from the EditCommand class instead of through a method in Person class.


#### Alternate implementation

The EditCommand function can be integrated with the deleteCommand class.
  
The upside of doing would be that there is less code overall.
However, doing so would result in multiple functions being in the same class which would:
1. Make the code messier.
2. Make the code more vulnerable to functionality bugs.
3. Make the code more complex to debug.
4. Make testing process more complicated.

### Menu and Order Command Classes

Command word to invoke the Menu Command and Order Command: `menu` and `list`.

The purpose of Menu Command class is to instantiate the menu of which the user can order from. By invoking `menu`, the menu will be printed on the console for the user to see.

The purpose of Order Command class is to print the orders which the user has ordered onto the console. By invoking `list`, the order will be printed in a list format for the user to view. 

Hence, the sequence of which how Menu Command class and Order Command class are very similar. To prevent repeating of Sequence diagrams, a shared diagram will be listed below for the Menu Command Class and the Order Command class.  
   
<br>![MenuAndOrdersSequenceDiagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/MenuAndOrdersSequenceDiagram/MenuAndOrdersSequenceDiagram.jpg)    
:information_source: **Note:** The diagram is shared between Menu Command Class and Orders Command Class.


The steps to using the `menu` and `list` command can be seen from the sequence diagram. In short: 
1. Invoke the Menu Command class by calling `menu`. The menu will display in the terminal.

2. After adding orders, invoke `list` command to see the orders added into the list.

**Note:** The command `menu` and `list` are just these two strings. Any edits to these two commands will result in an exception being thrown.

### Storage

The following diagram shows the interaction of the `storage` class with the other classes in the program.
<br>![StorageDiagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/StorageDiagrams/Storage%20Sequence%20Diagram.jpg)
<br>How the `storage` component behaves is as follows:  

**Upon startup:**
* Duke calls `initialiseFile()` to try to get the `.order.txt` file, which is done in `getOrdersFile()` method. In the case where the file is not found, a new `.orders.txt` file would be created.
* The content of the files are then checked line by line, to make sure it is of a valid format which includes: 
  * Valid name, valid quantity and valid array size of orders.
* If all lines satisfy the expected format,`executeLoad()` is called to load the contents of the file by formatting each line into a valid `add` command and then executing it by calling `executeFromFile()` in the `AddCommand` class. 
* Else, the file is wiped and all the data would be gone. This is accomplished by `updateFileWithEmptyManager()`, which updates the file with an empty list of people.

**During runtime:**
* After every command execution, `Duke` will call the `Storage.updateFile()`, passing the current `PeopleManager` object to the method.
* Every person's data (name and orders) is then retrieved using `getPersonName()` and `getEntireOrdersOfPerson()`, where it is then formatted and written into the file as such: 
  * `name, quantity of 1st food in menu, quantity of 2nd food in menu, ... , quantity of last food in menu`

## Product scope

### Target user profile

* User has good typing skills.
* Is comfortable without GUI.
* Tends to make large orders of food.
* Wishes to track multiple different orders of food.

### Value proposition

* Large orders can be confusing to keep track, especially if orders are to different stores.
* Allows users to keep track of bulk orders easily.
* Easily keep track of costs.
* Ensures that no orders are left out.
* Users are able to delete/edit entries quickly.
* Allows users to save order list locally.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|New student on campus|View the menu.|Avoid having to go down physically to the shops.|
|v1.0|User|View the current orders with their names attached.|Able to track who ordered what.|
|v1.0|User|Add my choices of food from the menu.| Track what orders I plan to get.|
|v1.0|User|Delete order's that I've added.|So i can remove orders if someone changes their mind.|
|v2.0|New User|Easily understand how to use the app.| Navigate the program with ease.| 
|v2.0|Person ordering for a group.|View the cost breakdown for the current order.| Know how much each person owes me.|
|v2.0|Person ordering for a large group.|View the total number of orders keyed in.| Ensure that everyone has ordered.|
|v2.0|User|Amend existing orders.|Edit an order without having to re-type it.|
|v2.0|Person ordering for a large group.|Find a specific order by name.|Easy know what they ordered without having to scroll through.|
|v2.5|Tech illiterate| View a user guide.| Learn how to use the program.|
|-|Person ordering for a group| Track everyone's food allergies.| Avoid ordering the wrong order.|
|-|Person ordering food for friends| Track what add-ons my friends want.|Track how much it is and won't forget about it.|
|-|Lecturer| Know how many people does a food serve.|Easily order food for the class.|
|-|Host of a party| Sort the menu based on categories| Keep track of different varieties of food.|
|-|Indecisive user| View recommendations based on my frequent orders.| Simplify my ordering process.|
|-|Non-local| View the menu with descriptions in multiple languages.| Order food without a language barrier.|
|-|User with poor data| Download the menu and order list.| Track my orders even without the internet.|
|-|User| View if a food item is sold out.| Decide whether to order from another store.|
|-|Health conscious user| View the nutritional value of a food.| Easily track my diet.|
|-|Ordering for a large group| View the recommended serving size.| Decide on how much food to order.|
|-|Recurring user| Store my favourite food| Easily find what I like.|
|-|Recurring user| Saved my food orders| Easily place my regular order again.|
|-|User| Retrieve my previous order.| Place my previous orders again.|
|-|User| View menu from a file| Avoid having to scroll through the whole menu.|
|-|User| Track who has paid| Know who still owes me money.|

## Non-Functional Requirements

- Program should be able to work on most Operating Systems such as `Windows`, `Linux`,
  `OS-X` & `Unix`.

- Program should work with Java `11` or above installed.

- Program is able to save the current list of orders to a file and upon re-start of the program, load the orders file.

- The program should be able to support up to all these values stated below.
   - Up to `99` _Unique person_ at a time.
   - Maximum of `99` _Unique Food_ items in the menu.
   - Maximum of `999` quantity for each distinct food item in a person's order.
    
## Instructions for manual testing

The instructions below give a brief overview on how to test the functions manually.

- Head over to [Setup For Developers](settingUp.md) to set up your IDE if you wish to edit the test cases.

- :information_source: More test cases can be found in each of their respective test class under
  `src/test/java/seedu.duke`
  
>:exclamation: **Important:** When using the `IO-Redirection` to run tests, take note of the
`orders` file created as part of the `saving` feature. It is **strongly recommended** to
  delete the `orders` file or delete all remaining orders in the list after each run of the test as any orders
> stored in the file **will** affect subsequent test runs.


- [Starting up and Shutting down](#starting-up-and-shutting-down)
- [Saving and Loading of data](#saving-and-loading-of-data)
- [Add function](#add-function)
- [Edit function](#edit-function)
- [Find function](#find-function)
- [Delete function](#delete-function)

---

### Starting up and Shutting down

1. To begin, [download](https://github.com/AY2122S1-CS2113-T13-2/tp/releases) the .jar file and place it in a folder.
2. Open the CLI at the file location and run by the jar file by giving the command,
   `java -jar <jar file name>.jar`
   - E.g) With `CS2113T.jar` the command would be `java -jar CS2113T.jar`
3. To end the program, enter the command `bye` or simply close the CLI window.

---

### Saving and Loading of data

- Prerequisite: The `Menu` has to contain at least `1` food item.

1. Add an order to the list. E.g) `add /n abc /i 1 /q 1`
2. Exit the program properly by giving the command `bye`.
3. Start up the program again and issue the command `list` to ensure that the previously added 
   order is still in the order list.

---

### Add Function

- The format of the command is `add /n <name> /i <index> /q <quanity>`
- Prerequisite: The `Menu` has to contain at least `2` food item.

| **Test Case** | **Command** | **Expected Result** |
|:------------:|:-------------|:--------------------|
|Add 1 person.|`add /n abc /i 1 /q 2`| Adds person named `abc` with `2` quantities of item `1`|
|Add a different order to the same person.| `add /n abc /i 2 /q 1`| Person `abc` should have 2 distinct orders under him|
|Add on to an existing order.| `add /n abc /i 1 /q 1`| Person `abc` order `1` quantity should increase by 1|
|Missing parameters| `add /n /i /q`| Error message to user|
|Exceeding more than 999 quantity| `add /n abc /i 1 /q 1000`| Error message to user|
|Food item not on the menu| `add /n abc /i 0 /q 1` | Error message to user|

---

### Edit Function

- The format of the command is `edit <person index>/<order index> /q <quantity>`
- Prerequisite: The current list has to contain at least `2` person each with `2`
  distinct orders.

| **Test Case** | **Command** | **Expected Result** |
|:-------------:|:------------|:-------------------|
|Edit order quantity to 3| `edit 1/1 /q 3`| Order 1 of person number 1 now has 3 quantity|
|Edit order to 0|`edit 1/2 /q 0`| 2nd order of person number 1 should deleted|
|Missing parameters| `edit 1/2 /q`| Error message to user|
|Edit order to more than 999| `edit 1/1 /q 1000`| Error message to user|
|Edit order out of food/person index| `edit 1/999 /q 2`| Error message to user|

---

### Find Function

- The format of the command is `find /n <SEARCH_STRING>`
- Prerequisite: Contains 2 person with names of `abc` & `bcd`.
   - The above names are just for testing purposes.

| **Test Case** | **Command** | **Expected Result** |
|:-------------:|:----------|:---------------------|
|Find person `abc`| `find /n abc`| list person `abc`|
|Find person with substring| `find /n ab`| list person `abc`|
|Find multiple people| `find /n bc`| list both `abc` & `bcd`|
|Missing parameters| `find /n`| Error message to user|
|Person does not exists| `find /n zzz`| Inform that no names match|

---

### Delete Function

- The format of the command is `delete <person index>/<order index>`
- Prerequisite: The current list has to contain at least `2` person each with `2`
  distinct orders.

| **Test Case** | **Command** | **Expected Result** |
|:-------------:|:-------------|:-------------------|
|Delete an order| `delete 1/1` | Order 1 of person 1 is deleted|
|Delete all orders from a person| `delete 1/1`| Person should not be listed anymore|
|Missing parameters| `delete 1/`| Error message to user|
|Out of index| `delete 100/1`| Error message to user|

## Glossary

* `Unique Person` - Every entry with a different name counts as a unique person.
* `Unique Food`   - Every element in the food array menu counts as a unique food.
