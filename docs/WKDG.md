# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

###PeopleManager Component
**API** : PeopleManager.java
<br>![PeopleManagerDiagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/PeopleManagerDiagram/PeopleManagerDiagram.jpg)
<div markdown="span" class="alert alert-primary">

:information_source: **Note:** This diagram shows the components of how the inputs are handled.

</div>

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

###Add Command
The purpose of the AddCommand is to take in the user input and split the input to three different categories. 
The three categories are the input name, order index and quantity. These data will then be added into a new Person object, 
which will be added into the list of People.
The class diagram below is a brief overview of how the Parser is related to other classes.
<br>![AddCommandDiagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/AddCommandDiagram/AddCommandDiagram.jpg)
<div markdown="span" class="alert alert-primary">

:information_source: **Note:** Details of each specific command class & Duke have been omitted from this diagram.

</div>

Listed below is an example on the usage of Add Command.
1) Example Command: `add /n Jacob /i 3 /q 2`. This command translates into adding a person named Jacob, ordering the 3rd food on the menu with 2 quantity.
2) The Add command will take in this input from the Parser class, which will then split this input string into `personName`, `foodIndex` and `foodQuantity` respectively.
A new `Person` object will be instantiated and the data `personName`, `foodIndex` and `foodQuantity` is passed into this object. 
3) This `Person` object will be added into the `listOfPeople` in the `PeopleManager` class.  

## Implementation
This section describes how the commands are implemented. 
Explanations and sequence diagrams are used to describe the implementation process.

###Add, Delete, Edit, Orders, Find Implementation
The commands `add`, `delete`, `edit`, `orders` and `find` have similar implementation, with a few differences in terms of the methods called.
Here is an overview of their class diagram.
<br>![OverallClassDiagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/LogicalComponentDiagrams/OverallClassDiagram.jpg)
<div markdown="span" class="alert alert-primary">

:information_source: **Note:** The diagram is shared between the AddCommand, DeleteCommand, EditCommand, OrdersCommand and FindCommand.

</div>

From the above class diagram, we can replace the **AbcCommand** with whichever command we are looking at. For example, if we are 
looking at the `AddCommand` class, we replace the **AbcCommand** with **AddCommand**. Same goes for all the other command classes. 
The overview class diagram is listed here to show how the command classes interact on the logical component to the manager component.

###Implementation for Menu Command Class and Order Command Class

Command word to invoke the Menu Command and Order Command: `menu` and `list`.

The purpose of Menu Command class is to instantiate the menu of which the user can order from. By invoking `menu`, the menu will be printed on the console for the user to see.

The purpose of Order Command class is to print the orders which the user has ordered onto the console. By invoking `list`, the order will be printed in a list format for the user to view. 

Hence, the sequence of which how Menu Command class and Order Command class are very similar. To prevent repeating of Sequence diagrams, a shared diagram will be listed below for the Menu Command Class and the Order Command class.

<br>![MenuAndOrdersSequenceDiagram](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/MenuAndOrdersSequenceDiagram/MenuAndOrdersSequenceDiagram.jpg)
<div markdown="span" class="alert alert-primary">

:information_source: **Note:** The diagram is shared between Menu Command Class and Orders Command Class.

</div>

The steps to using the `menu` and `list` command can be seen from the sequence diagram. In short: 
1) Invoke the Menu Command class by calling `menu`. The menu will display in the terminal.
   <br>![MenuCommandTerminalOutput](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/MenuAndOrdersSequenceDiagram/MenuCommandTerminalOutput.jpg)
   <div markdown="span" class="alert alert-primary">

:information_source: **Note:** The output is an example of what you will see when the `menu` command is entered.

</div>

2) After adding orders, invoke `list` command to see the orders added into the list.
   <br>![ListCommandTerminalOutput](https://raw.githubusercontent.com/AY2122S1-CS2113-T13-2/tp/master/UMLdiagrams/MenuAndOrdersSequenceDiagram/ListCommandTerminalOutput.jpg)
   <div markdown="span" class="alert alert-primary">

:information_source: **Note:** The output is an example of what you will see when the `list` command is entered.

</div>
**Note:** The command `menu` and `list` are just these two strings. Any edits to these two commands will result in an exception being thrown.


### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
