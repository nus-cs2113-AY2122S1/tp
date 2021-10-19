# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design

### Architecture

### Command

### Utilities

{combine ui, storage, parser, comparators?}

### Inventory

### Errors

## Implementation

### Main Logic

The main application logic shows how the commands are handled throughout the application. Below is the outline of the
logic:

* MediVault is called by the `main` method which calls the constructor of MediVault. Data is then loaded from the
  `Storage` class to the application.
* MediVault gets the user input via the `Ui` class and uses the `CommandParser` class to parse the input given by the
  user.
* The parameters will be parsed to a `LinkedHashMap` to make the parameters easily accessible.
* If a valid command is received, the `CommandParser` will call the `Command` object constructor and return the object
  to MediVault.
* MediVault will then invoke the `execute()` function of the `Command` object to execute the command.
* Should there be an invalid command, `CommandParser` will throw `InvalidCommand` and MediVault will display the error
  message using the `Ui` class.

Given below is the sequence diagram for the interactions within the main application logic.

![MainLogicSequenceDiagram](diagrams/diagram_images/MainLogicSequenceDiagram.png)

### AddStockCommand

### DeleteStockCommand

### ListCommand

There are three variations of the list command.

1. `liststock`
2. `listdispense`
3. `listorders`

The sequence diagram below shows how the `list` operation works.

![ListSequenceDiagram](diagrams/diagram_images/ListSequenceDiagram.png)

Note: Replace `*` with `Stock`, `Dispense` or `Order` depending on the command entered.

All three variations of `list` are similar as they are implemented by iterating through the `Medicine` ArrayList and
filtering out the respective object types. If the parameter `sort` or `rsort` is provided, the respective constructor of
the `Comparator` classes will be invoked to help sort the ArrayList. At the end of the execution the
respective `print()` method from the `Ui` class will be called to display the respective tables.

### UpdateStockCommand

MediVault initialises an UpdateStockCommand class when CommandParser identifies the
`updatestock` or the `update` keyword in the `stock` mode.

* MediVault updates medicine stock information when `parameter` and `parameterValues` provided by the user are valid.
* MediVault conducts another validation check on the provided `quantity`,`max_quantity` and `expiry`
  against the stored medicine stock information.

The sequence diagram for UpdateStockCommand is shown below.

![UpdateStockSequenceDiagram](diagrams/diagram_images/UpdateStockSequenceDiagram.png)

MediVault modifies the `STOCK_ID` when a user tries to update a medicine name. The old record
still exists in MediVault, but it will not be visisble to user when listed.
This approach solves the issue when a user is unable to delete a _dispense_ record 
when the medicine _stock_ name gets updated.

## Product scope

### Target user profile

* pharmacist handling storing, ordering and dispensing of medication
* has a need to manage large number of stocks in the pharmacy
* may forget how much medicine stock is left in the pharmacy
* is a fast typist

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

* `parameter` - Prefixes for MediVault to understand the type of information user provides.
* `parameter values` - The actual information provided by the user for a given `parameter` type.

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
