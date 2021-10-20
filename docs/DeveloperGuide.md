# Developer Guide

Duke is a **desktop application for managing tasks, optimised for usage
via a Command Line Interface (CLI).** If you can type fast, Duke can help
you manage your daily tasks faster than traditional GUI application.

- [Acknowledgements](#acknowledgements)
- [Setting up, getting started](#)
- [Design](#2-features)
  - [Architecture](#2-1-viewing-help--help)
  - [UI component](#22-adding-a-task)
  - [Logic component](#221-todo-todo)
  - [Model component](#222-deadline-deadline)
  - [Storage component](#223-event-event)
  - [Common classes](#23-listing-all-tasks-list)
- [Implementation](#24-marking-a-task-as-done-done)
- [Documentation, logging, testing, configuration, dev-ops](#3-faq)
- [Appendix: Requirements](#4-command-summary)
  - [Product scope](#4-command-summary)
  - [User stories](#4-command-summary)
  - [Use cases](#4-command-summary)
  - [Non-Functional Requirements](#4-command-summary)
  - [Glossary](#4-command-summary)
- [Appendix: Instructions for manual testing](#4-command-summary)
  - Launch and shutdown
  - Deleting a person
  
# Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

# Setting up, getting started

Refer to the guide [Setting up and getting started](https://www.google.com).

# Design

{Describe the design of the product. Use UML diagrams and short code snippets where applicable.}

# Implementation

## Tasks

The creation of tasks is done using Task Factories  
<img src="https://github.com/AY2122S1-CS2113T-W13-3/tp/blob/master/docs/images/TodoFactory%20Sequence%20Diagram.JPG?raw=true" alt="TodoFactory Sequence Diagram" width="400"/>  
The example above shows the creation of a Todo Task using TodoFactory.
1. It checks if it has the required arguments and then throws an exception for any required argument that does not exist e.g. description.
1. After that it parses the dates, priority and recurrence arguments into the appropriate objects that are stored in the Task object
1. Finally, it calls getConstructor() with the parameters. getConstructor()'s logic will find and call the appropriate Task constructor and return the Task created.  

The same logical structure is used in the Deadline and Event factories.  

# Documentation, logging, testing, configuration, dev-ops

- [Documentation guide](https://www.google.com)
- [Testing guide](https://www.google.com)
- [Logging guide](https://www.google.com)
- [Configuration guide](https://www.google.com)
- [DevOps guide](https://www.google.com)

# Appendix: Requirements

## Product scope

## User stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Use cases

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

# Appendix: Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
