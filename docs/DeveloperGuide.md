# Developer Guide

Duke is a **desktop application for managing tasks, optimised for usage
via a Command Line Interface (CLI).** If you can type fast, Duke can help
you manage your daily tasks faster than traditional GUI application.

- []
- [Acknowledgements](#acknowledgements)
- [Setting up, getting started]()
  
# 1. Introduction
**SchedUrMods** is a desktop application for NUS students who wish to manage 
their assignments and semester-related information via CLI (command-line interface).

**SchedUrMods** is written in `Java 11` and utilises the Object-Oriented Programming (OOP) paradigm
to provide the following benefits during the development of the application. 
- Modularity for easier troubleshooting
- Reuse of code through inheritance 
- Flexibility through polymorphism

## 1.1 Purpose
## 1.2 Acknowledgements
## Code structure
[Duke, by Peh Zhenhao, Amos](https://github.com/APZH/ip)
## Third-party library
### Development
- [Checkstyle](https://checkstyle.sourceforge.io/)
  - [Configuration file, from AddressBook Level 3 project](https://github.com/se-edu/addressbook-level3/tree/master/config/checkstyle)
- [JUnit](https://junit.org)
### Implementation
- [iCal4J](http://ical4j.github.io/)
- [Gson](https://github.com/google/gson)
- [Apache Commons IO](https://commons.apache.org/proper/commons-io/)

# Setting up, getting started

Refer to the guide [Setting up and getting started](https://www.google.com).

# Design

{Describe the design of the product. Use UML diagrams and short code snippets where applicable.}

# Implementation

## Tasks
Tasks are managed by the `TaskManager` class and are all stored in memory using a `private static ArrayList<Task>`.
The TaskManager provides functionality such as:
* listing the tasks `listTaskList(HashMap<String, String> filter)`.
* getting the taskList size `getTaskListSize()`.
* checking whether the list is empty `isEmpty()`.
* and adding Tasks `addTask()`.

The Class diagrams for the different Tasks:  
<img src="https://github.com/AY2122S1-CS2113T-W13-3/tp/blob/master/docs/images/Task%20Inheritence.jpeg?raw=true" alt="TodoFactory Sequence Diagram" width="650"/>  

Fixed values such as priority and recurrence are stored as an enum to ensure standardisation and that there are no invalid values being stored. The TaskTypes are also stored as an enum so that we can easily get the taskType when we have the task (and by extension the name of the task) for anything to do with parsing.  
<img src="https://github.com/AY2122S1-CS2113T-W13-3/tp/blob/master/docs/images/Task%20Enums.jpeg?raw=true" alt="TodoFactory Sequence Diagram" width="600"/>  

The creation of tasks with the td, deadline r event cmmands are done using their respecive Task Factories  
<img src="https://github.com/AY2122S1-CS2113T-W13-3/tp/blob/master/docs/images/TodoFactory%20Sequence%20Diagram.JPG?raw=true" alt="TodoFactory Sequence Diagram" width="500"/>  
The sequence diagram above shows the creation of a Todo Task using TodoFactory.
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
