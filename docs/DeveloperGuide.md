# Developer Guide

## Acknowledgements

Inspired by AB3's [Developer Guide](https://se-education.org/addressbook-level3/DeveloperGuide.html). 

## Design 

### Architecture

## Implementation

This section describes some noteworthy details on how certain features are implemented.
### Switch view feature

#### Implementation

The switch view mechanism is heavily linked to the `Parser` class. By having a
`ViewType` enumeration property in `Parser`, the view of the console can be switched by 
executing the appropriate `SwitchCommand` class, which modifies the corresponding `ViewType`
of the `Parser`. The 3 possible views and the corresponding user input commands are as follows:

* `switch patientinfo` - switches to the patient info view.
* `switch staffinfo` - switches to the medical staff info view.
* `switch scheduler` - switches to the scheduler view.
* `switch` - will switch to a another view depending on the current view. 

Each command essentially evokes the `Parser#setViewType(ViewType)` method, which will set the corresponding
`ViewType` property in the `Parser` class.

By having different views, we can execute different commands given the same
user input. This will be demonstrated in the example usage scenario below, and how
the switch view mechanism works for different views.

![Image of Sequence Diagram]()

<!--
Update with image + explanation
-->

#### Design Considerations

**Aspect: How different views are identified**

Currently, a enum property in the `Parser` class is used to
differentiate between views.

Pros: 
* Straightforward to implement

Cons: 
* Maintainability concerns as complexity of Uis increase

#### Alternatives Considered

3 sub `Ui` classes & sub `Parser` classes that inherit from the main `Ui` and `Parser` class.

Pros:
* Potential for reduced coupling where only sub `Ui` or sub `Parser` classes affected by changes in other class

Cons:
* Less straightforward implementation

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
