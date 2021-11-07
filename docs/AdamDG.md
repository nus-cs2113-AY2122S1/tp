# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation
## Architecture
<br>![Architecture Diagram](https://github.com/mohamad-adam8991/tp/blob/DGArchitecture_MA/UMLdiagrams/ArchitectureDiagrams/ArchitectureDiagram.drawio.png)
The ***Architecture Diagram*** given above explains the high-level design of the LOTS app.
<br>The following section gives a brief overview of the main components in the architecture and how they interact with
each other. Further explanation will be given in depth in the **Design** section of the developer guide.
### Main components of the architecture
1) **Main** consists of the Duke class, which is responsible for initializing the various components of the LOTS program 
at startup, and the handling of the interactions between classes.
2) **UI** handles the UI portion of the LOTS program.
3) **Logic** deals with the parsing and execution of user inputs.
4) **Manager** deals with the various types of data that is stored within the LOTS program.
### Component Interaction
The general flow of the program is as follows:
1) User inputs data which is read by the `UI` within the `Main`.
2) This data is passed to the `Parser` which will return a `Command`.
3) `Command` will be executed, carrying out whatever task the user has input. 
4) `UI` component handles the printing of data if required.
</br>Given below is a simplified sequence diagram showing how the components within the LOTS program interact with each other
when the user inputs the command `delete 1/2`
<br>![Delete Sequence Diagram](https://github.com/mohamad-adam8991/tp/blob/DGArchitecture_MA/UMLdiagrams/ArchitectureDiagrams/DeleteSeqDiagram.png)
### Orders Command

The purpose of the OrdersCommand is to display the list of current orders stored. The class diagram below shows the structure of
the OrdersCommand class and how it is related to the other classes.

<br>![OrdersCommand Diagram](https://github.com/mohamad-adam8991/tp/blob/AdamDG_MA/UMLdiagrams/OrderCommandDiagram/OrderCommand%20Diagram.jpg)
<div markdown="span" class="alert alert-primary">

:information_source: **Note:** This diagram only shows methods and attributes related to the OrdersCommand class.

</div>

Below is an example of how the OrdersCommand is used, assuming that there are orders currently stored.
1) Taking in the input `list`, the parser will instantiate an OrdersCommand object, which in turn is returned to the 
main duke program.
2) When the main duke program calls the `execute()` method of OrdersCommand, the `printOrdersList()` method of the UI 
class is executed.
3) Within the `printOrdersList()` method, the method loops through each person stored in the PeopleManager to print the 
names of all persons currently stored. On top of that, `printIndividualPersonOrder()` is called to print the `foodIndex` and
`quantity` data stored for each person.


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
