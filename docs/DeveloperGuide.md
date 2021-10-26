# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{TO DELETE: Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

This section describes some noteworthy details on how certain features are designed and implemented.

### Logic component

**API**: `Parser.java` {NOTE: this may change based on further implementations}

(insert relevant information here about taking in user input and parsing it etc.)

#### Delete Functionality
How deleting works:

1. When the `Parser` class parses `delete` as the command from the user, a new `Command` object, `DeleteCommand` is created.
2. The `DeleteCommand` constructor processes the entire input from the user by calling `prepareInputs`.
3. `DeleteCommand` has 3 uses: deleting an `Event`, a `Task`, or to delete all `Event`s through the command `delete all`.
4. The constructor processes the usage for `DeleteCommand` and executes the actual deletion through `execute` which returns a `CommandResult` object with the associated deletion message from the `Ui` class.
5. `delete all` will not immediately invoke the `clear()` method on the global `eventCatalog` ArrayList and will instead prompt a confirmation from the user before deleting all `Event`s.

#### Select Functionality

![](images/SelectDiagram.png)

How selecting an `Event` or an event's nested `Task` works:
1. When the `Parser` class parses `select` as the command from the user, a new `Command` object, `SelectCommand` is created.
2. If the command contains a valid flag (`-e` or `-t`), `SelectCommand` processes the input from the user by calling `prepareInputs`.
3. If the user selects an `Event`, `SelectCommand` updates the index of this `Event` in `Parser`.
4. `SelectCommand` then passes the processed inputs back to `Parser`, which passes it back to `Duke`.
5. `Duke` then calls the `execute` method in `SelectCommand` which will return an object of type `CommandResult`, and the respective output will be printed.


### Storage component

#### Save Functionality

![](images/SaveDiagram.png)

How the `save` functionality works: 
1. When the `save` method is called, it constructs a new `File` object using the configured `DEFAULT_FILE_PATH`
2. Checks for the presence of the `File` object on the local system and creates the `data` directory and `.txt` file if required.
3. For each `Event` in the provided `eventsList`, the `encodeEventsList` method will be called to parse each event and its respective tasks into an overall `ArrayList<String>` and return this. 
4. The `writeToFile` method will be called to write the returned `ArrayList<String>` into the `.txt` save file locally for future uses of the program.

## Product scope
### Target user profile

{Describe the target user profile}

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
